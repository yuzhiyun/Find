package com.yuzhiyun.find.FindOrRecord.controller.activity;

import android.content.Intent;
import android.widget.Button;
import android.widget.Toast;

import com.yuzhiyun.find.R;
import com.yuzhiyun.find.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

public class FindOrRecordActivity extends BaseActivity {


    @Bind(R.id.btnRecord)
    Button btnRecord ;

    @Bind(R.id.btnFind)
    Button btnFind ;


    @Override
    protected void setLayoutView() {
        setContentView(R.layout.activity_find_or_record);
    }

    @Override
    protected void initOther() {
        getSupportActionBar().setTitle("");

    }

    @OnClick(R.id.btnRecord)
    public void btnRecord(){
        Toast.makeText(FindOrRecordActivity.this, "记录", Toast.LENGTH_SHORT).show();
//        startActivity(new Intent(context,Ma));
    }
    @OnClick(R.id.btnFind)
    public void btnFind(){
        Toast.makeText(FindOrRecordActivity.this, "寻找", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(context,FindActivity.class));
    }

}
