package com.mhossam.rocknfit;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.room.Room;

import com.mhossam.rocknfit.Utils.BaseAppCompatActivity;
import com.mhossam.rocknfit.database.AppDatabase;
import com.mhossam.rocknfit.model.AccountInfo;

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
                Map<String, String> parametersMap = prepareLoginRequestMap();
                Call<Map<String, AccountInfo>> call = apiInterface.login(parametersMap);

                call.enqueue(new Callback<Map<String, AccountInfo>>() {
                    @Override
                    public void onResponse(Call<Map<String, AccountInfo>> call, Response<Map<String, AccountInfo>> response) {

                        Log.d("TAG", response.code() + "");

                        Map<String, AccountInfo> resource = response.body();
                        System.out.println(resource.get("1").toString());
                        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                                AppDatabase.class, "rockAndFit").allowMainThreadQueries().build();
                        db.accountInfoDao().insertAll(resource.get("1"));

                        for (AccountInfo accountInfo:db.accountInfoDao().getAll()) {
                            System.out.println(accountInfo);
                        }
//                        Toast.makeText(LoginActivity.this, resource, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Map<String, AccountInfo>> call, Throwable t) {
                        call.cancel();
                    }
                });

            }
        });

    }

    private Map<String, String> prepareLoginRequestMap() {
        Map<String,String> result = super.prepareRequestMap();
        result.put("Action", "AccountLogin");
        result.put("AccountEmail",username.getText().toString());
        result.put("AccountPassword",password.getText().toString());
        return result;
    }
}