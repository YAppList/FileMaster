package com.ejia.view;

import com.ejia.entity.EJAMessage;
import com.ejia.entity.Sign;

import java.util.List;

/**
 * Created by yangzhongyu on 2017/1/31.
 */
public interface ISignView {

    void onQuerySignList(List<Sign> signs);

    void onApplySign(EJAMessage message);
}
