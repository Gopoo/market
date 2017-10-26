package com.flyingstudio.market.helper.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.flyingstudio.fscore.ui.recycler.MulitipleViewHolder;
import com.flyingstudio.fscore.utils.GlideUtil;
import com.flyingstudio.market.R;
import com.flyingstudio.market.database.cart.CartProfile;

import java.util.List;

/**
 * Created by guopu on 2017/10/25.
 */

public class CartItemAdapter extends BaseQuickAdapter<CartProfile,BaseViewHolder> {

    private final OnItemClickListener LISTENER;

    public CartItemAdapter(@LayoutRes int layoutResId, @Nullable List<CartProfile> data, OnItemClickListener listener) {
        super(layoutResId, data);
        LISTENER = listener;
        init();

    }

    private void init(){
        setOnItemClickListener(LISTENER);
    }
    @Override
    protected MulitipleViewHolder createBaseViewHolder(View view) {
        return MulitipleViewHolder.creat(view);
    }

    @Override
    protected void convert(BaseViewHolder helper, CartProfile item) {
        if (item!=null){
            String content = item.getContent();
            String price =  item.getPrice();
            String imageUrl = item.getImageUrl();
            String addTime = item.getTime();
            helper.setText(R.id.tv_goods_content, content)
                    .setText(R.id.tv_goods_price,price)
                    .setText(R.id.tv_order_time,addTime);
            GlideUtil.loadpPreImageViewErrorSize(mContext,imageUrl,80,80, (ImageView) helper.getView(R.id.iv_goods_img),R.mipmap.errorimage,0.6f);
        }
    }

    public static CartItemAdapter creat(List<CartProfile> data, OnItemClickListener listener) {
       return new CartItemAdapter(R.layout.order_item, data,listener);
    }

}
