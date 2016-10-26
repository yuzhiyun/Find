package com.yuzhiyun.find.FindOrRecord.controller.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SearchView;
import android.widget.Button;
import android.widget.Toast;

import com.yuzhiyun.find.FindOrRecord.model.entity.Article;
import com.yuzhiyun.find.R;
import com.yuzhiyun.find.application.App;
import com.yuzhiyun.find.base.BaseActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

public class FindActivity extends BaseActivity implements SearchView.OnQueryTextListener {

    public static final String KEY_NAME = "number";
    @Bind(R.id.svNumber)
    SearchView svNumber;


    @Override
    protected void setLayoutView() {
        setContentView(R.layout.activity_find);
    }

    @Override
    protected void initOther() {
        getSupportActionBar().setTitle("搜索");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /**
         * 默认情况下是没提交搜索的按钮，你可以同过setSubmitButtonEnabled(
         * true)来添加一个提交按钮（"submit" button)
         * 设置true后，右边会出现一个箭头按钮。如果用户没有输入，就不会触发提交（submit）事件
         */
        svNumber.setSubmitButtonEnabled(true);
        /**
         * 默认情况下, search widget是"iconified“的，只是用一个图标 来表示它(一个放大镜),
         * 当用户按下它的时候才显示search box . 你可以调用setIconifiedByDefault(false)让search
         * box默认都被显示。 你也可以调用setIconified()让它以iconified“的形式显示。
         */
        svNumber.setIconifiedByDefault(false);
        svNumber.setOnQueryTextListener(this);
    }


    @Override
    public boolean onQueryTextSubmit(final String query) {
        final ProgressDialog progress;
        progress = new ProgressDialog(context);
        progress.show();
        BmobQuery<Article> bmobQuery = new BmobQuery<>();
        bmobQuery.addWhereEqualTo("number", query);
        bmobQuery.findObjects(context, new FindListener<Article>() {
            @Override
            public void onSuccess(List<Article> list) {
                progress.dismiss();

                if (0 != list.size()) {
                    App.article=list.get(0);
                    //此处使用序列化保存对象更好，通过intent传递一个序列化对象，
                    // 但是我暂时用static保存数据吧，写完业务再优化吧！
                    startActivity(new Intent(context, FindResultActivity.class));
                }
                else{
                    AlertDialog.Builder builder=new AlertDialog.Builder(context);
                    builder.setMessage("抱歉，该暗号尚未被使用，请确保您输入正确");
                    builder.create();
                    builder.show();
                }
            }

            @Override
            public void onError(int i, String s) {
                progress.dismiss();
                Toast.makeText(FindActivity.this, "抱歉"+s, Toast.LENGTH_SHORT).show();
            }
        });
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
