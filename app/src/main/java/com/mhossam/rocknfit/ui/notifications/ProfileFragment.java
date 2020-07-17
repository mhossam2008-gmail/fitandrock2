package com.mhossam.rocknfit.ui.notifications;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.android.material.navigation.NavigationView;
import com.mhossam.rocknfit.API.APIClient;
import com.mhossam.rocknfit.API.APIInterface;
import com.mhossam.rocknfit.R;
import com.mhossam.rocknfit.Utils.CircleTransformation;
import com.mhossam.rocknfit.Utils.LinearLayoutManagerWrapper;
import com.mhossam.rocknfit.adapter.FeedAdapter;
import com.mhossam.rocknfit.adapter.FeedItemAnimator;
import com.mhossam.rocknfit.database.AppDatabase;
import com.mhossam.rocknfit.model.LoggedInUser;
import com.mhossam.rocknfit.model.Post;
import com.mhossam.rocknfit.ui.activity.CommentsActivity;
import com.mhossam.rocknfit.ui.activity.NewsFeedActivity;
import com.mhossam.rocknfit.view.FeedContextMenu;
import com.mhossam.rocknfit.view.FeedContextMenuManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment  implements FeedAdapter.OnFeedItemClickListener,
        FeedContextMenu.OnFeedContextMenuItemClickListener {

    @BindView(R.id.photo)
    CircleImageView profilePhoto;
    @BindView(R.id.banner)
    ImageView banner;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.rvFeed)
    RecyclerView rvFeed;

    @BindView(R.id.btn_ham_menu)
    ImageButton btnHamMenu;


    private int currentPage = 0;
    private boolean loading = false;
    private FeedAdapter feedAdapter;
    private boolean pendingIntroAnimation;
    private APIInterface apiInterface;
    private LoggedInUser currentUser;


    @RequiresApi(api = Build.VERSION_CODES.M)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, root);

        btnHamMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DrawerLayout navDrawer = getActivity().findViewById(R.id.drawerLayout);
                // If the navigation drawer is not open then open it, if its already open then close it.
                if(!navDrawer.isDrawerOpen(GravityCompat.START))
                    navDrawer.openDrawer(GravityCompat.START);
                else navDrawer.closeDrawer(GravityCompat.END);
            }
        });


        AppDatabase db = Room.databaseBuilder(getContext(),
                AppDatabase.class, "rockAndFit").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        currentUser  = db.loggedInUserDao().getLoggedInUser();


        if(currentUser != null){
            String origUserProfilePhoto = "https://www.fitandrock.com/ProfilePictures/Org"+currentUser.getAccountImage();
            String coverPhoto = "https://www.fitandrock.com/Uploads/"+currentUser.getAccountContainer()+"/CoverPhotos/"+currentUser.getAccountCover();
            Picasso.get()
                    .load(origUserProfilePhoto)
                    .placeholder(R.drawable.img_circle_placeholder)
                    .fit()
                    .transform(new CircleTransformation())
                    .into(profilePhoto);

            Picasso.get()
                    .load(coverPhoto)
                    .fit()
                    .into(banner);
        }
        apiInterface = APIClient.getClient().create(APIInterface.class);
        name.setText(currentUser.getAccountFirstName()+" "+currentUser.getAccountLastName());
        setupFeed();
        return root;
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

    protected HashMap<String, String> prepareRequestMap(int page) {
        HashMap<String, String> result = ((NewsFeedActivity)getActivity()).prepareRequestMap();
        result.put("Action", "GetPosts");
        result.put("AccountID", currentUser.getAccountID());
        result.put("MyAccount", currentUser.getAccountID());
        result.put("Index", page+"");
        result.put("Size", "10");
        result.put("Type", "0");
        return result;
    }

}