package com.mhossam.rocknfit.ui.dashboard;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.snackbar.Snackbar;
import com.mhossam.rocknfit.API.APIClient;
import com.mhossam.rocknfit.API.APIInterface;
import com.mhossam.rocknfit.Utils.LinearLayoutManagerWrapper;
import com.mhossam.rocknfit.database.AppDatabase;
import com.mhossam.rocknfit.model.LoggedInUser;
import com.mhossam.rocknfit.ui.activity.CommentsActivity;
import com.mhossam.rocknfit.ui.activity.NewsFeedActivity;
import com.mhossam.rocknfit.R;
import com.mhossam.rocknfit.adapter.FeedAdapter;
import com.mhossam.rocknfit.adapter.FeedItemAnimator;
import com.mhossam.rocknfit.adapter.RecommendedUsersAdapter;
import com.mhossam.rocknfit.model.AccountInfo;
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

//    @BindView(R.id.srLayout)
//    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.rvRecommendedUsers)
    RecyclerView rvRecommendedUsers;

    @BindView(R.id.content)
    CoordinatorLayout clContent;

    @BindView(R.id.ivProfileImage)
    ImageView ivProfileImage;

//    private DashboardViewModel dashboardViewModel;
    private APIInterface apiInterface;
    private PopupWindow changeSortPopUp;
    private RecommendedUsersAdapter recommendedUsersAdapter;
    private int currentPage = 0;
    private boolean loading = false;
    private LoggedInUser currentUser;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        ButterKnife.bind(this , root);
        AppDatabase db = Room.databaseBuilder(getContext(),
                AppDatabase.class, "rockAndFit").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        currentUser  = db.loggedInUserDao().getLoggedInUser();
