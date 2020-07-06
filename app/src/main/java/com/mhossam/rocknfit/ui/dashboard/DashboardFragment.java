package com.mhossam.rocknfit.ui.dashboard;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.mhossam.rocknfit.API.APIClient;
import com.mhossam.rocknfit.API.APIInterface;
import com.mhossam.rocknfit.NewsFeedActivity;
import com.mhossam.rocknfit.R;
import com.mhossam.rocknfit.Utils.Utils;
import com.mhossam.rocknfit.adapter.FeedAdapter;
import com.mhossam.rocknfit.adapter.FeedItemAnimator;
import com.mhossam.rocknfit.model.Post;
import com.mhossam.rocknfit.view.FeedContextMenu;
import com.mhossam.rocknfit.view.FeedContextMenuManager;
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

public class DashboardFragment extends Fragment  implements FeedAdapter.OnFeedItemClickListener,
        FeedContextMenu.OnFeedContextMenuItemClickListener {

    public static final String ACTION_SHOW_LOADING_ITEM = "action_show_loading_item";

    private static final int ANIM_DURATION_TOOLBAR = 300;
    private static final int ANIM_DURATION_FAB = 400;

    private FeedAdapter feedAdapter;

    private boolean pendingIntroAnimation;

    @BindView(R.id.rvFeed)
    RecyclerView rvFeed;

    @BindView(R.id.content)
    CoordinatorLayout clContent;

    @BindView(R.id.ivProfileImage)
    ImageView ivProfileImage;

    private DashboardViewModel dashboardViewModel;
    private APIInterface apiInterface;
    private PopupWindow changeSortPopUp;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        ButterKnife.bind(this , root);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
            }
        });
        Picasso.get()
                .load("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcScj1w0UCdIr4kUblujW7B7IdaZoRmdHyZP5A&usqp=CAU")
                .placeholder(R.drawable.img_circle_placeholder)
                .fit()
//                        .centerCrop()
//                        .resize(avatarSize,avatarSize)
//                        .transform(new SquareTransformation())
                .into(ivProfileImage);

        ivProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSortPopup((NewsFeedActivity)getActivity());
            }
        });

        ((NewsFeedActivity)getActivity()).setFragment(this);
        setupFeed();

        return root;
    }


    private void setupFeed() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(((NewsFeedActivity)getActivity())) {
            @Override
            protected int getExtraLayoutSpace(RecyclerView.State state) {
                return 300;
            }
        };
        rvFeed.setLayoutManager(linearLayoutManager);

        feedAdapter = new FeedAdapter(((NewsFeedActivity)getActivity()));
        feedAdapter.setOnFeedItemClickListener(this);
        rvFeed.setAdapter(feedAdapter);
        rvFeed.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                FeedContextMenuManager.getInstance().onScrolled(recyclerView, dx, dy);
            }
        });
        rvFeed.setItemAnimator(new FeedItemAnimator());
        startLoading();
    }

    public void onNewIntent(Intent intent) {
        if (ACTION_SHOW_LOADING_ITEM.equals(intent.getAction())) {
            showFeedLoadingItemDelayed();
        }
    }


    public void showFeedLoadingItemDelayed() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                rvFeed.smoothScrollToPosition(0);
