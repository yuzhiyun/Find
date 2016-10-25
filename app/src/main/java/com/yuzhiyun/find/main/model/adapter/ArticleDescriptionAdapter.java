package com.yuzhiyun.find.main.model.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.yuzhiyun.find.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yuzhiyun on 2016-10-25.
 */
public class ArticleDescriptionAdapter extends BaseAdapter {

    Context context;

    public ArticleDescriptionAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Object getItem(int position) {
        return null;
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
        return convertView;
    }
}

class ViewHolder {

    public ViewHolder(View view) {
        ButterKnife.bind(this, view);
    }
}

