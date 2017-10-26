package com.flyingstudio.fscore.ui.banner;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * Created by guopu on 2017/10/19.
 */

public class BannerHolderCreator implements CBViewHolderCreator<BannerImageViewHolder> {
    @Override
    public BannerImageViewHolder createHolder() {
        return new BannerImageViewHolder();
    }
}
