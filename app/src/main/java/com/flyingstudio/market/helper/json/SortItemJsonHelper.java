package com.flyingstudio.market.helper.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.flyingstudio.fscore.ui.recycler.BaseJsonConvert;
import com.flyingstudio.fscore.ui.recycler.ItemFileds;
import com.flyingstudio.fscore.ui.recycler.MulitipleItem;
import com.flyingstudio.market.helper.bean.ItemType;

import java.util.ArrayList;

/**
 * Created by guopu on 2017/10/22.
 */

public class SortItemJsonHelper extends BaseJsonConvert {
    @Override
    public ArrayList<MulitipleItem> convert() {
        if (mJson==null||mJson.isEmpty()){
            throw new RuntimeException("Json data should not be empty,call setJson");
        }
        JSONObject json = JSON.parseObject(getJson());
        JSONArray datas = json.getJSONArray("data");
        int size = datas.size();
        for (int i = 0;i<size;i++){
            JSONObject object = datas.getJSONObject(i);
            String id = object.getString("id");
            String goods_image = object.getString("imageUrl");
            String price = object.getString("price");
            String content = object.getString("content");
            final MulitipleItem entity = MulitipleItem.builder()
                    .addFiled(ItemFileds.TYPE, ItemType.SORT_ITEM)
                    .addFiled(ItemFileds.ID, id)
                    .addFiled(ItemFileds.PRICE,price)
                    .addFiled(ItemFileds.IMAGE_URLS, goods_image)
                    .addFiled(ItemFileds.CONTENT, content)
                    .build();
            items.add(entity);
        }
        return items;
    }
}
