package com.mhossam.rocknfit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.mhossam.rocknfit.Utils.CircleTransformation;
import com.mhossam.rocknfit.dummy.DummyContent.DummyItem;
import com.mhossam.rocknfit.model.Question;
import com.mhossam.rocknfit.model.TrainingClass;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ViewHolder> {

    private List<TrainingClass> mValues;

    public ClassAdapter(List<TrainingClass> items) {
        mValues = items;
    }


    public void updateItems(List<TrainingClass> items) {
        mValues.addAll(items);
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_class, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.tvProfileName.setText(mValues.get(position).getBranchName());
        holder.postTextTV.setText(mValues.get(position).getAdditionalInfo());
        String userProfilePhoto = "https://www.fitandrock.com/ProfilePictures/Org"+mValues.get(position).getAccountImage();
        Picasso.get()
                .load(userProfilePhoto)
                .placeholder(R.drawable.img_circle_placeholder)
                .fit()
                .into(holder.ivFeedCenter);
        String dateRange = mValues.get(position).getClassStart()+" - "+mValues.get(position).getClassEnd();
        holder.tvDateRange.setText(dateRange);
        holder.tvGender.setText(mValues.get(position).getBranchGender());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TrainingClass mItem;

        @BindView(R.id.tvProfileName)
        TextView tvProfileName;
        @BindView(R.id.postTextTV)
        TextView postTextTV;
        @BindView(R.id.ivFeedCenter)
        ImageView ivFeedCenter;
        @BindView(R.id.tvDateRange)
        TextView tvDateRange;
        @BindView(R.id.tvGender)
        TextView tvGender;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }

}