package com.flyingstudio.market.fragments.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.flyingstudio.fscore.fragment.bottom.ItemFragment;
import com.flyingstudio.market.R;

import butterknife.BindView;


/**
 * Created by guopu on 2017/10/11.
 */

public class SortFragment extends ItemFragment {

    @BindView(R.id.tl_tabs)
    TabLayout tabs;

    @BindView(R.id.cfl_container)
    ViewPager pager;
    @Override
    public Integer setLayoutID() {
        return R.layout.fragment_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        TabsHolder holder = TabsHolder
                .creat()
                .addTitle("闲置数码")
                .addTitle("书籍杂志")
                .addTitle("家具日用")
                .addTitle("服装配饰")
                .addTitle("技术服务")
                .addFragment(SortItemFragment.newInstance(0))
                .addFragment(SortItemFragment.newInstance(1))
                .addFragment(SortItemFragment.newInstance(2))
                .addFragment(SortItemFragment.newInstance(3))
                .addFragment(SortItemFragment.newInstance(4));
        ScrollSortHelper.creat(holder,this,tabs,pager);
    }

}
