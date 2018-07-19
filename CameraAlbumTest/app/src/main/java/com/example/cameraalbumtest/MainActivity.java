package com.example.cameraalbumtest;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public static final int TAKE_PHOTO = 1;

    private ImageView picture;

    private Uri imageUri;

    private String TAG = "测试";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button takePhoto = (Button) findViewById(R.id.take_photo);
        picture = (ImageView) findViewById(R.id.picture);
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //创建File对象，用于储存拍照后的照片
                File outputImage = new File(getExternalCacheDir(),"output_image.jpg");
                Log.d(TAG, "getExternalCacheDir: "+getExternalCacheDir());
                try {
                    if (outputImage.exists()){
                        Log.d(TAG, "onClick: 测试3");
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                }catch (IOException e){
                    e.printStackTrace();
                }

                if (Build.VERSION.SDK_INT >= 24){
                    Log.d(TAG, "onClick: 测试4");
                    imageUri = FileProvider.getUriForFile(MainActivity.this,"com.example.cameraalbumtest.fileprovider",outputImage);
                }else {
                    Log.d(TAG, "onClick: 测试5");
                    imageUri = Uri.fromFile(outputImage);
                }
                //启动相机程序
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                Log.d(TAG, "onClick: "+imageUri);
                intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                startActivityForResult(intent,TAKE_PHOTO);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){

            case TAKE_PHOTO:
                if (resultCode == RESULT_OK){
                    Log.d(TAG, "onActivityResult:看看 ");
                    try {
                        //将拍设的照片显示出来
//找到原因了Bitmap too large to be uploaded into a texture 位图太大了
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        Log.d(TAG, "onActivityResult: "+bitmap);
                        picture.setImageBitmap(bitmap);
                        Log.d(TAG, "onActivityResult: "+imageUri);
                        Log.d(TAG, "onActivityResult:看看4 ");
                    }catch (FileNotFoundException e){
                        e.printStackTrace();
                    }
                }
                Log.d(TAG, String.valueOf(resultCode));
                Log.d(TAG, String.valueOf(RESULT_OK));
                Log.d(TAG, "onActivityResult:看看2 ");
                break;
            default:
                break;
        }
    }
}
