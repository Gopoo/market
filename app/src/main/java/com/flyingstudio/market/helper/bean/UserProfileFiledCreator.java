package com.flyingstudio.market.helper.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guopu on 2017/10/24.
 */

public class UserProfileFiledCreator {
    private List<UserProfileFileds>  items = new ArrayList<>();

    public UserProfileFiledCreator add(UserProfileFileds item){
        items.add(item);
        return this;
    }
    public UserProfileFiledCreator add(String type , String content){
        UserProfileFileds i = new UserProfileFileds(type,content);
        items.add(i);
        return this;
    }
    public UserProfileFiledCreator addAll(List<UserProfileFileds>  items){
        this.items.addAll(items);
        return this;
    }
    public List<UserProfileFileds> crate(){
        return items;
    }
}
