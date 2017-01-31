package com.ejia.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.yangzhongyu.myapplication.R;

/**
 * Created by yangzhongyu on 2017/1/22.
 */
public class SignFragment extends Fragment {

    private EditText etUserPhone;

    private EditText etRecommandUserPhone;

    private EditText etRecommandUserSignId;

    private View mView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mView = inflater.inflate(R.layout.frament_sign,null,false);
        initView();
        return  mView;
    }

    private void initView() {
        etUserPhone = (EditText) mView.findViewById(R.id.etUserPhone);
        etRecommandUserPhone = (EditText) mView.findViewById(R.id.etRecommanderPhone);
        etRecommandUserSignId = (EditText) mView.findViewById(R.id.et_recommander_signId);


    }
}
