package com.mhossam.rocknfit;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mhossam.rocknfit.API.APIClient;
import com.mhossam.rocknfit.API.APIInterface;
import com.mhossam.rocknfit.Utils.LinearLayoutManagerWrapper;
import com.mhossam.rocknfit.database.AppDatabase;
import com.mhossam.rocknfit.model.LoggedInUser;
import com.mhossam.rocknfit.model.Post;
import com.mhossam.rocknfit.model.PredefinedClass;
import com.mhossam.rocknfit.model.TrainingClass;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A fragment representing a list of Items.
 */
public class ClassFragment extends Fragment {

    private APIInterface apiInterface;
    private int currentPage = 0;
    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    ArrayList<TrainingClass> questionsList;
    private ClassAdapter trainingClassAdapter;
    private boolean loading;

    @BindView(R.id.list)
    RecyclerView recyclerView;


    @BindView(R.id.btn_ham_menu)
    ImageButton btnHamMenu;
    private LoggedInUser currentUser;

    @BindView(R.id.fabAddClass)
    FloatingActionButton fabAddClass;

    private View layout;
    private PopupWindow changeSortPopUp;
    private EditText etStartTime;
    private EditText etEndTime;
    private AppCompatSpinner sClassType;
    private List<PredefinedClass> predefinedClasses;
    private EditText etAdditionalInfo;
    private EditText etVideoURL;
    private EditText etClassName;
    private Spinner sGender;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ClassFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static QuestionsFragment newInstance(int columnCount) {
        QuestionsFragment fragment = new QuestionsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        AppDatabase db = Room.databaseBuilder(getContext(),
                AppDatabase.class, "rockAndFit").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        currentUser = db.loggedInUserDao().getLoggedInUser();
        predefinedClasses = db.lookupsDao().getAllPredefinedClasses();
        getTrainingClasses();
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }

    }

    private void getTrainingClasses() {
        loading = true;
        Map<String, String> requestMap = prepareRequestMap();
        requestMap.put("Index", currentPage + "");
        requestMap.put("Size", "10");

        Call<Map<String, TrainingClass>> call = apiInterface.getTrainingClass(requestMap);
        call.enqueue(new Callback<Map<String, TrainingClass>>() {
            @Override
            public void onResponse(Call<Map<String, TrainingClass>> call, Response<Map<String, TrainingClass>> response) {
                Log.d("TAG", response.code() + "");
                Map<String, TrainingClass> resource = response.body();
                if (resource != null) {
                    questionsList = new ArrayList<>(resource.values());
                    trainingClassAdapter.updateItems(questionsList);
                    currentPage++;
                }
                loading = false;
            }

            @Override
            public void onFailure(Call<Map<String, TrainingClass>> call, Throwable t) {
                call.cancel();
                loading = false;
            }
        });
    }

    private Map<String, String> prepareRequestMap() {
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("Action", "GetAccountBranchesClasses");
        requestMap.put("ApiUser", "Test");
        requestMap.put("ApiPass", "Test");
        requestMap.put("Type","C");
        requestMap.put("AccountID",currentUser.getAccountID());
        return requestMap;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_class, container, false);
        ButterKnife.bind(this , view);
        fabAddClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddClassPopup(getActivity(),null);
            }
        });
        trainingClassAdapter = new ClassAdapter(new ArrayList<TrainingClass>(),getActivity());
        // Set the adapter
        if (recyclerView instanceof RecyclerView) {
            Context context = view.getContext();
//            recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManagerWrapper(context));
            recyclerView.setAdapter(trainingClassAdapter);
            recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
//                        getTrainingClasses();
                    }
                }
            });
        }

        btnHamMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DrawerLayout navDrawer = getActivity().findViewById(R.id.drawerLayout);
                // If the navigation drawer is not open then open it, if its already open then close it.
                if(!navDrawer.isDrawerOpen(GravityCompat.START))
                    navDrawer.openDrawer(GravityCompat.START);
                else navDrawer.closeDrawer(GravityCompat.END);
            }
        });
        return view;
    }

    private void showAddClassPopup(final Activity context , Post postItem) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layout = layoutInflater.inflate(R.layout.class_popup_layout, null);


        // Creating the PopupWindow
        changeSortPopUp = new PopupWindow(context);
        changeSortPopUp.setContentView(layout);
        changeSortPopUp.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        changeSortPopUp.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        changeSortPopUp.setFocusable(true);


        // Clear the default translucent background
        changeSortPopUp.setBackgroundDrawable(new BitmapDrawable());

        // Displaying the popup at the specified location, + offsets.
        changeSortPopUp.showAtLocation(layout, Gravity.CENTER, 0, 0);
