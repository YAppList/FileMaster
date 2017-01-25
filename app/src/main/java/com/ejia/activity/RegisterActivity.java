package com.ejia.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ejia.entity.EJAMessage;
import com.ejia.entity.User;
import com.ejia.presenter.UserPresenter;
import com.ejia.view.IUserView;
import com.example.yangzhongyu.myapplication.R;

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
                mUserPreenter.register(etUserPhone.getText().toString(),etUserPsw.getText().toString(),etYzm.getText().toString());

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

    }

}
