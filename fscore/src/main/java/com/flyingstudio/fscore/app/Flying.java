package com.flyingstudio.fscore.app;

import android.content.Context;
import android.os.Handler;

import java.util.ArrayList;

import okhttp3.Interceptor;

/**
 * Created by guopu on 2017/10/11.
 */

public class Flying {
    public static final Configurator init(Context context){
        getConfigurator().setContext(context);
        return getConfigurator();
    }
    private static final Configurator getConfigurator(){
        return Configurator.newInstance();
    }
    public static final Context getAppContext(){
        return (Context)getConfigurator().getConfiguration(ConfigurationTypes.APP_CONTEXT);
    }

    public static final ArrayList<Interceptor> getInterceptors(){
        return (ArrayList<Interceptor>)getConfigurator().getConfiguration(ConfigurationTypes.INTERCEPTORS);
    }

    public static final String getApiHost(){
        return (String)getConfigurator().getConfiguration(ConfigurationTypes.API_HOST);
    }

    public static final Handler getHandler(){
        return (Handler) getConfigurator().getConfiguration(ConfigurationTypes.HANDLER);
    }
}
