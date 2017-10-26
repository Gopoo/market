package com.flyingstudio.fscore.utils;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.flyingstudio.fscore.app.Flying;

/**
 * Created by guopu on 2017/10/12.
 */

public class DimenUtil {
    public static int getScreenWidth(){
        final Resources resources = Flying.getAppContext().getResources();
        final DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.widthPixels;
    }
    public static int getScreenHeight(){
        final Resources resources = Flying.getAppContext().getResources();
        final DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.heightPixels;
    }
}
