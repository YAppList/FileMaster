package com.ejia.presenter;

import com.ejia.entity.EJAMessage;
import com.ejia.entity.Sign;
import com.ejia.model.ISignModel;
import com.ejia.model.SignModel;
import com.ejia.view.ISignView;

import java.util.List;

/**
 * Created by yangzhongyu on 2017/1/31.
 */
public class SignPresenter implements ISignPresenter {

    private ISignModel mSignModel = new SignModel();

    private ISignView mSignView ;

    public SignPresenter(ISignView signView){
        mSignView = signView;
    }

    @Override
    public void querySignListByPhone(String phone) {
        mSignModel.querySignListByPhone(phone,new NetworkListener<List<Sign>>() {
            @Override
            public void onResponse(List<Sign> signs) {
                  mSignView.onQuerySignList(signs);
            }

            @Override
            public void onFailure() {

            }
        });
    }

    @Override
    public void queryRecommendSignListByPhone(String phone) {
        mSignModel.queryRecommendSignListByPhone(phone,new NetworkListener<List<Sign>>() {
            @Override
            public void onResponse(List<Sign> signs) {
                mSignView.onQueryRecommendSignList(signs);
            }

            @Override
            public void onFailure() {

            }
        });
    }

    @Override
    public void applySign(Sign sign) {
        mSignModel.applySign(sign,new NetworkListener<EJAMessage>() {
            @Override
            public void onResponse(EJAMessage message) {
                mSignView.onApplySign(message);
            }

            @Override
            public void onFailure() {
            }
        });
    }
}
