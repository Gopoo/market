package com.flyingstudio.fscore.ui.loader;

import android.content.Context;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.WeakHashMap;

/**
 * Created by guopu on 2017/10/12.
 */

public class LoaderCreator {
    private static final WeakHashMap<String,Indicator> LOADERRS = new WeakHashMap<>();

     static AVLoadingIndicatorView createLoader(Context context, String name){
        AVLoadingIndicatorView view = new AVLoadingIndicatorView(context);
        Indicator indicator = LOADERRS.get(name);
        if (indicator==null) {
            if (!name.contains(".")) {
                StringBuilder builder = new StringBuilder();
                builder.append(view.getClass().getPackage().getName());
                builder.append(".indicators.");
                builder.append(name);
                indicator = getIndicator(builder.toString());
                if (indicator==null)
                    return null;
                view.setIndicator(indicator);
                LOADERRS.put(name,indicator);
                return view;
            }
            return null;
        }else {
            view.setIndicator(indicator);
            return view;
        }
    }
    private static Indicator getIndicator(String className){
        try {
            Class<?> indicator = Class.forName(className);
            return (Indicator) indicator.newInstance();
        } catch (Exception e) {
            return null;
        }
    }
}
