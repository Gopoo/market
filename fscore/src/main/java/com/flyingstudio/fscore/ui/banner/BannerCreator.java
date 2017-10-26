package com.flyingstudio.fscore.ui.banner;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;

import java.util.ArrayList;

/**
 * Created by guopu on 2017/10/19.
 */

public class BannerCreator {
    public static void setDefault(ConvenientBanner<String> convenientBanner,
                                  ArrayList<String> banners,
                                  OnItemClickListener clickListener) {

        convenientBanner
                .setPages(new BannerHolderCreator(), banners)
                .setOnItemClickListener(clickListener)
                .startTurning(4000)
                .setCanLoop(true);
    }
}
