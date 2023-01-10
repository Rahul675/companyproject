package com.example.companyproject1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {

    TextView textView;
    ListView listView;
    ArrayList<String> arr1 = new ArrayList<>();
    ArrayList<String> caption2 = new ArrayList<>();
    ArrayList<String> icon2 = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
//        textView = findViewById(R.id.tv3);
        listView = findViewById(R.id.lv3);
        Intent i = getIntent();
        arr1 = i.getStringArrayListExtra("jsonarray");
        try {
            JSONArray jsonArray = new JSONArray(arr1.get(0));
            for (int j=0;j< jsonArray.length();j++){
                JSONObject jsonObject = jsonArray.getJSONObject(j);
                String caption = jsonObject.getString("Caption");
                String icon = jsonObject.getString("Icon");
                caption2.add(caption);
                icon2.add(icon);
//                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                    }
//                });

            }

            CustomAdapter1 customAdapte1 = new CustomAdapter1(MainActivity3.this,caption2,icon2,arr1);
//                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(),R.layout.textviewlayout,R.id.tv1,caption2);
            listView.setAdapter(customAdapte1);

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
        }
        return super.onOptionsItemSelected(item);
    }
}