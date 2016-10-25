package com.yuzhiyun.find.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.yuzhiyun.find.R;
import com.yuzhiyun.find.application.App;

import butterknife.ButterKnife;
import cn.bmob.v3.Bmob;

public abstract class BaseActivity extends AppCompatActivity {
    public Context context;
    public String TAG=this.toString();
    public Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayoutView();
        Bmob.initialize(this, App.BMOB_APP_ID);
        ButterKnife.bind(this);
        context=this;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initOther();
    }
    protected abstract  void setLayoutView() ;
    protected abstract void initOther();

}
