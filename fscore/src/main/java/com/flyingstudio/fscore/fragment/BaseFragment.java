package com.flyingstudio.fscore.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * Created by guopu on 2017/10/11.
 */

public abstract class BaseFragment extends SwipeBackFragment {
    private Unbinder mUnbinder;
    public abstract Integer setLayoutID();
    public abstract void onBindView(@Nullable Bundle savedInstanceState, View rootView);
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = null;
        if (setLayoutID()!=null){
            rootView = inflater.inflate(setLayoutID(),container,false);
        }
        if (rootView!=null){
            mUnbinder = ButterKnife.bind(this,rootView);
            onBindView(savedInstanceState,rootView);
        }
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
