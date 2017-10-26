package com.flyingstudio.market.fragments.main.cart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyingstudio.fscore.fragment.bottom.ItemFragment;
import com.flyingstudio.market.R;
import com.flyingstudio.market.database.DatabaseManger;
import com.flyingstudio.market.database.cart.CartProfile;
import com.flyingstudio.market.database.cart.CartProfileDao;
import com.flyingstudio.market.helper.adapter.CartItemAdapter;

import java.util.List;

import butterknife.BindView;


/**
 * Created by guopu on 2017/10/11.
 */

public class CartFragment extends ItemFragment {

   @BindView(R.id.tv_title)
   TextView title = null;

    @BindView(R.id.rv_goods)
    RecyclerView recyclerView = null;
    @Override
    public Integer setLayoutID() {
        return R.layout.fragment_cart;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        title.setText(R.string.cart);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRecycleview();
    }
    private void initRecycleview(){
        mockCart();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        List<CartProfile> list =  DatabaseManger.newInstance().getCartDao().loadAll();
        CartItemAdapter adapter = CartItemAdapter.creat(list, new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
        recyclerView.setAdapter(adapter);
        adapter.bindToRecyclerView(recyclerView);
        adapter.setEmptyView(R.layout.empty_goods_layout);
    }
    private void mockCart(){
        CartProfileDao dao = DatabaseManger.newInstance().getCartDao();
        dao.insertOrReplace(new CartProfile(1111,"￥3123","iphone 6","2017年10月26日00:26:09","https://img.alicdn.com/bao/uploaded/i1/6000000004624/TB2QBThshBmpuFjSZFDXXXD8pXa_!!0-fleamarket.jpg_728x728.jpg"));
        dao.insertOrReplace(new CartProfile(2222,"￥3623","iphone 7","2017年10月26日00:26:14","https://img.alicdn.com/bao/uploaded/i1/6000000004624/TB2QBThshBmpuFjSZFDXXXD8pXa_!!0-fleamarket.jpg_728x728.jpg"));
        dao.insertOrReplace(new CartProfile(3333,"￥4123","iphone 8","2017年10月26日00:26:17","https://img.alicdn.com/bao/uploaded/i1/6000000004624/TB2QBThshBmpuFjSZFDXXXD8pXa_!!0-fleamarket.jpg_728x728.jpg"));
        dao.insertOrReplace(new CartProfile(4444,"￥6123","iphone 9","2017年10月26日00:26:20","https://img.alicdn.com/bao/uploaded/i1/6000000004624/TB2QBThshBmpuFjSZFDXXXD8pXa_!!0-fleamarket.jpg_728x728.jpg"));
        dao.insertOrReplace(new CartProfile(5555,"￥7123","iphone 10","2017年10月26日00:26:24","https://img.alicdn.com/bao/uploaded/i1/6000000004624/TB2QBThshBmpuFjSZFDXXXD8pXa_!!0-fleamarket.jpg_728x728.jpg"));
        dao.insertOrReplace(new CartProfile(6666,"￥17789","iphone 11","2017年10月26日00:26:28","https://img.alicdn.com/bao/uploaded/i1/6000000004624/TB2QBThshBmpuFjSZFDXXXD8pXa_!!0-fleamarket.jpg_728x728.jpg"));
        dao.insertOrReplace(new CartProfile(7777,"￥22789","iphone X","2017年10月26日00:26:38","https://img.alicdn.com/bao/uploaded/i1/6000000004624/TB2QBThshBmpuFjSZFDXXXD8pXa_!!0-fleamarket.jpg_728x728.jpg"));
    }
}
