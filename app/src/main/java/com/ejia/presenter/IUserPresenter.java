package com.ejia.presenter;

import com.ejia.entity.User;

/**
 * Created by yangzhongyu on 2017/1/24.
 */
public interface IUserPresenter {
    void login(String userPhone,String psw);

    void register(String s, String s1, String s2);
}
