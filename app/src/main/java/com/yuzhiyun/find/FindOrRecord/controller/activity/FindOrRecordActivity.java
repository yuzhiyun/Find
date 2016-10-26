package com.yuzhiyun.find.FindOrRecord.controller.activity;

import android.content.Intent;
import android.widget.Button;
import android.widget.Toast;

import com.yuzhiyun.find.LoginAndRegister.controller.activity.LoginActivity;
import com.yuzhiyun.find.R;
import com.yuzhiyun.find.base.BaseActivity;
import com.yuzhiyun.find.main.controller.activity.MainActivity;

import butterknife.Bind;
import butterknife.OnClick;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;

public class FindOrRecordActivity extends BaseActivity {


    @Bind(R.id.btnRecord)
    Button btnRecord;

    @Bind(R.id.btnFind)
    Button btnFind;


    @Override
    protected void setLayoutView() {
        setContentView(R.layout.activity_find_or_record);
    }

    @Override
    protected void initOther() {
        getSupportActionBar().setTitle("");

    }

    @OnClick(R.id.btnRecord)
    public void btnRecord() {
        //如果用户已经登录，则到MainActivity，否则到登录页面
        if (null == BmobUser.getCurrentUser(context)){
//            Toast.makeText(FindOrRecordActivity.this, "用户是"+BmobUser.getCurrentUser(context).getUsername(), Toast.LENGTH_SHORT).show();
            startActivity(new Intent(context, LoginActivity.class));
        }
        else {
            Toast.makeText(FindOrRecordActivity.this, "用户是"+BmobUser.getCurrentUser(context).getUsername(), Toast.LENGTH_SHORT).show();

            startActivity(new Intent(context, MainActivity.class));
        }
    }

    @OnClick(R.id.btnFind)
    public void btnFind() {
        startActivity(new Intent(context, FindActivity.class));
    }

}
