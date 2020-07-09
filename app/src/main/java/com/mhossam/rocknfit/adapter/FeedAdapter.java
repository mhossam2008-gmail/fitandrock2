package com.mhossam.rocknfit.adapter;

import android.content.Context;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.mhossam.rocknfit.API.APIClient;
import com.mhossam.rocknfit.API.APIInterface;
import com.mhossam.rocknfit.ui.activity.FeedActivity;
import com.mhossam.rocknfit.R;
import com.mhossam.rocknfit.Utils.CircleTransformation;
import com.mhossam.rocknfit.model.Post;
import com.mhossam.rocknfit.ui.activity.NewsFeedActivity;
import com.mhossam.rocknfit.ui.activity.SignupActivity;
import com.mhossam.rocknfit.view.LoadingFeedItemView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by froger_mcs on 05.11.14.
 */
public class FeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final String ACTION_LIKE_BUTTON_CLICKED = "action_like_button_button";
    public static final String ACTION_LIKE_IMAGE_CLICKED = "action_like_image_button";

    public static final int VIEW_TYPE_DEFAULT = 1;
    public static final int VIEW_TYPE_LOADER = 2;

    private final List<Post> feedItems = new ArrayList<>();
    private final APIInterface apiInterface;

    private Context context;
    private OnFeedItemClickListener onFeedItemClickListener;

    private boolean showLoadingView = false;

    public FeedAdapter(Context context) {
        this.context = context;
        apiInterface = APIClient.getClient().create(APIInterface.class);

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_DEFAULT) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_feed, parent, false);
            CellFeedViewHolder cellFeedViewHolder = new CellFeedViewHolder(view,context);
            setupClickableViews(view, cellFeedViewHolder);
            return cellFeedViewHolder;
        } else if (viewType == VIEW_TYPE_LOADER) {
            LoadingFeedItemView view = new LoadingFeedItemView(context);
            view.setLayoutParams(new LinearLayoutCompat.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT)
            );
            return new LoadingCellFeedViewHolder(view, context);
        }

        return null;
    }

    private void setupClickableViews(final View view, final CellFeedViewHolder cellFeedViewHolder) {
        cellFeedViewHolder.btnComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFeedItemClickListener.onCommentsClick(view, cellFeedViewHolder.getAdapterPosition());
            }
        });
        cellFeedViewHolder.btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFeedItemClickListener.onMoreClick(v, cellFeedViewHolder.getAdapterPosition());
            }
        });
