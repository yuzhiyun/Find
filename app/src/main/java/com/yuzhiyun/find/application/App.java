package com.yuzhiyun.find.application;

import android.app.Application;

import com.yuzhiyun.find.LoginAndRegister.model.entity.User;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;

/**
 * Created by yuzhiyun on 2016/5/28.
 */
public class App extends Application {
    public static final String BMOB_APP_ID ="135d264be98fc7060034891955603ce4";
    private static App myApplication =new App();

    public static App getInstance() {
        return myApplication;
    }


    //	获取现在的用户BmobUser.getCurrentUser
    public User getCurrentUser() {

        User user = BmobUser.getCurrentUser(myApplication, User.class);
        if (user != null) {
            return user;
        }
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();

        Bmob.initialize(this, "f2d3a5ea5d67ec7dd611db5f863e8e37");
    }
}
