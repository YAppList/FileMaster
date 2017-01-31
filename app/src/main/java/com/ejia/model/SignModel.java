package com.ejia.model;

import com.ejia.entity.Sign;
import com.ejia.entity.User;
import com.ejia.network.ApiService;
import com.ejia.presenter.MyCallBack;
import com.ejia.presenter.NetworkListener;

import java.util.List;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by yangzhongyu on 2017/1/31.
 */
public class SignModel implements  ISignModel {
    @Override
    public void querySignListByPhone(String phone, NetworkListener listener) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);

        Call<List<Sign>> call =  apiService.getSignListByPhone(ApiService.SIGNLIST,phone);

        call.enqueue(new MyCallBack<List<Sign>>(listener));
    }
}
