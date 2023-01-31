package com.example.companyproject1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.firebase.auth.FirebaseAuth;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity5 extends AppCompatActivity {

    ListView listView;
    ImageView backimg;
    ArrayList<String> arr1 = new ArrayList<>();
    ArrayList<String> caption2 = new ArrayList<>();
    ArrayList<String> icon2 = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        listView = findViewById(R.id.lv5);
        backimg = findViewById(R.id.backimg2);
        Intent i = getIntent();
        arr1 = i.getStringArrayListExtra("jsonarray");
        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity5.super.onBackPressed();
            }
        });
        try {
            JSONArray jsonArray = new JSONArray(arr1.get(0));
            for (int j=0;j< jsonArray.length();j++){
                JSONObject jsonObject = jsonArray.getJSONObject(j);
//                Toast.makeText(this, "jsonobject: "+jsonObject, Toast.LENGTH_SHORT).show();
                String caption = jsonObject.getString("Caption");
                String icon = jsonObject.getString("Icon");
                caption2.add(StringUtils.capitalize(caption));
                icon2.add(icon);
//                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(),R.layout.textviewlayout,R.id.tv1,caption2);
//                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                    }
//                });

            }

            CustomAdapter2 customAdapter2 = new CustomAdapter2(this,caption2,icon2,arr1);
            listView.setAdapter(customAdapter2);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.items, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
//                setContentView(R.layout.loginpage_layout);
                startActivity(new Intent(this,Loginpageactivity.class));
                Toast.makeText(this, "Logout Successful", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

}