package com.ejia.presenter;

import com.ejia.entity.Sign;

/**
 * Created by yangzhongyu on 2017/1/31.
 */
public interface ISignPresenter {

    public void querySignListByPhone(String phone);

    public void queryRecommendSignListByPhone(String phone);

    public void applySign(Sign sign);
}
