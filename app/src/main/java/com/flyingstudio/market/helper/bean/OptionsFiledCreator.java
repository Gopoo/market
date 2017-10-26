package com.flyingstudio.market.helper.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guopu on 2017/10/24.
 */

public class OptionsFiledCreator {
    private List<OptionsFiled>  items = new ArrayList<>();

    public OptionsFiledCreator add(OptionsFiled item){
        items.add(item);
        return this;
    }
    public OptionsFiledCreator add(int type,int drawable,String content){
        OptionsFiled i = new OptionsFiled(type,drawable,content);
        items.add(i);
        return this;
    }
    public OptionsFiledCreator addAll(List<OptionsFiled>  items){
        this.items.addAll(items);
        return this;
    }
    public List<OptionsFiled> crate(){
        return items;
    }
}
