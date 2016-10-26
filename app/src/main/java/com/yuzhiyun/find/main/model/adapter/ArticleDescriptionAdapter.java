package com.yuzhiyun.find.main.model.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yuzhiyun.find.FindOrRecord.model.entity.Article;
import com.yuzhiyun.find.R;
import com.yuzhiyun.find.application.App;
import com.yuzhiyun.find.main.controller.activity.EditArticleActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.listener.DeleteListener;

/**
 * Created by yuzhiyun on 2016-10-25.
 */
public class ArticleDescriptionAdapter extends BaseAdapter {

    public static final String KEY_OBJECT_ID ="objectId" ;
    List<Article> articleList = null;
    Context context;

    public ArticleDescriptionAdapter(List<Article> articleList, Context context) {
        this.articleList = articleList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return articleList.size();
    }

    @Override
    public Object getItem(int position) {
        return articleList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 优化listView
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item_description_article, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final Article article=(Article)getItem(position);
        viewHolder.tvNumber.setText(article.getNumber());
        viewHolder.tvName.setText(article.getName());
        viewHolder.tvPhone.setText(article.getPhone());
        viewHolder.tvDescription.setText(article.getDescription());

        viewHolder.linearDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setMessage("确定删除吗？数据将不可恢复");
                builder.setPositiveButton("删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Article deleteArticle=new Article();
                        deleteArticle.setObjectId(article.getObjectId());
                        deleteArticle.delete(context, new DeleteListener() {
                            @Override
                            public void onSuccess() {
                                Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
                                articleList.remove(article);
                                notifyDataSetChanged();
                            }

                            @Override
                            public void onFailure(int i, String s) {
                                Toast.makeText(context, "删除失败"+s, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create();
                builder.show();

            }
        });

        viewHolder.linearEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                static变量保存一下article的数据，以免到下一个页面重新获取
                App.article=article;
                Intent intent=new Intent(context,EditArticleActivity.class);
                context.startActivity(intent);
            }
        });
        return convertView;
    }
}

class ViewHolder {

    @Bind(R.id.tvNumber)
    TextView tvNumber;

    @Bind(R.id.tvName)
    TextView tvName;

    @Bind(R.id.tvPhone)
    TextView tvPhone;

    @Bind(R.id.tvDescription)
    TextView tvDescription;

    @Bind(R.id.linearDelete)
    LinearLayout linearDelete;

    @Bind(R.id.linearEdit)
    LinearLayout linearEdit;

    public ViewHolder(View view) {
        ButterKnife.bind(this, view);
    }
}

