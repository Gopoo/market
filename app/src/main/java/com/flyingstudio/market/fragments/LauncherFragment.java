package com.flyingstudio.market.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.flyingstudio.fscore.fragment.BaseLauncherFragment;
import com.flyingstudio.market.R;
import com.flyingstudio.market.fragments.main.ContainerFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by guopu on 2017/10/16.
 */

public class LauncherFragment extends BaseLauncherFragment {

    @BindView(R.id.tv_countdown)
    TextView numScreen = null;

    @OnClick(R.id.tv_countdown)
    public void onClick(){
        timer.cancel();
        timer = null;
        extraTransaction()
                .setCustomAnimations(R.animator.nullanim, R.animator.nullanim)
                .startWithPop(new ContainerFragment());
    }
    @Override
    public Integer setLayoutID() {
        return R.layout.fragment_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        super.onBindView(savedInstanceState, rootView);
    }
    @Override
    public void countDown(int count) {
        numScreen.setText("跳过\n"+count+"s");
        if (count<= 0){
            onClick();
        }
    }
}
