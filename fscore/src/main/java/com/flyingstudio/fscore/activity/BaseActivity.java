package com.flyingstudio.fscore.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;
import android.view.WindowManager;

import com.flyingstudio.fscore.R;
import com.flyingstudio.fscore.fragment.FlyingFragment;

import me.yokeyword.fragmentation.SupportActivity;
import qiu.niorgai.StatusBarCompat;


/**
 * Created by guopu on 2017/10/11.
 */

public abstract class BaseActivity extends SupportActivity {
    public abstract FlyingFragment setRootFragment();
    public abstract void init();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
            loadRootFragment(R.id.content_framelayout,setRootFragment());
        }
    }
}
