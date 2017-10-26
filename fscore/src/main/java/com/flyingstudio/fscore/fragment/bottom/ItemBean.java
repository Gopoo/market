package com.flyingstudio.fscore.fragment.bottom;

/**
 * Created by guopu on 2017/10/18.
 */

public class ItemBean {

    private final int PHOTO;
    private final int PRESSED_PHOTO;
    private final String TITLE;
    private final int TITLE_PRESSED_COLOR;
    public ItemBean(int photo, int pressed_photo, String title, int title_pressed_color) {
        PHOTO = photo;
        PRESSED_PHOTO = pressed_photo;
        TITLE = title;
        TITLE_PRESSED_COLOR = title_pressed_color;
    }

    public int getTitlrPressedColor() {
        return TITLE_PRESSED_COLOR;
    }

    public int getPhoto() {
        return PHOTO;
    }

    public int getPressedPhoto() {
        return PRESSED_PHOTO;
    }


    public String getTitle() {
        return TITLE;
    }
}
