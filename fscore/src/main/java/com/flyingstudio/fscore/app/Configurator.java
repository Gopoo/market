package com.flyingstudio.fscore.app;

import android.content.Context;
import android.os.Handler;

import java.util.ArrayList;
import java.util.WeakHashMap;

import okhttp3.Interceptor;

/**
 * Created by guopu on 2017/10/11.
 */

public class Configurator {
    private static final WeakHashMap<String,Object> CONFIGS = new WeakHashMap<>();
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();
    private static final Handler HANDLER = new Handler();

    private Configurator(){
        CONFIGS.put(ConfigurationTypes.CONFIG_READY.name(),false);
        CONFIGS.put(ConfigurationTypes.HANDLER.name(),HANDLER);
    }
    private static final class Holder{
        private static final Configurator CONFIGURATOR = new Configurator();
    }
    static Configurator newInstance(){
        return Holder.CONFIGURATOR;
    }
    public final void configure(){
        CONFIGS.put(ConfigurationTypes.CONFIG_READY.name(),true);
    }


    public final Configurator setAPIHost(String APIHost){
        CONFIGS.put(ConfigurationTypes.API_HOST.name(),APIHost);
        return this;
    }
    public final Configurator setContext(Context context){
        CONFIGS.put(ConfigurationTypes.APP_CONTEXT.name(),context);
        return this;
    }
    public final Configurator addInterceptor(Interceptor interceptor){
        INTERCEPTORS.add(interceptor);
        CONFIGS.put(ConfigurationTypes.INTERCEPTORS.name(),INTERCEPTORS);
        return this;
    }

    final void checkReady(){
        final boolean isReady = (boolean) CONFIGS.get(ConfigurationTypes.CONFIG_READY.name());
        if (!isReady){
            throw new RuntimeException("Configurator is not ready,must call configure()");
        }
    }
    final WeakHashMap<String,Object> getConfigs(){
        return CONFIGS;
    }
    final <T> T getConfiguration(Enum<ConfigurationTypes> type){
        checkReady();
        T t = (T) CONFIGS.get(type.name());
        return t;
    }
}
