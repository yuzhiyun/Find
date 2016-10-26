package com.yuzhiyun.find.FindOrRecord.controller.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yuzhiyun.find.FindOrRecord.model.entity.Article;
import com.yuzhiyun.find.FindOrRecord.model.entity.Number;
import com.yuzhiyun.find.LoginAndRegister.model.entity.User;
import com.yuzhiyun.find.R;
import com.yuzhiyun.find.base.BaseActivity;
import com.yuzhiyun.find.main.controller.activity.MainActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

public class BindNewArticleActivity extends BaseActivity {

    @Bind(R.id.btnSave)
    Button btnSave;

    @Bind(R.id.etNumber)
    EditText etNumber;

    @Bind(R.id.etName)
    EditText etName;

    @Bind(R.id.etPhone)
    EditText etPhone;

    @Bind(R.id.etDescription)
    EditText etDescription;

    ProgressDialog progress;
    @Override
    protected void setLayoutView() {
        setContentView(R.layout.activity_bind_new_article);
    }

    @Override
    protected void initOther() {
        getSupportActionBar().setTitle("绑定新物品");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @OnClick(R.id.btnSave)
    public void  btnSave(){



        final String number=etNumber.getText().toString().trim();
        final String name=etName.getText().toString().trim();
        final String phone=etPhone.getText().toString().trim();
        final String description=etDescription.getText().toString().trim();

        if(number.equals("")|name.equals("")|phone.equals("")|description.equals("")){
            Toast.makeText(BindNewArticleActivity.this, "请确保所有输入框不为空", Toast.LENGTH_SHORT).show();
            return;
        }

        progress = new ProgressDialog(context);
        progress.show();
        /**
         * 先判断该号码是否是有效的
         * */
        BmobQuery<Number> query=new BmobQuery<>();
        query.addWhereEqualTo("number",number);
        query.findObjects(context, new FindListener<Number>() {
            @Override
            public void onSuccess(List<Number> list) {
                progress.dismiss();
                if(0!=list.size()){
                    /**
                     * 号码有效，现在开始绑定该物品
                     * */
                    User user= BmobUser.getCurrentUser(context,User.class);
                    Article article=new Article( user,number,name,phone,description);
                    article.save(context, new SaveListener() {
                        @Override
                        public void onSuccess() {
                            progress.dismiss();
                            Toast.makeText(BindNewArticleActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(context, MainActivity.class));
                        }

                        @Override
                        public void onFailure(int i, String s) {
                            progress.dismiss();
                            //暗号被占用
                            if(401==i){
                                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                                builder.setMessage("抱歉，您输入的暗号已经被占用，请务必购买官方提供的卡贴或其他带有暗号的标示物！");
                                builder.create();
                                builder.show();
                            }
                            Toast.makeText(BindNewArticleActivity.this,"错误编号"+i+"失败"+s, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else{
                    AlertDialog.Builder builder=new AlertDialog.Builder(context);
                    builder.setMessage("抱歉，您输入的暗号尚未投入使用，请务必购买官方提供的卡贴或其他带有暗号的标示物！");
                    builder.create();
                    builder.show();
                }
            }

            @Override
            public void onError(int i, String s) {
                progress.dismiss();
                Toast.makeText(BindNewArticleActivity.this, "抱歉"+s, Toast.LENGTH_SHORT).show();
            }
        });

    }

}
