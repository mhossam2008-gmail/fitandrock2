package com.mhossam.rocknfit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mhossam.rocknfit.Utils.BaseAppCompatActivity;

import java.util.HashMap;

public class FeedActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
    }

    @Override
    protected HashMap<String, String> prepareRequestMap() {
        HashMap<String, String> result = super.prepareRequestMap();
        return result;
    }
}