package com.flyingstudio.fscore.fragment.bottom;

import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyingstudio.fscore.R;
import com.flyingstudio.fscore.fragment.FlyingFragment;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;



/**
 * Created by guopu on 2017/10/18.
 */

public abstract class BottomTabsFragment extends FlyingFragment implements View.OnClickListener {
    private final ArrayList<ItemBean> ITEMS_BEANS = new ArrayList<>();
    private final ArrayList<FlyingFragment> ITEMS = new ArrayList<>();
    private final LinkedHashMap<ItemBean,FlyingFragment> TABS = new LinkedHashMap<>();
    private int mCurrentTab = 0;
    //默认为灰色
    private int mNotPressedColor = 0xFF707070;

    LinearLayout item_container = null;
    @ColorInt
    public  int setNotPressedColor(){
        return -1;
    }
    public abstract void setTabs(BottomTabsHolder builder);
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (setNotPressedColor()!=-1){
                mNotPressedColor = setNotPressedColor();
            }
            BottomTabsHolder builder = BottomTabsHolder.builder();
            setTabs(builder);
            TABS.putAll(builder.getAllTabs());
            for (Map.Entry<ItemBean,FlyingFragment> tab :TABS.entrySet()){
                ITEMS_BEANS.add(tab.getKey());
                ITEMS.add(tab.getValue());
            }
}

    @Override
    public Integer setLayoutID() {
        return R.layout.bottom_tabs_fragment;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        item_container = rootView.findViewById(R.id.bottom_items);
        int size = TABS.size();
        for (int i = 0;i<size;i++){
            LayoutInflater.from(getContext()).inflate(R.layout.bottom_bean_layout,item_container);
            final LinearLayout item = (LinearLayout) item_container.getChildAt(i);
            item.setTag(i);
            item.setOnClickListener(this);
            final ImageView image = (ImageView) item.getChildAt(0);
            final TextView title = (TextView) item.getChildAt(1);
            //设置首页图片（Pressed）、文本、文本颜色（Pressed）
            if (i==0){
                image.setImageResource(ITEMS_BEANS.get(0).getPressedPhoto());
                title.setText(ITEMS_BEANS.get(0).getTitle());
                title.setTextColor(ITEMS_BEANS.get(0).getTitlrPressedColor());
            }else {
                //设置图片（NotPressed）、文本、文本颜色（NotPressed）
                image.setImageResource(ITEMS_BEANS.get(i).getPhoto());
                title.setText(ITEMS_BEANS.get(i).getTitle());
                title.setTextColor(mNotPressedColor);
            }
        }
        final FlyingFragment fragments[] = ITEMS.toArray(new FlyingFragment[size]);
        getSupportDelegate().loadMultipleRootFragment(R.id.fragment_container,mCurrentTab,fragments);
    }
    private void resetAllItem(){
        int size = item_container.getChildCount();
        for (int i = 0;i<size;i++){
            final LinearLayout item = (LinearLayout) item_container.getChildAt(i);
            final ImageView image = (ImageView) item.getChildAt(0);
            final TextView title = (TextView) item.getChildAt(1);

            image.setImageResource(ITEMS_BEANS.get(i).getPhoto());
            title.setTextColor(mNotPressedColor);

        }
    }
    @Override
    public void onClick(View v) {
        int tag = (int) v.getTag();
        resetAllItem();
        final LinearLayout item = (LinearLayout)v;
        final ImageView image = (ImageView) item.getChildAt(0);
        final TextView title = (TextView) item.getChildAt(1);
        image.setImageResource(ITEMS_BEANS.get(tag).getPressedPhoto());
        title.setTextColor(ITEMS_BEANS.get(tag).getTitlrPressedColor());
        getSupportDelegate().showHideFragment(ITEMS.get(tag), ITEMS.get(mCurrentTab));
        mCurrentTab = tag;
    }
}
