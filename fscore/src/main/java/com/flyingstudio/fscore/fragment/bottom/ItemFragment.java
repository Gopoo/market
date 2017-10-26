package com.flyingstudio.fscore.fragment.bottom;

import android.widget.Toast;

import com.flyingstudio.fscore.app.Flying;
import com.flyingstudio.fscore.fragment.FlyingFragment;

/**
 * Created by guopu on 2017/10/18.
 */

public abstract class ItemFragment extends FlyingFragment {
    private final int CLOSE_TIME = 2500;
    private long mFirstCloseTime = 0;

    @Override
    public boolean onBackPressedSupport() {
        if (System.currentTimeMillis()-mFirstCloseTime>CLOSE_TIME){
            Toast.makeText(Flying.getAppContext(),"再按一次退出",Toast.LENGTH_SHORT).show();
            mFirstCloseTime = System.currentTimeMillis();
        }else {
            _mActivity.finish();
        }
        return true;
    }


}
