package com.ejia.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ejia.entity.EJAMessage;
import com.ejia.entity.User;
import com.ejia.presenter.IUserPresenter;
import com.ejia.presenter.UserPresenter;
import com.ejia.view.IUserView;
import com.example.yangzhongyu.myapplication.R;
import com.rupeng.view.constants.Constants;

/**
 * Created by yangzhongyu on 2017/1/22.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener ,IUserView {

    private EditText etUserPhone;
    private EditText etUserPsw;
    private EditText etYzm;
    private Button btnLogin;
    private Button btnRegister;

    private IUserPresenter mUserPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView(){
        etUserPhone = (EditText) findViewById(R.id.userPhone);
        etUserPsw = (EditText) findViewById(R.id.password);
        etYzm = (EditText) findViewById(R.id.etyzm);
        btnLogin = (Button) findViewById(R.id.login);
        btnLogin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                login(view);
            }
        });

        btnRegister = (Button) findViewById(R.id.register);
        btnRegister.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    public void login(View view){
        String phone = etUserPhone.getText().toString();
        String psw = etUserPsw.getText().toString();

        mUserPresenter = new UserPresenter(this);
        mUserPresenter.login(phone,psw);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void login(User user) {

        Log.i("yzy","user login = " + user.getErrorCode());
        if(user.getErrorCode().equals(Constants.REQUEST_SUCCESS) ){
               Intent intent = new Intent(LoginActivity.this,EjiaMainActivity.class);
               startActivity(intent);
        }else{
           // Toast.makeText(this,)
        }
    }

    @Override
    public void register(EJAMessage messge) {

    }
}
