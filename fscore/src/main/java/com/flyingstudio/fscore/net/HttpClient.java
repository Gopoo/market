package com.flyingstudio.fscore.net;

import android.content.Context;

import com.flyingstudio.fscore.net.callback.IError;
import com.flyingstudio.fscore.net.callback.IFailure;
import com.flyingstudio.fscore.net.callback.IRequest;
import com.flyingstudio.fscore.net.callback.ISuccess;
import com.flyingstudio.fscore.net.callback.RequestCallBack;
import com.flyingstudio.fscore.ui.loader.FlyingLoader;
import com.flyingstudio.fscore.ui.loader.LoaderStyle;

import java.io.File;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

/**
 * Created by guopu on 2017/10/12.
 */

public class HttpClient {
    private final String URL;
    private final Map<String,Object> PARAMS;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final IRequest REQUEST;
    private final RequestBody REQUEST_BODY;
    private final Context CONTEXT;
    private final File FILE;
    private final String FILE_NAME;
    private final String FILE_PATH;
    private final Enum<LoaderStyle> LOADER_STYLE;
     HttpClient(String url, Map<String
             , Object> params
             , ISuccess success
             , IFailure failure
             , IError error
             , IRequest request
             , RequestBody request_body
             , Context context
             , File file
             , String file_name
             , String file_path
             , Enum<LoaderStyle> loader_style) {
         URL = url;
         PARAMS = params;
         SUCCESS = success;
         FAILURE = failure;
         ERROR = error;
         REQUEST = request;
         REQUEST_BODY = request_body;
         CONTEXT = context;
         FILE =file;
         FILE_NAME = file_name;
         FILE_PATH = file_path;
         LOADER_STYLE = loader_style;
     }
     public static final HttpClientBuilder builder(){
         return new HttpClientBuilder();
     }

     public void get(){
         request(HttpMethod.GET);
     }

    public void put(){
        if(REQUEST_BODY !=null){
            if (PARAMS!=null)
                throw new RuntimeException("either REQUEST_BODY or PARAMS should be null");
            request(HttpMethod.PUT_RAW);
            }else {
                request(HttpMethod.PUT);
        }
    }

    public void post(){
        if(REQUEST_BODY !=null){
            if (PARAMS!=null)
                throw new RuntimeException("either REQUEST_BODY or PARAMS should be null");
            request(HttpMethod.POST_RAW);
        }else {
            request(HttpMethod.POST);
        }
    }

    public void delete(){
        request(HttpMethod.DELETE);
    }

    public void download(){
        if (REQUEST!=null)
            REQUEST.onRequestStart();
        new FileDownloader(URL,PARAMS,SUCCESS,ERROR,FAILURE,REQUEST,FILE_NAME,FILE_PATH).startDownload();
    }
     private void request(HttpMethod method){
         if (REQUEST!=null)
             REQUEST.onRequestStart();
         Call<String> call = null;
         HttpService service = HttpCreator.newHttpService();
         switch (method){
             case GET:
                 if (PARAMS!=null)
                 call = service.get(URL,PARAMS);
                 else
                     call = service.get(URL);
                 break;
             case POST:
                 call = service.post(URL,PARAMS);
                 break;
             case POST_RAW:
                 call = service.postraw(URL,REQUEST_BODY);
                 break;
             case PUT:
                 call = service.put(URL,PARAMS);
                 break;
             case PUT_RAW:
                 call = service.putraw(URL,REQUEST_BODY);
                 break;
             case DELETE:
                 call = service.delete(URL,PARAMS);
                 break;
             case UPLOAD:
                 RequestBody requestFile = RequestBody.create(MultipartBody.FORM, FILE);
                 MultipartBody.Part body = MultipartBody.Part.createFormData("file", FILE.getName(), requestFile);
                 call = service.upload(URL,body);
                 break;
             default:
                 break;
         }
         if (call!=null){
             if (CONTEXT!=null)
             FlyingLoader.showLoader(CONTEXT,LOADER_STYLE);
             call.enqueue(getCallBack());
         }
     }
     private RequestCallBack getCallBack(){
         return new RequestCallBack(SUCCESS,FAILURE,ERROR,REQUEST);
     }
}
