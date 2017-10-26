package com.flyingstudio.market.fragments.goods;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyingstudio.fscore.fragment.FlyingFragment;
import com.flyingstudio.fscore.net.HttpClient;
import com.flyingstudio.fscore.net.callback.IError;
import com.flyingstudio.fscore.net.callback.IFailure;
import com.flyingstudio.fscore.net.callback.ISuccess;
import com.flyingstudio.fscore.ui.recycler.MulitipleItem;
import com.flyingstudio.market.R;
import com.flyingstudio.market.helper.adapter.GoodsItemAdapter;
import com.flyingstudio.market.helper.json.OrderJsonHelper;

import java.util.List;

import butterknife.BindView;

/**
 * Created by guopu on 2017/10/25.
 */

public class OrderListFragment extends FlyingFragment {

    private int type;
    private String name;
    private static final String ORDER_TYPE = "ORDER_TYPE";
    private static final String ORDER_TITLE = "ORDER_NAME";
    private GoodsItemAdapter mAdapter;
    @BindView(R.id.rv_goods)
    RecyclerView recyclerView = null;
    @BindView(R.id.tv_title)
    TextView title = null;
    @Override
    public Integer setLayoutID() {
        return R.layout.fragment_cart;
    }
    public static OrderListFragment newInstance(int id, String title){
        Bundle bundle = new Bundle();
        bundle.putInt(ORDER_TYPE,id);
        bundle.putString(ORDER_TITLE,title);
        OrderListFragment s = new OrderListFragment();
        s.setArguments(bundle);
        return s;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getArguments();
        if (b!=null){
            type = b.getInt(ORDER_TYPE);
            name = b.getString(ORDER_TITLE);
        }
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        title.setText(name);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        HttpClient.builder()
                .url("order")
                .params("type",type)
                .loader(getContext(),null)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String responce) {
                        OrderJsonHelper helper = new OrderJsonHelper();
                        List<MulitipleItem> itemList = helper.setJson(responce).convert();
                        mAdapter =  GoodsItemAdapter.creat(itemList, new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            }
                        });
                        recyclerView.setAdapter(mAdapter);
                        mAdapter.bindToRecyclerView(recyclerView);
                        mAdapter.setEmptyView(R.layout.empty_goods_layout);
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        checkItem();
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String message) {
                        checkItem();
                    }
                })
                .build()
                .get();
    }
    private void checkItem(){
        if(mAdapter==null){
            mAdapter = GoodsItemAdapter.creat(null,null);
            mAdapter.bindToRecyclerView(recyclerView);
            mAdapter.setEmptyView(R.layout.empty_goods_layout);
        }
    }
}
