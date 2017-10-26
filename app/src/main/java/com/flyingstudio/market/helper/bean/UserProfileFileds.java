package com.flyingstudio.market.helper.bean;


/**
 * Created by guopu on 2017/10/25.
 */

public class UserProfileFileds {

    private String mType;

    private String mContent;

    UserProfileFileds() {
    }

    public UserProfileFileds(String type, String content) {
        this.mType = type;
        this.mContent = content;
    }
    public static UserProfileFiledCreator creator(){
        return new UserProfileFiledCreator();
    }
    public String getType() {
        return mType;
    }

    public void setType(String mType) {
        this.mType = mType;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String mContent) {
        this.mContent = mContent;
    }
}