//        swipeRefreshLayout.setEnabled(false);
        apiInterface = APIClient.getClient().create(APIInterface.class);

        Picasso.get()
                .load("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcScj1w0UCdIr4kUblujW7B7IdaZoRmdHyZP5A&usqp=CAU")
                .placeholder(R.drawable.img_circle_placeholder)
                .fit()
                .into(ivProfileImage);

        ivProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSortPopup((NewsFeedActivity)getActivity());
            }
        });

        ((NewsFeedActivity)getActivity()).setFragment(this);
        setupFollowSuggesstions();
        setupFeed();

        return root;
    }

    private void setupFollowSuggesstions() {

        rvRecommendedUsers.setLayoutManager(new LinearLayoutManagerWrapper((NewsFeedActivity)getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recommendedUsersAdapter = new RecommendedUsersAdapter((NewsFeedActivity)getActivity());
        rvRecommendedUsers.setAdapter(recommendedUsersAdapter);

        startLoadingRecommendedUser();
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setupFeed() {
        LinearLayoutManagerWrapper linearLayoutManager = new LinearLayoutManagerWrapper(((NewsFeedActivity)getActivity())) {
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
                if(dy > 0) //check for scroll down
                {
                    int visibleItemCount = linearLayoutManager.getChildCount();
                    int totalItemCount = linearLayoutManager.getItemCount();
                    int pastVisiblesItems = linearLayoutManager.findFirstVisibleItemPosition();

                    if (!loading)
                    {
                        if ( (visibleItemCount + pastVisiblesItems) >= totalItemCount)
                        {
                            startLoading(false);
                        }
                    }
                }
                FeedContextMenuManager.getInstance().onScrolled(recyclerView, dx, dy);
            }
        });
        rvFeed.setItemAnimator(new FeedItemAnimator());
        startLoading(true);
    }

    private boolean startLoadingRecommendedUser() {
        Map<String, String> parametersMap = prepareUserSuggesstionsRequestMap();
        Call<Map<String, AccountInfo>> call = apiInterface.getFollowSuggestions(parametersMap);

        call.enqueue(new Callback<Map<String, AccountInfo>>() {
            @Override
            public void onResponse(Call<Map<String, AccountInfo>> call, Response<Map<String, AccountInfo>> response) {

                Log.d("TAG", response.code() + "");

                Map<String, AccountInfo> resource = response.body();
                if (resource != null) {
//                    setupFeed();
                    fillUserSuggestions(resource);
//                    feedAdapter.updateItems(true,new ArrayList<Post>());

                }
            }

            @Override
            public void onFailure(Call<Map<String, AccountInfo>> call, Throwable t) {
                call.cancel();
            }
        });
        return true;
    }

    private void fillUserSuggestions(Map<String, AccountInfo> resource) {
        List<AccountInfo> result = new ArrayList(resource.values());
        recommendedUsersAdapter.updateItems(true,result);
        recommendedUsersAdapter.notifyDataSetChanged();

//        rvFeed.setOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                FeedContextMenuManager.getInstance().onScrolled(recyclerView, dx, dy);
//            }
//        });
        rvRecommendedUsers.setItemAnimator(new FeedItemAnimator());
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

    public boolean startLoading(boolean animate) {
        loading = true;
        pendingIntroAnimation = false;
        Map<String, String> parametersMap = prepareRequestMap(currentPage);
        Call<Map<String, Post>> call = apiInterface.getPosts(parametersMap);

        call.enqueue(new Callback<Map<String, Post>>() {
            @Override
            public void onResponse(Call<Map<String, Post>> call, Response<Map<String, Post>> response) {

                Log.d("TAG", response.code() + "");

                Map<String, Post> resource = response.body();
                if (resource != null) {
//                    setupFeed();
                    startIntroAnimation(resource,animate);
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

    private void startIntroAnimation(Map<String, Post> resource, boolean animate) {
//        int actionbarSize = Utils.dpToPx(56);
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
                        startContentAnimation(new ArrayList<Post>(resource.values()),animate);
//                    }
//                })
//                .start();
    }

    private void startContentAnimation(List<Post> values, boolean animate) {
//        fabCreate.animate()
//                .translationY(0)
//                .setInterpolator(new OvershootInterpolator(1.f))
//                .setStartDelay(300)
//                .setDuration(ANIM_DURATION_FAB)
//                .start();
        if(currentPage==0) {
            feedAdapter.updateItems(animate, values);
        }else{
            feedAdapter.updateItems(animate, values, true);
        }
        currentPage++;
        loading = false;
    }

    @Override
    public void onCommentsClick(View v, int position) {
        final Intent intent = new Intent(getActivity(), CommentsActivity.class);
        int[] startingLocation = new int[2];
        Post clickedPost = feedAdapter.getPostAtPosition(position);
        intent.putExtra("PostID",clickedPost.getPostID());
        v.getLocationOnScreen(startingLocation);
        intent.putExtra(CommentsActivity.ARG_DRAWING_START_LOCATION, startingLocation[1]);
        startActivity(intent);
        getActivity().overridePendingTransition(0, 0);
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

    protected HashMap<String, String> prepareRequestMap(int page) {
        HashMap<String, String> result = ((NewsFeedActivity)getActivity()).prepareRequestMap();
        result.put("Action", "GetPosts");
        result.put("AccountID", "0");
        result.put("MyAccount", currentUser.getAccountID());
        result.put("Index", page+"");
        result.put("Size", "10");
        result.put("Type", "0");
        return result;
    }

    protected HashMap<String, String> prepareRequestMap() {
        HashMap<String, String> result = ((NewsFeedActivity)getActivity()).prepareRequestMap();
        result.put("Action", "GetPosts");
        result.put("AccountID", "0");
        result.put("AccountID",currentUser.getAccountID());
        result.put("Index", "0");
        result.put("Size", "10");
        result.put("Type", "0");
        return result;
    }

    protected HashMap<String, String> prepareUserSuggesstionsRequestMap() {
        HashMap<String, String> result = ((NewsFeedActivity)getActivity()).prepareRequestMap();
//        Action:FollowSuggestions
//        ApiUser:Test
//        ApiPass:Test
//        Limit:100
//        AccountID:95
        result.put("Action", "FollowSuggestions");
        result.put("AccountID",currentUser.getAccountID());
        result.put("Limit", "5");
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
        changeSortPopUp.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        changeSortPopUp.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        changeSortPopUp.setFocusable(true);

        // Some offset to align the popup a bit to the left, and a bit down, relative to button's position.
        int OFFSET_X = -20;
        int OFFSET_Y = 95;

        // Clear the default translucent background
        changeSortPopUp.setBackgroundDrawable(new BitmapDrawable());
//        changeSortPopUp.setBackgroundDrawable(R.drawable.rounded_edit_text);

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