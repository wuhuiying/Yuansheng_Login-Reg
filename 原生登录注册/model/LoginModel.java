package com.dash.a08_kuangjia.model;

import com.dash.a08_kuangjia.model.bean.LoginBean;
import com.dash.a08_kuangjia.model.bean.RegistBean;
import com.dash.a08_kuangjia.presenter.LoginPresenter;
import com.dash.a08_kuangjia.presenter.interfac.ILoginP;
import com.dash.a08_kuangjia.util.CommonUtils;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Dash on 2017/12/7.
 */
public class LoginModel {

    private ILoginP iLoginP;

    public LoginModel(ILoginP iLoginP) {
        this.iLoginP = iLoginP;
    }

    public void loginUser(String phone, String encodePwd, String loginUrl) {
        //注册
        OkHttpClient okHttpClient = new OkHttpClient();

        RequestBody requestBody = new FormBody.Builder()
                .add("mobile",phone)
                .add("password",encodePwd)
                .build();
        Request request = new Request.Builder()
                .post(requestBody)
                .url(loginUrl)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                iLoginP.onLoginError(e.getLocalizedMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    //response.body().string()响应只能一次,,,子线程
                    final String json = response.body().string();

                    //回调给主线程
                    CommonUtils.runOnUIThread(new Runnable() {
                        @Override
                        public void run() {
                            LoginBean loginBean = new Gson().fromJson(json, LoginBean.class);
                            //返回presenter
                            iLoginP.onLoginSuccess(loginBean);
                        }
                    });

                }
            }
        });

    }
}
