package com.flyingstudio.fscore.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ContentFrameLayout;
import android.view.MotionEvent;
import android.view.WindowManager;

import com.flyingstudio.fscore.R;
import com.flyingstudio.fscore.fragment.BaseFragment;
import com.flyingstudio.fscore.fragment.FlyingFragment;

import me.yokeyword.fragmentation.ExtraTransaction;
import me.yokeyword.fragmentation.ISupportActivity;
import me.yokeyword.fragmentation.SupportActivityDelegate;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;
import qiu.niorgai.StatusBarCompat;


/**
 * Created by guopu on 2017/10/11.
 */

public abstract class FlyingActivity extends AppCompatActivity implements ISupportActivity {
    private final SupportActivityDelegate DELEGATE = new SupportActivityDelegate(this);
    public abstract FlyingFragment setRootFragment();
    public abstract void init();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DELEGATE.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        StatusBarCompat.setStatusBarColor(this,0xFF1296db);
        initContainer(savedInstanceState);
        init();
    }
    private void initContainer(@Nullable Bundle savedInstanceState){
        ContentFrameLayout frameLayout = new ContentFrameLayout(this);
        frameLayout.setId(R.id.content_framelayout);
        setContentView(frameLayout);
        if (savedInstanceState==null){
            DELEGATE.loadRootFragment(R.id.content_framelayout,setRootFragment());
        }
    }
    @Override
    public SupportActivityDelegate getSupportDelegate() {
        return DELEGATE;
    }

    /**
     * Perform some extra transactions.
     * 额外的事务：自定义Tag，添加SharedElement动画，操作非回退栈Fragment
     */
    @Override
    public ExtraTransaction extraTransaction() {
        return DELEGATE.extraTransaction();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        DELEGATE.onPostCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        DELEGATE.onDestroy();
        super.onDestroy();
    }

    /**
     * Note： return mDelegate.dispatchTouchEvent(ev) || super.dispatchTouchEvent(ev);
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return DELEGATE.dispatchTouchEvent(ev) || super.dispatchTouchEvent(ev);
    }

    /**
     * 不建议复写该方法,请使用 {@link #onBackPressedSupport} 代替
     */
    @Override
      public void onBackPressed() {
        DELEGATE.onBackPressed();
    }

    /**
     * 该方法回调时机为,Activity回退栈内Fragment的数量 小于等于1 时,默认finish Activity
     * 请尽量复写该方法,避免复写onBackPress(),以保证SupportFragment内的onBackPressedSupport()回退事件正常执行
     */
    @Override
    public void onBackPressedSupport() {
        DELEGATE.onBackPressedSupport();
    }

    /**
     * 获取设置的全局动画 copy
     *
     * @return FragmentAnimator
     */
    @Override
    public FragmentAnimator getFragmentAnimator() {
        return DELEGATE.getFragmentAnimator();
    }

    @Override
    public void setFragmentAnimator(FragmentAnimator fragmentAnimator) {
        DELEGATE.setFragmentAnimator(fragmentAnimator);
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
}
