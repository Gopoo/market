package com.flyingstudio.market.ui.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.ImageView;

import com.flyingstudio.market.R;

/**
 * Created by guopu on 2017/10/25.
 */

public class ImageViewBehavior extends CoordinatorLayout.Behavior<ImageView> {

    // 动画插值器，可以控制动画的变化率
    private static final Interpolator INTERPOLATOR = new FastOutSlowInInterpolator();

    // 是否正在执行隐藏的动画
    private boolean mIsAnimatingOut = false;
    //顶部距离
    private int mDistanceY = 0;


    public ImageViewBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, ImageView child, View dependency) {
        return dependency.getId() == R.id.rv_goods;
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, ImageView child, View directTargetChild, View target, int nestedScrollAxes) {
        return true;
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, final ImageView child, View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
        final int targetHeight = child.getBottom();
        mDistanceY += dy;
        //toolbar的高度
        mDistanceY = mDistanceY>targetHeight?targetHeight:mDistanceY;
        mDistanceY = mDistanceY<-targetHeight?-targetHeight:mDistanceY;

        if (mDistanceY >= 35&&!this.mIsAnimatingOut && child.getVisibility() == View.VISIBLE) {
            animateOut(child);

        }else if (mDistanceY<-35 && child.getVisibility() != View.VISIBLE){
            animateIn(child);
        }
    }
    // 执行隐藏动画隐藏FAB
    private void animateOut(final ImageView view) {

        ViewCompat.animate(view).scaleX(0.0F).scaleY(0.0F).alpha(0.0F).setInterpolator(INTERPOLATOR).withLayer()
                .setListener(new ViewPropertyAnimatorListener() {
                    public void onAnimationStart(View view) {
                        ImageViewBehavior.this.mIsAnimatingOut = true;
                    }

                    public void onAnimationCancel(View view) {
                        ImageViewBehavior.this.mIsAnimatingOut = false;
                    }

                    public void onAnimationEnd(View view) {
                        ImageViewBehavior.this.mIsAnimatingOut = false;
                        view.setVisibility(View.INVISIBLE);
                    }
                }).start();

    }

    // Same animation that FloatingActionButton.Behavior uses to show the FAB when the AppBarLayout enters
    // 执行显示动画显示FAB
    private void animateIn(ImageView view) {
        view.setVisibility(View.VISIBLE);

        ViewCompat.animate(view).scaleX(1.0F).scaleY(1.0F).alpha(1.0F)
                .setInterpolator(INTERPOLATOR).withLayer().setListener(null)
                .start();

    }
}
