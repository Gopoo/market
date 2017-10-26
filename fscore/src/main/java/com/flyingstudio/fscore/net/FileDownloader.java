package com.flyingstudio.fscore.net;

import android.os.AsyncTask;

import com.flyingstudio.fscore.net.callback.IError;
import com.flyingstudio.fscore.net.callback.IFailure;
import com.flyingstudio.fscore.net.callback.IRequest;
import com.flyingstudio.fscore.net.callback.ISuccess;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by guopu on 2017/10/13.
 */

public class FileDownloader {
    private final String mUl;
    private final Map<String,Object> mParams;
    private final ISuccess mSuccess;
    private final IError mError;
    private final IFailure mFailure;
    private final IRequest mRequest;
    private final String mFileName;
    private final String mFilePath;
    public FileDownloader(String mUl, Map<String, Object> mParams, ISuccess mSuccess , IError mError,IFailure mFailure,IRequest mRequest, String mFileName, String mFilePath) {
        this.mUl = mUl;
        this.mParams = mParams;
        this.mSuccess = mSuccess;
        this.mError = mError;
        this.mRequest = mRequest;
        this.mFailure =mFailure;
        this.mFileName = mFileName;
        this.mFilePath = mFilePath;
    }
    public void startDownload(){
        if (mRequest!=null)
            mRequest.onRequestStart();
        Call<ResponseBody> call = null;
        HttpService service = HttpCreator.newHttpService();
        call = service.download(mUl,mParams);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    new FileDownloadTask(mSuccess,mError,mRequest).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,mFilePath,mFileName,response.body());
                }else {
                    if (mError!=null){
                        mError.onError(response.code(),response.message());
                    }
                    if (mRequest!=null) {
                        mRequest.onRequestEnd();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (mFailure!=null) {
                    mFailure.onFailure();
                }
                if (mRequest!=null) {
                    mRequest.onRequestEnd();
                }
            }
        });
    }
}
