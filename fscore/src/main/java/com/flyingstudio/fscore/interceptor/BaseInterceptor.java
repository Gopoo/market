package com.flyingstudio.fscore.interceptor;

import java.util.LinkedHashMap;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;

/**
 * Created by guopu on 2017/10/15.
 */

public abstract class BaseInterceptor implements Interceptor {

    public LinkedHashMap<String,String> getUrlParams(Chain chain){
        HttpUrl url = chain.request().url();
        LinkedHashMap<String,String> params = new LinkedHashMap<>();
        int size = url.querySize();
        for (int i = 0;i<size;i++){
            params.put(url.queryParameterName(i),url.queryParameterValue(i));
        }
        return params;
    }
    public String getUrlParam(Chain chain,String key){
        return getUrlParams(chain).get(key);
    }

    public LinkedHashMap<String,String> getBodyParams(Chain chain){
        FormBody body = (FormBody) chain.request().body();
        LinkedHashMap<String,String> params = new LinkedHashMap<>();
        int size = body.size();
        for (int i = 0;i<size;i++){
            params.put(body.name(i),body.value(i));
        }
        return params;
    }
    public String getBodyParam(Chain chain,String key){
        return getBodyParams(chain).get(key);
    }
}
