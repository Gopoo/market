package com.flyingstudio.fscore.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.flyingstudio.fscore.launcher.BaseTimerTask;
import com.flyingstudio.fscore.launcher.ITimerTask;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by guopu on 2017/10/16.
 */

public abstract class BaseLauncherFragment extends FlyingFragment implements ITimerTask {

    protected  Timer timer = null;
    private  int count = 4;
    private final static long DELAY_TIME = 0;
    private final static long PERIOD_TIME = 1000;
    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        TimerTask task = new BaseTimerTask(this);
        timer = new Timer();
        timer.schedule(task,DELAY_TIME,PERIOD_TIME);
    }
    @Override
    public void onTimer(){
        _mActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    countDown(count);
                }
            });
        count--;
    }
    public abstract void countDown(int count);
}
