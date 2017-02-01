package com.ejia.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ejia.entity.EJAMessage;
import com.ejia.entity.User;
import com.ejia.presenter.UserPresenter;
import com.ejia.view.IUserView;
import com.example.yangzhongyu.myapplication.R;
import com.rupeng.view.utility.EncryptUtils;

/**
 * Created by yangzhongyu on 2017/1/23.
 */
public class RegisterActivity extends BaseActivity implements IUserView {
   //
    private Button mBtnRegister;
    private UserPresenter mUserPreenter;
    private EditText etUserPhone;
    private EditText etUserPsw;
    private EditText etYzm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUserPreenter = new UserPresenter(this);
        setContentView(R.layout.activity_register);

        initView();
        mBtnRegister = (Button) findViewById(R.id.register);
        mBtnRegister.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String psw = EncryptUtils.Encrypt(etUserPsw.getText().toString(),"SHA-256");
                mUserPreenter.register(etUserPhone.getText().toString(),psw,etYzm.getText().toString());

            }
        });
    }

    private void initView() {
        etUserPhone = (EditText) findViewById(R.id.userPhone);
        etUserPsw = (EditText) findViewById(R.id.password);
        etYzm = (EditText) findViewById(R.id.etyzm);
    }

    @Override
    public void login(User user) {

    }

    @Override
    public void register(EJAMessage messge) {
        Toast.makeText(this,messge.getErrorMsg(),Toast.LENGTH_LONG).show();
    }

}
