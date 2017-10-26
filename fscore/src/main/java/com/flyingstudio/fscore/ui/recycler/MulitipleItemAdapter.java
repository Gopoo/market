package com.flyingstudio.fscore.ui.recycler;

import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.flyingstudio.fscore.R;
import com.flyingstudio.fscore.ui.banner.BannerCreator;
import com.flyingstudio.fscore.utils.GlideUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guopu on 2017/10/19.
 */

public class MulitipleItemAdapter extends BaseMultiItemQuickAdapter<MulitipleItem,MulitipleViewHolder> {
    //Banner点击事件
    private final com.bigkoo.convenientbanner.listener.OnItemClickListener bannerItemListener;
    private final OnItemClickListener itemClickListener;
    private boolean isLoadedBanner = false;

    protected MulitipleItemAdapter(List<MulitipleItem> data, com.bigkoo.convenientbanner.listener.OnItemClickListener listener
            , OnItemClickListener itemClickListener) {
        super(data);
        this.bannerItemListener = listener;
        this.itemClickListener = itemClickListener;
        init();
    }

    private void init(){
        addItemType(ItemType.BANNER, R.layout.home_top_banner);
        addItemType(ItemType.GOODS, R.layout.home_goods_item);
        addItemType(ItemType.INQUIRY, R.layout.home_inquiry_item);
        setOnItemClickListener(itemClickListener);
    }

    @Override
    protected MulitipleViewHolder createBaseViewHolder(View view) {
        return MulitipleViewHolder.creat(view);
    }
    public static MulitipleItemAdapter creat(List<MulitipleItem> data, com.bigkoo.convenientbanner.listener.OnItemClickListener  listener
            , OnItemClickListener itemClickListener) {
        return new MulitipleItemAdapter(data ,listener, itemClickListener);
    }
    @Override
    protected void convert(MulitipleViewHolder helper, MulitipleItem item) {
        switch (item.getItemType()){
            case ItemType.GOODS:
                {
                    String id = item.getField(ItemFileds.ID);
                    String price = item.getField(ItemFileds.PRICE);
                    String nickname = item.getField(ItemFileds.USER_NICK_NAME);
                    String avatar = item.getField(ItemFileds.USER_AVATAR);
                    String posttime = item.getField(ItemFileds.POST_TIME);
                    String content = item.getField(ItemFileds.CONTENT);
                    ArrayList<String> goodsImages = item.getField(ItemFileds.IMAGE_URLS);
                    helper.setText(R.id.tv_price,price)
                            .setText(R.id.tv_user_nickname,nickname)
                            .setText(R.id.tv_post_time,posttime)
                            .setText(R.id.tv_goods_content,content);
                    GlideUtil.loadImageViewSizeError(mContext,avatar
                            ,40,40,(ImageView) helper.getView(R.id.iv_user_avatar)
                            , R.drawable.user_error_image);
                    loadGoodsImage(goodsImages,helper);
                }
                break;
            case ItemType.BANNER:
                if (!isLoadedBanner){
                    String id = item.getField(ItemFileds.ID);
                    ArrayList<String> bannerImages = item.getField(ItemFileds.BANNERS);
                    ConvenientBanner<String> banner = helper.getView(R.id.cb_top_banner);
                    BannerCreator.setDefault(banner,bannerImages,bannerItemListener);
                    isLoadedBanner = true;
                }
                break;
            case ItemType.INQUIRY:
            {
                String id = item.getField(ItemFileds.ID);
                String nickname = item.getField(ItemFileds.USER_NICK_NAME);
                String avatar = item.getField(ItemFileds.USER_AVATAR);
                String posttime = item.getField(ItemFileds.POST_TIME);
                String content = item.getField(ItemFileds.CONTENT);
                helper.setText(R.id.tv_user_nickname,nickname)
                        .setText(R.id.tv_post_time,posttime)
                        .setText(R.id.tv_inquiry_content,content);
                GlideUtil.loadImageViewSizeError(mContext,avatar
                        ,40,40,(ImageView) helper.getView(R.id.iv_user_avatar)
                        , R.drawable.user_error_image);

            }
                break;
            default:
                break;
        }
    }
    private void loadGoodsImage(ArrayList<String> goodsImages,MulitipleViewHolder helper){
        int size = goodsImages.size();
        size = size>3?3:size;
        switch (size){
            case 3:
                GlideUtil.loadpPreImageViewErrorSize(mContext,goodsImages.get(2)
                    ,100,100,(ImageView) helper.getView(R.id.iv_right_image),
                    R.drawable.errorimage,0.5f);
            case 2:
                GlideUtil.loadpPreImageViewErrorSize(mContext,goodsImages.get(1)
                        ,100,100,(ImageView) helper.getView(R.id.iv_center_image)
                        , R.drawable.errorimage,0.5f);
            case 1:
                GlideUtil.loadpPreImageViewErrorSize(mContext,goodsImages.get(0)
                        ,100,100,(ImageView) helper.getView(R.id.iv_left_image)
                        , R.drawable.errorimage,0.5f);
            default:break;
        }
    }
}
