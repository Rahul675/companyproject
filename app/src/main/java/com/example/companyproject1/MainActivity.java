package com.example.companyproject1;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    CardView cardView;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.lv1);
        cardView = findViewById(R.id.cv1);
//        getSupportActionBar().hide();
//        setSupportActionBar(null);
//        toolbar = findViewById(R.id.main_toolbar);
//        setSupportActionBar(toolbar);

        android_networking_connection conn = new android_networking_connection(this);
        conn.callApi1(listView);
    }

}