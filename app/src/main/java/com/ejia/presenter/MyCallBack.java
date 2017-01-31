package com.ejia.presenter;

import android.util.Log;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by yangzhongyu on 2017/1/31.
 */
public class MyCallBack<T> implements Callback<T> {


    NetworkListener listener;
    public MyCallBack(NetworkListener listener){
        this.listener = listener;
    }
    @Override
    public void onResponse(Response<T> response, Retrofit retrofit) {
        Log.i("yzy","onRespone"+response.body());
        this.listener.onResponse(response.body());

    }

    @Override
    public void onFailure(Throwable t) {
        Log.i("yzy","onFail");
        this.listener.onFailure();
    }
}
