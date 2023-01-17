package com.example.companyproject1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class CameraActivity extends AppCompatActivity implements MainAdapter.CallbackInterface {

    ImageView imageView;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_activity);
        capture();
    }

    public void capture(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA}, 1110);
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
            } else {
                Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePicture,1110);
                Toast.makeText(this, "working....", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
//                context.startActivity(intent);
            }

        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1110){
            bitmap = (Bitmap) data.getExtras().get("data");
//            Toast.makeText(this, "onactivityresult working...", Toast.LENGTH_SHORT).show();
//            imageView = findViewById(R.id.imview);
//            imageView.setImageBitmap(bitmap);
//            ImageView imageView = new ImageView(this);
//            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//            imageView.setLayoutParams(lp);
//            imageView.setImageBitmap(bitmap);
//            Intent intent = new Intent(CameraActivity.this,MainActivity2.class);
//            intent.putExtra("bitmap",data);
//            startActivity(intent);
        }
    }

    @Override
    public void onHandleSelection(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}