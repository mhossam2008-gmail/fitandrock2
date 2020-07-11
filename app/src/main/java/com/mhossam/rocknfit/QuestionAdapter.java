package com.mhossam.rocknfit;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.mhossam.rocknfit.Utils.CircleTransformation;
import com.mhossam.rocknfit.dummy.DummyContent.DummyItem;
import com.mhossam.rocknfit.model.Question;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {

    private List<Question> mValues;

    public QuestionAdapter(List<Question> items) {
        mValues = items;
    }


    public void updateItems(List<Question> items) {
        mValues.addAll(items);
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_question, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.tvProfileName.setText(mValues.get(position).getAccountFullName());
        holder.postTextTV.setText(mValues.get(position).getQuestionText());

        String userProfilePhoto = "https://www.fitandrock.com/ProfilePictures/"+mValues.get(position).getAccountImage();
        Picasso.get()
                .load(userProfilePhoto)
                .placeholder(R.drawable.img_circle_placeholder)
                .centerCrop()
                .resize(holder.avatarSize,holder.avatarSize)
                .transform(new CircleTransformation())
                .into(holder.ivUserProfile);
        holder.tvCommentCount.setText(mValues.get(position).getAnswersCount());


    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public Question mItem;

        @BindView(R.id.tvProfileName)
        TextView tvProfileName;
        @BindView(R.id.postTextTV)
        TextView postTextTV;
        @BindView(R.id.ivUserProfile)
        ImageView ivUserProfile;
        @BindView(R.id.btnComments)
        ImageButton btnComments;
        @BindView(R.id.tvCommentCount)
        TextView tvCommentCount;
        @BindDimen(R.dimen.global_menu_avatar_size)
        int avatarSize;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }

}