//                feedAdapter.showLoadingView();
            }
        }, 500);
    }

    public boolean startLoading() {
        pendingIntroAnimation = false;
        Map<String, String> parametersMap = prepareRequestMap();
        Call<Map<String, Post>> call = apiInterface.getPosts(parametersMap);

        call.enqueue(new Callback<Map<String, Post>>() {
            @Override
            public void onResponse(Call<Map<String, Post>> call, Response<Map<String, Post>> response) {

                Log.d("TAG", response.code() + "");

                Map<String, Post> resource = response.body();
                if (resource != null) {
//                    setupFeed();
                    startIntroAnimation(resource);
//                    feedAdapter.updateItems(true,new ArrayList<Post>());

                }
            }

            @Override
            public void onFailure(Call<Map<String, Post>> call, Throwable t) {
                call.cancel();
            }
        });
        return true;
    }

    private void startIntroAnimation(Map<String, Post> resource) {
        int actionbarSize = Utils.dpToPx(56);
//        ((NewsFeedActivity)getActivity()).getToolbar().setTranslationY(-actionbarSize);
//        ((NewsFeedActivity)getActivity()).getIvLogo().setTranslationY(-actionbarSize);
//        ((NewsFeedActivity)getActivity()).getInboxMenuItem().getActionView().setTranslationY(-actionbarSize);

//        ((NewsFeedActivity)getActivity()).getToolbar().animate()
//                .translationY(0)
//                .setDuration(ANIM_DURATION_TOOLBAR)
//                .setStartDelay(300);
//        ((NewsFeedActivity)getActivity()).getIvLogo().animate()
//                .translationY(0)
//                .setDuration(ANIM_DURATION_TOOLBAR)
//                .setStartDelay(400);
//        ((NewsFeedActivity)getActivity()).getInboxMenuItem().getActionView().animate()
//                .translationY(0)
//                .setDuration(ANIM_DURATION_TOOLBAR)
//                .setStartDelay(500)
//                .setListener(new AnimatorListenerAdapter() {
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
                        startContentAnimation(new ArrayList<Post>(resource.values()));
//                    }
//                })
//                .start();
    }

    private void startContentAnimation(List<Post> values) {
//        fabCreate.animate()
//                .translationY(0)
//                .setInterpolator(new OvershootInterpolator(1.f))
//                .setStartDelay(300)
//                .setDuration(ANIM_DURATION_FAB)
//                .start();
        feedAdapter.updateItems(true, values);
    }

    @Override
    public void onCommentsClick(View v, int position) {
//        final Intent intent = new Intent(this, CommentsActivity.class);
        int[] startingLocation = new int[2];
        v.getLocationOnScreen(startingLocation);
//        intent.putExtra(CommentsActivity.ARG_DRAWING_START_LOCATION, startingLocation[1]);
//        startActivity(intent);
//        overridePendingTransition(0, 0);
    }

    @Override
    public void onMoreClick(View v, int itemPosition) {
        FeedContextMenuManager.getInstance().toggleContextMenuFromView(v, itemPosition, this);
    }

    @Override
    public void onProfileClick(View v) {
        int[] startingLocation = new int[2];
        v.getLocationOnScreen(startingLocation);
        startingLocation[0] += v.getWidth() / 2;
//        UserProfileActivity.startUserProfileFromLocation(startingLocation, this);
        ((NewsFeedActivity)getActivity()).overridePendingTransition(0, 0);
    }

    @Override
    public void onReportClick(int feedItem) {
        FeedContextMenuManager.getInstance().hideContextMenu();
    }

    @Override
    public void onSharePhotoClick(int feedItem) {
        FeedContextMenuManager.getInstance().hideContextMenu();
    }

    @Override
    public void onCopyShareUrlClick(int feedItem) {
        FeedContextMenuManager.getInstance().hideContextMenu();
    }

    @Override
    public void onCancelClick(int feedItem) {
        FeedContextMenuManager.getInstance().hideContextMenu();
    }

//    @OnClick(R.id.btnCreate)
//    public void onTakePhotoClick() {
//        int[] startingLocation = new int[2];
////        fabCreate.getLocationOnScreen(startingLocation);
////        startingLocation[0] += fabCreate.getWidth() / 2;
////        TakePhotoActivity.startCameraFromLocation(startingLocation, this);
//        ((NewsFeedActivity)getActivity()).overridePendingTransition(0, 0);
//    }

    public void showLikedSnackbar() {
        Snackbar.make(clContent, "Liked!", Snackbar.LENGTH_SHORT).show();
    }

    protected HashMap<String, String> prepareRequestMap() {
        HashMap<String, String> result = ((NewsFeedActivity)getActivity()).prepareRequestMap();
        result.put("Action", "GetPosts");
        result.put("AccountID", "0");
        result.put("MyAccount", "95");
        result.put("Index", "0");
        result.put("Size", "10");
        result.put("Type", "0");
        return result;
    }

    private void showSortPopup(final Activity context)
    {
        // Inflate the popup_layout.xml
//        LinearLayout viewGroup = (LinearLayout) context.findViewById(R.id.llSortChangePopup);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.sort_popup_layout, null);

        // Creating the PopupWindow
        changeSortPopUp = new PopupWindow(context);
        changeSortPopUp.setContentView(layout);
        changeSortPopUp.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        changeSortPopUp.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        changeSortPopUp.setFocusable(true);

        // Some offset to align the popup a bit to the left, and a bit down, relative to button's position.
        int OFFSET_X = -20;
        int OFFSET_Y = 95;

        // Clear the default translucent background
//        changeSortPopUp.setBackgroundDrawable(new BitmapDrawable());

        // Displaying the popup at the specified location, + offsets.
        changeSortPopUp.showAtLocation(layout, Gravity.CENTER, 0, 0);


        // Getting a reference to Close button, and close the popup when clicked.
        Button close = (Button) layout.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                changeSortPopUp.dismiss();
            }
        });

    }
}