package com.example.notificationtest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sendNotice = (Button) findViewById(R.id.send_notice);
        sendNotice.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.send_notice:
//意图
                Intent intent = new Intent(this , NotificationActivity.class);
                PendingIntent pi = PendingIntent.getActivity(this,0,intent,0);

                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                //这里原有的NotificationCompat.Builder被弃用，后面括号里面新加入个String参数即可
                Notification notification = new NotificationCompat.Builder(this,"default")
                        .setContentTitle("这是标题")
                        .setContentText("这是内容,现在我们把通知的内容加长，加到特别长，你说多长我也不知道，反正很长很长，长到手机装不下，看他是什么效果。")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                        //意图
                        .setContentIntent(pi)
                        //点击后销毁通知
                        .setAutoCancel(true)
                        //通知播放音乐
                        .setSound(Uri.fromFile(new File("/system/media/audio/ringtones/Celesta.ogg")))
                        //手机振动（需要在AndroidManifest中指定振动权限）
                        .setVibrate(new long[]{0,1000,1000,1000})
                        //设置LED灯的颜色和闪烁效果
                        .setLights(Color.GREEN,1000,1000)
                        //手机默认
//                        .setDefaults(NotificationCompat.DEFAULT_ALL)
                        //长通知效果，但是小米4测试无效
                        .setStyle(new NotificationCompat.BigTextStyle().bigText("这是内容,现在我们把通知的内容加长，加到特别长，你说多长我也不知道，反正很长很长，长到手机装不下，看他是什么效果。"))
                        //通知显示图片，小米测试无效。Nexus测试成功
                        .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(),R.drawable.a)))
                        //设置重要程度PRIORITY_DEFAULT：默认，PRIORITY_MIN：最低，PRIORITY_LOW:较低，PRIORITY_HIGH：较高，PRIORITY_MAX：最高
                        //测试发现，小米没有反应因为小米需要设置悬浮权限，Nexus测试成功
                        .setPriority(NotificationCompat.PRIORITY_MAX)
                        .build();
                manager.notify(1,notification);
                break;
            default:
                break;
        }
    }
}
