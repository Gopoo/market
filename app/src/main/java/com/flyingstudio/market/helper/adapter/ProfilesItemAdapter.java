package com.flyingstudio.market.helper.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.flyingstudio.market.R;
import com.flyingstudio.market.helper.bean.OptionsFiled;
import com.flyingstudio.market.helper.bean.UserProfileFileds;

import java.util.List;

/**
 * Created by guopu on 2017/10/22.
 */

public class ProfilesItemAdapter extends BaseQuickAdapter<UserProfileFileds,BaseViewHolder> {
    private final OnItemClickListener LISTENER;
    private ProfilesItemAdapter(@Nullable List<UserProfileFileds> data, OnItemClickListener listener) {
        super(R.layout.user_profile_item, data);
        LISTENER = listener;
        this.setOnItemClickListener(LISTENER);
    }
    public static ProfilesItemAdapter creat(@Nullable List<UserProfileFileds> data, OnItemClickListener listener){
        return new ProfilesItemAdapter(data, listener);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserProfileFileds item) {
        if (item != null){
            String content = item.getContent();
            String type =  item.getType();
            helper.setText(R.id.tv_profile_content, content)
                    .setText(R.id.tv_profile_type,type);
        }
    }
}
