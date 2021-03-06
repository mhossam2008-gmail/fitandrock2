package com.mhossam.rocknfit.ui.dashboard;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mhossam.rocknfit.API.APIClient;
import com.mhossam.rocknfit.API.APIInterface;
import com.mhossam.rocknfit.ForgetPasswordActivity;
import com.mhossam.rocknfit.R;
import com.mhossam.rocknfit.Utils.LinearLayoutManagerWrapper;
import com.mhossam.rocknfit.adapter.FeedAdapter;
import com.mhossam.rocknfit.adapter.FeedItemAnimator;
import com.mhossam.rocknfit.adapter.RecommendedUsersAdapter;
import com.mhossam.rocknfit.database.AppDatabase;
import com.mhossam.rocknfit.model.AccountInfo;
import com.mhossam.rocknfit.model.LoggedInUser;
import com.mhossam.rocknfit.model.Post;
import com.mhossam.rocknfit.ui.activity.CommentsActivity;
import com.mhossam.rocknfit.ui.activity.NewsFeedActivity;
import com.mhossam.rocknfit.ui.activity.RegisterationSuccessActivity;
import com.mhossam.rocknfit.view.FeedContextMenu;
import com.mhossam.rocknfit.view.FeedContextMenuManager;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@RuntimePermissions
public class DashboardFragment extends Fragment implements FeedAdapter.OnFeedItemClickListener,
        FeedContextMenu.OnFeedContextMenuItemClickListener {


    private static final int GALLERY_PICTURE_REQUEST = 2;
    private FeedAdapter feedAdapter;

    private boolean pendingIntroAnimation;

    @BindView(R.id.rvFeed)
    RecyclerView rvFeed;

    @BindView(R.id.rvRecommendedUsers)
    RecyclerView rvRecommendedUsers;

    @BindView(R.id.content)
    CoordinatorLayout clContent;

    @BindView(R.id.ivProfileImage)
    ImageView ivProfileImage;

    @BindView(R.id.etNewPost)
    EditText etNewPost;

    @BindView(R.id.btn_ham_menu)
    ImageButton btnHamMenu;

    CircleImageView ivUserProfilePost;

    TextView etPostText;

    private APIInterface apiInterface;
    private PopupWindow changeSortPopUp;
    private RecommendedUsersAdapter recommendedUsersAdapter;
    private int currentPage = 0;
    private boolean loading = false;
    private LoggedInUser currentUser;
    private final static int CAMERA_PIC_REQUEST = 1;
    private View layout;
    private boolean isImagePost = false;
    private Bitmap bitmap;
    private File photoFile;
    private String mCurrentPhotoPath;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        ButterKnife.bind(this, root);
        AppDatabase db = Room.databaseBuilder(getContext(),
                AppDatabase.class, "rockAndFit").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        currentUser = db.loggedInUserDao().getLoggedInUser();
        apiInterface = APIClient.getClient().create(APIInterface.class);
        btnHamMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DrawerLayout navDrawer = getActivity().findViewById(R.id.drawerLayout);
                // If the navigation drawer is not open then open it, if its already open then close it.
                if (!navDrawer.isDrawerOpen(GravityCompat.START))
                    navDrawer.openDrawer(GravityCompat.START);
                else navDrawer.closeDrawer(GravityCompat.END);
            }
        });
        String origUserProfilePhoto = "https://www.fitandrock.com/ProfilePictures/Org" + currentUser.getAccountImage();


        Picasso.get()
                .load(origUserProfilePhoto)
                .placeholder(R.drawable.img_circle_placeholder)
                .fit()
                .into(ivProfileImage);

        ivProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((BottomNavigationView) getActivity().findViewById(R.id.nav_view)).setSelectedItemId(R.id.navigation_profile);
            }
        });

        etNewPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPostPopup((NewsFeedActivity) getActivity(), null);
            }
        });

        ((NewsFeedActivity) getActivity()).setFragment(this);
        setupFollowSuggesstions();
        setupFeed();

        return root;
    }

    private void setupFollowSuggesstions() {

        rvRecommendedUsers.setLayoutManager(new LinearLayoutManagerWrapper((NewsFeedActivity) getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recommendedUsersAdapter = new RecommendedUsersAdapter((NewsFeedActivity) getActivity());
        rvRecommendedUsers.setAdapter(recommendedUsersAdapter);

        startLoadingRecommendedUser();
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setupFeed() {
        LinearLayoutManagerWrapper linearLayoutManager = new LinearLayoutManagerWrapper(((NewsFeedActivity) getActivity())) {
            @Override
            protected int getExtraLayoutSpace(RecyclerView.State state) {
                return 300;
            }
        };
        rvFeed.setLayoutManager(linearLayoutManager);
        feedAdapter = new FeedAdapter(((NewsFeedActivity) getActivity()));
        feedAdapter.setOnFeedItemClickListener(this);
        rvFeed.setAdapter(feedAdapter);

        rvFeed.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) //check for scroll down
                {
                    int visibleItemCount = linearLayoutManager.getChildCount();
                    int totalItemCount = linearLayoutManager.getItemCount();
                    int pastVisiblesItems = linearLayoutManager.findFirstVisibleItemPosition();

                    if (!loading) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            startLoading(false);
                        }
                    }
                }
                FeedContextMenuManager.getInstance().onScrolled(recyclerView, dx, dy);
            }
        });
        rvFeed.setItemAnimator(new FeedItemAnimator());
        startLoading(true);
    }

    private boolean startLoadingRecommendedUser() {
        Map<String, String> parametersMap = prepareUserSuggesstionsRequestMap();
        Call<Map<String, AccountInfo>> call = apiInterface.getFollowSuggestions(parametersMap);

        call.enqueue(new Callback<Map<String, AccountInfo>>() {
            @Override
            public void onResponse(Call<Map<String, AccountInfo>> call, Response<Map<String, AccountInfo>> response) {

                Log.d("TAG", response.code() + "");

                Map<String, AccountInfo> resource = response.body();
                if (resource != null) {
                    fillUserSuggestions(resource);
                }
            }

            @Override
            public void onFailure(Call<Map<String, AccountInfo>> call, Throwable t) {
                call.cancel();
            }
        });
        return true;
    }

    private void fillUserSuggestions(Map<String, AccountInfo> resource) {
        List<AccountInfo> result = new ArrayList(resource.values());
        recommendedUsersAdapter.updateItems(true, result);
        recommendedUsersAdapter.notifyDataSetChanged();
        rvRecommendedUsers.setItemAnimator(new FeedItemAnimator());
    }

    public boolean startLoading(boolean animate) {
        loading = true;
        pendingIntroAnimation = false;
        Map<String, String> parametersMap = prepareRequestMap(currentPage);
        Call<Map<String, Post>> call = apiInterface.getPosts(parametersMap);

        call.enqueue(new Callback<Map<String, Post>>() {
            @Override
            public void onResponse(Call<Map<String, Post>> call, Response<Map<String, Post>> response) {

                Log.d("TAG", response.code() + "");

                Map<String, Post> resource = response.body();
                if (resource != null) {
                    startIntroAnimation(resource, animate);
                }
            }

            @Override
            public void onFailure(Call<Map<String, Post>> call, Throwable t) {
                call.cancel();
            }
        });
        return true;
    }

    private void startIntroAnimation(Map<String, Post> resource, boolean animate) {
        startContentAnimation(new ArrayList<Post>(resource.values()), animate);
    }

    private void startContentAnimation(List<Post> values, boolean animate) {
        if (currentPage == 0) {
            feedAdapter.updateItems(animate, values);
        } else {
            feedAdapter.updateItems(animate, values, true);
        }
        currentPage++;
        loading = false;
    }

    @Override
    public void onCommentsClick(View v, int position) {
        final Intent intent = new Intent(getActivity(), CommentsActivity.class);
        int[] startingLocation = new int[2];
        Post clickedPost = feedAdapter.getPostAtPosition(position);
        intent.putExtra("PostID", clickedPost.getPostID());
        v.getLocationOnScreen(startingLocation);
        intent.putExtra(CommentsActivity.ARG_DRAWING_START_LOCATION, startingLocation[1]);
        startActivity(intent);
        getActivity().overridePendingTransition(0, 0);
    }

    @Override
    public void onMoreClick(View v, int itemPosition) {
        FeedContextMenuManager.getInstance().toggleContextMenuFromView(v, itemPosition, this);
    }

    @Override
    public void onProfileClick(View v) {
        int[] startingLocation = new int[2];
        v.getLocationOnScreen(startingLocation);
        startingLocation[0] += v.getWidth() / 2;
        ((NewsFeedActivity) getActivity()).overridePendingTransition(0, 0);
    }

    @Override
    public void onDeleteClick(int pos) {
        Post currentItem = feedAdapter.getPostAtPosition(pos);
        if(currentItem.getAccountID().equals(currentUser.getAccountID())){
            Map<String,String> requestMap = prepareRequestMap();
            requestMap.put("PostID",currentItem.getPostID());
            requestMap.put("AccountID",currentUser.getAccountID());
            requestMap.put("Action","DeletePost");
            Call<String> call = apiInterface.deletePost(requestMap);

            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {

                    Log.d("TAG", response.code() + "");

                    feedAdapter.deleteItem(pos);
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    call.cancel();
                }
            });
        }
        FeedContextMenuManager.getInstance().hideContextMenu();
    }

    @Override
    public void onSharePhotoClick(int feedItem) {
        FeedContextMenuManager.getInstance().hideContextMenu();
    }

    @Override
    public void onCopyShareUrlClick(int feedItem) {
        FeedContextMenuManager.getInstance().hideContextMenu();
    }

    @Override
    public void onCancelClick(int feedItem) {
        FeedContextMenuManager.getInstance().hideContextMenu();
    }

    @Override
    public void onUpdatePost(int pos) {
        Post currentItem = feedAdapter.getPostAtPosition(pos);
        showPostPopup(getActivity(),currentItem);
        FeedContextMenuManager.getInstance().hideContextMenu();
    }

    protected HashMap<String, String> prepareRequestMap(int page) {
        HashMap<String, String> result = ((NewsFeedActivity) getActivity()).prepareRequestMap();
        result.put("Action", "GetPosts");
        result.put("AccountID", "0");
        result.put("MyAccount", currentUser.getAccountID());
        result.put("Index", page + "");
        result.put("Size", "10");
        result.put("Type", "0");
        return result;
    }

    protected HashMap<String, String> prepareRequestMap() {
        HashMap<String, String> result = ((NewsFeedActivity) getActivity()).prepareRequestMap();
        result.put("Action", "GetPosts");
        result.put("AccountID", "0");
        result.put("AccountID", currentUser.getAccountID());
        result.put("Index", "0");
        result.put("Size", "10");
        result.put("Type", "0");
        return result;
    }

    protected HashMap<String, String> prepareUserSuggesstionsRequestMap() {
        HashMap<String, String> result = ((NewsFeedActivity) getActivity()).prepareRequestMap();
        result.put("Action", "FollowSuggestions");
        result.put("AccountID", currentUser.getAccountID());
        result.put("Limit", "5");
        return result;
    }

    private void showPostPopup(final Activity context , Post postItem) {
        isImagePost = false;
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layout = layoutInflater.inflate(R.layout.post_popup_layout, null);


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
        ivUserProfilePost = layout.findViewById(R.id.ivUserProfilePost);
        String origUserProfilePhoto = "https://www.fitandrock.com/ProfilePictures/Org" + currentUser.getAccountImage();
        Picasso.get()
                .load(origUserProfilePhoto)
                .placeholder(R.drawable.img_circle_placeholder)
                .fit()
                .into(ivUserProfilePost);
        // Getting a reference to Close button, and close the popup when clicked.
        ImageButton close = layout.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeSortPopUp.dismiss();
            }
        });
        Button post = (Button) layout.findViewById(R.id.post);
        etPostText = layout.findViewById(R.id.etPostText);
        String postID = null ;
        if(postItem!=null){
            etPostText.setText(postItem.getPostContent());
            postID = postItem.getPostID();
        }

        String finalPostID = postID;
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isImagePost) {
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100 /*ignored for PNG*/, bos);
                    byte[] bitmapdata = bos.toByteArray();
