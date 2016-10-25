package com.yuzhiyun.find.LoginAndRegister.controller.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatEditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.yuzhiyun.find.LoginAndRegister.model.proxy.UserProxy;
import com.yuzhiyun.find.R;
import com.yuzhiyun.find.base.BaseActivity;
import com.yuzhiyun.find.main.controller.activity.MainActivity;

import butterknife.Bind;
import butterknife.OnClick;
import cn.bmob.v3.BmobInstallation;

public class RegisterActivity extends BaseActivity implements UserProxy.ISignUpListener {

    @Bind(R.id.tvLogin)
    TextView tvLogin;

    @Bind(R.id.etUserName)
    AppCompatEditText etUserName;

    @Bind(R.id.etUserPwd)
    AppCompatEditText etUserPwd;

    @Bind(R.id.etUserPwdAgain)
    AppCompatEditText etUserPwdAgain;

    @Bind(R.id.btnRegister)
    Button btnRegister;

    //用户代理，其中设置了多个接口
    UserProxy userProxy;

    ProgressDialog progress;

    @Override
    protected void setLayoutView() {
        setContentView(R.layout.activity_register);
    }

    @Override
    protected void initOther() {
        getSupportActionBar().setTitle("");
        userProxy = new UserProxy(context);
        userProxy.setOnSignUpListener(this);
    }

    @OnClick(R.id.tvLogin)
    public void tvLogin() {
        startActivity(new Intent(context, LoginActivity.class));
    }

    @OnClick(R.id.btnRegister)
    public void btnRegister() {
        if (etUserPwd.getText().toString().trim().equals(etUserPwdAgain.getText().toString().trim())) {
            //传入用户名、密码
            progress = new ProgressDialog(context);
            progress.show();
            userProxy.signUp(etUserName.getText().toString().trim(), etUserPwd.getText().toString().trim());

        } else
            Toast.makeText(RegisterActivity.this, "两次输入密码不一致", Toast.LENGTH_SHORT).show();
    }


    /**
     * 注册成功回调接口
     */
    @Override
    public void onSignUpSuccess() {
        progress.dismiss();

        Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(context, MainActivity.class));

    }

    /**
     * 注册失败回调接口
     */
    @Override
    public void onSignUpFailure(String msg) {
        progress.dismiss();
        Toast.makeText(RegisterActivity.this, "注册失败" + msg, Toast.LENGTH_SHORT).show();

    }
}
