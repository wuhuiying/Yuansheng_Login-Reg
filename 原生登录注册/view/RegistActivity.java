package com.dash.a08_kuangjia.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.dash.a08_kuangjia.R;
import com.dash.a08_kuangjia.model.bean.RegistBean;
import com.dash.a08_kuangjia.presenter.RegistPresenter;
import com.dash.a08_kuangjia.util.ApiUtil;
import com.dash.a08_kuangjia.util.Md5Encoder;
import com.dash.a08_kuangjia.view.IView.IRegist;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistActivity extends AppCompatActivity implements IRegist{

    private EditText edit_phone;
    private EditText edit_pwd;
    private String regex;
    private RegistPresenter registPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        //控件
        edit_phone = findViewById(R.id.edit_phone);
        edit_pwd = findViewById(R.id.edit_pwd);

        //手机号的正则
        regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$";

        //创建presenter
        registPresenter = new RegistPresenter(this);
    }

    public void regist(View view) {
        String phone = edit_phone.getText().toString();
        String pwd = edit_pwd.getText().toString();

        //非空判断
        if (! TextUtils.isEmpty(phone) && !TextUtils.isEmpty(pwd)){
            //验证手机号
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            if (m.matches() && pwd.length()>=6){

                //MD5加密....Md5Encoder.encode(pwd)
                try {
                    registPresenter.registUser(phone, pwd, ApiUtil.REGIST_URL);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }else if (!m.matches()){
                Toast.makeText(RegistActivity.this,"请输入正确的手机号",Toast.LENGTH_SHORT).show();
            }else if (pwd.length()<6){
                Toast.makeText(RegistActivity.this,"密码长度至少六位",Toast.LENGTH_SHORT).show();
            }


        }else if (TextUtils.isEmpty(phone)){
            Toast.makeText(RegistActivity.this,"手机号不能为空",Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(pwd)){
            Toast.makeText(RegistActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onSuccess(RegistBean registBean) {//此时已经是主线程
        String code = registBean.getCode();//0注册成功...1注册失败
        if ("0".equals(code)){
            Toast.makeText(RegistActivity.this,"注册成功,请登录",Toast.LENGTH_SHORT).show();

            RegistActivity.this.finish();
        }else {
            Toast.makeText(RegistActivity.this,registBean.getMsg(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onError(String s) {
        Log.e("RegistActivity",s);
    }
}
