package com.flyingstudio.market.ui.behavior;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import com.flyingstudio.market.R;


/**
 * Created by guopu on 2017/10/20.
 */

public class ToolbarAlphaBehavior extends CoordinatorLayout.Behavior<Toolbar>{
    //顶部距离
    private int mDistanceY = 0;
    //颜色变化速度
    private boolean isToolbar_Dark = false;

    private static final int RED = 18;
    private static final int GREEN = 150;
    private static final int BLUE = 219;

    public ToolbarAlphaBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, Toolbar child, View dependency) {
        return dependency.getId() == R.id.rv_goods;
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, Toolbar child, View directTargetChild, View target, int nestedScrollAxes) {
        return true;
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, final Toolbar child, View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
        final int targetHeight = child.getBottom();
        mDistanceY += dy;
        //toolbar的高度
        mDistanceY = mDistanceY>targetHeight?targetHeight:mDistanceY;
        mDistanceY = mDistanceY<-targetHeight?-targetHeight:mDistanceY;

        if (mDistanceY >= 35&&!isToolbar_Dark) {
            //下拉且Toolbar背景全透明
            ValueAnimator animator = new ValueAnimator().ofInt(60,255);
            animator.setDuration(1000);
            animator.setInterpolator(new AccelerateInterpolator());
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    int value = (int) animation.getAnimatedValue();
                    child.setBackgroundColor(Color.argb(value, RED, GREEN, BLUE));
                }
            });
            animator.start();
            isToolbar_Dark =true;

        }else if (mDistanceY<-35&&isToolbar_Dark){
            //上滑动且Toolbar背景无透明
            ValueAnimator animator = new ValueAnimator().ofInt(255,60);
            animator.setDuration(1000);
            animator.setInterpolator(new AccelerateInterpolator());
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    int value = (int) animation.getAnimatedValue();
                    child.setBackgroundColor(Color.argb(value, RED, GREEN, BLUE));
                }
            });
            animator.start();
            isToolbar_Dark=false;
        }
    }

}
