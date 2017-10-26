package com.flyingstudio.market.helper.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.flyingstudio.market.R;
import com.flyingstudio.market.helper.bean.OptionsFiled;

import java.util.List;

/**
 * Created by guopu on 2017/10/22.
 */

public class OptionItemAdapter extends BaseQuickAdapter<OptionsFiled,BaseViewHolder> {
    private final OnItemClickListener LISTENER;
    private OptionItemAdapter(@Nullable List<OptionsFiled> data, OnItemClickListener listener) {
        super(R.layout.option_item, data);
        LISTENER = listener;
        this.setOnItemClickListener(LISTENER);
    }
    public static OptionItemAdapter creat(@Nullable List<OptionsFiled> data,OnItemClickListener listener){
        return new OptionItemAdapter(data, listener);
    }

    @Override
    protected void convert(BaseViewHolder helper, OptionsFiled item) {
        if (item != null){
            String content = item.getContent();
            int drawable =  item.getDrawable();
            helper.setText(R.id.tv_option_content, content)
                    .setImageResource(R.id.iv_option_logo,drawable);
        }
    }
}
