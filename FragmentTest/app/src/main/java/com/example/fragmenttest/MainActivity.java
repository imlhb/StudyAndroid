package com.example.fragmenttest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("测试", "测试测试2");
        setContentView(R.layout.activity_main);
        Log.d("测试", "测试测试1");
        Button button = findViewById(R.id.button);
        button.setOnClickListener(this);
//        replaceFragment(new RightFragment());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                replaceFragment(new AnotherRightFragment());
            default:
                break;
        }
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        // 通过id查找对应的Fragment实例
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.right_layout,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
