package com.flyingstudio.fscore.fragment.bottom;

import com.flyingstudio.fscore.fragment.FlyingFragment;

import java.util.LinkedHashMap;

/**
 * Created by guopu on 2017/10/18.
 */

public class BottomTabsHolder {
    private final LinkedHashMap<ItemBean,FlyingFragment> TABS = new LinkedHashMap<>();

    public static final BottomTabsHolder builder(){
        return new BottomTabsHolder();
    }

    public void addTab(ItemBean bean, FlyingFragment fragment){
        TABS.put(bean,fragment);
    }
    public LinkedHashMap<ItemBean,FlyingFragment> getAllTabs(){
        return TABS;
    }
}
