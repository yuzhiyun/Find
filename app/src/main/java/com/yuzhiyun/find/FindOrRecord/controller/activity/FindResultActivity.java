package com.yuzhiyun.find.FindOrRecord.controller.activity;

import android.content.Intent;
import android.net.Uri;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.yuzhiyun.find.R;
import com.yuzhiyun.find.application.App;
import com.yuzhiyun.find.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

public class FindResultActivity extends BaseActivity {


    @Bind(R.id.tvNumber)
    TextView tvNumber;

    @Bind(R.id.tvName)
    TextView tvName;

    @Bind(R.id.tvPhone)
    TextView tvPhone;

    @Bind(R.id.tvDescription)
    TextView tvDescription;

    @Bind(R.id.btnContactOwner)
    Button btnContactOwner;


    @Override
    protected void setLayoutView() {
        setContentView(R.layout.activity_find_result);
    }

    @Override
    protected void initOther() {
        getSupportActionBar().setTitle("失物详情");
        initView();
    }

    private void initView() {
        tvNumber.setText(App.article.getNumber());
        tvName.setText(App.article.getName());
        tvPhone.setText(App.article.getPhone());
        tvDescription.setText(App.article.getDescription());
    }

    @OnClick(R.id.btnContactOwner)
    public void  btnContactOwner(){
        Uri uri = Uri.parse("tel:"+App.article.getPhone());

        Intent intent = new Intent(Intent.ACTION_DIAL, uri);

        startActivity(intent);
//        Toast.makeText(FindResultActivity.this, "向"+App.article.getPhone()+"拨打电话", Toast.LENGTH_SHORT).show();
    }
}