//        ivUserProfilePost = layout.findViewById(R.id.ivUserProfilePost);
//        String origUserProfilePhoto = "https://www.fitandrock.com/ProfilePictures/Org" + currentUser.getAccountImage();
//        Picasso.get()
//                .load(origUserProfilePhoto)
//                .placeholder(R.drawable.img_circle_placeholder)
//                .fit()
//                .into(ivUserProfilePost);
//        // Getting a reference to Close button, and close the popup when clicked.
        ImageButton close = layout.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeSortPopUp.dismiss();
            }
        });
        sClassType = (AppCompatSpinner)layout.findViewById(R.id.sClassType);
        ArrayAdapter<PredefinedClass> adapter = new ArrayAdapter<PredefinedClass>(getActivity(), android.R.layout.simple_spinner_dropdown_item, predefinedClasses.toArray(new PredefinedClass[predefinedClasses.size()]));

        sClassType.setAdapter(adapter);
        etStartTime = (EditText) layout.findViewById(R.id.etStartTime);
        etEndTime = (EditText)layout.findViewById(R.id.etEndTime);
        etAdditionalInfo = (EditText)layout.findViewById(R.id.etAdditionalInfo);
        etVideoURL = (EditText)layout.findViewById(R.id.etVideoURL);
        etClassName = (EditText)layout.findViewById(R.id.etClassName);
        sGender = (Spinner)layout.findViewById(R.id.sGender);
        etStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomTimePicker(true);
            }
        });
        etEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomTimePicker(false);
            }
        });
        Button addClassButton = (Button) layout.findViewById(R.id.bAddClass);
        addClassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String,String> requestMap = prepareRequestMap();
                requestMap.put("Action","AddClass");
                requestMap.put("AccountID",currentUser.getAccountID());
                requestMap.put("ClassName", etClassName.getText().toString());
                requestMap.put("ClassID",((PredefinedClass)sClassType.getSelectedItem()).getClassID());
                requestMap.put("ClassGender",sGender.getSelectedItem().toString());
                requestMap.put("ClassStart",etStartTime.getText().toString());
                requestMap.put("ClassEnd",etEndTime.getText().toString());
                requestMap.put("Info",etAdditionalInfo.getText().toString());

                 Call<String> call = apiInterface.addClass(requestMap);
                 call.enqueue(new Callback<String>() {
                     @Override
                     public void onResponse(Call<String> call, Response<String> response) {
                         Log.d("TAG", response.code() + "");

                            String resource = response.body();
                            if (resource != null) {
                                Toast.makeText(getActivity(), "Class Created Successfully", Toast.LENGTH_SHORT).show();
                                changeSortPopUp.dismiss();
                                getTrainingClasses();
                            }
                     }

                     @Override
                     public void onFailure(Call<String> call, Throwable t) {

                     }
                 });
            }
        });
        /**
         *
         * Action:AddClass
         * ApiUser:Test
         * ApiPass:Test
         * AccountID:98
         * ClassName:Test PostMan 2
         * ClassID:16
         * ClassGender:Male
         * ClassStart:2020-08-25
         * ClassEnd:2020-09=25
         * Info:Test Class from Postman to check what is needed in Android APP 2
         * */
//        etPostText = layout.findViewById(R.id.etPostText);
//        String postID = null ;
//        if(postItem!=null){
//            etPostText.setText(postItem.getPostContent());
//            postID = postItem.getPostID();
//        }

