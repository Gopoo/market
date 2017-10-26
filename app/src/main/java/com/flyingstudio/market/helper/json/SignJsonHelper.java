package com.flyingstudio.market.helper.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.flyingstudio.fscore.app.AccountManger;
import com.flyingstudio.market.database.user.UserProfile;

/**
 * Created by guopu on 2017/10/17.
 */

public class SignJsonHelper {
    public static final void onSignSuccess(String responce){
        JSONObject jsonObject = JSON.parseObject(responce).getJSONObject("data");
        final long id = jsonObject.getLongValue("id");
        final String username = jsonObject.getString("username");
        final String wechat = jsonObject.getString("wechat");
        final String token = jsonObject.getString("token");
        final String avator = jsonObject.getString("avator");
        final String email = jsonObject.getString("email");
        UserProfile user = new UserProfile();
        user.setId(id);
        user.setAvatar(avator);
        user.setEmail(email);
        user.setToken(token);
        user.setUsername(username);
        user.setWechat(wechat);

//        DatabaseManger.newInstance().get().insertOrReplace(user);
        AccountManger.setAccountSignin();
    }
}
