package com.mhossam.rocknfit;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.mhossam.rocknfit.API.APIClient;
import com.mhossam.rocknfit.API.APIInterface;
import com.mhossam.rocknfit.dummy.DummyContent.DummyItem;
import com.mhossam.rocknfit.model.LoggedInUser;
import com.mhossam.rocknfit.model.PlanDetails;
import com.mhossam.rocknfit.model.TrainingPlan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class PlanDetailsAdapter extends RecyclerView.Adapter<PlanDetailsAdapter.ViewHolder> {

    private final Context context;
    private final APIInterface apiInterface;
    private List<PlanDetails> mValues;
    private LoggedInUser currentUser;

    public PlanDetailsAdapter(List<PlanDetails> items, Context context, LoggedInUser currentUser) {
        mValues = items;
        this.context = context;
        this.currentUser = currentUser;
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }


    public void updateItems(List<PlanDetails> items) {
        mValues = new ArrayList<>();
        mValues.addAll(items);
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_plan_details, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.tvDetailsName.setText("Day "+mValues.get(position).getDayNumber());
        holder.tvDetailsName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context , PlanDetailsActivity.class);
                i.putExtra("PlanID",holder.mItem.getPlanID());
                context.startActivity(i);
            }
        });
        holder.tvDateRange.setText(holder.mItem.getWorkoutName());
        String details = "Sets: "+holder.mItem.getSetsNo()+", Reps: "+holder.mItem.getRepsNo()+", "+holder.mItem.getKG()+" KGs";
        holder.postTextTV.setText(details);
