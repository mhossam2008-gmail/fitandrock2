package com.mhossam.rocknfit.adapter;

import android.app.Activity;
import android.content.Context;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.mhossam.rocknfit.API.APIClient;
import com.mhossam.rocknfit.API.APIInterface;
import com.mhossam.rocknfit.ProfileActivity;
import com.mhossam.rocknfit.R;
import com.mhossam.rocknfit.Utils.CircleTransformation;
import com.mhossam.rocknfit.database.AppDatabase;
import com.mhossam.rocknfit.model.LoggedInUser;
import com.mhossam.rocknfit.model.Post;
import com.mhossam.rocknfit.view.LoadingFeedItemView;
import com.mhossam.rocknfit.view.SquaredFrameLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
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
    private final LoggedInUser currentUser;

    private Context context;
    private OnFeedItemClickListener onFeedItemClickListener;

    private boolean showLoadingView = false;

    public FeedAdapter(Context context) {
        this.context = context;
        apiInterface = APIClient.getClient().create(APIInterface.class);
        AppDatabase db = Room.databaseBuilder(context,
                AppDatabase.class, "rockAndFit").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        currentUser = db.loggedInUserDao().getLoggedInUser();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_DEFAULT) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_feed, parent, false);
            CellFeedViewHolder cellFeedViewHolder = new CellFeedViewHolder(view, context);
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
        cellFeedViewHolder.btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = cellFeedViewHolder.getAdapterPosition();
                Post currentFeedItem = feedItems.get(adapterPosition);

                notifyItemChanged(adapterPosition, ACTION_LIKE_BUTTON_CLICKED);

                Map<String, String> parametersMap = prepareRequestMap();
                parametersMap.put("Action", "LikePost");
                parametersMap.put("PostID", currentFeedItem.getPostID());
                parametersMap.put("AccountID", currentUser.getAccountID());
                Call<String> call = apiInterface.likePost(parametersMap);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.d("TAG", response.code() + "");
                        String resource = response.body();
                        if (resource == null || resource.contains("null")) {
                            Toast.makeText(context, "Liked", Toast.LENGTH_SHORT).show();
                            currentFeedItem.setLikesCounter(currentFeedItem.getLikesCounter() + 1);
                            currentFeedItem.setLikeID("liked");
                            notifyItemChanged(adapterPosition);
                        } else {
                            Toast.makeText(context, resource, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(context, "Internal Server Error", Toast.LENGTH_SHORT).show();
                        call.cancel();
                    }
                });
            }
        });

        cellFeedViewHolder.btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = cellFeedViewHolder.getAdapterPosition();
                Post currentFeedItem = feedItems.get(adapterPosition);
                showSharePopup(currentFeedItem);
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

    public void updateItems(boolean animated, List<Post> updatedFeedItems) {
        updateItems(animated, updatedFeedItems, false);
    }


    public void updateItems(boolean animated, List<Post> updatedFeedItems, boolean keepOldItems) {
        if (!keepOldItems) {
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

    public Post getPostAtPosition(int position) {
        return feedItems.get(position);
    }

    public static class CellFeedViewHolder extends RecyclerView.ViewHolder {
        private final Context context;
        @BindView(R.id.ivFeedCenter)
        ImageView ivFeedCenter;
        @BindView(R.id.ivFeedCenterContainer)
        CardView ivFeedCenterContainer;
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
        @BindView(R.id.btnShare)
        ImageButton btnShare;

        @BindView(R.id.ivLike)
        ImageView ivLike;
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
        @BindView(R.id.tvCommentCount)
        TextView commentsCount;
        @BindView(R.id.tvLikeCount)
        TextView likesCount;
        @BindView(R.id.tvShareCount)
        TextView shareCount;

        @BindView(R.id.tvShareProfileName)
        TextView origProfileName;
        @BindView(R.id.ivShareFeedCenter)
        ImageView origFeedCenter;
        @BindView(R.id.origPlayIcon)
        ImageView origPlayIcon;
        @BindView(R.id.ivShareUserProfile)
        ImageView origProfilePhoto;
        @BindView(R.id.vshareImageRoot)
        SquaredFrameLayout origRoot;
        @BindView(R.id.origShareConstLayout)
        ConstraintLayout origPostConstLayout;
        @BindView(R.id.sharePostRoot)
        LinearLayout origPostRoot;
        @BindView(R.id.tvOrigPost)
        TextView origPost;

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
            if (!feedItem.getOriginalPostID().equals("0") && feedItem.getPostType().equalsIgnoreCase("h")) {

                vImageRoot.setVisibility(View.GONE);

                origPostRoot.setVisibility(View.VISIBLE);

                String origUserProfilePhoto = "https://www.fitandrock.com/" + feedItem.getOrgPicturePath();
                String origName = feedItem.getOrgName();
                origProfileName.setText(origName);
                Picasso.get()
                        .load(origUserProfilePhoto)
                        .placeholder(R.drawable.img_circle_placeholder)
                        .centerCrop()
                        .resize(avatarSize, avatarSize)
                        .transform(new CircleTransformation())
                        .into(origProfilePhoto);
                origPost.setText(feedItem.getPostContent());
                postTextView.setVisibility(View.GONE);
                origPlayIcon.setVisibility(View.GONE);
                if (feedItem.getPostMedia() == null || feedItem.getPostMedia().equals("")) {
                    origRoot.setVisibility(View.GONE);
                } else if (feedItem.getPostMedia() instanceof String && ((String) feedItem.getPostMedia()).contains("youtube")) {
                    String postPhoto = ((String) feedItem.getPostMedia()).replace("www.youtube.com/watch?v=", "img.youtube.com/vi/");
                    postPhoto += "/mqdefault.jpg";

                    Picasso.get()
                            .load(postPhoto)
                            .placeholder(R.drawable.img_circle_placeholder)
                            .fit()
                            .into(origFeedCenter);
                    origPlayIcon.setVisibility(View.VISIBLE);
                    origFeedCenter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse((String) feedItem.getPostMedia()));
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.setPackage("com.google.android.youtube");
                            context.startActivity(intent);
                        }
                    });
                    origPlayIcon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse((String) feedItem.getPostMedia()));
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.setPackage("com.google.android.youtube");
                            context.startActivity(intent);
                        }
                    });
                } else {
                    String postPhoto = "https://www.fitandrock.com" + feedItem.getFullImagePath();

                    Picasso.get()
                            .load(postPhoto)
                            .placeholder(R.drawable.img_circle_placeholder)
                            .fit()
                            .into(origFeedCenter);
                }

            } else {
                origPostRoot.setVisibility(View.GONE);
                vImageRoot.setVisibility(View.VISIBLE);
                playIcon.setVisibility(View.GONE);
                if (feedItem.getPostMedia() == null || feedItem.getPostMedia().equals("")) {
                    vImageRoot.setVisibility(View.GONE);
                } else if (feedItem.getPostMedia() instanceof String && ((String) feedItem.getPostMedia()).contains("youtube")) {
                    String postPhoto = ((String) feedItem.getPostMedia()).replace("www.youtube.com/watch?v=", "img.youtube.com/vi/");
                    postPhoto += "/mqdefault.jpg";

                    Picasso.get()
                            .load(postPhoto)
                            .placeholder(R.drawable.img_circle_placeholder)
                            .fit()
                            .into(ivFeedCenter);
                    playIcon.setVisibility(View.VISIBLE);
                    ivFeedCenter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse((String) feedItem.getPostMedia()));
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.setPackage("com.google.android.youtube");
                            context.startActivity(intent);
                        }
                    });
                    playIcon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse((String) feedItem.getPostMedia()));
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.setPackage("com.google.android.youtube");
                            context.startActivity(intent);
                        }
                    });
                } else {
                    String postPhoto = "https://www.fitandrock.com" + feedItem.getFullImagePath();

                    Picasso.get()
                            .load(postPhoto)
                            .placeholder(R.drawable.img_circle_placeholder)
                            .fit()
                            .into(ivFeedCenter);
                }
            }
            int adapterPosition = getAdapterPosition();
            String profilePhoto = "https://www.fitandrock.com" + feedItem.getProfilePicturePath();

            Picasso.get()
                    .load(profilePhoto)
                    .placeholder(R.drawable.img_circle_placeholder)
                    .centerCrop()
                    .resize(avatarSize, avatarSize)
                    .transform(new CircleTransformation())
                    .into(ivUserProfile);
            ivUserProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, ProfileActivity.class);
                    i.putExtra("AccountID", feedItem.getAccountID());
                    context.startActivity(i);
                }
            });
            commentsCount.setText(feedItem.getCommentsCounter());
            likesCount.setText(feedItem.getLikesCounter() + "");
            shareCount.setText(feedItem.getShareCounter());
            tvProfileName.setText(feedItem.getAccountFullName());
            postTextView.setText(feedItem.getPostContent());
            btnLike.setImageResource(!(feedItem.getLikeID() == null ||
                    feedItem.getLikeID().isEmpty()) ? R.drawable.ic_heart_red : R.drawable.ic_heart_outline_grey);
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


    private void showSharePopup(Post sharedPost) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.post_share_popup_layout, null);


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
        CircleImageView ivUserProfilePost = layout.findViewById(R.id.ivUserProfilePost);
        String origUserProfilePhoto = "https://www.fitandrock.com/ProfilePictures/Org" + currentUser.getAccountImage();
        Picasso.get()
                .load(origUserProfilePhoto)
                .placeholder(R.drawable.img_circle_placeholder)
                .fit()
                .into(ivUserProfilePost);

        ImageView sharedPostImage = layout.findViewById(R.id.ivShareFeedCenter);
        CircleImageView sharedPostOwnerImage = layout.findViewById(R.id.ivShareUserProfile);
        TextView sharedPostName = layout.findViewById(R.id.tvShareProfileName);
        sharedPostName.setText(sharedPost.getAccountFullName());
        TextView postContent = layout.findViewById(R.id.tvOrigPost);
        postContent.setText(sharedPost.getPostContent());
        String shareUserProfilePhoto = "https://www.fitandrock.com/ProfilePictures/Org" + sharedPost.getAccountImage();
        Picasso.get()
                .load(origUserProfilePhoto)
                .placeholder(R.drawable.img_circle_placeholder)
                .fit()
                .into(sharedPostOwnerImage);
        SquaredFrameLayout squaredFrameLayout = layout.findViewById(R.id.vshareImageRoot);
        squaredFrameLayout.setVisibility(View.GONE);
        if(sharedPost.getFullImagePath()!=null&& !sharedPost.getFullImagePath().trim().endsWith("/")){
//            SquaredFrameLayout squaredFrameLayout = layout.findViewById(R.id.vshareImageRoot);
            squaredFrameLayout.setVisibility(View.VISIBLE);

            String postPhoto = "https://www.fitandrock.com" + sharedPost.getFullImagePath();

            Picasso.get()
                    .load(postPhoto)
                    .placeholder(R.drawable.img_circle_placeholder)
                    .fit()
                    .centerCrop()
                    .into(sharedPostImage);

        }
        // Getting a reference to Close button, and close the popup when clicked.

        ImageButton close = layout.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeSortPopUp.dismiss();
            }
        });
        Button post = (Button) layout.findViewById(R.id.post);
        EditText etPostText = layout.findViewById(R.id.etPostText);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> parametersMap = prepareRequestMap();
                parametersMap.put("Action", "SharePost");
                parametersMap.put("AccountID", currentUser.getAccountID());
                parametersMap.put("PostID", sharedPost.getPostID());
                parametersMap.put("Content", etPostText.getText().toString());
                parametersMap.put("Type", "S");
                Call<String> call = apiInterface.sharePost(parametersMap);
                call.enqueue(new Callback<String>() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.d("TAG", response.code() + "");
                        String resource = response.body();
                        if (resource == null || resource.contains("null")) {
                            Toast.makeText(context, "Internal Server Error", Toast.LENGTH_SHORT).show();
                        } else {
                            changeSortPopUp.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(context, "Internal Server Error", Toast.LENGTH_SHORT).show();
                        call.cancel();
                    }
                });

            }
        });
    }


}
