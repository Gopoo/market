package com.flyingstudio.fscore.ui.recycler;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.LinkedHashMap;

/**
 * Created by guopu on 2017/10/19.
 */

public class MulitipleItem implements MultiItemEntity {
    private ReferenceQueue<LinkedHashMap<Object,Object>> QUEUE = new ReferenceQueue<>();
    private LinkedHashMap<Object,Object> ITEM_BEAN = new LinkedHashMap<>();
    private WeakReference<LinkedHashMap<Object,Object>> REFERENCE = new WeakReference<LinkedHashMap<Object, Object>>(ITEM_BEAN,QUEUE);

    @Override
    public int getItemType() {
        return (int) REFERENCE.get().get(ItemFileds.TYPE);
    }

    MulitipleItem(LinkedHashMap<Object,Object> fileds){
        REFERENCE.get().putAll(fileds);
    }

    public static MulitipleItemBuilder builder(){
        return new MulitipleItemBuilder();
    }

    public MulitipleItem setFiled(Enum<ItemFileds> key, Object value) {
        REFERENCE.get().put(value,value);
        return this;
    }
    public MulitipleItem setFileds(LinkedHashMap<Object,Object> fileds) {
        REFERENCE.get().putAll(fileds);
        return this;
    }
    public final <T> T getField(Object key){
        return (T) REFERENCE.get().get(key);
    }

    public final LinkedHashMap<?,?> getFields(){
        return REFERENCE.get();
    }
}
