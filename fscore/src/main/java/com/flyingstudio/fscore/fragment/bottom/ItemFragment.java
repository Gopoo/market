package com.flyingstudio.fscore.fragment.bottom;

import android.widget.Toast;

import com.flyingstudio.fscore.app.Flying;
import com.flyingstudio.fscore.fragment.FlyingFragment;

/**
 * Created by guopu on 2017/10/18.
 */

public abstract class ItemFragment extends FlyingFragment {
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;

    @Override
    public boolean onBackPressedSupport() {
        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            _mActivity.finish();
        } else {
            TOUCH_TIME = System.currentTimeMillis();
            Toast.makeText(_mActivity, "双击退出" , Toast.LENGTH_SHORT).show();
        }
        return true;
    }

}
