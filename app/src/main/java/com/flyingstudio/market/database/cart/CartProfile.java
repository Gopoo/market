package com.flyingstudio.market.database.cart;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by guopu on 2017/10/26.
 */
@Entity(nameInDb = "cart_profile")
public class CartProfile {
    @Id
    private long id;
    private String price;
    private String content;
    private String time;
    private String imageUrl;
    @Generated(hash = 2045921596)
    public CartProfile(long id, String price, String content, String time,
            String imageUrl) {
        this.id = id;
        this.price = price;
        this.content = content;
        this.time = time;
        this.imageUrl = imageUrl;
    }
    @Generated(hash = 1506129651)
    public CartProfile() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getPrice() {
        return this.price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getImageUrl() {
        return this.imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
