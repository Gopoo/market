package com.flyingstudio.fscore.ui.recycler;

import java.util.ArrayList;

/**
 * Created by guopu on 2017/10/19.
 */

public abstract class BaseJsonConvert {
    protected ArrayList<MulitipleItem> items = new ArrayList<>();
    protected String mJson = null;

    public BaseJsonConvert setJson(String json){
        this.mJson = json;
        return this;
    }
    public abstract ArrayList<MulitipleItem> convert();

    public String getJson(){
        if (mJson==null||mJson.isEmpty()){
            throw new RuntimeException("json should not be empty,call setJson");
        }
        return mJson;
    }
    public void clearData(){
        mJson = null;
        items.clear();
    }
}
