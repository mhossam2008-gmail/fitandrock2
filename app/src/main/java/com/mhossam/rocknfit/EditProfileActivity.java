package com.mhossam.rocknfit;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.room.Room;

import android.Manifest;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mhossam.rocknfit.database.AppDatabase;
import com.mhossam.rocknfit.model.Country;
import com.mhossam.rocknfit.model.District;
import com.mhossam.rocknfit.model.Governorate;
import com.mhossam.rocknfit.model.LoggedInUser;
import com.mhossam.rocknfit.view.BaseDrawerActivity;
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
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@RuntimePermissions
public class EditProfileActivity extends BaseDrawerActivity {

    @BindView(R.id.sCountry)
    Spinner sCountry;


    @BindView(R.id.sDistrict)
    Spinner sDistrict;

    @BindView(R.id.sGovernerate)
    Spinner sGovernerate;

    @BindView(R.id.ivUserProfile)
    ImageView ivUserProfile;

    @BindView(R.id.banner)
    ImageView banner;

    @BindView(R.id.tvFirstName)
    TextView tvFirstName;

    @BindView(R.id.tvLastName)
    TextView tvLastName;

    @BindView(R.id.tvEmail)
    TextView tvEmail;

    @BindView(R.id.tvBirthDay)
    TextView tvBirthday;

    @BindView(R.id.sAccountType)
    Spinner sAccountType;

    @BindView(R.id.sGGender)
    Spinner sGender;

    @BindView(R.id.sMartialStatus)
    Spinner sMartialStatus;

    @BindView(R.id.etHobbies)
    EditText etHobbies;

    @BindView(R.id.etDescription)
    EditText etDescription;

    @BindView(R.id.etContactNumber)
    EditText etContactNumber;

    @BindView(R.id.updateProfile)
    Button updateProfile;

    private Country[] countries;
    private Governorate[] governorates;
    private District[] districts;
    private AppDatabase db;
    private LoggedInUser loggedInUser;
    private int GALLERY_PICTURE_PROFILE_REQUEST = 0;
    private int GALLERY_PICTURE_BANNER_REQUEST = 2;

