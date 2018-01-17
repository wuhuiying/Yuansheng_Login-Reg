package com.dash.a08_kuangjia.presenter;

import com.dash.a08_kuangjia.model.LoginModel;
import com.dash.a08_kuangjia.model.bean.LoginBean;
import com.dash.a08_kuangjia.presenter.interfac.ILoginP;
import com.dash.a08_kuangjia.view.IView.ILogin;
import com.dash.a08_kuangjia.view.activity.LoginActivity;

/**
 * Created by Dash on 2017/12/7.
 */
public class LoginPresenter implements ILoginP{

    private ILogin iLogin;
    private LoginModel loginModel;

    public LoginPresenter(ILogin iLogin) {
        //登录的model
        loginModel = new LoginModel(this);
        this.iLogin = iLogin;

    }

    public void loginUser(String phone, String encode, String loginUrl) {
        loginModel.loginUser(phone, encode, loginUrl);

    }

    @Override
    public void onLoginSuccess(LoginBean loginBean) {
        iLogin.onSuccess(loginBean);
    }

    @Override
    public void onLoginError(String s) {
        iLogin.onError(s);
    }
}
