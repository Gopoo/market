package com.flyingstudio.fscore.app;

import com.flyingstudio.fscore.utils.FlyingPreferences;

/**
 * Created by guopu on 2017/10/17.
 */

public class AccountManger {
    private static final String SIGNIN_FLAG = "ACCOUNT_SIGNIN_FLAG";
    public static void setAccountSignin(){
        FlyingPreferences.setAppFlags(SIGNIN_FLAG,true);
    }
    public static void setAccountNotSignin(){
        FlyingPreferences.setAppFlags(SIGNIN_FLAG,false);
    }
    public static boolean getSigninState(){
        return FlyingPreferences.getAppFlags(SIGNIN_FLAG,false);
    }
    public static void isAccountanSignin(ISigninCallBack signinCallBack){
        if (getSigninState()){
            signinCallBack.onSignin();
        }else {
            signinCallBack.onNotSignin();
        }
    }
}
