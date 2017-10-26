package com.flyingstudio.market.fragments.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyingstudio.fscore.fragment.FlyingFragment;
import com.flyingstudio.market.R;
import com.flyingstudio.market.helper.refresh.SortRefreshHelper;

import butterknife.BindView;

/**
 * Created by guopu on 2017/10/21.
 */

public class SortItemFragment extends FlyingFragment {

    @BindView(R.id.swp_refresh)
    SwipeRefreshLayout refreshLayout = null;

    @BindView(R.id.rv_sort_goods)
    RecyclerView recyclerView = null;

    private static final String ITEM_ARG = "ITEM";
    private int TYPE;
    private SortItemFragment(){}
    public static SortItemFragment newInstance(int type){
        Bundle bundle = new Bundle();
        bundle.putInt(ITEM_ARG,type);
        SortItemFragment s = new SortItemFragment();
        s.setArguments(bundle);
        return s;
    }
    private void initRefreshLayout(){
        refreshLayout.setColorSchemeColors(0xFF1296db);
        refreshLayout.setProgressViewOffset(false,125,250);
    }
    private void initRecyclerView(){
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args != null) {
            TYPE = args.getInt(ITEM_ARG);
        }
    }
    @Override
    public Integer setLayoutID() {
        return R.layout.fragment_sort_item;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initRefreshLayout();
        initRecyclerView();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        SortRefreshHelper helper = new SortRefreshHelper(TYPE, refreshLayout, recyclerView, new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(getContext(),"you click sort item "+ position,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
