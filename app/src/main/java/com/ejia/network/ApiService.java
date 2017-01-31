package com.ejia.network;

import com.ejia.entity.EJAMessage;
import com.ejia.entity.Sign;
import com.ejia.entity.User;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

public interface ApiService {

    public static final String LOGIN = "login";
    public static final String REGISTER = "register";
    public static final String SIGNLIST = "signlist";
    public static final String RECOMMEND_SIGN_LIST = "recommend_sign_list";


    public static final String ENDPOINT  =  "http://192.168.1.104:8784";

    @GET("/eja/servlet/UserServlet")
    Call<User> login(@Query("method") String method, @Query("phone") String phone, @Query("psw") String psw);

    @GET("/eja/servlet/UserServlet")
    Call<EJAMessage> register(@Query("method") String method, @Query("phone") String phone, @Query("psw") String psw,@Query("yzm") String yzm);

    @GET("/eja/servlet/SignServlet")
    Call<List<Sign>>  getSignListByPhone(@Query("method") String method, @Query("phone") String phone);

    @GET("/eja/servlet/SignServlet")
    Call<List<Sign>>  getSignListByRecommendPhone(@Query("method") String method, @Query("recommendPhone") String phone);




}
