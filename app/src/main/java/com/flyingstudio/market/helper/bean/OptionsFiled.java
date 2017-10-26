package com.flyingstudio.market.helper.bean;

import android.support.annotation.DrawableRes;

/**
 * Created by guopu on 2017/10/24.
 */

public class OptionsFiled {
    private int mType;
    @DrawableRes
    private int mDrawable;

    private String mContent;


    public OptionsFiled(int type, int drawable, String content) {
        this.mType = type;
        this.mDrawable = drawable;
        this.mContent = content;
    }
    public OptionsFiled(){
    }
    public int getType() {
        return mType;
    }

    public OptionsFiled setType(int type) {
        this.mType = type;
        return this;
    }

    public int getDrawable() {
        return mDrawable;
    }

    public OptionsFiled setDrawable(int mDrawable) {
        this.mDrawable = mDrawable;
        return this;
    }

    public String getContent() {
        return mContent;
    }

    public OptionsFiled setContent(String mContent) {
        this.mContent = mContent;
        return this;
    }
    public static OptionsFiledCreator creator(){
        return new OptionsFiledCreator();
    }
}
