package com.example.litepaltest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivityTest";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button createDatabase = findViewById(R.id.create_database);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LitePal.getDatabase();
            }
        });

        //添加数据
        Button addData = (Button) findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book = new Book();
                book.setName("第一行代码");
                book.setAuthor("郭霖");
                book.setPages(570);
                book.setPrice(79);
                book.setPress("人民邮电出版社");
                book.save();
            }
        });

        //更新数据
        Button updateData = (Button) findViewById(R.id.update_data);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book = new Book();
                book.setPrice(14.95);
                book.setPress("Anchor");
                book.updateAll("name = ? and author = ?","第一行代码","郭霖");
            }
        });

        //删除数据
        Button deleteButton = (Button) findViewById(R.id.delete_data);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataSupport.deleteAll(Book.class,"price<?","15");
            }
        });

        //查询数据
        Button queryButton = (Button) findViewById(R.id.query_data);
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Book> books = DataSupport.findAll(Book.class);
                for (Book book:books){
                    Log.d(TAG, "onClick: name is " + book.getName());
                    Log.d(TAG, "onClick: author is " + book.getAuthor());
                    Log.d(TAG, "onClick: pages is " + book.getPages());
                    Log.d(TAG, "onClick: price is " + book.getPrice());
                    Log.d(TAG, "onClick: press is " + book.getPress());
                }
            }
        });
    }


}
