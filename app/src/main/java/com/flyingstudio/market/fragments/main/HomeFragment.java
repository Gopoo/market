package com.flyingstudio.market.fragments.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyingstudio.fscore.app.Flying;
import com.flyingstudio.fscore.fragment.bottom.ItemFragment;
import com.flyingstudio.market.R;
import com.flyingstudio.market.helper.refresh.HomeRefreshHelper;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by guopu on 2017/10/11.
 */

public class HomeFragment extends ItemFragment {

    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout refreshLayout = null;

    @BindView(R.id.rv_goods)
    RecyclerView recyclerView = null;

    @BindView(R.id.tb_toolbar)
    Toolbar toolbar = null;

    @OnClick(R.id.fab_submmit)
    public void onClickSubmmit(){
        Toast.makeText(Flying.getAppContext(), "YOU CLICK FAB !", Toast.LENGTH_SHORT).show();
    }
    private void initRefreshLayout(){
        refreshLayout.setColorSchemeColors(0xFF1296db);
        refreshLayout.setProgressViewOffset(false,125,250);
    }
    private void initRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
    private void initToolbar(){
        toolbar.inflateMenu(R.menu.home_toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });
    }
    @Override
    public Integer setLayoutID() {
        return R.layout.fragment_home;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
    }


    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        initRecyclerView();
        initRefreshLayout();
        initToolbar();
        HomeRefreshHelper helper = new HomeRefreshHelper(refreshLayout, recyclerView, new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(Flying.getAppContext(), "YOU CLICK BANNER :" + position, Toast.LENGTH_SHORT).show();
            }
        }, new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(Flying.getAppContext(), "YOU CLICK ITEM INSTEAD OF BANNER :" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
