package com.mhossam.rocknfit;

import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.mhossam.rocknfit.database.AppDatabase;
import com.mhossam.rocknfit.model.Country;
import com.mhossam.rocknfit.model.District;
import com.mhossam.rocknfit.model.Governorate;
import com.mhossam.rocknfit.model.LoggedInUser;
import com.mhossam.rocknfit.view.BaseDrawerActivity;

import java.util.ArrayList;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends BaseDrawerActivity {

    @BindView(R.id.sCountry)
    Spinner sCountry;


    @BindView(R.id.sDistrict)
    Spinner sDistrict;

    @BindView(R.id.sGovernerate)
    Spinner sGovernerate;

    private Country[] countries;
    private Governorate[] governorates;
    private District[] districts;
    private AppDatabase db;
    private LoggedInUser loggedInUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        ButterKnife.bind(this);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "rockAndFit").allowMainThreadQueries().fallbackToDestructiveMigration().build();

        loggedInUser = db.loggedInUserDao().getLoggedInUser();

        Map<String, String> parametersMap = prepareRequestMap();
        parametersMap.put("Action", "GetCountries");
        Call<Map<String, Country>> call = apiInterface.getCountries(parametersMap);

        call.enqueue(new Callback<Map<String, Country>>() {
            @Override
            public void onResponse(Call<Map<String, Country>> call, Response<Map<String, Country>> response) {

                Log.d("TAG", response.code() + "");

                Map<String, Country> resource = response.body();
                if (response.body() != null) {
                    ArrayList<Country> countriesList = new ArrayList<>(resource.values());
                    countries = countriesList.toArray(new Country[countriesList.size()]);
                    db.regionDAO().insertAllCountries(countries);
                    //fill data in spinner
                    int selectedPos = -1;
                    for (int i = 0; i < countriesList.size(); i++) {
                        if (countriesList.get(i).getCountryID().equals(loggedInUser.getCountryID())) {
                            selectedPos = i;
                            break;
                        }
                    }
                    ArrayAdapter<Country> adapter = new ArrayAdapter<Country>(EditProfileActivity.this, android.R.layout.simple_spinner_dropdown_item, countries);
                    sCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            Country selectedCountry = (Country) adapterView.getItemAtPosition(i);
                            loadGovenerates(selectedCountry);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                    sCountry.setAdapter(adapter);
                    if (selectedPos > -1)
                        sCountry.setSelection(selectedPos);

                }
            }

            @Override
            public void onFailure(Call<Map<String, Country>> call, Throwable t) {
                call.cancel();
            }
        });

    }

    private void loadGovenerates(Country country) {

        Map<String, String> parametersMap = prepareRequestMap();
        parametersMap.put("Action", "GetGovernorate");
        parametersMap.put("CountryID", country.getCountryID());
        Call<Map<String, Governorate>> call = apiInterface.getGovernerates(parametersMap);
        call.enqueue(new Callback<Map<String, Governorate>>() {
            @Override
            public void onResponse(Call<Map<String, Governorate>> call, Response<Map<String, Governorate>> response) {

                Log.d("TAG", response.code() + "");

                Map<String, Governorate> resource = response.body();
                if (response.body() != null) {
                    ArrayList<Governorate> governoratesList = new ArrayList<>(resource.values());
                    EditProfileActivity.this.governorates = governoratesList.toArray(new Governorate[governoratesList.size()]);
                    db.regionDAO().insertAllGovernorates(governorates);
                    //fill data in spinner
                    ArrayAdapter<Governorate> adapter = new ArrayAdapter<Governorate>(EditProfileActivity.this, android.R.layout.simple_spinner_dropdown_item, governorates);
                    int selectedPos = -1;
                    for (int i = 0 ; i< governoratesList.size() ; i++) {
                        if(governoratesList.get(i).getGovernorateID().equals(loggedInUser.getGovernorateID())){
                            selectedPos = i;
                            break;
                        }
                    }
                    sGovernerate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            Governorate selectedGovernerate = (Governorate) adapterView.getItemAtPosition(i);
                            loadDistricts(selectedGovernerate);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                    sGovernerate.setAdapter(adapter);
                    if (selectedPos > -1)
                        sGovernerate.setSelection(selectedPos);

                }
            }

            @Override
            public void onFailure(Call<Map<String, Governorate>> call, Throwable t) {
                call.cancel();
            }
        });
    }


    private void loadDistricts(Governorate governorate) {

        Map<String, String> parametersMap = prepareRequestMap();
        parametersMap.put("Action", "GetDistrict");
        parametersMap.put("GovernorateID", governorate.getGovernorateID());
        Call<Map<String, District>> call = apiInterface.getDistricts(parametersMap);

        call.enqueue(new Callback<Map<String, District>>() {
            @Override
            public void onResponse(Call<Map<String, District>> call, Response<Map<String, District>> response) {

                Log.d("TAG", response.code() + "");

                Map<String, District> resource = response.body();
                if (response.body() != null) {

                    ArrayList<District> countriesList = new ArrayList<>(resource.values());
                    districts = countriesList.toArray(new District[countriesList.size()]);
                    db.regionDAO().insertAllDistricts(districts);
                    //fill data in spinner
                    int selectedPos = -1;
                    for (int i = 0 ; i< districts.length ; i++) {
                        if(districts[i].getDistrictID().equals(loggedInUser.getDistrictID())){
                            selectedPos = i;
                            break;
                        }
                    }
                    ArrayAdapter<District> adapter = new ArrayAdapter<District>(EditProfileActivity.this, android.R.layout.simple_spinner_dropdown_item, districts);
                    sDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            District selectedCountry = (District) adapterView.getItemAtPosition(i);
                            Toast.makeText(EditProfileActivity.this, selectedCountry.getDistrictID(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                    sDistrict.setAdapter(adapter);
                    if (selectedPos > -1)
                        sDistrict.setSelection(selectedPos);
                }
            }

            @Override
            public void onFailure(Call<Map<String, District>> call, Throwable t) {
                call.cancel();
            }
        });
    }
}