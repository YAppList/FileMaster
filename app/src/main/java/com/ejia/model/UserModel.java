package com.ejia.model;

import android.util.Log;

import com.ejia.entity.EJAMessage;
import com.ejia.entity.User;
import com.ejia.network.ApiService;
import com.ejia.presenter.NetworkListener;
import com.ejia.presenter.UserPresenter;
import com.rupeng.view.constants.Constants;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by yangzhongyu on 2017/1/24.
 */
public class UserModel implements  IUserModel {

    public UserModel(){
    }

    @Override
    public void login(String phone, String psw,NetworkListener listener)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);

        Call<User> call =  apiService.login(ApiService.LOGIN,phone,psw);

        call.enqueue(new MyCallBack<User>(listener));

    }

    @Override
    public void register(String phone, String psw, String yzm,NetworkListener listener) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        Call<EJAMessage> call = apiService.register(ApiService.REGISTER, phone, psw,yzm);

        call.enqueue(new MyCallBack<EJAMessage>(listener));

    }
    class MyCallBack<T> implements Callback<T> {


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
}
