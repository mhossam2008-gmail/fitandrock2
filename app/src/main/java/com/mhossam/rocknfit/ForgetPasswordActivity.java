package com.mhossam.rocknfit;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.room.Room;

import com.mhossam.rocknfit.Utils.BaseAppCompatActivity;
import com.mhossam.rocknfit.database.AppDatabase;
import com.mhossam.rocknfit.model.LoggedInUser;
import com.mhossam.rocknfit.ui.activity.LoginActivity;
import com.mhossam.rocknfit.ui.activity.NewsFeedActivity;
import com.mhossam.rocknfit.ui.activity.RegisterationSuccessActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPasswordActivity extends BaseAppCompatActivity {

    @BindView(R.id.buttonForgetPassword)
    Button buttonForgetPassword;

    @BindView(R.id.usernameEditText)
    EditText userNameEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_forget_password);
        ButterKnife.bind(this);

        buttonForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> parametersMap = prepareRequestMap();
                Call<String> call = apiInterface.forgetPassword(parametersMap);

                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {

                        Log.d("TAG", response.code() + "");

                        String resource = response.body();
                        if(response.body()!=null)
                        {
                            Intent i = new Intent(ForgetPasswordActivity.this, RegisterationSuccessActivity.class);
                            startActivity(i);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        call.cancel();
                    }
                });

            }
        });

    }

    @Override
    public HashMap<String, String> prepareRequestMap() {
        HashMap<String,String> result = super.prepareRequestMap();
        result.put("Action", "AddPasswordReset");
        result.put("AccountEmail",userNameEditText.getText().toString());
        return result;
    }
}