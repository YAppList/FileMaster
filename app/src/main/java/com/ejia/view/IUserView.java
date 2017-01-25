package com.ejia.view;

import com.ejia.entity.EJAMessage;
import com.ejia.entity.User;

/**
 * Created by yangzhongyu on 2017/1/24.
 */
public interface  IUserView {
      void   login(User user);
      void   register(EJAMessage messge);
}
