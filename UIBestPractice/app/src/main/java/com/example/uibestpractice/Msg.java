package com.example.uibestpractice;

import android.util.Log;

public class Msg {
    public static final int TYPE_RECEIVED = 0;
    public static final int TYPE_SENT = 1;
    private String content;
    private  int type;
    Msg(String content, int type){
        Log.d("测试","测试内容2");
        this.content = content;
        this.type = type;
    }

    public String getContent(){
        Log.d("测试","测试内容3");
        return  content;
    }
    public int getType(){
        Log.d("测试","测试内容4");
        return type;
    }
}
