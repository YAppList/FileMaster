package com.ejia.presenter;

import com.ejia.entity.EJAMessage;
import com.ejia.entity.User;
import com.ejia.model.IUserModel;
import com.ejia.model.UserModel;
import com.ejia.view.IUserView;

/**
 * Created by yangzhongyu on 2017/1/24.
 */
public class UserPresenter implements IUserPresenter {

    private IUserModel mUserModel = new UserModel();
    private IUserView mUserView;

    public UserPresenter(IUserView view){
        mUserView = view;
    }

    @Override
    public void  login(String phone,String psw) {

         mUserModel.login(phone,psw,new NetworkListener<User>(){

             @Override
             public void onResponse(User user) {

                 if(mUserView != null){

                     mUserView.login(user);
                 }
             }

             @Override
             public void onFailure() {

             }
         });
    }

    @Override
    public void register(String phone, String psw, String yzm) {
        mUserModel.register(phone,psw,yzm,new NetworkListener<EJAMessage>() {

            @Override
            public void onResponse(EJAMessage msg) {

                if (mUserView != null) {

                    mUserView.register(msg);
                }
            }

            @Override
            public void onFailure() {

            }
        });
    }

}