//        holder.tvPlanDays.setText(holder.mItem.getDaysCounter());
//        holder.postTextTV.setText(mValues.get(position).getAdditionalInfo());
//        String userProfilePhoto = "https://www.fitandrock.com/ProfilePictures/Org"+mValues.get(position).getAccountImage();
//        Picasso.get()
//                .load(userProfilePhoto)
//                .placeholder(R.drawable.img_circle_placeholder)
//                .fit()
//                .into(holder.ivFeedCenter);
//        String dateRange = mValues.get(position).getClassStart()+" - "+mValues.get(position).getClassEnd();
//        holder.tvDateRange.setText(holder.mItem.getCreationDate());
//        holder.btnCopyPlan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                holder.showCopyPopup();
//            }
//        });
//        holder.btnInvitePlan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                holder.showInvitePopup();
//            }
//        });
//
//        holder.btnDeletePlan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                holder.showDeletePopup();
//            }
//        });
//        holder.tvGender.setText(mValues.get(position).getBranchGender());
//        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(context, "Delete Pressed , but functionality not provided by Backend yet", Toast.LENGTH_LONG).show();
//            }
//        });
//        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(context, "Edit Pressed , but functionality not provided by Backend yet", Toast.LENGTH_LONG).show();
//            }
//        });
    }


    private Map<String, String> prepareRequestMap() {
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("ApiUser", "Test");
        requestMap.put("ApiPass", "Test");
        return requestMap;
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public PlanDetails mItem;
        //
        @BindView(R.id.tvDetailsName)
        TextView tvDetailsName;
//        @BindView(R.id.tvPlanDays)
//        TextView tvPlanDays;
//        //        @BindView(R.id.ivFeedCenter)
////        ImageView ivFeedCenter;
        @BindView(R.id.tvDateRange)
        TextView tvDateRange;

        @BindView(R.id.postTextTV)
        TextView postTextTV;
//
//        @BindView(R.id.btnCopyPlan)
//        Button btnCopyPlan;
//
//        @BindView(R.id.btnInvitePlan)
//        Button btnInvitePlan;
//
//        @BindView(R.id.btnDeletePlan)
//        ImageButton btnDeletePlan;
////        @BindView(R.id.tvGender)
////        TextView tvGender;
////        @BindView(R.id.btnDelete)
////        Button btnDelete;
////        @BindView(R.id.btnEdit)
////        ImageView btnEdit;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
        private void showInvitePopup() {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = layoutInflater.inflate(R.layout.invite_plan_popup_layout, null);


            // Creating the PopupWindow
            PopupWindow changeSortPopUp = new PopupWindow(context);
            changeSortPopUp.setContentView(layout);
            changeSortPopUp.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
            changeSortPopUp.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
            changeSortPopUp.setFocusable(true);


            // Clear the default translucent background
            changeSortPopUp.setBackgroundDrawable(new BitmapDrawable());

            // Displaying the popup at the specified location, + offsets.
            changeSortPopUp.showAtLocation(layout, Gravity.CENTER, 0, 0);
//        ivUserProfilePost = layout.findViewById(R.id.ivUserProfilePost);
//        String origUserProfilePhoto = "https://www.fitandrock.com/ProfilePictures/Org" + currentUser.getAccountImage();
//        Picasso.get()
//                .load(origUserProfilePhoto)
//                .placeholder(R.drawable.img_circle_placeholder)
//                .fit()
//                .into(ivUserProfilePost);
//        // Getting a reference to Close button, and close the popup when clicked.
            ImageButton close = layout.findViewById(R.id.close);
            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changeSortPopUp.dismiss();
                }
            });
        }

        private void showDeletePopup() {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = layoutInflater.inflate(R.layout.delete_plan_popup_layout, null);


            // Creating the PopupWindow
            PopupWindow changeSortPopUp = new PopupWindow(context);
            changeSortPopUp.setContentView(layout);
            changeSortPopUp.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
            changeSortPopUp.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
            changeSortPopUp.setFocusable(true);


            // Clear the default translucent background
            changeSortPopUp.setBackgroundDrawable(new BitmapDrawable());

            // Displaying the popup at the specified location, + offsets.
            changeSortPopUp.showAtLocation(layout, Gravity.CENTER, 0, 0);

            ImageButton close = layout.findViewById(R.id.close);
            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changeSortPopUp.dismiss();
                }
            });
            Button deletePlan = (Button) layout.findViewById(R.id.btnDeletePlan);
            deletePlan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Map<String, String> requestMap = prepareRequestMap();
                    requestMap.put("Action", "DeletePlan");
                    requestMap.put("AccountID", currentUser.getAccountID());
                    requestMap.put("PlanID",mItem.getPlanID());
                    Call<String> call = apiInterface.copyTrainingPlan(requestMap);
                    call.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            Log.d("TAG", response.code() + "");

                            String resource = response.body();
                            if (resource == null || resource.equals("null")) {
                                Toast.makeText(context, "Plan Deleted Successfully", Toast.LENGTH_SHORT).show();
                                changeSortPopUp.dismiss();
//                                parentFragment.getTrainingPlans();
                            }else{
                                Toast.makeText(context, resource, Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                }
            });
        }


        private void showCopyPopup() {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = layoutInflater.inflate(R.layout.copy_plan_popup_layout, null);


            // Creating the PopupWindow
            PopupWindow changeSortPopUp = new PopupWindow(context);
            changeSortPopUp.setContentView(layout);
            changeSortPopUp.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
            changeSortPopUp.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
            changeSortPopUp.setFocusable(true);


            // Clear the default translucent background
            changeSortPopUp.setBackgroundDrawable(new BitmapDrawable());

            // Displaying the popup at the specified location, + offsets.
            changeSortPopUp.showAtLocation(layout, Gravity.CENTER, 0, 0);
            // Getting a reference to Close button, and close the popup when clicked.
            ImageButton close = layout.findViewById(R.id.close);
            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changeSortPopUp.dismiss();
                }
            });

            Button copyPlan = (Button) layout.findViewById(R.id.btnCopyPlan);
            EditText planName = (EditText)layout.findViewById(R.id.etPlanName);
            copyPlan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Map<String, String> requestMap = prepareRequestMap();
                    requestMap.put("Action", "CopyPlan");
                    requestMap.put("AccountID", currentUser.getAccountID());
                    requestMap.put("Name", planName.getText().toString());
                    requestMap.put("PlanID",mItem.getPlanID());
                    Call<String> call = apiInterface.copyTrainingPlan(requestMap);
                    call.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            Log.d("TAG", response.code() + "");

                            String resource = response.body();
                            if (resource == null || resource.equals("null")) {
                                Toast.makeText(context, "Plan Copied Successfully", Toast.LENGTH_SHORT).show();
                                changeSortPopUp.dismiss();
//                                parentFragment.getTrainingPlans();
//                                ((TrainingPlanActivity)context).recreate();
                            }else{
                                Toast.makeText(context, resource, Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                }
            });
        }

    }

}