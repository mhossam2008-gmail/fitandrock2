package com.mhossam.rocknfit.Utils;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mhossam.rocknfit.R;

public class BaseAppCompatActivity extends AppCompatActivity {
    protected String apiUserHeader;
    protected String apiActionHeader;
    protected String apiPasswordHeader;
    protected String apiUserName;
    protected String apiPassword;
    protected String apiURL;
    protected String apiAction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiUserHeader = getString(R.string.api_user_header);
        apiPasswordHeader = getString(R.string.api_password_header);
        apiActionHeader = getString(R.string.api_action_header);
        apiUserName = getString(R.string.api_user);
        apiPassword = getString(R.string.api_pass);
        apiURL = getString(R.string.api_url);
    }
}
