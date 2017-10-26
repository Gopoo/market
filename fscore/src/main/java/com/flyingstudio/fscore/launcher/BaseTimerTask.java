package com.flyingstudio.fscore.launcher;

import java.util.TimerTask;

/**
 * Created by guopu on 2017/10/16.
 */

public class BaseTimerTask extends TimerTask {
    private final ITimerTask iTimerTask;

    public BaseTimerTask(ITimerTask timerTask) {
        this.iTimerTask = timerTask;
    }

    @Override
    public void run() {
        if (iTimerTask!=null)
            iTimerTask.onTimer();
    }
}
