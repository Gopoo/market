package com.flyingstudio.market.ui.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Interpolator;


/**
 * Created by guopu on 2017/10/21.
 */

public class FloatingButtonBehavior extends FloatingActionButton.Behavior {
    // 动画插值器，可以控制动画的变化率
    private static final Interpolator INTERPOLATOR = new FastOutSlowInInterpolator();

    // 是否正在执行隐藏的动画
    private boolean mIsAnimatingOut = false;

    public FloatingButtonBehavior(Context context, AttributeSet attrs) {
        super();
    }

    @Override
    public boolean onStartNestedScroll(final CoordinatorLayout coordinatorLayout, final FloatingActionButton child,
                                       final View directTargetChild, final View target, final int nestedScrollAxes) {
        // 如果是上下滑动的，则返回true
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL
                || super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
    }

    @Override
    public void onNestedScroll(final CoordinatorLayout coordinatorLayout, final FloatingActionButton child,
                               final View target, final int dxConsumed, final int dyConsumed,
                               final int dxUnconsumed, final int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        if (dyConsumed > 0 && !this.mIsAnimatingOut && child.getVisibility() == View.VISIBLE) {
            // 用户向下滑动时判断是否正在执行隐藏动画，如果不是，而且FAB当前是可见的，则执行隐藏动画隐藏FAB
            animateOut(child);
        } else if (dyConsumed < 0 && child.getVisibility() != View.VISIBLE) {
            // 用户向上滑动时判断FAB是否可见，如果不可见则执行显示动画显示FAB
            animateIn(child);
        }
        Log.w("TAG",dyConsumed+child.getVisibility()+"");
    }

    // Same animation that FloatingActionButton.Behavior uses to hide the FAB when the AppBarLayout exits
    // 执行隐藏动画隐藏FAB
    private void animateOut(final FloatingActionButton button) {

            ViewCompat.animate(button).scaleX(0.0F).scaleY(0.0F).alpha(0.0F).setInterpolator(INTERPOLATOR).withLayer()
                    .setListener(new ViewPropertyAnimatorListener() {
                        public void onAnimationStart(View view) {
                            FloatingButtonBehavior.this.mIsAnimatingOut = true;
                        }

                        public void onAnimationCancel(View view) {
                            FloatingButtonBehavior.this.mIsAnimatingOut = false;
                        }

                        public void onAnimationEnd(View view) {
                            FloatingButtonBehavior.this.mIsAnimatingOut = false;
                            view.setVisibility(View.INVISIBLE);
                        }
                    }).start();

    }

    // Same animation that FloatingActionButton.Behavior uses to show the FAB when the AppBarLayout enters
    // 执行显示动画显示FAB
    private void animateIn(FloatingActionButton button) {
        button.setVisibility(View.VISIBLE);

            ViewCompat.animate(button).scaleX(1.0F).scaleY(1.0F).alpha(1.0F)
                    .setInterpolator(INTERPOLATOR).withLayer().setListener(null)
                    .start();

    }
}
