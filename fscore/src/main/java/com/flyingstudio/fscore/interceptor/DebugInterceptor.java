package com.flyingstudio.fscore.interceptor;

import android.support.annotation.RawRes;

import com.flyingstudio.fscore.utils.FileUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by guopu on 2017/10/15.
 */

public class DebugInterceptor extends BaseInterceptor {
    private final String DEBUG_URL;
    private final int RAW_ID;

    public DebugInterceptor(String url, int rawId) {
        DEBUG_URL = url;
        RAW_ID = rawId;
    }
    private Response getResponse(Interceptor.Chain chain, String json){
        return new Response.Builder()
                .code(200)
                .addHeader("Content-Type","application/json")
                .message("ok")
                .protocol(Protocol.HTTP_1_1)
                .request(chain.request())
                .body(ResponseBody.create(MediaType.parse("application/json"),json))
                .build();
    }
    private Response getResponse(Interceptor.Chain chain, @RawRes int rawId){
        return getResponse(chain, FileUtil.getRawRes(rawId));
    }
    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        String url = chain.request().url().url().toString();
        if (url.contains(DEBUG_URL)){
            return getResponse(chain,RAW_ID);
        }
        return chain.proceed(chain.request());
    }
}