//        String finalPostID = postID;
//        post.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (isImagePost) {
//                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
//                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100 /*ignored for PNG*/, bos);
//                    byte[] bitmapdata = bos.toByteArray();
////                    RequestBody fileToSend = RequestBody.create(bitmapdata);
//                    // create RequestBody instance from file
//                    RequestBody requestFile =
//                            RequestBody.create(MediaType.parse("image/*"),
//                                    bitmapdata
//                            );
//
//                    // MultipartBody.Part is used to send also the actual file name
//                    MultipartBody.Part body =
//                            MultipartBody.Part.createFormData("Media", "Media", requestFile);
//
//                    Map<String, String> parametersMap = prepareRequestMap();
//                    if(finalPostID ==null) {
//                        parametersMap.put("Action", "AddPostHasMedia");
//                        parametersMap.put("Content", etPostText.getText().toString());
//                    }else{
//                        parametersMap.put("Action", "UpdatePost");
//                        parametersMap.put("Content", etPostText.getText().toString());
//                        parametersMap.put("PostID",postItem.getPostID());
//                    }
//                    parametersMap.put("AccountID", currentUser.getAccountID());
//                    parametersMap.put("Type", "S");
//
//                    HashMap<String, RequestBody> requestBodyMap = new HashMap<>();
//                    for (Map.Entry<String, String> pair : parametersMap.entrySet()) {
//                        RequestBody currentField =
//                                RequestBody.create(
//                                        okhttp3.MultipartBody.FORM, pair.getValue());
//                        requestBodyMap.put(pair.getKey(), currentField);
//                    }
//
//                    Call<String> call = apiInterface.postWithImage(requestBodyMap,
//                            body);
//                    call.enqueue(new Callback<String>() {
//                        @Override
//                        public void onResponse(Call<String> call, Response<String> response) {
//
//                            Log.d("TAG", response.code() + "");
//
//                            String resource = response.body();
//                            if (resource != null) {
//                                Toast.makeText(getActivity(), "Posted Successfully", Toast.LENGTH_SHORT).show();
//                                changeSortPopUp.dismiss();
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<String> call, Throwable t) {
//                            call.cancel();
//                        }
//                    });
//
//                } else {
//                    Map<String, String> parametersMap = prepareRequestMap();
//                    if(finalPostID ==null) {
//                        parametersMap.put("Action", "AddPost");
//                        parametersMap.put("Content", etPostText.getText().toString());
//                    }else{
//                        parametersMap.put("Action", "UpdatePost");
//                        parametersMap.put("Content", etPostText.getText().toString());
//                        parametersMap.put("PostID",postItem.getPostID());
//                    }
//                    parametersMap.put("AccountID", currentUser.getAccountID());
//                    parametersMap.put("Type", "S");
//                    Call<String> call = apiInterface.sharePost(parametersMap);
//                    call.enqueue(new Callback<String>() {
//                        @RequiresApi(api = Build.VERSION_CODES.M)
//                        @Override
//                        public void onResponse(Call<String> call, Response<String> response) {
//                            Log.d("TAG", response.code() + "");
//                            String resource = response.body();
//                            if ((resource == null || resource.contains("null"))&&finalPostID==null) {
//                                Toast.makeText(context, "Internal Server Error", Toast.LENGTH_SHORT).show();
//                            } else {
//                                changeSortPopUp.dismiss();
//                                currentPage = 0;
//                                setupFeed();
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<String> call, Throwable t) {
//                            Toast.makeText(context, "Internal Server Error", Toast.LENGTH_SHORT).show();
//                            call.cancel();
//                        }
//                    });
//                }
//            }
//        });
//
//        ImageButton galleryButton = layout.findViewById(R.id.ibCameraButton);
//        galleryButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                DashboardFragmentPermissionsDispatcher.startDialogWithPermissionCheck(DashboardFragment.this);
//            }
//        });
    }

    public void showCustomTimePicker(boolean start) {

        final Calendar myCalender = Calendar.getInstance();
        int hour = myCalender.get(Calendar.HOUR_OF_DAY);
        int minute = myCalender.get(Calendar.MINUTE);


        TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                if (view.isShown()) {
                    myCalender.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    myCalender.set(Calendar.MINUTE, minute);
                    if(start){
                        etStartTime.setText(hourOfDay+":"+minute);
                    }else{
                        etEndTime.setText(hourOfDay+":"+minute);
                    }
                }
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), android.R.style.Theme_Holo_Light_Dialog_NoActionBar, myTimeListener, hour, minute, true);
        timePickerDialog.setTitle("Choose hour:");
        timePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        timePickerDialog.show();
    }
}