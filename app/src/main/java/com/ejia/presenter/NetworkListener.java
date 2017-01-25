package com.ejia.presenter;

/**
 * Created by yangzhongyu on 2017/1/24.
 */
public interface NetworkListener<T> {
    public void onResponse(T t) ;

    public void onFailure() ;
}
