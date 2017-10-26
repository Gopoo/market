package com.flyingstudio.market.fragments.main.sort;

import java.util.ArrayList;

import me.yokeyword.fragmentation.ISupportFragment;

/**
 * Created by guopu on 2017/10/21.
 */

public class TabsHolder {

    private final ArrayList<String> TITLES = new ArrayList<>();
    private final ArrayList<ISupportFragment> FRAGMENTS = new ArrayList<>();

    private TabsHolder(){}
    public static final TabsHolder creat(){
        return new TabsHolder();
    }

    public TabsHolder addTitle(String title){
        TITLES.add(title);
        return this;
    }
    public TabsHolder addTitles(ArrayList<String> titles){
        TITLES.addAll(titles);
        return this;
    }
    public TabsHolder addFragment(ISupportFragment fragment){
        FRAGMENTS.add(fragment);
        return this;
    }
    public TabsHolder addFragments(ArrayList<ISupportFragment> fragments ){
        FRAGMENTS.addAll(fragments);
        return this;
    }

    public ArrayList<String> getAllTitles(){
        return TITLES;
    }

    public ArrayList<ISupportFragment>  getAllFragments(){
        return FRAGMENTS;
    }
}
