package com.dash.a08_kuangjia.view.IView;

import com.dash.a08_kuangjia.model.bean.LoginBean;

/**
 * Created by Dash on 2017/12/7.
 */
public interface ILogin {
    void onSuccess(LoginBean loginBean);
    void onError(String s);
}