    private String mCurrentPhotoPath;
    private int CAMERA_PIC_PROFILE_REQUEST = 1;
    private int CAMERA_PIC_BANNER_REQUEST = 3;
    private File photoFile;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_edit_profile);
        ButterKnife.bind(this);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "rockAndFit").allowMainThreadQueries().fallbackToDestructiveMigration().build();

        loggedInUser = db.loggedInUserDao().getLoggedInUser();

        tvFirstName.setText(loggedInUser.getAccountFirstName());
        tvLastName.setText(loggedInUser.getAccountLastName());
        tvBirthday.setText(loggedInUser.getAccountDOB());
        tvEmail.setText(loggedInUser.getAccountEmail());
        tvEmail.setEnabled(false);
        etHobbies.setText(loggedInUser.getHobbies());
        etDescription.setText(loggedInUser.getLittleDescription());
        etContactNumber.setText(loggedInUser.getContactNumber());
        switch (loggedInUser.getAccountType()) {
            case ("T"):
                sAccountType.setSelection(2);
                break;
            case ("G"):
                sAccountType.setSelection(3);
                break;
            default:
                sAccountType.setSelection(1);
                break;
        }

        if (loggedInUser.getMaritalStatus().equalsIgnoreCase("Married")) {
            sMartialStatus.setSelection(1);
        }

        if (loggedInUser.getAccountGender().equalsIgnoreCase("Male")) {
            sGender.setSelection(1);
        } else {
            sGender.setSelection(2);
        }


        sAccountType.setEnabled(false);
        String origUserProfilePhoto = "https://www.fitandrock.com/ProfilePictures/Org" + loggedInUser.getAccountImage();
        String coverPhoto = "https://www.fitandrock.com/Uploads/" + loggedInUser.getAccountContainer() + "/CoverPhotos/" + loggedInUser.getAccountCover();

        Picasso.get()
                .load(coverPhoto)
                .fit()
                .into(banner);
        banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startDialog(false);
            }
        });
        ivUserProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startDialog(true);
            }
        });
        Picasso.get()
                .load(origUserProfilePhoto)
                .placeholder(R.drawable.img_circle_placeholder)
                .fit()
                .into(ivUserProfile);
        Map<String, String> parametersMap = prepareRequestMap();
        parametersMap.put("Action", "GetCountries");
        Call<Map<String, Country>> call = apiInterface.getCountries(parametersMap);

        call.enqueue(new Callback<Map<String, Country>>() {
            @Override
            public void onResponse(Call<Map<String, Country>> call, Response<Map<String, Country>> response) {

                Log.d("TAG", response.code() + "");

                Map<String, Country> resource = response.body();
                if (response.body() != null) {
                    ArrayList<Country> countriesList = new ArrayList<>(resource.values());
                    countries = countriesList.toArray(new Country[countriesList.size()]);
                    db.regionDAO().insertAllCountries(countries);
                    //fill data in spinner
                    int selectedPos = -1;
                    for (int i = 0; i < countriesList.size(); i++) {
                        if (countriesList.get(i).getCountryID().equals(loggedInUser.getCountryID())) {
                            selectedPos = i;
                            break;
                        }
                    }
                    ArrayAdapter<Country> adapter = new ArrayAdapter<Country>(EditProfileActivity.this, android.R.layout.simple_spinner_dropdown_item, countries);
                    sCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            Country selectedCountry = (Country) adapterView.getItemAtPosition(i);
                            loadGovenerates(selectedCountry);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                    sCountry.setAdapter(adapter);
                    if (selectedPos > -1)
                        sCountry.setSelection(selectedPos);

                }
            }

            @Override
            public void onFailure(Call<Map<String, Country>> call, Throwable t) {
                call.cancel();
            }
        });
        updateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> parametersMap = prepareAccountUpdateMap();
                Call<String> call = apiInterface.addAccount(parametersMap);

                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.d("TAG", response.code() + "");
                        String resource = response.body();

                        Toast.makeText(EditProfileActivity.this, "Changes Saved", Toast.LENGTH_SHORT).show();
                        updateProfileFromServer();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(EditProfileActivity.this, "Internal Server Error", Toast.LENGTH_SHORT).show();
                        call.cancel();
                    }
                });
            }
        });
    }

    private void loadGovenerates(Country country) {

        Map<String, String> parametersMap = prepareRequestMap();
        parametersMap.put("Action", "GetGovernorate");
        parametersMap.put("CountryID", country.getCountryID());
        Call<Map<String, Governorate>> call = apiInterface.getGovernerates(parametersMap);
        call.enqueue(new Callback<Map<String, Governorate>>() {
            @Override
            public void onResponse(Call<Map<String, Governorate>> call, Response<Map<String, Governorate>> response) {

                Log.d("TAG", response.code() + "");

                Map<String, Governorate> resource = response.body();
                if (response.body() != null) {
                    ArrayList<Governorate> governoratesList = new ArrayList<>(resource.values());
                    EditProfileActivity.this.governorates = governoratesList.toArray(new Governorate[governoratesList.size()]);
                    db.regionDAO().insertAllGovernorates(governorates);
                    //fill data in spinner
                    ArrayAdapter<Governorate> adapter = new ArrayAdapter<Governorate>(EditProfileActivity.this, android.R.layout.simple_spinner_dropdown_item, governorates);
                    int selectedPos = -1;
                    for (int i = 0; i < governoratesList.size(); i++) {
                        if (governoratesList.get(i).getGovernorateID().equals(loggedInUser.getGovernorateID())) {
                            selectedPos = i;
                            break;
                        }
                    }
                    sGovernerate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            Governorate selectedGovernerate = (Governorate) adapterView.getItemAtPosition(i);
                            loadDistricts(selectedGovernerate);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                    sGovernerate.setAdapter(adapter);
                    if (selectedPos > -1)
                        sGovernerate.setSelection(selectedPos);

                }
            }

            @Override
            public void onFailure(Call<Map<String, Governorate>> call, Throwable t) {
                call.cancel();
            }
        });
    }


    private void loadDistricts(Governorate governorate) {

        Map<String, String> parametersMap = prepareRequestMap();
        parametersMap.put("Action", "GetDistrict");
        parametersMap.put("GovernorateID", governorate.getGovernorateID());
        Call<Map<String, District>> call = apiInterface.getDistricts(parametersMap);

        call.enqueue(new Callback<Map<String, District>>() {
            @Override
            public void onResponse(Call<Map<String, District>> call, Response<Map<String, District>> response) {

                Log.d("TAG", response.code() + "");

                Map<String, District> resource = response.body();
                if (response.body() != null) {

                    ArrayList<District> countriesList = new ArrayList<>(resource.values());
                    districts = countriesList.toArray(new District[countriesList.size()]);
                    db.regionDAO().insertAllDistricts(districts);
                    //fill data in spinner
                    int selectedPos = -1;
                    for (int i = 0; i < districts.length; i++) {
                        if (districts[i].getDistrictID().equals(loggedInUser.getDistrictID())) {
                            selectedPos = i;
                            break;
                        }
                    }
                    ArrayAdapter<District> adapter = new ArrayAdapter<District>(EditProfileActivity.this, android.R.layout.simple_spinner_dropdown_item, districts);

                    sDistrict.setAdapter(adapter);
                    if (selectedPos > -1)
                        sDistrict.setSelection(selectedPos);
                }
            }

            @Override
            public void onFailure(Call<Map<String, District>> call, Throwable t) {
                call.cancel();
            }
        });
    }

    private Map<String, String> prepareAccountUpdateMap() {
        Map<String, String> result = prepareRequestMap();
        result.put("Action", "UpdateAccountPersonalInfo");
        result.put("AccountID", loggedInUser.getAccountID());
        result.put("FirstName", tvFirstName.getText().toString());
        result.put("LastName", tvLastName.getText().toString());
        result.put("Gender", sGender.getSelectedItem().toString());
        result.put("DOB", tvBirthday.getText().toString());
        result.put("MaritalStatus", sMartialStatus.getSelectedItem().toString());
        result.put("GovernorateID", ((Governorate) sGovernerate.getSelectedItem()).getGovernorateID());
        result.put("DistrictID", ((District) sDistrict.getSelectedItem()).getDistrictID());
        result.put("ContactNumber", etContactNumber.getText().toString());
        result.put("Description", etDescription.getText().toString());
        result.put("Hobbies", etHobbies.getText().toString());
//        AccountID, FirstName, LastName, Gender, DOB, MaritalStatus, GovernorateID, DistrictID, ContactNumber,
//        Description, Hobbies
        return result;
    }
    private void updateProfileFromServer(){
        HashMap<String, String> requestMap = this.prepareRequestMap();
        requestMap.put("Action", "GetAccountByID");
        requestMap.put("AccountID", loggedInUser.getAccountID());

        Call<Map<String, LoggedInUser>> call = apiInterface.getLoggedUserInfo(requestMap);

        call.enqueue(new Callback<Map<String, LoggedInUser>>() {
            @Override
            public void onResponse(Call<Map<String, LoggedInUser>> call, Response<Map<String, LoggedInUser>> response) {

                Log.d("TAG", response.code() + "");

                Map<String, LoggedInUser> resource = response.body();
                if (response.body() != null && response.body().size() == 1) {
                    AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                            AppDatabase.class, "rockAndFit").allowMainThreadQueries().fallbackToDestructiveMigration().build();
                    db.loggedInUserDao().insertAll(resource.get("1"));
                    db.close();
                } else {
                    Toast.makeText(EditProfileActivity.this, "Internal Server Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Map<String, LoggedInUser>> call, Throwable t) {
                call.cancel();
            }
        });
    }

    @NeedsPermission({Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE})
    void startDialog(boolean isProfile) {
        AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(
                EditProfileActivity.this);
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
                        int galleryRequestCode = GALLERY_PICTURE_PROFILE_REQUEST;
                        if(!isProfile){
                            galleryRequestCode = GALLERY_PICTURE_BANNER_REQUEST;
                        }
                        startActivityForResult(
                                pictureActionIntent,
                                galleryRequestCode);

                    }
                });

        myAlertDialog.setNegativeButton("Camera",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {

                        dispatchTakePictureIntent(isProfile);

                    }
                });
        myAlertDialog.show();
    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        // NOTE: delegate the permission handling to generated method
