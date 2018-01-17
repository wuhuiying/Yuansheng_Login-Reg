package com.dash.a08_kuangjia.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.dash.a08_kuangjia.R;
import com.dash.a08_kuangjia.application.DashApplication;
import com.dash.a08_kuangjia.model.bean.LoginBean;
import com.dash.a08_kuangjia.presenter.LoginPresenter;
import com.dash.a08_kuangjia.presenter.interfac.ILoginP;
import com.dash.a08_kuangjia.util.ApiUtil;
import com.dash.a08_kuangjia.util.CommonUtils;
import com.dash.a08_kuangjia.util.Md5Encoder;
import com.dash.a08_kuangjia.view.IView.ILogin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity implements ILogin {

    private EditText edit_phone;
    private EditText edit_pwd;
    private String regex;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //控件
        edit_phone = findViewById(R.id.edit_phone);
        edit_pwd = findViewById(R.id.edit_pwd);

        ////手机号的正则
        regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$";

        loginPresenter = new LoginPresenter(this);
    }

    public void login(View view) {
        String phone = edit_phone.getText().toString();
        String pwd = edit_pwd.getText().toString();

        //非空判断
        if (! TextUtils.isEmpty(phone) && !TextUtils.isEmpty(pwd)){
            //验证手机号
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            if (m.matches() && pwd.length()>=6){

                //MD5加密...Md5Encoder.encode(pwd)
                try {
                    loginPresenter.loginUser(phone, pwd, ApiUtil.LOGIN_URL);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }else if (!m.matches()){
                Toast.makeText(this,"请输入正确的手机号",Toast.LENGTH_SHORT).show();
            }else if (pwd.length()<6){
                Toast.makeText(this,"密码长度至少六位",Toast.LENGTH_SHORT).show();
            }


        }else if (TextUtils.isEmpty(phone)){
            Toast.makeText(this,"手机号不能为空",Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(pwd)){
            Toast.makeText(this,"密码不能为空",Toast.LENGTH_SHORT).show();
        }

    }

    public void regist(View view) {
        Intent intent = new Intent(LoginActivity.this,RegistActivity.class);
        startActivity(intent);
    }

    @Override
    public void onSuccess(LoginBean loginBean) {
        String code = loginBean.getCode();
        if ("0".equals(code)){//登陆成功
            DashApplication.isLoginSuccess = true;

            //登录的状态需要存在本地\
            CommonUtils.putBoolean("isLogin",true);

            Toast.makeText(this,loginBean.getMsg(),Toast.LENGTH_SHORT).show();

            LoginActivity.this.finish();

        }else {
            Toast.makeText(this,loginBean.getMsg(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onError(String s) {
        Log.e("LoginActivity",s);
    }
}
