package com.flyingstudio.fscore.ui.recycler;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by guopu on 2017/10/19.
 */

public class MulitipleViewHolder extends BaseViewHolder {
    private MulitipleViewHolder(View view) {
        super(view);
    }
    public static MulitipleViewHolder creat(View view){
        return new MulitipleViewHolder(view);
    }
}
