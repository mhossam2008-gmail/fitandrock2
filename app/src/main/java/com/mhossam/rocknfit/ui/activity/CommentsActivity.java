package com.mhossam.rocknfit.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.mhossam.rocknfit.R;
import com.mhossam.rocknfit.Utils.Utils;
import com.mhossam.rocknfit.adapter.CommentItemAnimator;
import com.mhossam.rocknfit.adapter.CommentsAdapter;
import com.mhossam.rocknfit.database.AppDatabase;
import com.mhossam.rocknfit.model.AccountInfo;
import com.mhossam.rocknfit.model.LoggedInUser;
import com.mhossam.rocknfit.model.PostComment;
import com.mhossam.rocknfit.view.BaseDrawerActivity;
import com.mhossam.rocknfit.view.SendCommentButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by froger_mcs on 11.11.14.
 */
public class CommentsActivity extends BaseDrawerActivity implements SendCommentButton.OnSendClickListener {
    public static final String ARG_DRAWING_START_LOCATION = "arg_drawing_start_location";

    @BindView(R.id.contentRoot)
    LinearLayout contentRoot;
    @BindView(R.id.rvComments)
    RecyclerView rvComments;
    @BindView(R.id.llAddComment)
    LinearLayout llAddComment;
    @BindView(R.id.etComment)
    EditText etComment;
    @BindView(R.id.btnSendComment)
    SendCommentButton btnSendComment;

    private CommentsAdapter commentsAdapter;
    private int drawingStartLocation;
    private ArrayList<PostComment> postComments;
    private LoggedInUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        commentsAdapter = new CommentsAdapter(this);
        AppDatabase db = Room.databaseBuilder(CommentsActivity.this,
                AppDatabase.class, "rockAndFit").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        currentUser  = db.loggedInUserDao().getLoggedInUser();
        startLoadingComments();
        drawingStartLocation = getIntent().getIntExtra(ARG_DRAWING_START_LOCATION, 0);
        if (savedInstanceState == null) {
            contentRoot.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    contentRoot.getViewTreeObserver().removeOnPreDrawListener(this);
                    startIntroAnimation();
                    return true;
                }
            });
        }
    }

    private void setupComments() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvComments.setLayoutManager(linearLayoutManager);
//        rvComments.setHasFixedSize(true);
        commentsAdapter.setPostComments(postComments);
        commentsAdapter = new CommentsAdapter(this);
        rvComments.setAdapter(commentsAdapter);
        rvComments.setOverScrollMode(View.OVER_SCROLL_NEVER);
        rvComments.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
//                    commentsAdapter.setAnimationsLocked(true);
                }
            }
        });
        rvComments.setItemAnimator(new CommentItemAnimator());

    }

    private void setupSendCommentButton() {
        btnSendComment.setOnSendClickListener(this);
    }

    private void startIntroAnimation() {
//        ViewCompat.setElevation(getToolbar(), 0);
//        contentRoot.setScaleY(0.1f);
//        contentRoot.setPivotY(drawingStartLocation);
//        llAddComment.setTranslationY(200);

//        contentRoot.animate()
//                .scaleY(1)
//                .setDuration(200)
//                .setInterpolator(new AccelerateInterpolator())
//                .setListener(new AnimatorListenerAdapter() {
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
////                        ViewCompat.setElevation(getToolbar(), Utils.dpToPx(8));
                        animateContent();
//                    }
//                })
//                .start();
    }

    private void animateContent() {
        commentsAdapter.updateItems();
//        llAddComment.animate().translationY(0)
//                .setInterpolator(new DecelerateInterpolator())
//                .setDuration(200)
//                .start();
    }


    @Override
    public void onSendClickListener(View v) {
        if (validateComment()) {
//            commentsAdapter.addItem();
//            commentsAdapter.setAnimationsLocked(false);
//            commentsAdapter.setDelayEnterAnimation(false);
//            rvComments.smoothScrollBy(0, rvComments.getChildAt(0).getHeight() * commentsAdapter.getItemCount());

            Map<String, String> parametersMap = prepareAddCommentRequestMap();
            Call<String> call = apiInterface.addComment(parametersMap);

            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {

                    Log.d("TAG", response.code() + "");

                    String resource = response.body();
                    if (resource != null) {
//                    setupFeed();
//                    fillUserSuggestions(resource);
//                    feedAdapter.updateItems(true,new ArrayList<Post>());
//                    Toast.makeText(CommentsActivity.this, "For Debug", Toast.LENGTH_SHORT).show();
                        System.out.println(resource);
                        etComment.setText(null);
                        btnSendComment.setCurrentState(SendCommentButton.STATE_DONE);
                        startLoadingComments();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    call.cancel();
                }
            });
        }
    }

    private boolean validateComment() {
        if (TextUtils.isEmpty(etComment.getText())) {
            btnSendComment.startAnimation(AnimationUtils.loadAnimation(this, R.anim.shake_error));
            return false;
        }

        return true;
    }
    private boolean startLoadingComments() {
        Map<String, String> parametersMap = prepareRequestMap();
        Call<Map<String, PostComment>> call = apiInterface.getPostComments(parametersMap);

        call.enqueue(new Callback<Map<String, PostComment>>() {
            @Override
            public void onResponse(Call<Map<String, PostComment>> call, Response<Map<String, PostComment>> response) {

                Log.d("TAG", response.code() + "");

                Map<String, PostComment> resource = response.body();
                if (resource != null) {
//                    setupFeed();
//                    fillUserSuggestions(resource);
//                    feedAdapter.updateItems(true,new ArrayList<Post>());
//                    Toast.makeText(CommentsActivity.this, "For Debug", Toast.LENGTH_SHORT).show();
                    postComments = new ArrayList<>(resource.values());
                    setupComments();
                    commentsAdapter.setPostComments(postComments);
                    startIntroAnimation();
                    commentsAdapter.notifyDataSetChanged();

                }
                setupSendCommentButton();

            }

            @Override
            public void onFailure(Call<Map<String, PostComment>> call, Throwable t) {
                call.cancel();
            }
        });
        return true;
    }

    @Override
    public HashMap<String, String> prepareRequestMap() {
        HashMap<String, String> requestMap = super.prepareRequestMap();
        requestMap.put("PostID",getIntent().getStringExtra("PostID"));
        requestMap.put("Action","GetPostComments");
        requestMap.put("AccountID",currentUser.getAccountID());
        requestMap.put("Index","0");
        requestMap.put("Size","20");
        return requestMap;
    }

    public HashMap<String, String> prepareCommentRequestMap(String comment) {
        HashMap<String, String> requestMap = super.prepareRequestMap();
        requestMap.put("PostID",getIntent().getStringExtra("PostID"));
        requestMap.put("Action","GetPostComments");
        requestMap.put("AccountID",currentUser.getAccountID());
        requestMap.put("Index","0");
        requestMap.put("Size","20");
        return requestMap;
    }

    public HashMap<String, String> prepareAddCommentRequestMap() {
        HashMap<String, String> requestMap = super.prepareRequestMap();
        requestMap.put("PostID",getIntent().getStringExtra("PostID"));
        requestMap.put("Action","AddComment");
        requestMap.put("AccountID",currentUser.getAccountID());        requestMap.put("Type","C");
        requestMap.put("Content",etComment.getText().toString());
        return requestMap;
    }
}
