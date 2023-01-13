package com.example.companyproject1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreen extends AppCompatActivity {

    FirebaseAuth auth;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setStatusBarColor(ContextCompat.getColor(this, android.R.color.transparent));
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();
        auth = FirebaseAuth.getInstance();
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                FirebaseUser user = auth.getCurrentUser();
//                Toast.makeText(SplashScreen.this, "user: "+user, Toast.LENGTH_SHORT).show();
                if (user==null){
//                    Toast.makeText(SplashScreen.this, "loginpage", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SplashScreen.this,Loginpageactivity.class));
                    finish();
                }else {
                    Toast.makeText(SplashScreen.this, "user already logged in", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SplashScreen.this,MainActivity.class));
                    finish();
                }
//                else {
//                    Toast.makeText(SplashScreen.this, "mainactivity", Toast.LENGTH_SHORT).show();
//
//                }
            }
        },2000);

    }
}