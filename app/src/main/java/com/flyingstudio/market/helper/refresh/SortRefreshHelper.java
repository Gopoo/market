package com.flyingstudio.market.helper.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyingstudio.fscore.app.Flying;
import com.flyingstudio.fscore.net.HttpClient;
import com.flyingstudio.fscore.net.callback.IError;
import com.flyingstudio.fscore.net.callback.IFailure;
import com.flyingstudio.fscore.net.callback.ISuccess;
import com.flyingstudio.fscore.ui.recycler.BaseJsonConvert;
import com.flyingstudio.fscore.ui.recycler.MulitipleItem;
import com.flyingstudio.fscore.ui.recycler.PageIndicator;
import com.flyingstudio.market.helper.adapter.GoodsItemAdapter;
import com.flyingstudio.market.helper.json.SortItemJsonHelper;

import java.util.ArrayList;

/**
 * Created by guopu on 2017/10/19.
 */

public class SortRefreshHelper implements SwipeRefreshLayout.OnRefreshListener ,BaseQuickAdapter.RequestLoadMoreListener{
    private final int SORT_ITEM_TYPE;
    private final SwipeRefreshLayout LAYOUT;
    private BaseQuickAdapter mAdapter;
    private final BaseQuickAdapter.OnItemClickListener ITEMS_LISTENER;
    private final RecyclerView RECYCLERVIEW;
    private final PageIndicator INDICATOR = new PageIndicator();
    private final BaseJsonConvert HELPER = new SortItemJsonHelper();
    private boolean firstPageIsLoaded = false;

    public SortRefreshHelper(int itemType, SwipeRefreshLayout layout, RecyclerView recyclerview, BaseQuickAdapter.OnItemClickListener items) {
        SORT_ITEM_TYPE = itemType;
        LAYOUT = layout;
        ITEMS_LISTENER = items;
        RECYCLERVIEW = recyclerview;
        LAYOUT.setOnRefreshListener(this);
        initFirstPage();
    }

    public void initFirstPage(){
        if (!firstPageIsLoaded){
            RECYCLERVIEW.setItemAnimator(null);
            RECYCLERVIEW.setAnimation(null);
            HttpClient.builder()
                    .url("sort")
                    .params("type",SORT_ITEM_TYPE)
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String responce) {
                            LAYOUT.setRefreshing(false);
                            JSONObject result = JSON.parseObject(responce);
                            INDICATOR.setCurrentPage(1).setTotoalPages(result.getInteger("pages"));
                            ArrayList<MulitipleItem> items =  HELPER.setJson(responce).convert();
                            mAdapter =  GoodsItemAdapter.creat(items,ITEMS_LISTENER);
                            RECYCLERVIEW.setAdapter(mAdapter);
                            mAdapter.setOnLoadMoreListener(SortRefreshHelper.this,RECYCLERVIEW);
                            firstPageIsLoaded = true;
                            checkItem();
                        }
                    })
                    .failure(new IFailure() {
                        @Override
                        public void onFailure() {
                            LAYOUT.setRefreshing(false);
                            checkItem();
                        }
                    })
                    .error(new IError() {
                        @Override
                        public void onError(int code, String message) {
                            Toast.makeText(Flying.getAppContext(),message,Toast.LENGTH_SHORT).show();
                            LAYOUT.setRefreshing(false);
                            checkItem();
                        }
                    })
                    .build()
                    .get();
        }else {
            LAYOUT.setRefreshing(false);
        }
    }
    @Override
    public void onRefresh() {
        initFirstPage();
}
    @Override
    public void onLoadMoreRequested() {
        final int total = INDICATOR.getTotoalPages();
        final int index = INDICATOR.startLoadNextPage(); //加载下一页
        if (index>total){
            mAdapter.loadMoreEnd();
        }else {
            Toast.makeText(Flying.getAppContext(),"isLoadMore",Toast.LENGTH_SHORT).show();
            mAdapter.loadMoreFail();
//            HttpClient.builder()
//                    .url(URL)
//                    .success(new ISuccess() {
//                        @Override
//                        public void onSuccess(String response) {
//                            //删除JSON转换器中的数据
//                            HELPER.clearData();
//                            mAdapter.addData(HELPER.setJson(response).convert());
//                            mAdapter.loadMoreComplete();
//                        }
//                    })
//                    .error(new IError() {
//                        @Override
//                        public void onError(int code, String message) {
//                            mAdapter.loadMoreComplete();
//                        }
//                    })
//                    .failure(new IFailure() {
//                        @Override
//                        public void onFailure() {
//                            mAdapter.loadMoreComplete();
//                        }
//                    })
//                    .build()
//                    .get();
        }
    }
    private void checkItem(){
        if(mAdapter!=null){
            if (mAdapter.getItemCount()!=0){
                RECYCLERVIEW.setVisibility(View.VISIBLE);
            }else {
                mAdapter.setEmptyView(com.flyingstudio.fscore.R.layout.rv_emtyview_layout);
            }
        }else{
            mAdapter = GoodsItemAdapter.creat(null,null);
            mAdapter.bindToRecyclerView(RECYCLERVIEW);
            mAdapter.setEmptyView(com.flyingstudio.fscore.R.layout.rv_emtyview_layout);
        }
    }
}
