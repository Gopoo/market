package com.flyingstudio.fscore.fragment.bottom;

import java.util.LinkedHashMap;

/**
 * Created by guopu on 2017/10/18.
 */

public class BottomTabsHolder {
    private final LinkedHashMap<ItemBean,ItemFragment> TABS = new LinkedHashMap<>();

    public static final BottomTabsHolder builder(){
        return new BottomTabsHolder();
    }

    public void addTab(ItemBean bean, ItemFragment fragment){
        TABS.put(bean,fragment);
    }
    public LinkedHashMap<ItemBean,ItemFragment> getAllTabs(){
        return TABS;
    }
}
