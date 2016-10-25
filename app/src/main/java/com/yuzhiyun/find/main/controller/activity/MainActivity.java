package com.yuzhiyun.find.main.controller.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.yuzhiyun.find.R;
import com.yuzhiyun.find.FindOrRecord.controller.activity.BindNewArticleActivity;
import com.yuzhiyun.find.FindOrRecord.controller.activity.FindActivity;
import com.yuzhiyun.find.LoginAndRegister.controller.activity.LoginActivity;
import com.yuzhiyun.find.main.model.adapter.ArticleDescriptionAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.fabBindNewArticle)
    FloatingActionButton fabBindNewArticle;

    @Bind(R.id.lvArticle)
    ListView lvArticle;

    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;
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
    }

    /**
     * 初始化物品展示列表
     */
    private void initListView() {
        lvArticle.setAdapter(new ArticleDescriptionAdapter(context));
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
        switch (item.getItemId()){
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