//                    RequestBody fileToSend = RequestBody.create(bitmapdata);
                    // create RequestBody instance from file
                    RequestBody requestFile =
                            RequestBody.create(MediaType.parse("image/*"),
                                    bitmapdata
                            );

                    // MultipartBody.Part is used to send also the actual file name
                    MultipartBody.Part body =
                            MultipartBody.Part.createFormData("Media", "Media", requestFile);

                    Map<String, String> parametersMap = prepareRequestMap();
                    if(finalPostID ==null) {
                        parametersMap.put("Action", "AddPostHasMedia");
                        parametersMap.put("Content", etPostText.getText().toString());
                    }else{
                        parametersMap.put("Action", "UpdatePost");
                        parametersMap.put("Content", etPostText.getText().toString());
                        parametersMap.put("PostID",postItem.getPostID());
                    }
                    parametersMap.put("AccountID", currentUser.getAccountID());
                    parametersMap.put("Type", "S");

                    HashMap<String, RequestBody> requestBodyMap = new HashMap<>();
                    for (Map.Entry<String, String> pair : parametersMap.entrySet()) {
                        RequestBody currentField =
                                RequestBody.create(
                                        okhttp3.MultipartBody.FORM, pair.getValue());
                        requestBodyMap.put(pair.getKey(), currentField);
                    }

                    Call<String> call = apiInterface.postWithImage(requestBodyMap,
                            body);
                    call.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {

                            Log.d("TAG", response.code() + "");

                            String resource = response.body();
                            if (resource != null) {
                                Toast.makeText(getActivity(), "Posted Successfully", Toast.LENGTH_SHORT).show();
                                changeSortPopUp.dismiss();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            call.cancel();
                        }
                    });

                } else {
                    Map<String, String> parametersMap = prepareRequestMap();
                    if(finalPostID ==null) {
                        parametersMap.put("Action", "AddPost");
                        parametersMap.put("Content", etPostText.getText().toString());
                    }else{
                        parametersMap.put("Action", "UpdatePost");
                        parametersMap.put("Content", etPostText.getText().toString());
                        parametersMap.put("PostID",postItem.getPostID());
                    }
                    parametersMap.put("AccountID", currentUser.getAccountID());
                    parametersMap.put("Type", "S");
                    Call<String> call = apiInterface.sharePost(parametersMap);
                    call.enqueue(new Callback<String>() {
                        @RequiresApi(api = Build.VERSION_CODES.M)
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            Log.d("TAG", response.code() + "");
                            String resource = response.body();
                            if ((resource == null || resource.contains("null"))&&finalPostID==null) {
                                Toast.makeText(context, "Internal Server Error", Toast.LENGTH_SHORT).show();
                            } else {
                                changeSortPopUp.dismiss();
                                currentPage = 0;
                                setupFeed();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Toast.makeText(context, "Internal Server Error", Toast.LENGTH_SHORT).show();
                            call.cancel();
                        }
                    });
                }
            }
        });

        ImageButton galleryButton = layout.findViewById(R.id.ibCameraButton);
        galleryButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DashboardFragmentPermissionsDispatcher.startDialogWithPermissionCheck(DashboardFragment.this);
            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_PIC_REQUEST && resultCode == getActivity().RESULT_OK) {
            ImageView imageView = layout.findViewById(R.id.ivPostImage);
            // set the dimensions of the image
            int targetW =imageView.getWidth();
            int targetH = imageView.getWidth();

            // Get the dimensions of the bitmap
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(photoFile.getAbsolutePath(), bmOptions);
            int photoW = bmOptions.outWidth;
            int photoH = bmOptions.outHeight;

            // Determine how much to scale down the image
            int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

            // Decode the image file into a Bitmap sized to fill the View
            bmOptions.inJustDecodeBounds = false;
            bmOptions.inSampleSize = scaleFactor;
            bmOptions.inPurgeable = true;

            // stream = getContentResolver().openInputStream(data.getData());
            bitmap = BitmapFactory.decodeFile(photoFile.getAbsolutePath(),bmOptions);
            imageView.setImageBitmap(bitmap);
            isImagePost = true;
        }
        if (requestCode == GALLERY_PICTURE_REQUEST && resultCode == getActivity().RESULT_OK) {
            Uri selectedImage = data.getData();

            InputStream inputStream = null;

            if (ContentResolver.SCHEME_CONTENT.equals(selectedImage.getScheme())) {
                try {
                    inputStream = getActivity().getContentResolver().openInputStream(selectedImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                if (ContentResolver.SCHEME_FILE.equals(selectedImage.getScheme())) {
                    try {
                        inputStream = new FileInputStream(selectedImage.getPath());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
            isImagePost = true;
            bitmap = BitmapFactory.decodeStream(inputStream);
            ImageView imageView = layout.findViewById(R.id.ivPostImage);
            imageView.setImageBitmap(bitmap);
        }

    }

    @NeedsPermission({Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE})
    void startDialog() {
        AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(
                getActivity());
        myAlertDialog.setTitle("Upload Pictures Option");
        myAlertDialog.setMessage("How do you want to set your picture?");
        Method m = null;
        try {
            m = StrictMode.class.getMethod("disableDeathOnFileUriExposure");
            m.invoke(null);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        myAlertDialog.setPositiveButton("Gallery",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent pictureActionIntent = null;

                        pictureActionIntent = new Intent(
                                Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(
                                pictureActionIntent,
                                GALLERY_PICTURE_REQUEST);

                    }
                });

        myAlertDialog.setNegativeButton("Camera",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {

                        dispatchTakePictureIntent();

                    }
                });
        myAlertDialog.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // NOTE: delegate the permission handling to generated method
        DashboardFragmentPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
//        File storageDir = Environment.getExternalStoragePublicDirectory(
//                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }


    private void dispatchTakePictureIntent() {

        Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            photoFile = createImageFile();
            takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
            startActivityForResult(takePhotoIntent, CAMERA_PIC_REQUEST);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}