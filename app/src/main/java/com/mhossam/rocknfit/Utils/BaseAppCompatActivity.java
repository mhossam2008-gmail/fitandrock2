package com.mhossam.rocknfit.Utils;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mhossam.rocknfit.API.APIClient;
import com.mhossam.rocknfit.API.APIInterface;
import com.mhossam.rocknfit.R;

import java.util.HashMap;
import java.util.Map;

public class BaseAppCompatActivity extends AppCompatActivity {
    protected String apiUserHeader;
    protected String apiActionHeader;
    protected String apiPasswordHeader;
    protected String apiUserName;
    protected String apiPassword;
    protected String apiURL;
    protected String apiAction;
    protected APIInterface apiInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiInterface = APIClient.getClient().create(APIInterface.class);

        apiUserHeader = getString(R.string.api_user_header);
        apiPasswordHeader = getString(R.string.api_password_header);
        apiActionHeader = getString(R.string.api_action_header);
        apiUserName = getString(R.string.api_user);
        apiPassword = getString(R.string.api_pass);
        apiURL = getString(R.string.api_url);
    }

    public HashMap<String, String> prepareRequestMap() {
        HashMap<String, String> result = new HashMap<>();
        result.put("ApiUser", "Test");
        result.put("ApiPass", "Test");
        return result;
    }
}
