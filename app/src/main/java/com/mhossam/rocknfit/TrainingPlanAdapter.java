package com.mhossam.rocknfit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.mhossam.rocknfit.dummy.DummyContent.DummyItem;
import com.mhossam.rocknfit.model.TrainingClass;
import com.mhossam.rocknfit.model.TrainingPlan;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class TrainingPlanAdapter extends RecyclerView.Adapter<TrainingPlanAdapter.ViewHolder> {

    private final Context context;
    private List<TrainingPlan> mValues;

    public TrainingPlanAdapter(List<TrainingPlan> items, Context context) {
        mValues = items;
        this.context = context;
    }


    public void updateItems(List<TrainingPlan> items) {
        mValues = new ArrayList<>();
        mValues.addAll(items);
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_training_plan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.tvPlanName.setText(mValues.get(position).getPlanName());
        holder.tvPlanDays.setText(holder.mItem.getDaysCounter());
//        holder.postTextTV.setText(mValues.get(position).getAdditionalInfo());
//        String userProfilePhoto = "https://www.fitandrock.com/ProfilePictures/Org"+mValues.get(position).getAccountImage();
//        Picasso.get()
//                .load(userProfilePhoto)
//                .placeholder(R.drawable.img_circle_placeholder)
//                .fit()
//                .into(holder.ivFeedCenter);
//        String dateRange = mValues.get(position).getClassStart()+" - "+mValues.get(position).getClassEnd();
        holder.tvDateRange.setText(holder.mItem.getCreationDate());
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

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TrainingPlan mItem;
//
        @BindView(R.id.tvPlanName)
        TextView tvPlanName;
        @BindView(R.id.tvPlanDays)
        TextView tvPlanDays;
//        @BindView(R.id.ivFeedCenter)
//        ImageView ivFeedCenter;
        @BindView(R.id.tvDateRange)
        TextView tvDateRange;
//        @BindView(R.id.tvGender)
//        TextView tvGender;
//        @BindView(R.id.btnDelete)
//        Button btnDelete;
//        @BindView(R.id.btnEdit)
//        ImageView btnEdit;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }

}