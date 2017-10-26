package com.flyingstudio.fscore.net.callback;

import android.os.Handler;
import android.os.Looper;

import com.flyingstudio.fscore.ui.loader.FlyingLoader;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by guopu on 2017/10/12.
 */

public class RequestCallBack implements Callback<String> {
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final IRequest REQUEST;
    private static final Handler HANDLER = new Handler(Looper.getMainLooper());
    public RequestCallBack(ISuccess success, IFailure failure, IError error, IRequest request) {
        SUCCESS = success;
        FAILURE = failure;
        ERROR = error;
        REQUEST = request;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()){
            if (call.isExecuted()){
                if (SUCCESS!=null)
                SUCCESS.onSuccess(response.body());
            }else {
                if (FAILURE!=null)
                FAILURE.onFailure();
            }
        }else {
            if (ERROR!=null)
            ERROR.onError(response.code(),response.message());
            if (REQUEST!=null)
            REQUEST.onRequestEnd();
        }
        stopLoader();
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (FAILURE!=null)
        FAILURE.onFailure();
        if (REQUEST!=null)
        REQUEST.onRequestEnd();
        stopLoader();
    }
    private void stopLoader(){
        HANDLER.postDelayed(new Runnable() {
            @Override
            public void run() {
                FlyingLoader.cloaseLoader();
            }
        },1000);
    }
}
