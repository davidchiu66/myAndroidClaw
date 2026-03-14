package com.common.commonutils;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

/**
 * @author:pingni
 * @date: on 2021/8/19
 */
public class AnimaJavaUtils {


    public enum AnimationState {
        STATE_SHOW,
        STATE_HIDDEN
    }

    /**
     * 渐隐渐现动画
     *
     * @param view     需要实现动画的对象
     * @param state    需要实现的状态
     * @param duration 动画实现的时长（ms）
     */
    public static void showAndHiddenAnimation(final View view, AnimationState state, long duration) {
        float start = 0f;
        float end = 0f;
        if (state == AnimationState.STATE_SHOW) {
            end = 1f;
            view.setVisibility(View.VISIBLE);
        } else if (state == AnimationState.STATE_HIDDEN) {
            start = 1f;
            view.setVisibility(View.INVISIBLE);
        }
        AlphaAnimation animation = new AlphaAnimation(start, end);
        animation.setDuration(duration);
        animation.setFillAfter(true);
        animation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.clearAnimation();
            }
        });
        view.setAnimation(animation);
        animation.start();
    }

    /**
     * view淡入淡出动画
     * @param view
     * @param time
     */
    public static void fadeInOut(View view,long time){
        if (view==null)return;
        view.setAlpha(0);
        ViewUtils.showView(view);
        view.animate().alpha(1).setDuration(time).setListener(null);
    }


    /**
     * 从控件的底部移动到控件所在位置
     *
     * @param v
     * @param Duration 动画时间
     */
    public static void bottomMoveToViewLocation(View v, long Duration, float f) {
        ViewUtils.showView(v);
        TranslateAnimation mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                f, Animation.RELATIVE_TO_SELF, 0.0f);
        mShowAction.setDuration(Duration);
        v.setAnimation(mShowAction);
    }
}
