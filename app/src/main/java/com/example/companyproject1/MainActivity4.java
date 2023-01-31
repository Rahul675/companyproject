package com.example.companyproject1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity4 extends AppCompatActivity {

    String url = "http://www.trinityapplab.in/DemoOneNetwork/checkpoint.php?&empId=9716744965&roleId=10";
    TextView textView;
    ImageView backimg;
//    ImageView logoutimg;
    RecyclerView recyclerView;

    ArrayList<String> arr1 = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView = findViewById(R.id.tv1);
        backimg = findViewById(R.id.backimg3);
//        logoutimg = findViewById(R.id.logoutimg);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        recyclerView = findViewById(R.id.recycler_view);
        Intent i = getIntent();
        arr1 = i.getStringArrayListExtra("chkpid1");
        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity4.super.onBackPressed();
            }
        });

//        logoutimg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FirebaseAuth.getInstance().signOut();
//////                setContentView(R.layout.loginpage_layout);
//                startActivity(new Intent(MainActivity4.this,Loginpageactivity.class));
//                Toast.makeText(MainActivity4.this, "Logout Successful", Toast.LENGTH_SHORT).show();
//
//            }
//        });

        JsonArrayRequest jar = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                ArrayList<String> chkpidarr = new ArrayList<>();
                ArrayList<String> descri = new ArrayList<>();
                ArrayList<String> typeid = new ArrayList<>();
                ArrayList<String[]> value = new ArrayList<>();
                ArrayList<String> size = new ArrayList<>();
                ArrayList<String> editable = new ArrayList<>();
                ArrayList<String> score = new ArrayList<>();

                for (int c=0;c<arr1.size();c++){
                    for (int d=0;d<response.length();d++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(d);
                            String chkpid = jsonObject.getString("chkpId");
                            if (chkpid.equals(arr1.get(c))){
//                                Toast.makeText(MainActivity4.this, "chkpid: "+chkpid, Toast.LENGTH_SHORT).show();
                                String des = jsonObject.getString("description");
                                String tid = jsonObject.getString("typeId");
                                String val = jsonObject.getString("value");
                                String siz = jsonObject.getString("size");
                                String edi = jsonObject.getString("editable");
                                String scr = jsonObject.getString("Score");

                                String[] valarr = val.split(",");
                                chkpidarr.add(chkpid);
                                descri.add(des);
                                typeid.add(tid);
                                value.add(valarr);
                                size.add(siz);
                                editable.add(edi);
                                score.add(scr);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity4.this));
                MainAdapter mainAdapter = new MainAdapter(MainActivity4.this,chkpidarr,descri,typeid,value,size,editable,score);
                recyclerView.setAdapter(mainAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity4.this, "error", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jar);


//        AndroidNetworking.initialize(this);
//        AndroidNetworking.get("http://www.trinityapplab.in/DemoOneNetwork/checkpoint.php?&empId=9716744965&roleId=10")
//                .setPriority(Priority.HIGH)
//                .build()
//                .getAsJSONArray(new JSONArrayRequestListener() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        for (int j = 0;j<response.length();j++){
//                            try {
//                                JSONObject jo = response.getJSONObject(j);
//                                String ss = jo.getString("chkpId");
////                                s.add(ss);
//                                String tt = jo.getString("typeId");
////                                t.add(tt);
//                                String dd = jo.getString("description");
////                                d.add(dd);
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                        for (int i = 0;i<ar.length;i++){
//                            String cid = String.valueOf(ar[i]);
//
//                            for (int j = 0;j<response.length();j++){
//                                try {
//                                    JSONObject jo1 = response.getJSONObject(j);
//                                    String sid = jo1.getString("chkpId");
//                                    if(cid.equals(sid)){
//                                        String tid = jo1.getString("typeId");
////                                        Toast.makeText(MainActivity4.this, "tid: "+tid, Toast.LENGTH_SHORT).show();
//                                        if (tid.equals("1")||tid.equals("13")||tid.equals("14")){
//                                            String did = jo1.getString("description");
////                                            darr.add(did);
//                                        }
//                                        if(tid.equals("4")){
//                                            String did = jo1.getString("description");
//                                            String arl = jo1.getString("value");
////                                            stsa(arl);
//                                            for(int l = 0;l<ar1.length;l++){
//                                                String aid = String.valueOf(ar1[l]);
//                                                arlist.add(aid);
//                                            }
//                                            Toast.makeText(MainActivity.this, "arlist: "+arlist, Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        }
//
////                        ArrayAdapter aradapter1 = new ArrayAdapter(getApplicationContext(), R.layout.act4layout, R.id.altv, darr);
////                        listView.setAdapter(aradapter1);
////                        Toast.makeText(MainActivity4.this, "value: "+arlist.size(), Toast.LENGTH_SHORT).show();
////                        ArrayAdapter aradapter2 = new ArrayAdapter(getApplicationContext(), R.layout.chblayout, R.id.chbtv, arlist);
////                        listView2.setAdapter(aradapter2);
//                    }
//
//                    @Override
//                    public void onError(ANError anError) {
//
//                    }
//                });

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.items, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.logout:
//                FirebaseAuth.getInstance().signOut();
////                setContentView(R.layout.loginpage_layout);
//                startActivity(new Intent(this,Loginpageactivity.class));
//        }
//        return super.onOptionsItemSelected(item);
//    }
}