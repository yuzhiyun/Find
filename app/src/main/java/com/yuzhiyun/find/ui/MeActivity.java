//package com.yuzhiyun.find.ui;
//
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.yuzhiyun.find.R;
//import com.yuzhiyun.find.application.App;
//import com.yuzhiyun.find.LoginAndRegister.model.entity.User;
//import com.yuzhiyun.find.base.BaseActivity;
//
//import cn.bmob.v3.Bmob;
//
//public class MeActivity extends BaseActivity {
//public TextView tv_CardNumber;
//public TextView tv_phone ;
//
//    @Override
//    protected void setLayoutView() {
//        setContentView(R.layout.activity_me);
//    }
////
////    @Override
////    protected void findView() {
////        tv_CardNumber= (TextView) findViewById(R.id.tv_CardNumber);
////        tv_phone= (TextView) findViewById(R.id.tv_phone);
////    }
////
////    @Override
////    protected void setListener() {
////
////    }
//
//    @Override
//    protected void initOther() {
//        Bmob.initialize(this, "f2d3a5ea5d67ec7dd611db5f863e8e37");
//        App app=App.getInstance();
//        if(app!=null){
//            User user= app.getCurrentUser();
//            if(user!=null) {
//                tv_CardNumber.setText(user.getUsername());
//                tv_phone.setText(user.getMobilePhoneNumber());
//            }
//        }
//        else
//            Toast.makeText(getApplication(),"app=null",Toast.LENGTH_LONG).show();
//
//
//    }
//}
