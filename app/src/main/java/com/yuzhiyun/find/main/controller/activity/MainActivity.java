package com.yuzhiyun.find.main.controller.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.yuzhiyun.find.FindOrRecord.model.entity.Article;
import com.yuzhiyun.find.R;
import com.yuzhiyun.find.FindOrRecord.controller.activity.BindNewArticleActivity;
import com.yuzhiyun.find.FindOrRecord.controller.activity.FindActivity;
import com.yuzhiyun.find.LoginAndRegister.controller.activity.LoginActivity;
import com.yuzhiyun.find.main.model.adapter.ArticleDescriptionAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @Bind(R.id.fabBindNewArticle)
    FloatingActionButton fabBindNewArticle;

    @Bind(R.id.lvArticle)
    ListView lvArticle;

    //    @Bind(R.id.tvUserName)
    TextView tvUserName;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        //ButterKnife
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initListView();
        /**
         * NavigationView的header布局中常用来放置用户头像、用户名等信息，
         * 所以我们必须获取到header布局中的view。
         * NavigationView是一个RecyclerView（在23.1.0版本之前是ListView），
         * header布局通常是0号元素
         * 可以通过getHeaderView获取得到
         * */
        View view = navigationView.getHeaderView(0);
        tvUserName = (TextView) view.findViewById(R.id.tvUserName);
        if (null != BmobUser.getCurrentUser(context)) {
            String name = BmobUser.getCurrentUser(context).getUsername();
            tvUserName.setText(name);
        }
    }

    /**
     * 编辑某个物品的信息成功后，会自动返回这个activity,于是需要重新获取一下数据
     */
    @Override
    protected void onRestart() {
        super.onRestart();
        initListView();
    }

    /**
     * 初始化物品展示列表
     */
    private void initListView() {
        final ProgressDialog progress;
        progress = new ProgressDialog(context);
        progress.show();
        BmobQuery<Article> bmobQuery = new BmobQuery<>();
        //查询登录用户的所有物品
        bmobQuery.addWhereEqualTo("user", BmobUser.getCurrentUser(context));
        /**
         * 在某些情况下，你想在一个查询内获取Pointer类型的关联对象。
         *比如上述示例中，如果希望在查询帖子信息的同时也把该帖子的作者的信息查询出来，可以使用include方法
         *query.include("author");
         */
        bmobQuery.include("user");
        bmobQuery.findObjects(context, new FindListener<Article>() {
            @Override
            public void onSuccess(List<Article> list) {
                progress.dismiss();
                if (0 != list.size())
                    lvArticle.setAdapter(new ArticleDescriptionAdapter(list, context));
                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("遗憾~_~\n您暂时没有记录任何物品\n快点击右下角按钮绑定你的宝贝吧");
                    builder.create();
                    builder.show();
                }
            }

            @Override
            public void onError(int i, String s) {
                progress.dismiss();
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("错误" + s);
                builder.create();
                builder.show();
            }
        });

    }

    /**
     * 绑定新物品
     */
    @OnClick(R.id.fabBindNewArticle)
    public void fabBindNewArticle() {
        startActivity(new Intent(MainActivity.this, BindNewArticleActivity.class));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {
            //我的物品
            case R.id.nav_camera:
                break;
            //寻找失主
            case R.id.nav_gallery:
                startActivity(new Intent(MainActivity.this, FindActivity.class));
                break;
            //退出登录
            case R.id.nav_slideshow:
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                break;
            case R.id.nav_share:
                Toast.makeText(MainActivity.this, "分享", Toast.LENGTH_SHORT).show();
                break;
//            case R.id.nav_camera:
//                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
