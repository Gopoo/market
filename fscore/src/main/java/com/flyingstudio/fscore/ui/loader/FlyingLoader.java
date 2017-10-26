package com.flyingstudio.fscore.ui.loader;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.flyingstudio.fscore.R;
import com.flyingstudio.fscore.utils.DimenUtil;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

/**
 * Created by guopu on 2017/10/12.
 */

public class FlyingLoader {
    private static final ArrayList<AppCompatDialog> LOADERS = new ArrayList<>();
    private static final String BASE_STYLE = LoaderStyle.BallSpinFadeLoaderIndicator.name();

    private static void showLoader(Context context, String type){
        AppCompatDialog dialog = new AppCompatDialog(context, R.style.loader_dialog_fullscreen);
        AVLoadingIndicatorView view = LoaderCreator.createLoader(context,type);
        dialog.setContentView(view);
        int width = DimenUtil.getScreenWidth();
        int height = DimenUtil.getScreenHeight();
        Window dialogWindow = dialog.getWindow();
        if (dialogWindow!=null) {
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.height = height/8;
            lp.width = width/8;
            lp.gravity = Gravity.CENTER;
        }

        LOADERS.add(dialog);
        dialog.show();
    }

    public static void showLoader(Context context, @Nullable Enum<LoaderStyle> type){
        if (type ==null){
            showLoader(context);
        }else {
            showLoader(context,type.name());
        }
    }

    public static void showLoader(Context context){
        showLoader(context,BASE_STYLE);
    }

    public static void cloaseLoader(){
        for (AppCompatDialog dialog:LOADERS){
            if (dialog!=null){
                if (dialog.isShowing()){
                    dialog.cancel();
                }
            }
        }
    }
}
