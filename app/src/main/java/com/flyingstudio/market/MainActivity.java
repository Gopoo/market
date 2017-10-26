package com.flyingstudio.market;

import com.flyingstudio.fscore.activity.FlyingActivity;
import com.flyingstudio.fscore.app.Flying;
import com.flyingstudio.fscore.fragment.FlyingFragment;
import com.flyingstudio.fscore.interceptor.DebugInterceptor;
import com.flyingstudio.market.database.DatabaseManger;
import com.flyingstudio.market.fragments.LauncherFragment;

public class MainActivity extends FlyingActivity {


    @Override
    public FlyingFragment setRootFragment() {
        return new LauncherFragment();
    }

    @Override
    public void init() {
        Flying.init(this.getApplicationContext())
                .setAPIHost("http://www.baidu.com/")
                .addInterceptor(new DebugInterceptor("http://www.baidu.com/index",R.raw.index))
                .addInterceptor(new DebugInterceptor("http://www.baidu.com/sort?type=0",R.raw.sort))
                .addInterceptor(new DebugInterceptor("http://www.baidu.com/sort?type=1",R.raw.sort2))
                .addInterceptor(new DebugInterceptor("http://www.baidu.com/order?type=0",R.raw.mysubmit))
                .configure();
        DatabaseManger.newInstance().initManger(Flying.getAppContext());
    }
}
