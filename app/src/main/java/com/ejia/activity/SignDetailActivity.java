package com.ejia.activity;

import android.content.Intent;
import android.os.Bundle;

import com.ejia.entity.Sign;

/**
 * Created by yangzhongyu on 2017/1/31.
 */
public class SignDetailActivity extends  BaseActivity {

    private Sign mSign;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        if(intent != null){
            mSign = intent.getParcelableExtra("");
        }

    }
}
