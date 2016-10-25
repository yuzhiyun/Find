package com.yuzhiyun.find.LoginAndRegister.model.entity;

import cn.bmob.v3.BmobUser;

/**
 * Created by yuzhiyun on 2016/5/28.
 */
public class User extends BmobUser {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
