package com.flyingstudio.market.helper.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.flyingstudio.fscore.ui.recycler.ItemFileds;
import com.flyingstudio.fscore.ui.recycler.MulitipleItem;
import com.flyingstudio.fscore.ui.recycler.MulitipleViewHolder;
import com.flyingstudio.fscore.utils.GlideUtil;
import com.flyingstudio.market.R;
import com.flyingstudio.market.helper.bean.ItemType;

import java.util.List;

/**
 * Created by guopu on 2017/10/25.
 */

public class GoodsItemAdapter extends BaseMultiItemQuickAdapter<MulitipleItem,MulitipleViewHolder> {

    private final OnItemClickListener LISTENER;
    private GoodsItemAdapter(List<MulitipleItem> data, OnItemClickListener listener) {
        super(data);
        LISTENER = listener;
        init();
    }
    private void init(){
        addItemType(ItemType.ORDER, R.layout.order_item);
        addItemType(ItemType.SORT_ITEM, R.layout.sort_item);
        setOnItemClickListener(LISTENER);
    }
    @Override
    protected MulitipleViewHolder createBaseViewHolder(View view) {
        return MulitipleViewHolder.creat(view);
    }
    public static GoodsItemAdapter creat(List<MulitipleItem> data, OnItemClickListener listener) {
       return new GoodsItemAdapter(data, listener);
    }
    @Override
    protected void convert(MulitipleViewHolder helper, MulitipleItem item) {
        switch (item.getItemType()){
            case ItemType.ORDER:
                {
                    String content = item.getField(ItemFileds.CONTENT);
                    String price =  item.getField(ItemFileds.PRICE);
                    String imageUrl = item.getField(ItemFileds.IMAGE_URLS);
                    String postTime = item.getField(ItemFileds.POST_TIME);
                    helper.setText(R.id.tv_goods_content, content)
                            .setText(R.id.tv_goods_price,price)
                            .setText(R.id.tv_order_time,postTime);
                    GlideUtil.loadpPreImageViewErrorSize(mContext,imageUrl,80,80, (ImageView) helper.getView(R.id.iv_goods_img),R.mipmap.errorimage,0.6f);
                    break;
                }
            case ItemType.SORT_ITEM:
                {
                    String content = item.getField(ItemFileds.CONTENT);
                    String price =  item.getField(ItemFileds.PRICE);
                    String imageUrl = item.getField(ItemFileds.IMAGE_URLS);
                    helper.setText(R.id.tv_content, content)
                            .setText(R.id.tv_price,price);
                    GlideUtil.loadpPreImageViewErrorSize(mContext,imageUrl,150,150, (ImageView) helper.getView(R.id.iv_goods),R.mipmap.errorimage,0.6f);
                    break;
                }

            default:
                break;
        }
    }
}
