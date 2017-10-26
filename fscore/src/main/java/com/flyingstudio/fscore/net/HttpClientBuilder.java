package com.flyingstudio.fscore.net;

import android.content.Context;
import android.support.annotation.Nullable;

import com.flyingstudio.fscore.net.callback.IError;
import com.flyingstudio.fscore.net.callback.IFailure;
import com.flyingstudio.fscore.net.callback.IRequest;
import com.flyingstudio.fscore.net.callback.ISuccess;
import com.flyingstudio.fscore.ui.loader.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by guopu on 2017/10/12.
 */

public class HttpClientBuilder {
    private String mUrl;
    private String mFile_name;
    private String mFfile_path;
    private Map<String,Object> mParams;
    private ISuccess mSuccess;
    private IFailure mFailure;
    private IError mError;
    private IRequest mRequest;
    private RequestBody mBody;
    private Context mContext;
    private Enum<LoaderStyle> mStyle;
    private File mFile;
    public HttpClientBuilder url(String mUrl) {
        this.mUrl = mUrl;
        return this;
    }

    public HttpClientBuilder params(@Nullable Map<String, Object> mParams) {
        checkParams();
        this.mParams = mParams;
        return this;
    }

    public HttpClientBuilder params(String key, Object value) {
        checkParams();
        mParams.put(key,value);
        return this;
    }

    public HttpClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),raw);
        return this;
    }

    public HttpClientBuilder success(ISuccess mSuccess) {
        this.mSuccess = mSuccess;
        return this;
    }

    public HttpClientBuilder failure(IFailure mFailure) {
        this.mFailure = mFailure;
        return this;
    }

    public HttpClientBuilder error(IError mError) {
        this.mError = mError;
        return this;
    }

    public HttpClientBuilder request(IRequest mRequest) {
        this.mRequest = mRequest;
        return this;
    }

    public HttpClientBuilder loader(Context context, @Nullable Enum<LoaderStyle> style) {
        this.mContext = context;
        this.mStyle = style;
        return this;
    }
    public HttpClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }
    public HttpClientBuilder file(String name, String path) {
        this.mFile_name = name;
        this.mFfile_path = path;
        return this;
    }
    private void checkParams() {
        if (mParams==null){
            mParams = new WeakHashMap<>();
        }
    }
    public HttpClient build() {
        return new HttpClient(mUrl,mParams,mSuccess,mFailure,mError,mRequest,mBody, mContext,mFile, mFile_name, mFfile_path, mStyle);
    }
}
