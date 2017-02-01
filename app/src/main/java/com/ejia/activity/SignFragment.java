package com.ejia.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ejia.entity.EJAMessage;
import com.ejia.entity.Sign;
import com.ejia.presenter.SignPresenter;
import com.ejia.view.ISignView;
import com.example.yangzhongyu.myapplication.R;

import java.util.List;

/**
 * Created by yangzhongyu on 2017/1/22.
 */
public class SignFragment extends Fragment implements ISignView{

    private EditText etUserPhone;

    private EditText etRecommandUserPhone;

    private EditText etRecommandUserSignId;

    private EditText etCity;

    private EditText etZoneName;

    private EditText etFloor;

    private EditText etDepartment;

    private EditText etRoomNum;

    private Button btnApply;

    private View mView;

    private SignPresenter mSignPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mView = inflater.inflate(R.layout.frament_sign,null,false);
        mSignPresenter = new SignPresenter(this);
        initView();
        return  mView;
    }

    private void initView() {
        etUserPhone = (EditText) mView.findViewById(R.id.etUserPhone);
        etRecommandUserPhone = (EditText) mView.findViewById(R.id.etRecommanderPhone);
        etRecommandUserSignId = (EditText) mView.findViewById(R.id.et_recommander_signId);

        etCity = (EditText) mView.findViewById(R.id.et_city);
        etZoneName = (EditText) mView.findViewById(R.id.et_zoneName);
        etFloor = (EditText) mView.findViewById(R.id.et_floorNum);
        etDepartment = (EditText) mView.findViewById(R.id.et_department);
        etRoomNum = (EditText) mView.findViewById(R.id.et_RoomNum);

        btnApply = (Button) mView.findViewById(R.id.btn_apply);

        btnApply.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                apply();
            }
        });
    }

    private void apply() {
        Sign sign = new Sign();
        String userPhone = etUserPhone.getText().toString();
        String recommandUserPhone = etRecommandUserPhone.getText().toString();
        String recommandUserSignId = etRecommandUserSignId.getText().toString();
        String city = etCity.getText().toString();
        String zoneName = etZoneName.getText().toString();
        String floor = etFloor.getText().toString();
        String department = etDepartment.getText().toString();
        String roomNum = etRoomNum.getText().toString();
        if(!check(userPhone,recommandUserPhone,recommandUserSignId,city,zoneName,floor,department,roomNum)){
            return;
        }
        sign.setUserPhone(userPhone);
        sign.setRecommenderPhone(recommandUserPhone);
        sign.setRecommenderSignId(Integer.parseInt(recommandUserSignId));
        sign.setCity(city);
        sign.setZoneName(zoneName);
        sign.setFloor(floor);
        sign.setDepartment(department);
        sign.setRoomNum(roomNum);

         mSignPresenter.applySign(sign);
    }

    @Override
    public void onResume() {
        super.onResume();
        apply();
    }

    private boolean check(String userPhone, String recommandUserPhone, String recommandUserSignId, String city, String zoneName, String floor, String department, String roomNum) {
        if(userPhone.isEmpty() || recommandUserPhone.isEmpty() || recommandUserSignId.isEmpty() || city.isEmpty() || zoneName.isEmpty() || floor.isEmpty()
                || department.isEmpty() || roomNum.isEmpty()){
            Toast.makeText(getContext(),"请填写完整",Toast.LENGTH_LONG).show();
            return false;
        }
        return  true;
    }

    @Override
    public void onQuerySignList(List<Sign> signs) {

    }

    @Override
    public void onApplySign(EJAMessage message) {
           Log.i("yzy","onApplySign....message="+message.getErrorMsg());
        Toast.makeText(getContext(),message.getErrorMsg(),Toast.LENGTH_LONG).show();
    }
}
