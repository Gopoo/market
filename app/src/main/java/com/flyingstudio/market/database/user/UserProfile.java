package com.flyingstudio.market.database.user;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by guopu on 2017/10/17.
 */
@Entity(nameInDb = "user_profile")
public class UserProfile {
    @Id
    private long id;
    private String username;
    private String token;
    private String avatar;
    private String wechat;
    private String email;
    @Generated(hash = 731671351)
    public UserProfile(long id, String username, String token, String avatar,
            String wechat, String email) {
        this.id = id;
        this.username = username;
        this.token = token;
        this.avatar = avatar;
        this.wechat = wechat;
        this.email = email;
    }
    @Generated(hash = 968487393)
    public UserProfile() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getToken() {
        return this.token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getAvatar() {
        return this.avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public String getWechat() {
        return this.wechat;
    }
    public void setWechat(String wechat) {
        this.wechat = wechat;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
