package com.dash.a08_kuangjia.presenter.interfac;

import com.dash.a08_kuangjia.model.bean.LoginBean;

/**
 * Created by Dash on 2017/12/7.
 */
public interface ILoginP {
    void onLoginSuccess(LoginBean loginBean);
    void onLoginError(String s);
}
