package com.ejia.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.ejia.entity.Sign;
import com.example.yangzhongyu.myapplication.R;
import com.rupeng.view.constants.Constants;
import com.rupeng.view.utility.SharePreferenceUtil;

/**
 * Created by yangzhongyu on 2017/1/31.
 */
public class SignDetailActivity extends  BaseActivity {

    private Sign mSign;

    private EditText etUserPhone;

    private EditText etRecommandUserPhone;

    private EditText etRecommandUserSignId;

    private EditText etCity;

    private EditText etZoneName;

    private EditText etFloor;

    private EditText etDepartment;

    private EditText etRoomNum;

    private TextView tvSignStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my_sign_detail);

        Intent intent = getIntent();
        if(intent != null){
            mSign = (Sign) intent.getSerializableExtra("sign");
        }
        initView();

    }

    private void initView() {
        etUserPhone = (EditText) findViewById(R.id.etUserPhone);
        etUserPhone.setText(mSign.getUserPhone());

        etRecommandUserPhone = (EditText) findViewById(R.id.etRecommanderPhone);
        etRecommandUserPhone.setText(mSign.getRecommenderPhone());

        etRecommandUserSignId = (EditText) findViewById(R.id.et_recommander_signId);
        etRecommandUserSignId.setText(mSign.getRecommenderSignId()+"");

        etCity = (EditText) findViewById(R.id.et_city);
        etCity.setText(mSign.getCity());

        etZoneName = (EditText) findViewById(R.id.et_zoneName);
        etZoneName.setText(mSign.getZoneName());

        etFloor = (EditText) findViewById(R.id.et_floorNum);
        etFloor.setText(mSign.getFloor());

        etDepartment = (EditText) findViewById(R.id.et_department);
        etDepartment.setText(mSign.getDepartment());

        etRoomNum = (EditText) findViewById(R.id.et_RoomNum);
        etRoomNum.setText(mSign.getRoomNum());

        tvSignStatus = (TextView) findViewById(R.id.tv_sign_status);
        tvSignStatus.setText(Constants.getSignStatusDesc(mSign.getSignStatus()));

    }
}
