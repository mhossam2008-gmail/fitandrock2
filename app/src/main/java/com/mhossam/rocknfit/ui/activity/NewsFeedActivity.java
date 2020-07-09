package com.mhossam.rocknfit.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mhossam.rocknfit.R;
import com.mhossam.rocknfit.ui.dashboard.DashboardFragment;
import com.mhossam.rocknfit.view.BaseDrawerActivity;

public class NewsFeedActivity extends BaseDrawerActivity {
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_news_feed);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                 R.id.navigation_dashboard,R.id.navigation_home, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        // Check if the fragment is an instance of the right fragment
        if (fragment instanceof DashboardFragment) {
            DashboardFragment my = (DashboardFragment) fragment;
            // Pass intent or its data to the fragment's method
            my.onNewIntent(intent);
        }

    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }
}