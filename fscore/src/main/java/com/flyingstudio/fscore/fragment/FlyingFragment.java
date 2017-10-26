package com.flyingstudio.fscore.fragment;

/**
 * Created by guopu on 2017/10/11.
 */

public abstract class FlyingFragment extends CheckPermissionFragment {
    @SuppressWarnings("unchecked")
    public <T extends FlyingFragment> T getParentDelegate() {
        return (T) getParentFragment();
    }
}
