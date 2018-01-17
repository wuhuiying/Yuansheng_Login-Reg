package com.dash.a08_kuangjia.presenter;

import com.dash.a08_kuangjia.model.RegistModel;
import com.dash.a08_kuangjia.model.bean.RegistBean;
import com.dash.a08_kuangjia.presenter.interfac.IRegistP;
import com.dash.a08_kuangjia.view.IView.IRegist;
import com.dash.a08_kuangjia.view.activity.RegistActivity;

/**
 * Created by Dash on 2017/12/7.
 */
public class RegistPresenter implements IRegistP{

    private IRegist iRegist;
    private RegistModel registModel;

    public RegistPresenter(IRegist iRegist) {
        this.iRegist = iRegist;

        //创建model
        registModel = new RegistModel(this);
    }

    public void registUser(String phone, String encodePwd, String registUrl) {
        //去model里面注册
        registModel.registUser(phone,encodePwd,registUrl);
    }

    @Override
    public void onRegistSuccess(RegistBean registBean) {
        //回调给registActivity
        iRegist.onSuccess(registBean);
    }

    @Override
    public void onRegistError(String string) {
        iRegist.onError(string);
    }
}
