package com.mhossam.rocknfit.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import com.mhossam.rocknfit.R;

import java.util.HashMap;
import java.util.Map;

public class CommentItemAnimator extends DefaultItemAnimator {
    private static final DecelerateInterpolator DECCELERATE_INTERPOLATOR = new DecelerateInterpolator();
    private static final AccelerateInterpolator ACCELERATE_INTERPOLATOR = new AccelerateInterpolator();
    private static final OvershootInterpolator OVERSHOOT_INTERPOLATOR = new OvershootInterpolator(4);

    Map<RecyclerView.ViewHolder, AnimatorSet> heartAnimationsMap = new HashMap<>();

    private void animateHeartButton(final CommentsAdapter.CommentViewHolder holder) {
        AnimatorSet animatorSet = new AnimatorSet();

        ObjectAnimator rotationAnim = ObjectAnimator.ofFloat(holder.btnLike, "rotation", 0f, 360f);
        rotationAnim.setDuration(300);
        rotationAnim.setInterpolator(ACCELERATE_INTERPOLATOR);

        ObjectAnimator bounceAnimX = ObjectAnimator.ofFloat(holder.btnLike, "scaleX", 0.2f, 1f);
        bounceAnimX.setDuration(300);
        bounceAnimX.setInterpolator(OVERSHOOT_INTERPOLATOR);

        ObjectAnimator bounceAnimY = ObjectAnimator.ofFloat(holder.btnLike, "scaleY", 0.2f, 1f);
        bounceAnimY.setDuration(300);
        bounceAnimY.setInterpolator(OVERSHOOT_INTERPOLATOR);
        bounceAnimY.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                holder.btnLike.setImageResource(R.drawable.ic_heart_red);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                heartAnimationsMap.remove(holder);
                dispatchChangeFinishedIfAllAnimationsEnded(holder);
            }
        });

        animatorSet.play(bounceAnimX).with(bounceAnimY).after(rotationAnim);
        animatorSet.start();

        heartAnimationsMap.put(holder, animatorSet);
    }

    //    private void updateLikesCounter(FeedAdapter.CellFeedViewHolder holder, int toValue) {
////        String likesCountTextFrom = holder.tsLikesCounter.getResources().getQuantityString(
////                R.plurals.likes_count, toValue - 1, toValue - 1
////        );
////        holder.tsLikesCounter.setCurrentText(likesCountTextFrom);
////
////        String likesCountTextTo = holder.tsLikesCounter.getResources().getQuantityString(
////                R.plurals.likes_count, toValue, toValue
////        );
//        holder.likesCount.setText(toValue+"");
//    }
    private void dispatchChangeFinishedIfAllAnimationsEnded(CommentsAdapter.CommentViewHolder holder) {
        if (heartAnimationsMap.containsKey(holder)) {
            return;
        }

        dispatchAnimationFinished(holder);
    }

    @Override
    public boolean animateChange(@NonNull RecyclerView.ViewHolder oldHolder,
                                 @NonNull RecyclerView.ViewHolder newHolder,
                                 @NonNull ItemHolderInfo preInfo,
                                 @NonNull ItemHolderInfo postInfo) {
        cancelCurrentAnimationIfExists(newHolder);

        if (preInfo instanceof CommentItemAnimator.CommentHolderInfo) {
            CommentHolderInfo feedItemHolderInfo = (CommentHolderInfo) preInfo;
            CommentsAdapter.CommentViewHolder holder = (CommentsAdapter.CommentViewHolder) newHolder;

            animateHeartButton(holder);
            updateLikesCounter(holder, holder.getPostComment().getLikesCounter());
            if (FeedAdapter.ACTION_LIKE_IMAGE_CLICKED.equals(feedItemHolderInfo.updateAction)) {
//                animatePhotoLike(holder);
            }
        }

        return false;
    }


    private void updateLikesCounter(CommentsAdapter.CommentViewHolder holder, String likesCounter) {
        holder.tvLikeCount.setText(likesCounter);
    }

    private void cancelCurrentAnimationIfExists(RecyclerView.ViewHolder item) {
//        if (likeAnimationsMap.containsKey(item)) {
//            likeAnimationsMap.get(item).cancel();
//        }
        if (heartAnimationsMap.containsKey(item)) {
            heartAnimationsMap.get(item).cancel();
        }
    }

    public static class CommentHolderInfo extends ItemHolderInfo {
        public String updateAction;

        public CommentHolderInfo(String updateAction) {
            this.updateAction = updateAction;
        }
    }
}
