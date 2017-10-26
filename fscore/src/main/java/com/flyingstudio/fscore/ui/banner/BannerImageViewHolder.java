package com.flyingstudio.fscore.ui.banner;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.flyingstudio.fscore.utils.GlideUtil;

/**
 * Created by guopu on 2017/10/19.
 */

public class BannerImageViewHolder implements Holder<String> {
    private ImageView imageView;
    @Override
    public View createView(Context context) {
        imageView = new ImageView(context);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, int position, String data) {
        GlideUtil.loadPreImageView(context,data
                ,imageView,0.3f);
    }
}
