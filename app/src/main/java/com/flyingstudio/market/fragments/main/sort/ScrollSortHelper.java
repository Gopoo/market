package com.flyingstudio.market.fragments.main.sort;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;

import me.yokeyword.fragmentation.ISupportFragment;

/**
 * Created by guopu on 2017/10/21.
 */

public class ScrollSortHelper {
    private final TabsHolder mHolder;
    private final TabLayout TABS;
    private final SortFragment MANGER;
    private final ViewPager PAGER;
    private final ArrayList<String> TITLES;
    private final ArrayList<ISupportFragment> FRAGMENTS;

    private ScrollSortHelper(TabsHolder holder, SortFragment manger, TabLayout tabs, ViewPager pager){
        mHolder = holder;
        MANGER = manger;
        TABS = tabs;
        PAGER = pager;
        TITLES = mHolder.getAllTitles();
        FRAGMENTS = mHolder.getAllFragments();
        init();
    }
    private void init(){
        int size = FRAGMENTS.size();
        for (int i = 0;i<size;i++){
            TABS.addTab(TABS.newTab());
        }
        TABS.setTabGravity(TabLayout.GRAVITY_FILL);
        TABS.setTabMode(TabLayout.MODE_SCROLLABLE);
        PAGER.setAdapter(ScrollViewPagerAdapter.build(MANGER.getFragmentManager(),FRAGMENTS, TITLES));
        PAGER.setCurrentItem(0);
        PAGER.setOffscreenPageLimit(size);
        TABS.setupWithViewPager(PAGER);

    }
    public static ScrollSortHelper creat(TabsHolder holder, SortFragment manger, TabLayout tabs, ViewPager pager){
        return new ScrollSortHelper(holder,manger,tabs, pager);
    }
}
