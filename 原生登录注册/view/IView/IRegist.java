package com.dash.a08_kuangjia.view.IView;

import com.dash.a08_kuangjia.model.bean.RegistBean;

/**
 * Created by Dash on 2017/12/7.
 */
public interface IRegist {
    void onSuccess(RegistBean registBean);
    void onError(String s);

}
