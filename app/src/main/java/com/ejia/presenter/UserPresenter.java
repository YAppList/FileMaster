package com.ejia.presenter;

import com.ejia.entity.User;
import com.ejia.model.IUserModel;
import com.ejia.model.UserModel;
import com.ejia.view.IUserView;

/**
 * Created by yangzhongyu on 2017/1/24.
 */
public class UserPresenter implements IUserPresenter {

    private IUserModel mUserMode;
    private IUserView mUserView;

    public UserPresenter(IUserView view){
        mUserView = view;
        mUserMode = new UserModel();
    }

    @Override
    public User login(String phone,String psw) {

        User user = mUserMode.login();

        mUserView.login(user);
        return user;
    }
}
