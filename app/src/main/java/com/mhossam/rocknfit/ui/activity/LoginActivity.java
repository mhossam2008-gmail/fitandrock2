package com.mhossam.rocknfit.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.room.Room;

import com.mhossam.rocknfit.R;
import com.mhossam.rocknfit.Utils.BaseAppCompatActivity;
import com.mhossam.rocknfit.database.AppDatabase;
import com.mhossam.rocknfit.model.LoggedInUser;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends BaseAppCompatActivity {

    @BindView(R.id.buttonLogin)
    Button login;
    @BindView(R.id.usernameEditText)
    EditText username;
    @BindView(R.id.passwordEditText)
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> parametersMap = prepareRequestMap();
                Call<Map<String, LoggedInUser>> call = apiInterface.login(parametersMap);

                call.enqueue(new Callback<Map<String, LoggedInUser>>() {
                    @Override
                    public void onResponse(Call<Map<String, LoggedInUser>> call, Response<Map<String, LoggedInUser>> response) {

                        Log.d("TAG", response.code() + "");

                        Map<String, LoggedInUser> resource = response.body();
                        if(response.body()!=null&&response.body().size()==1)
                        {
                            AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                                AppDatabase.class, "rockAndFit").allowMainThreadQueries().fallbackToDestructiveMigration().build();
                            db.loggedInUserDao().insertAll(resource.get("1"));
                            for (LoggedInUser accountInfo:db.loggedInUserDao().getAll()) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent i = new Intent(getApplicationContext() , NewsFeedActivity.class);
                                        startActivity(i);
                                        finish();
                                    }
                                });

                            }
                        }else{
                            Toast.makeText(LoginActivity.this, "Wrong username/password", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Map<String, LoggedInUser>> call, Throwable t) {
                        call.cancel();
                    }
                });

            }
        });

    }

    @Override
    public HashMap<String, String> prepareRequestMap() {
        HashMap<String,String> result = super.prepareRequestMap();
        result.put("Action", "AccountLogin");
        result.put("AccountEmail",username.getText().toString());
        result.put("AccountPassword",password.getText().toString());
        return result;
    }
}