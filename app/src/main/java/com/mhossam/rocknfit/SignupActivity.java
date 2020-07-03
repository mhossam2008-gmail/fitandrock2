package com.mhossam.rocknfit;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.mhossam.rocknfit.API.APIClient;
import com.mhossam.rocknfit.API.APIInterface;
import com.mhossam.rocknfit.Utils.BaseAppCompatActivity;
import com.mhossam.rocknfit.model.AccountInfo;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends BaseAppCompatActivity {
    @BindView(R.id.buttonSignup)
    Button signup;
    @BindView(R.id.fullNameEditText)
    EditText fullName;
    @BindView(R.id.emailEditText)
    EditText userName;
    @BindView(R.id.birthdayEditText)
    EditText birthday;
    @BindView(R.id.passwordEditText)
    EditText password;
    @BindView(R.id.genderSpinner)
    Spinner gender;
    @BindView(R.id.accountTypespinner)
    Spinner accountType;
    private int _birthYear;
    private int _month;
    private int _day;
    APIInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        apiInterface = APIClient.getClient().create(APIInterface.class);
//        Call<List<AccountInfo>> call = apiInterface.createUser("GetAccountByID","Test", "Test",1);
        HashMap<String, String> map = new HashMap<>();
        map.put("Action", "GetAccountByID");
//        map.put("Action","AddAccount");
        map.put("ApiUser", "Test");
        map.put("ApiPass", "Test");
        map.put("AccountID", "1");
        map.put("FirstName", "Mohamed");
        map.put("LastName", "Hossam");
        map.put("Email", "mhossam2008@gmail.com");
        map.put("Gender", "Male");
        map.put("DOB", "20/11/1986");
        map.put("Type", "A");
        //Add New Account (Action=AddAccount),
        //FirstName, LastName, Email, Password, Gender, DOB, Type (‘A’,’T’,’G’)

        Call<Map<String, AccountInfo>> call = apiInterface.addAccount(map);

        call.enqueue(new Callback<Map<String, AccountInfo>>() {
            @Override
            public void onResponse(Call<Map<String, AccountInfo>> call, Response<Map<String, AccountInfo>> response) {

                Log.d("TAG", response.code() + "");

                Map<String, AccountInfo> resource = response.body();
                System.out.println(resource.get("1").toString());

            }

            @Override
            public void onFailure(Call<Map<String, AccountInfo>> call, Throwable t) {
                call.cancel();
            }
        });


        final Calendar myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                _birthYear = year;
                _month = monthOfYear;
                _day = dayOfMonth;
                updateLabel();
            }

        };
        birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(SignupActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> parametersMap = prepareRequestMap();
            }
        });

    }

    private Map<String, String> prepareRequestMap() {
        HashMap<String, String> result = new HashMap<>();
        //FirstName, LastName, Email, Password, Gender, DOB, Type (‘A’,’T’,’G’)
//        result.put("FirstName",);
//        result.put("LastName",);
        result.put("Email", userName.getText().toString());
        result.put("Password", password.getText().toString());
        result.put("Gender", gender.getSelectedItem().toString());
        result.put("DOB", birthday.getText().toString());

        String selectedType = gender.getSelectedItem().toString();
        if ("User".equals(selectedType)) {
            result.put("Type", "A");
        } else if ("Trainer".equals(selectedType)) {
            result.put("Type", "T");
        } else if ("Gym".equals(selectedType)) {
            result.put("Type", "G");
        }

        return result;
    }

    private void updateLabel() {
        birthday.setText(new StringBuilder().append(_day).append("/").append(_month + 1).append("/").append(_birthYear).append(" "));
    }
}