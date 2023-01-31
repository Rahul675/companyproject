package com.example.companyproject1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    CardView cardView;
    Toolbar toolbar;
    Boolean login;
    Spinner spinner;
    ImageView logoutimg;
    List<String> spinnerArray =  new ArrayList<String>();

//    ArrayList<String> spinitem;
    boolean doubleBackToExitPressedOnce = false;
    @SuppressLint({"RestrictedApi", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.lv1);
        cardView = findViewById(R.id.cv1);
        logoutimg = findViewById(R.id.logoutimg);
//        getSupportActionBar().hide();
//        setSupportActionBar(toolbar);
//        TextView textView = (TextView) getSupportActionBar().getCustomView().findViewById(androidx.core.R.id.title);
//        textView.setText("Trinity World");
//        toolbar = findViewById(R.id.main_toolbar);

//        toolbar.inflateMenu(R.menu.items);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerArray);
//
//        adapter.setDropDownViewResource(
//                android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);
        logoutimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
//                setContentView(R.layout.loginpage_layout);
                Intent intent = new Intent(MainActivity.this,Loginpageactivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "Logout Successful", Toast.LENGTH_SHORT).show();
            }
        });

//        spinitem.add("Logout");
//        ArrayAdapter aa = new ArrayAdapter(this,R.menu.items,spinitem);
//        spinner.setAdapter(aa);
//        getActionBar().hide();
//        toolbar = findViewById(R.id.main_toolbar);
//        setSupportActionBar(toolbar);
//        setSupportActionBar(toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().hide();
//        setSupportActionBar(null);
//        toolbar = findViewById(R.id.main_toolbar);
//        setSupportActionBar(toolbar);

        android_networking_connection conn = new android_networking_connection(this);
        conn.callApi1(listView);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.items, menu);
//
////        int positionOfMenuItem = 0; // or whatever...
////        MenuItem item = menu.getItem(positionOfMenuItem);
////        SpannableString s = new SpannableString("Logout");
////        s.setSpan(new ForegroundColorSpan(Color.BLACK), 0, s.length(), 0);
////        item.setTitle(s);
//        return true;
//    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            finish();
            System.exit(0);
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.logout:
//                FirebaseAuth.getInstance().signOut();
////                setContentView(R.layout.loginpage_layout);
//                Intent intent = new Intent(this,Loginpageactivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                startActivity(intent);
//                Toast.makeText(this, "Logout Successful", Toast.LENGTH_SHORT).show();
//        }
//        return super.onOptionsItemSelected(item);
//    }

}