package com.yuzhiyun.find.LoginAndRegister.controller.activity;

import android.app.ProgressDialog;
import android.content.Intent;
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

public class LoginActivity extends BaseActivity implements UserProxy.ILoginListener{

    @Bind(R.id.tvRegister)
    TextView tvRegister;

    @Bind(R.id.etUserName)
    AppCompatEditText etUserName;

    @Bind(R.id.etUserPwd)
    AppCompatEditText etUserPwd;


    @Bind(R.id.btnLogin)
    Button btnLogin;

    //用户代理，其中设置了多个接口
    UserProxy userProxy;

    ProgressDialog progress;
    @Override
    protected void setLayoutView() {
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initOther() {

        getSupportActionBar().setTitle("");
        userProxy = new UserProxy(context);
        userProxy.setOnLoginListener(this);

    }

    @OnClick(R.id.tvRegister)
    public void tvRegister() {
        startActivity(new Intent(context, RegisterActivity.class));
    }
    @OnClick(R.id.btnLogin)
    public void btnLogin() {
            //传入用户名、密码
            progress = new ProgressDialog(context);
            progress.show();
            userProxy.login(etUserName.getText().toString().trim(), etUserPwd.getText().toString().trim());
    }


    @Override
    public void onLoginSuccess() {
        progress.dismiss();
        Toast.makeText(context, "登录成功", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(context, MainActivity.class));
    }

    @Override
    public void onLoginFailure(String msg) {
        progress.dismiss();
        Toast.makeText(context, "登录失败"+msg, Toast.LENGTH_SHORT).show();
    }
}
