package com.mhossam.rocknfit;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.mhossam.rocknfit.Utils.BaseAppCompatActivity;
import com.mhossam.rocknfit.database.AppDatabase;
import com.mhossam.rocknfit.model.LoggedInUser;
import com.mhossam.rocknfit.model.PlanDetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlanDetailsActivity extends BaseAppCompatActivity {

    @BindView(R.id.rvPlanDetails)
    RecyclerView rvPlanDetails;
    private ArrayList<PlanDetails> planDetailsList = new ArrayList<>();
    private LoggedInUser currentUser;
    private PlanDetailsAdapter planDetailsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_details);
        ButterKnife.bind(this);
//        Toast.makeText(this, getIntent().getStringExtra("PlanID"), Toast.LENGTH_SHORT).show();
        AppDatabase db = Room.databaseBuilder(this,
                AppDatabase.class, "rockAndFit").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        currentUser = db.loggedInUserDao().getLoggedInUser();
        HashMap<String, String> requestMap = prepareRequestMap();
        requestMap.put("Action","GetPlanDetails");
        requestMap.put("PlanID",getIntent().getStringExtra("PlanID"));
        Call<Map<String, PlanDetails>> call = apiInterface.getPlanDetails(requestMap);
        planDetailsAdapter = new PlanDetailsAdapter(planDetailsList,this, currentUser);
        call.enqueue(new Callback<Map<String, PlanDetails>>() {
            @Override
            public void onResponse(Call<Map<String, PlanDetails>> call, Response<Map<String, PlanDetails>> response) {
                Map<String, PlanDetails> resource = response.body();
                for (Map.Entry<String, PlanDetails> entry : resource.entrySet()) {
                    String key = entry.getKey();
                    PlanDetails value = entry.getValue();
                    planDetailsList.add(value);
                }
                planDetailsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Map<String, PlanDetails>> call, Throwable t) {

            }
        });
        rvPlanDetails.setAdapter(planDetailsAdapter);
    }
}