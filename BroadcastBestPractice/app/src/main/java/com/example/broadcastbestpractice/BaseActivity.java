package com.example.broadcastbestpractice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
//创建BaseActivity 类作为所有活动的父类
public class BaseActivity extends AppCompatActivity {
    private ForceOfflineReceiver receiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.broadcastbestpractice.FORCE_OFFLINE");
        receiver = new ForceOfflineReceiver();
        registerReceiver(receiver , intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (receiver != null){
            unregisterReceiver(receiver);
            receiver = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    private class ForceOfflineReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(final Context context, Intent intent) {
            //构建一个对话框
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("账号下线");
            builder.setMessage("对不起，您的账号在别处登录！");
            builder.setCancelable(false);
            //注册一个对话框按钮
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //销毁所有程序
                    ActivityCollector.finishAll();
                    //重启程序到登录页
                    Intent i = new Intent(context,LoginActivity.class);
                    context.startActivity(i);
                }
            });
            builder.show();
        }
    }
}
