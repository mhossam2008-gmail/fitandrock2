package com.mhossam.rocknfit;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.mhossam.rocknfit.Utils.BaseAppCompatActivity;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

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