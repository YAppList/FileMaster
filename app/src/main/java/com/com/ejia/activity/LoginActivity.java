package com.com.ejia.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ejia.entity.User;
import com.ejia.network.ApiService;
import com.ejia.respone.LoginRespone;
import com.ejia.view.IUserView;
import com.example.yangzhongyu.myapplication.R;
import com.rupeng.view.constants.Constants;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by yangzhongyu on 2017/1/22.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener ,IUserView {

    private EditText etUserPhone;
    private EditText etUserPsw;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        Log.i("yzy","initview...");
    }

    private void initView(){
        etUserPhone = (EditText) findViewById(R.id.userPhone);
        etUserPsw = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.login);
        btnLogin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                login(view);
            }
        });
    }

    public void login(View view){
        String phone = etUserPhone.getText().toString();
        String psw = etUserPsw.getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);

        Call<LoginRespone> call =  apiService.login(ApiService.LOGIN_TAG,"aaa","dddd");
        call.enqueue(new Callback<LoginRespone>() {
            @Override
            public void onResponse(Response<LoginRespone> response, Retrofit retrofit) {

                LoginRespone loginRespone = response.body();

                Log.i("yzy","loginRes = " + loginRespone.errorCode);
            }
            @Override
            public void onFailure(Throwable t) {
                Log.i("yzy","error"+t.toString());
            }
        });
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void login(User user) {

    }
}
