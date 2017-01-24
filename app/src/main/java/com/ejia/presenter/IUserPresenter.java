package com.ejia.presenter;

import com.ejia.entity.User;

/**
 * Created by yangzhongyu on 2017/1/24.
 */
public interface IUserPresenter {
    User login(String userPhone,String psw);
}
