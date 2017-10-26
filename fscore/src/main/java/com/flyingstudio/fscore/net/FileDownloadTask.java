package com.flyingstudio.fscore.net;

import android.os.AsyncTask;

import com.flyingstudio.fscore.net.callback.IError;
import com.flyingstudio.fscore.net.callback.IRequest;
import com.flyingstudio.fscore.net.callback.ISuccess;
import com.flyingstudio.fscore.utils.FileUtil;

import java.io.File;
import java.io.InputStream;

import okhttp3.ResponseBody;

/**
 * Created by guopu on 2017/10/13.
 */

public class FileDownloadTask extends AsyncTask<Object,Integer,File> {

    private final ISuccess mSuccess;
    private final IError mError;
    private final IRequest mRequest;
    private static final int FILE_SAVE_ERROR_CODE =1000;
    public FileDownloadTask(ISuccess mSuccess, IError mError, IRequest mRequest) {
        this.mSuccess = mSuccess;
        this.mError = mError;
        this.mRequest = mRequest;
    }

    @Override
    protected File doInBackground(Object... params) {
        String path = (String) params[0];
        String name = (String) params[1];
        final ResponseBody body = (ResponseBody) params[2];
        final InputStream is = body.byteStream();
        File file = FileUtil.writeToDisk(is,path,name);
        return file;
    }
    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if (file!=null){
            FileUtil.autoInstallApk(file);
            if (mSuccess!=null)
                mSuccess.onSuccess("下载成功:"+file.getName());
        } else {
            if (mError!=null)
                mError.onError(FILE_SAVE_ERROR_CODE,"Save file error,file is null.");
        }
        if(mRequest!=null)
            mRequest.onRequestEnd();
    }


}
