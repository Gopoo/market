package com.flyingstudio.market.helper.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.flyingstudio.fscore.ui.recycler.ItemFileds;
import com.flyingstudio.fscore.ui.recycler.BaseJsonConvert;
import com.flyingstudio.fscore.ui.recycler.MulitipleItem;

import java.util.ArrayList;

/**
 * Created by guopu on 2017/10/19.
 */

public class HomeJsonHelper extends BaseJsonConvert {

    @Override
    public ArrayList<MulitipleItem> convert() {
        if (mJson==null||mJson.isEmpty()){
            throw new RuntimeException("Json data should not be empty,call setJson");
        }
        JSONObject result = JSON.parseObject(mJson);
        JSONArray datas = result.getJSONArray("data");
        int size = datas.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = datas.getJSONObject(i);
            final String id = data.getString("id");
            final int type = data.getInteger("type");
            final String price = data.getString("price");
            final String nickname = data.getString("nickname");
            final String avatar = data.getString("avatar");
            final String posttime = data.getString("postTime");
            final String content = data.getString("content");
            final JSONArray goodsImageUrls= data.getJSONArray("imageUrls");
            final JSONArray banners = data.getJSONArray("banners");
            ArrayList<String> goodsImages = null;
            ArrayList<String> bannerImages = null;
            if (goodsImageUrls != null) {
                goodsImages = new ArrayList<>();
                final int goodsImageUrlSize = goodsImageUrls.size();
                for (int j = 0; j < goodsImageUrlSize; j++) {
                    final String image = goodsImageUrls.getString(j);
                    goodsImages.add(image);
                }
            }
            if (banners != null) {
                bannerImages = new ArrayList<>();
                final int bannerSize = banners.size();
                for (int j = 0; j < bannerSize; j++) {
                    final String banner = banners.getString(j);
                    bannerImages.add(banner);
                }
            }
            final MulitipleItem entity = MulitipleItem.builder()
                    .addFiled(ItemFileds.ID, id)
                    .addFiled(ItemFileds.TYPE, type)
                    .addFiled(ItemFileds.PRICE,price)
                    .addFiled(ItemFileds.USER_NICK_NAME, nickname)
                    .addFiled(ItemFileds.USER_AVATAR, avatar)
                    .addFiled(ItemFileds.POST_TIME, posttime)
                    .addFiled(ItemFileds.IMAGE_URLS, goodsImages)
                    .addFiled(ItemFileds.CONTENT, content)
                    .addFiled(ItemFileds.BANNERS, bannerImages)
                    .build();
            items.add(entity);
        }
        return items;
    }
}
