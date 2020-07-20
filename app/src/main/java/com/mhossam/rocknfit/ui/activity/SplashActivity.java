package com.mhossam.rocknfit.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.room.Room;

import com.mhossam.rocknfit.API.APIClient;
import com.mhossam.rocknfit.API.APIInterface;
import com.mhossam.rocknfit.R;
import com.mhossam.rocknfit.database.AppDatabase;
import com.mhossam.rocknfit.model.LoggedInUser;
import com.mhossam.rocknfit.view.BaseActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends BaseActivity {

    private static final long SPLASH_DISPLAY_LENGTH = 3000;
    private APIInterface apiInterface;

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
        setContentView(R.layout.activity_splash);
        apiInterface = APIClient.getClient().create(APIInterface.class);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "rockAndFit").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        List<LoggedInUser> users = db.loggedInUserDao().getAll();
        Class myActivity;
        if (users == null || users.isEmpty()) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    /* Create an Intent that will start the Menu-Activity. */
                    Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                    SplashActivity.this.startActivity(mainIntent);
                    SplashActivity.this.finish();
                }
            }, SPLASH_DISPLAY_LENGTH);
        } else {
            LoggedInUser currentUser = db.loggedInUserDao().getLoggedInUser();
            currentUser = db.loggedInUserDao().getLoggedInUser();
            HashMap<String, String> requestMap = this.prepareRequestMap();
            requestMap.put("Action", "GetAccountByID");
            requestMap.put("AccountID", currentUser.getAccountID());

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
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                /* Create an Intent that will start the Menu-Activity. */
                                Intent mainIntent = new Intent(SplashActivity.this, NewsFeedActivity.class);
                                SplashActivity.this.startActivity(mainIntent);
                                SplashActivity.this.finish();
                            }
                        }, SPLASH_DISPLAY_LENGTH);
                    } else {
                        Toast.makeText(SplashActivity.this, "Internal Server Error", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Map<String, LoggedInUser>> call, Throwable t) {
                    call.cancel();
                }
            });

        }

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/

    }
}