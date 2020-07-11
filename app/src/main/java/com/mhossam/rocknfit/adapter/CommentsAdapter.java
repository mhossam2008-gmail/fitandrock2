package com.mhossam.rocknfit.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.mhossam.rocknfit.API.APIClient;
import com.mhossam.rocknfit.API.APIInterface;
import com.mhossam.rocknfit.R;
import com.mhossam.rocknfit.Utils.RoundedTransformation;
import com.mhossam.rocknfit.model.Post;
import com.mhossam.rocknfit.model.PostComment;
import com.mhossam.rocknfit.ui.activity.CommentsActivity;
import com.mhossam.rocknfit.ui.activity.NewsFeedActivity;
import com.squareup.picasso.Picasso;

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
 * Created by froger_mcs on 11.11.14.
 */
public class CommentsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private int itemsCount = 0;
    private int lastAnimatedPosition = -1;
    private int avatarSize;
    public static final String ACTION_LIKE_BUTTON_CLICKED = "action_like_button_button";
    public static final int VIEW_TYPE_DEFAULT = 1;
    public static final int VIEW_TYPE_LOADER = 2;

    private boolean animationsLocked = false;
    private boolean delayEnterAnimation = true;
    private List<PostComment> postComments = new ArrayList<>(0);
    private final APIInterface apiInterface;


    public CommentsAdapter(Context context) {
        this.context = context;
        avatarSize = context.getResources().getDimensionPixelSize(R.dimen.comment_avatar_size);
        apiInterface = APIClient.getClient().create(APIInterface.class);

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(context).inflate(R.layout.item_comment, parent, false);
        CommentViewHolder commentsView = new CommentViewHolder(view);
        if (viewType == VIEW_TYPE_DEFAULT) {
            setupClickableViews(view, commentsView);
        } else if (viewType == VIEW_TYPE_LOADER) {
        }

        return commentsView;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
//        runEnterAnimation(viewHolder.itemView, position);
        CommentViewHolder holder = (CommentViewHolder) viewHolder;
        PostComment postComment = postComments.get(position);
        holder.setPostComment(postComment);
        holder.tvComment.setText(postComment.getCommentContent());
        String profilePhoto = "https://www.fitandrock.com/ProfilePictures/"+postComment.getAccountImage();

        Picasso.get()
                .load(profilePhoto)
                .centerCrop()
                .resize(avatarSize, avatarSize)
                .transform(new RoundedTransformation())
                .into(holder.ivUserAvatar);
        holder.tvLikeCount.setText(postComment.getLikesCounter());
        holder.btnLike.setImageResource(!(postComment.getLikeID()==null ||
                postComment.getLikeID().isEmpty()) ? R.drawable.ic_heart_red : R.drawable.ic_heart_outline_grey);
//        holder.btnLike.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(postComment.getLikeID()==null || postComment.getLikeID().isEmpty()){
//                    holder.btnLike.setImageResource(R.drawable.ic_heart_red);
//                    postComment.setLikesCounter(((Integer.parseInt(postComment.getLikesCounter()))+1)+"");
//                    postComment.setLikeID("Liked");
//                    notifyItemChanged(position);
//                }
//            }
//        });
    }

    private void runEnterAnimation(View view, int position) {
        if (animationsLocked) return;

        if (position > lastAnimatedPosition) {
            lastAnimatedPosition = position;
            view.setTranslationY(100);
            view.setAlpha(0.f);
            view.animate()
                    .translationY(0).alpha(1.f)
                    .setStartDelay(delayEnterAnimation ? 20 * (position) : 0)
                    .setInterpolator(new DecelerateInterpolator(2.f))
                    .setDuration(300)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            animationsLocked = true;
                        }
                    })
                    .start();
        }
    }

    @Override
    public int getItemCount() {
        return postComments.size();
    }

    public void updateItems() {
        itemsCount = 10;
        notifyDataSetChanged();
    }

    public void addItem() {
        itemsCount++;
        notifyItemInserted(itemsCount - 1);
    }

    public void setAnimationsLocked(boolean animationsLocked) {
        this.animationsLocked = animationsLocked;
    }

    public void setDelayEnterAnimation(boolean delayEnterAnimation) {
        this.delayEnterAnimation = delayEnterAnimation;
    }

    public void setPostComments(List<PostComment> postComments) {
        this.postComments = postComments;
        notifyDataSetChanged();
    }

    public List<PostComment> getPostComments() {
        return postComments;
    }

    public static class CommentViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivUserAvatar)
        ImageView ivUserAvatar;
        @BindView(R.id.tvComment)
        TextView tvComment;
        @BindView(R.id.btnLike)
        ImageButton btnLike;
        @BindView(R.id.tvLikeCount)
        TextView tvLikeCount;

        private PostComment postComment;

        public CommentViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public PostComment getPostComment() {
            return postComment;
        }

        public void setPostComment(PostComment postComment) {
            this.postComment = postComment;
        }
    }

    private void setupClickableViews(final View view, final CommentViewHolder cellFeedViewHolder) {
        cellFeedViewHolder.btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = cellFeedViewHolder.getAdapterPosition();
                PostComment postComment = postComments.get(adapterPosition);

                notifyItemChanged(adapterPosition, ACTION_LIKE_BUTTON_CLICKED);
                if (context instanceof CommentsActivity) {

                    Map<String, String> parametersMap = prepareRequestMap();
                    parametersMap.put("Action","LikeComment");
                    parametersMap.put("CommentID",postComment.getCommentID());
                    parametersMap.put("AccountID","95");
                    Call<String> call = apiInterface.likePost(parametersMap);
                    call.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            Log.d("TAG", response.code() + "");
                            String resource = response.body();
                            if(resource==null || resource.contains("null")) {
                                Toast.makeText(context, "Liked", Toast.LENGTH_SHORT).show();
                                postComment.setLikesCounter(postComment.getLikesCounter()+1);
                                postComment.setLikeID("liked");
                                notifyItemChanged(adapterPosition);
                            }else{
                                Toast.makeText(context, resource, Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Toast.makeText(context, "Internal Server Error", Toast.LENGTH_SHORT).show();
                            call.cancel();
                        }
                    });
//                    ((FeedActivity) context).showLikedSnackbar();
                }
            }
        });
    }
    public HashMap<String, String> prepareRequestMap() {
        HashMap<String, String> result = new HashMap<>();
        result.put("ApiUser", "Test");
        result.put("ApiPass", "Test");
        return result;
    }

    @Override
    public int getItemViewType(int position) {
        if ((postComments == null || postComments.size()==0)&& position == 0) {
            return VIEW_TYPE_LOADER;
        } else {
            return VIEW_TYPE_DEFAULT;
        }
    }
}
