package com.ejia.model;

import com.ejia.presenter.NetworkListener;

/**
 * Created by yangzhongyu on 2017/1/31.
 */
public interface ISignModel {
    public void querySignListByPhone(String phone, NetworkListener listener);
}
