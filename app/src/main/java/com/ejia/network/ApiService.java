package com.ejia.network;

import com.ejia.entity.User;
import com.ejia.respone.LoginRespone;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface ApiService {

    public static final String LOGIN_TAG = "login";

    @GET("/eja/servlet/UserServlet")
    Call<LoginRespone> login(@Query("method") String method,@Query("phone") String phone,@Query("psw") String psw);
}