//        cellFeedViewHolder.ivFeedCenter.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int adapterPosition = cellFeedViewHolder.getAdapterPosition();
////                feedItems.get(adapterPosition).likesCount++;
//                notifyItemChanged(adapterPosition, ACTION_LIKE_IMAGE_CLICKED);
//                if (context instanceof FeedActivity) {
//                    ((FeedActivity) context).showLikedSnackbar();
//                }
//            }
//        });
        cellFeedViewHolder.btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = cellFeedViewHolder.getAdapterPosition();
                Post currentFeedItem = feedItems.get(adapterPosition);
                
                notifyItemChanged(adapterPosition, ACTION_LIKE_BUTTON_CLICKED);
                if (context instanceof NewsFeedActivity) {

                    Map<String, String> parametersMap = prepareRequestMap();
                    parametersMap.put("Action","LikePost");
                    parametersMap.put("PostID",currentFeedItem.getPostID());
                    parametersMap.put("AccountID","95");
                    Call<String> call = apiInterface.likePost(parametersMap);
                    call.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            Log.d("TAG", response.code() + "");
                            String resource = response.body();
                            if(resource==null || resource.contains("null")) {
                                Toast.makeText(context, "Liked", Toast.LENGTH_SHORT).show();
                                currentFeedItem.setLikesCounter(currentFeedItem.getLikesCounter()+1);
                                currentFeedItem.setLikeID("liked");
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
        cellFeedViewHolder.ivUserProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFeedItemClickListener.onProfileClick(view);
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
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {


        ((CellFeedViewHolder) viewHolder).bindView(feedItems.get(position));

        if (getItemViewType(position) == VIEW_TYPE_LOADER) {
            bindLoadingFeedItem((LoadingCellFeedViewHolder) viewHolder);
        }
    }
    private void bindLoadingFeedItem(final LoadingCellFeedViewHolder holder) {
        holder.loadingFeedItemView.setOnLoadingFinishedListener(new LoadingFeedItemView.OnLoadingFinishedListener() {
            @Override
            public void onLoadingFinished() {
                showLoadingView = false;
                notifyItemChanged(0);
            }
        });
        holder.loadingFeedItemView.startLoading();
    }

    @Override
    public int getItemViewType(int position) {
        if (showLoadingView && position == 0) {
            return VIEW_TYPE_LOADER;
        } else {
            return VIEW_TYPE_DEFAULT;
        }
    }

    @Override
    public int getItemCount() {
        return feedItems.size();
    }

    public void updateItems(boolean animated , List<Post> updatedFeedItems) {
        updateItems(animated,updatedFeedItems,false);
    }


    public void updateItems(boolean animated , List<Post> updatedFeedItems, boolean keepOldItems) {
        if(!keepOldItems){
            feedItems.clear();
        }
        feedItems.addAll(updatedFeedItems);
        if (animated) {
            notifyItemRangeInserted(0, feedItems.size());
        } else {
            notifyDataSetChanged();
        }
    }

    public void setOnFeedItemClickListener(OnFeedItemClickListener onFeedItemClickListener) {
        this.onFeedItemClickListener = onFeedItemClickListener;
    }

    public void showLoadingView() {
        showLoadingView = true;
        notifyItemChanged(0);
    }


    public static class CellFeedViewHolder extends RecyclerView.ViewHolder {
        private final Context context;
        @BindView(R.id.ivFeedCenter)
        ImageView ivFeedCenter;
        @BindView(R.id.ivFeedBottom)
        TextView ivFeedBottom;
        @BindView(R.id.btnComments)
        ImageButton btnComments;
        @BindView(R.id.btnLike)
        ImageButton btnLike;
        @BindView(R.id.btnMore)
        ImageButton btnMore;
        @BindView(R.id.vBgLike)
        View vBgLike;
        @BindView(R.id.ivLike)
        ImageView ivLike;
        @BindView(R.id.tsLikesCounter)
        TextSwitcher tsLikesCounter;
        @BindView(R.id.ivUserProfile)
        ImageView ivUserProfile;
        @BindView(R.id.vImageRoot)
        FrameLayout vImageRoot;
        @BindView(R.id.postTextTV)
        TextView postTextView;
        @BindView(R.id.tvProfileName)
        TextView tvProfileName;
        @BindView(R.id.playIcon)
        ImageView playIcon;
        @BindDimen(R.dimen.global_menu_avatar_size)
        int avatarSize;

        Post feedItem;

        public CellFeedViewHolder(View view, Context context) {
            super(view);
            ButterKnife.bind(this, view);
            this.context = context;
        }

        public void bindView(Post feedItem) {
            this.feedItem = feedItem;
            int adapterPosition = getAdapterPosition();
//            ivFeedCenter.setImageResource(adapterPosition % 2 == 0 ? R.drawable.img_feed_center_1 : R.drawable.img_feed_center_2);
//            ivFeedBottom.setImageResource(adapterPosition % 2 == 0 ? R.drawable.img_feed_bottom_1 : R.drawable.img_feed_bottom_2);
            String profilePhoto = "https://www.fitandrock.com"+feedItem.getProfilePicturePath();
            playIcon.setVisibility(View.GONE);
            if(feedItem.getPostMedia() == null || feedItem.getPostMedia().equals("")){
                vImageRoot.setVisibility(View.GONE);
            }else if(feedItem.getPostMedia() instanceof String &&((String) feedItem.getPostMedia()).contains("youtube")){
                String postPhoto = ((String) feedItem.getPostMedia()).replace("www.youtube.com/watch?v=","img.youtube.com/vi/");
                postPhoto+="/mqdefault.jpg";

                Picasso.get()
                        .load(postPhoto)
                        .placeholder(R.drawable.img_circle_placeholder)
                        .fit()
//                        .centerCrop()
//                        .resize(avatarSize,avatarSize)
//                        .transform(new SquareTransformation())
                        .into(ivFeedCenter);
                playIcon.setVisibility(View.VISIBLE);
                ivFeedCenter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse((String)feedItem.getPostMedia()));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setPackage("com.google.android.youtube");
                        context.startActivity(intent);
                    }
                });
                playIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse((String)feedItem.getPostMedia()));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setPackage("com.google.android.youtube");
                        context.startActivity(intent);
                    }
                });
            }
            else{
                String postPhoto = "https://www.fitandrock.com"+feedItem.getFullImagePath();

                Picasso.get()
                        .load(postPhoto)
                        .placeholder(R.drawable.img_circle_placeholder)
                        .fit()
//                        .centerCrop()
//                        .resize(avatarSize,avatarSize)
//                        .transform(new SquareTransformation())
                        .into(ivFeedCenter);
            }
            Picasso.get()
                .load(profilePhoto)
                .placeholder(R.drawable.img_circle_placeholder)
                .centerCrop()
                .resize(avatarSize,avatarSize)
                    .transform(new CircleTransformation())
                .into(ivUserProfile);

            tvProfileName.setText(feedItem.getAccountFullName());
            postTextView.setText(feedItem.getPostContent());
            btnLike.setImageResource(!(feedItem.getLikeID()==null ||
                    feedItem.getLikeID().isEmpty()) ? R.drawable.ic_heart_red : R.drawable.ic_heart_outline_grey);
            tsLikesCounter.setCurrentText(vImageRoot.getResources().getQuantityString(
                    R.plurals.likes_count, feedItem.getLikesCounter(), feedItem.getLikesCounter()
            ));
        }

        public Post getFeedItem() {
            return feedItem;
        }
    }

    public static class LoadingCellFeedViewHolder extends CellFeedViewHolder {

        private final Context context;
        LoadingFeedItemView loadingFeedItemView;

        public LoadingCellFeedViewHolder(LoadingFeedItemView view, Context context) {
            super(view, context);
            this.loadingFeedItemView = view;
            this.context = context;
        }

        @Override
        public void bindView(Post feedItem) {
            super.bindView(feedItem);
        }
    }

    public static class FeedItem {
        public int likesCount;
        public boolean isLiked;

        public FeedItem(int likesCount, boolean isLiked) {
            this.likesCount = likesCount;
            this.isLiked = isLiked;
        }
    }

    public interface OnFeedItemClickListener {
        void onCommentsClick(View v, int position);

        void onMoreClick(View v, int position);

        void onProfileClick(View v);
    }
}
