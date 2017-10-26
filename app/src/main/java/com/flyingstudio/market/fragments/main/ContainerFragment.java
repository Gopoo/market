package com.flyingstudio.market.fragments.main;

import com.flyingstudio.fscore.fragment.bottom.BottomTabsFragment;
import com.flyingstudio.fscore.fragment.bottom.BottomTabsHolder;
import com.flyingstudio.fscore.fragment.bottom.ItemBean;
import com.flyingstudio.market.R;
import com.flyingstudio.market.fragments.main.cart.CartFragment;
import com.flyingstudio.market.fragments.main.home.HomeFragment;
import com.flyingstudio.market.fragments.main.sort.SortFragment;
import com.flyingstudio.market.fragments.main.user.UserFragment;


/**
 * Created by guopu on 2017/10/18.
 */

public class ContainerFragment extends BottomTabsFragment {
    @Override
    public void setTabs(BottomTabsHolder builder) {
        int pressedColor = 0xFF1296db;
        builder.addTab(new ItemBean(R.mipmap.home_light,R.mipmap.home_fill_light,"首页",pressedColor),new HomeFragment());
        builder.addTab(new ItemBean(R.mipmap.news_hot_light,R.mipmap.news_hot_fill_light,"分类",pressedColor),new SortFragment());
        builder.addTab(new ItemBean(R.mipmap.cart_light,R.mipmap.cart_fill_light,"购物车",pressedColor),new CartFragment());
        builder.addTab(new ItemBean(R.mipmap.my_light,R.mipmap.my_fill_light,"我",pressedColor),new UserFragment());
    }
}
