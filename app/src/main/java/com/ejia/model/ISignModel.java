package com.ejia.model;

import com.ejia.entity.Sign;
import com.ejia.presenter.NetworkListener;

/**
 * Created by yangzhongyu on 2017/1/31.
 */
public interface ISignModel {

    public void querySignListByPhone(String phone, NetworkListener listener);

    public void applySign(Sign sign,NetworkListener listener);

    public void queryRecommendSignListByPhone(String phone, NetworkListener listener);
}
