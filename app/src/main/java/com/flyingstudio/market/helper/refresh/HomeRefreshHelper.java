package com.flyingstudio.market.helper.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyingstudio.fscore.app.Flying;
import com.flyingstudio.fscore.net.HttpClient;
import com.flyingstudio.fscore.net.callback.IError;
import com.flyingstudio.fscore.net.callback.IFailure;
import com.flyingstudio.fscore.net.callback.ISuccess;
import com.flyingstudio.fscore.ui.recycler.BaseJsonConvert;
import com.flyingstudio.fscore.ui.recycler.MulitipleItem;
import com.flyingstudio.fscore.ui.recycler.MulitipleItemAdapter;
import com.flyingstudio.fscore.ui.recycler.PageIndicator;
import com.flyingstudio.market.helper.json.HomeJsonHelper;

import java.util.ArrayList;

/**
 * Created by guopu on 2017/10/19.
 */

public class HomeRefreshHelper implements SwipeRefreshLayout.OnRefreshListener ,BaseQuickAdapter.RequestLoadMoreListener{

    private final SwipeRefreshLayout LAYOUT;
    private BaseQuickAdapter mAdapter;
    private final OnItemClickListener BANNER_LISTENER;
    private final BaseQuickAdapter.OnItemClickListener ITEMS_LISTENER;
    private final RecyclerView RECYCLERVIEW;
    private final PageIndicator INDICATOR = new PageIndicator();
    private final BaseJsonConvert HELPER = new HomeJsonHelper();
    private boolean firstPageIsLoaded = false;
    public HomeRefreshHelper(SwipeRefreshLayout layout, RecyclerView recyclerview, OnItemClickListener banner, BaseQuickAdapter.OnItemClickListener items) {
        this.LAYOUT = layout;
        BANNER_LISTENER = banner;
        ITEMS_LISTENER = items;
        RECYCLERVIEW = recyclerview;
        LAYOUT.setOnRefreshListener(this);
        initFirstPage();
    }

    public void initFirstPage(){
        LAYOUT.setRefreshing(true);
        RECYCLERVIEW.setItemAnimator(null);
        RECYCLERVIEW.setAnimation(null);
        if (!firstPageIsLoaded){
            HttpClient.builder()
                    .url("index")
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String responce) {
                            LAYOUT.setRefreshing(false);
                            JSONObject result = JSON.parseObject(responce);
                            INDICATOR.setCurrentPage(1).setTotoalPages(result.getInteger("pages"));
                            ArrayList<MulitipleItem> items =  HELPER.setJson(responce).convert();
                            mAdapter =  MulitipleItemAdapter.creat(items,BANNER_LISTENER,ITEMS_LISTENER);
                            RECYCLERVIEW.setAdapter(mAdapter);

                            mAdapter.setOnLoadMoreListener(HomeRefreshHelper.this,RECYCLERVIEW);
                            firstPageIsLoaded = true;
                        }
                    })
                    .failure(new IFailure() {
                        @Override
                        public void onFailure() {
                            LAYOUT.setRefreshing(false);
                        }
                    })
                    .error(new IError() {
                        @Override
                        public void onError(int code, String message) {
                            Toast.makeText(Flying.getAppContext(),message,Toast.LENGTH_SHORT).show();
                            LAYOUT.setRefreshing(false);
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
}
