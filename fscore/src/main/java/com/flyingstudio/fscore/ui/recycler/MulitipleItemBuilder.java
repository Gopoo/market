package com.flyingstudio.fscore.ui.recycler;

import java.util.LinkedHashMap;

/**
 * Created by guopu on 2017/10/19.
 */

public class MulitipleItemBuilder {
    private static final LinkedHashMap<Object,Object> ITEMS = new LinkedHashMap<>() ;
    MulitipleItemBuilder(){
        ITEMS.clear();
    }
    public MulitipleItem build(){
        return new MulitipleItem(ITEMS);
    }
    public MulitipleItemBuilder addFiled(Enum<ItemFileds> key, Object value) {
        ITEMS.put(key,value);
        return this;
    }
    public MulitipleItemBuilder addFileds(LinkedHashMap<Object,Object> items) {
        ITEMS.putAll(items);
        return this;
    }
}
