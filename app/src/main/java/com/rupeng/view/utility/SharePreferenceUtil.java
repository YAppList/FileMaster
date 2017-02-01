package com.rupeng.view.utility;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by yangzhongyu on 2017/1/31.
 */
public class SharePreferenceUtil {

    public static void saveUserPhone(Context context,String value){

        SharedPreferences settings = context.getSharedPreferences("setting", 0);

        settings.edit().putString("userPhone",value).commit();

    }

    public static void saveUserToken(Context context,String value){
        SharedPreferences settings = context.getSharedPreferences("setting", 0);

        settings.edit().putString("userToken",value).commit();
    }

    public static String getUserToken(Context context,String value){
        return context.getSharedPreferences("setting",0).getString("userToken","");
    }

    public static String  getUserPhone(Context context){
        return  context.getSharedPreferences("setting",0).getString("userPhone","");

    }


}