//        DashboardFragmentPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
//    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }


    private void dispatchTakePictureIntent(boolean isProfile) {

        Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            photoFile = createImageFile();
            takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
            int cameraRequestCode = CAMERA_PIC_PROFILE_REQUEST;
            if(!isProfile){
                cameraRequestCode =     CAMERA_PIC_BANNER_REQUEST;
            }
            startActivityForResult(takePhotoIntent, cameraRequestCode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void uploadImage(int requestCode) {
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
                MultipartBody.Part.createFormData("AccountImage", "Media", requestFile);

        Map<String, String> parametersMap = prepareRequestMap();
        if(requestCode == CAMERA_PIC_PROFILE_REQUEST || requestCode == GALLERY_PICTURE_PROFILE_REQUEST){
            parametersMap.put("Action", "UpdateAccountImage");
        }else {
            parametersMap.put("Action", "UpdateAccountCoverImage");
        }

        parametersMap.put("AccountID", loggedInUser.getAccountID());

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
                    Toast.makeText(EditProfileActivity.this, "Image Updated Successfully", Toast.LENGTH_SHORT).show();
                    updateProfileFromServer();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                call.cancel();
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == CAMERA_PIC_PROFILE_REQUEST || requestCode == CAMERA_PIC_BANNER_REQUEST) && resultCode == RESULT_OK) {
            ImageView imageView = ivUserProfile;
            if(requestCode==CAMERA_PIC_BANNER_REQUEST){
                imageView = banner;
            }
            // set the dimensions of the image
            int targetW =imageView.getWidth();
            int targetH = imageView.getHeight();

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
            uploadImage(requestCode);
        }
        if ((requestCode == GALLERY_PICTURE_PROFILE_REQUEST ||
                requestCode == GALLERY_PICTURE_BANNER_REQUEST ) && resultCode == RESULT_OK) {
            Uri selectedImage = data.getData();

            InputStream inputStream = null;

            if (ContentResolver.SCHEME_CONTENT.equals(selectedImage.getScheme())) {
                try {
                    inputStream = getContentResolver().openInputStream(selectedImage);
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
            bitmap = BitmapFactory.decodeStream(inputStream);
            uploadImage(requestCode);
        }

    }
}