package com.dash.a08_kuangjia.presenter.interfac;

import com.dash.a08_kuangjia.model.bean.RegistBean;

/**
 * Created by Dash on 2017/12/7.
 */
public interface IRegistP {
    void onRegistSuccess(RegistBean registBean);
    void onRegistError(String string);

}
