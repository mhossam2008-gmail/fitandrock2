package com.mhossam.rocknfit;

import android.os.Bundle;
import android.widget.Toast;

import com.mhossam.rocknfit.Utils.BaseAppCompatActivity;


public class LoginActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toast.makeText(this, apiURL, Toast.LENGTH_SHORT).show();
    }
}