package com.example.companyproject1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity5 extends AppCompatActivity {

    ListView listView;
    ArrayList<String> arr1 = new ArrayList<>();
    ArrayList<String> caption2 = new ArrayList<>();
    ArrayList<String> icon2 = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        listView = findViewById(R.id.lv5);

        Intent i = getIntent();
        arr1 = i.getStringArrayListExtra("jsonarray");
        try {
            JSONArray jsonArray = new JSONArray(arr1.get(0));
            for (int j=0;j< jsonArray.length();j++){
                JSONObject jsonObject = jsonArray.getJSONObject(j);
//                Toast.makeText(this, "jsonobject: "+jsonObject, Toast.LENGTH_SHORT).show();
                String caption = jsonObject.getString("Caption");
                String icon = jsonObject.getString("Icon");
                caption2.add(caption);
                icon2.add(icon);
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(),R.layout.textviewlayout,R.id.tv1,caption2);
                listView.setAdapter(arrayAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        try {
                            ArrayList<String> act2 = new ArrayList<>();
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            String chkpid = jsonObject.getString("checkpointId");
                            String[] strarr = chkpid.replace(":",",").split(",");
                            for (int k=0;k<strarr.length;k++){
                                act2.add(strarr[k]);
                            }
//                            Toast.makeText(MainActivity5.this, "checkpointid: "+act2.size(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity5.this,MainActivity6.class);
                            intent.putStringArrayListExtra("chkpid1",act2);
                            startActivity(intent);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}