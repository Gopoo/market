package com.flyingstudio.fscore.utils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

import com.flyingstudio.fscore.app.Flying;

/**
 * Created by guopu on 2017/10/16.
 */

public class FlyingPreferences {
    private static final String PREFERENCES = "flying";
    private static final SharedPreferences FLYING_PREFERENCES = PreferenceManager.getDefaultSharedPreferences(Flying.getAppContext());

    private static SharedPreferences getPreference(){
        return FLYING_PREFERENCES;
    }
    public static void addProfile(String name,String value){
        getPreference().edit().putString(name,value).apply();
    }
    public static String getProfile(String name,@Nullable String defaultvalue){
        return getPreference().getString(name,defaultvalue);
    }
    public static void setAppFlags(String name,boolean value){
        getPreference().edit().putBoolean(name,value).apply();
    }
    public static boolean getAppFlags(String name,@Nullable boolean defaultvalue){
        return getPreference().getBoolean(name,defaultvalue);
    }
    public static void removeProfile(String name){
        getPreference().edit().remove(name).apply();
    }
    public static void clearProfiles(){
        getPreference().edit().clear().apply();
    }
}
