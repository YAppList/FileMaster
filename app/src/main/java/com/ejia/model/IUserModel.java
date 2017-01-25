package com.ejia.model;

import com.ejia.entity.User;
import com.ejia.presenter.NetworkListener;

/**
 * Created by yangzhongyu on 2017/1/24.
 */
public interface IUserModel {
    public void login(String phone, String psw, NetworkListener listener);
    public void register(String phone,String psw,String yzm,NetworkListener listener);
}
