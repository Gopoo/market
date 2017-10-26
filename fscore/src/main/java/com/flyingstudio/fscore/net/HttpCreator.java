package com.flyingstudio.fscore.net;

import com.flyingstudio.fscore.app.Flying;

import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by guopu on 2017/10/12.
 */

public class HttpCreator {
    public static final WeakHashMap<String ,Object> PARAMS = new WeakHashMap<>();
    private static final ArrayList<Interceptor> INTERCEPTORS = Flying.getInterceptors();
    public static final HttpService newHttpService(){
        return HttpServiceHolder.HTTP_SERVICE;
    }
    public static final Retrofit newRetrofit(){
        return RetrofitHolder.HTTP_CLIENT;
    }
    public static final OkHttpClient newOkHttp(){
        return OKHttpHolder.OK_HTTP_CLIENT;
    }
    public static final class RetrofitHolder{
        public static final String API_HOST = Flying.getApiHost();
        public static final Retrofit HTTP_CLIENT = new Retrofit.Builder()
                .baseUrl(API_HOST)
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(OKHttpHolder.OK_HTTP_CLIENT)
                .build();

    }
    public static final class OKHttpHolder{
        private static final int CONNECT_TIME_OUT = 6;
        private static final OkHttpClient.Builder BUILDER = new OkHttpClient.Builder();
        private static final OkHttpClient.Builder addInterceptors(){
            if (INTERCEPTORS==null||INTERCEPTORS.isEmpty()){
                return BUILDER;
            }
            for (Interceptor i :INTERCEPTORS){
                BUILDER.addInterceptor(i);
            }
            return BUILDER;
        }

        public static final OkHttpClient OK_HTTP_CLIENT = addInterceptors()
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .build();
    }
    public static final class HttpServiceHolder{
        private static final HttpService HTTP_SERVICE = RetrofitHolder.HTTP_CLIENT.create(HttpService.class);
    }

}
