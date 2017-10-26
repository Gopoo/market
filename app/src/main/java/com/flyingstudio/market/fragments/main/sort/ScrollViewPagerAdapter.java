package com.flyingstudio.market.fragments.main.sort;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import me.yokeyword.fragmentation.ISupportFragment;

/**
 * Created by guopu on 2017/10/21.
 */

public class ScrollViewPagerAdapter extends FragmentPagerAdapter {

    private final ArrayList<ISupportFragment> FRAGMENTS;
    private final ArrayList<String> TITLES;
    ScrollViewPagerAdapter(FragmentManager manager, ArrayList<ISupportFragment> fragments, ArrayList<String> titles) {
        super(manager);
        FRAGMENTS = fragments;
        TITLES = titles;
    }
    public static final ScrollViewPagerAdapter build(FragmentManager manager, ArrayList<ISupportFragment> fragments, ArrayList<String> titles){
        return new ScrollViewPagerAdapter(manager,fragments,titles);
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return (Fragment) FRAGMENTS.get(position);
    }

    @Override
    public int getCount() {
        return FRAGMENTS.size();
    }
}
