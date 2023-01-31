package com.example.companyproject1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    String ss = "[{\n" +
            "\t\"caption\": \"Test\",\n" +
            "\t\"timeStamp\": \"1643977974796\",\n" +
            "\t\"checklist\": [{\n" +
            "\t\t\"Dependent\": \"0\",\n" +
            "\t\t\"Chkp_Id\": \"4013\",\n" +
            "\t\t\"value\": \"/storage/emulated/0/Pictures/demo_onenetwork/9716744965/Test/1643977974796/4013-0.mp4\"\n" +
            "\t}, {\n" +
            "\t\t\"Dependent\": \"0\",\n" +
            "\t\t\"Chkp_Id\": \"4724\",\n" +
            "\t\t\"value\": \"C-17, CO-DESQ, 7th Floor, Platina Heights, C-24, C Block, Phase 2, Industrial Area, Sector 62, Noida, Uttar Pradesh 201309, India\\n\"\n" +
            "\t}, {\n" +
            "\t\t\"Dependent\": \"0\",\n" +
            "\t\t\"Chkp_Id\": \"4904\",\n" +
            "\t\t\"value\": \"C-17, CO-DESQ, 7th Floor, Platina Heights, C-24, C Block, Phase 2, Industrial Area, Sector 62, Noida, Uttar Pradesh 201309, India\\n\"\n" +
            "\t}, {\n" +
            "\t\t\"Dependent\": \"0\",\n" +
            "\t\t\"Chkp_Id\": \"4905\",\n" +
            "\t\t\"value\": \"_1\"\n" +
            "\t}, {\n" +
            "\t\t\"Dependent\": \"0\",\n" +
            "\t\t\"Chkp_Id\": \"4902\",\n" +
            "\t\t\"value\": \"_1\"\n" +
            "\t}, {\n" +
            "\t\t\"Dependent\": \"0\",\n" +
            "\t\t\"Chkp_Id\": \"578\",\n" +
            "\t\t\"value\": \"Other\"\n" +
            "\t}, {\n" +
            "\t\t\"Dependent\": \"578\",\n" +
            "\t\t\"Chkp_Id\": \"578_3714\",\n" +
            "\t\t\"value\": \"dependable\"\n" +
            "\t}],\n" +
            "\t\"did\": \"879\",\n" +
            "\t\"distance\": \"\",\n" +
            "\t\"Emp_id\": \"9716744965\",\n" +
            "\t\"event\": \"Submit\",\n" +
            "\t\"fakeGpsMessage\": \"\",\n" +
            "\t\"geolocation\": \"28.6112289,77.3612274\",\n" +
            "\t\"locationId\": \"\",\n" +
            "\t\"M_Id\": \"241\",\n" +
            "\t\"mappingId\": \"0\",\n" +
            "\t\"mobiledatetime\": \"2022-02-04 06:03:58 PM\"\n" +
            "}]";

//    String sss = "[{\n" +
//            " \"Emp_id\": \"116\",\n" +
//            " \"M_Id\": \"3\",\n" +
//            " \"caption\": \"Self Intro 3\",\n" +
//            " \"checklist\": [{\n" +
//            "  \"Dependent\": \"0\",\n" +
//            "  \"Chkp_Id\": \"1\",\n" +
//            "  \"value\": \"ok\"\n" +
//            " }, {\n" +
//            "  \"Dependent\": \"0\",\n" +
//            "  \"Chkp_Id\": \"2\",\n" +
//            "  \"value\": \"_1\"\n" +
//            " }],\n" +
//            " \"did\": \"12\",\n" +
//            " \"event\": \"Submit\",\n" +
//            " \"latLong\": \"28.6225288,77.3660704\",\n" +
//            " \"mappingId\": \"0\",\n" +
//            " \"mobiledatetime\": \"2023-01-30 16:00:49\",\n" +
//            " \"timeStamp\": \"1675074634337\",\n" +
//            " \"uid\": 0\n" +
//            "}]";

    String url = "http://www.trinityapplab.in/DemoOneNetwork/checkpoint.php?&empId=9716744965&roleId=10";
    String url2 = "http://www.trinityapplab.in/DemoOneNetwork/saveCheckpoint.php";
//    String url3 = "http://www.trinityapplab.in/UniversalApp/saveCheckpoint.php";
    TextView textView,tvh;
    Button button;
    ImageView backimg;
    RecyclerView recyclerView;
    ArrayList<String> arr1 = new ArrayList<>();
    ArrayList<String> chkpid = new ArrayList<>();
    ArrayList<String> menuid = new ArrayList<>();
    JSONObject jo = new JSONObject();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView = findViewById(R.id.tv1);
        tvh = findViewById(R.id.tvh);
        button = findViewById(R.id.submitbtn);
        backimg = findViewById(R.id.backimg3);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        recyclerView = findViewById(R.id.recycler_view);
        Intent i = getIntent();
        arr1 = i.getStringArrayListExtra("chkpid1");

        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity2.super.onBackPressed();
            }
        });

        JsonArrayRequest jar = new JsonArrayRequest(Request.Method.GET, url,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                String isd = null;
                String cid = null;
                String val = null;
                ArrayList<String> chkpidarr = new ArrayList<>();
                ArrayList<String> descri = new ArrayList<>();
                ArrayList<String> typeid = new ArrayList<>();
                ArrayList<String[]> value = new ArrayList<>();
                ArrayList<String> size = new ArrayList<>();
                ArrayList<String> editable = new ArrayList<>();
                ArrayList<String> score = new ArrayList<>();
                ArrayList<String> Is_Dept = new ArrayList<>();

                for (int c=0;c<arr1.size();c++){
                    for (int d=0;d<response.length();d++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(d);
                            cid = jsonObject.getString("chkpId");
                            if (chkpid.equals(arr1.get(c))){
//                                Toast.makeText(MainActivity4.this, "chkpid: "+chkpid, Toast.LENGTH_SHORT).show();
                                String des = jsonObject.getString("description");
                                String tid = jsonObject.getString("typeId");
                                val = jsonObject.getString("value");
                                String siz = jsonObject.getString("size");
                                String edi = jsonObject.getString("editable");
                                String scr = jsonObject.getString("Score");
                                isd = jsonObject.getString("Is_Dept");


                                String[] valarr = val.split(",");
                                chkpidarr.add(cid);
                                descri.add(des);
                                typeid.add(tid);
                                value.add(valarr);
                                size.add(siz);
                                editable.add(edi);
                                score.add(scr);
                                Is_Dept.add(isd);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity2.this, "e: "+e, Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                submitcontent submitcontent = new submitcontent();
                List<Values> list = new ArrayList<>();
                for (int i = 0; i < chkpidarr.size(); i++) {
                    Values values = new Values();
                    values.setDependent(isd);
                    values.setChkp_Id(cid);
                    values.setValue(val);
                    list.add(values);
                }
                submitcontent.setChecklist(list);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity2.this));
                MainAdapter mainAdapter = new MainAdapter(MainActivity2.this,chkpidarr,descri,typeid,value,size,editable,score);
                recyclerView.setAdapter(mainAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity2.this, "error" + error, Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue rq = Volley.newRequestQueue(MainActivity2.this);
                submitcontent sc = new submitcontent();
                JSONArray jsonArray = null;
//                try {
//                    jsonArray = new JSONArray(sss);
//                    tvh.setText(jsonArray.toString());
//                    Toast.makeText(MainActivity2.this, "clicked...", Toast.LENGTH_SHORT).show();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                Toast.makeText(MainActivity2.this, "sc: "+sc.toString(), Toast.LENGTH_SHORT).show();
                try {
                    AndroidNetworking.post(url2)
                            .addJSONArrayBody(new JSONArray(ss))
                            .setPriority(Priority.HIGH).build()
                            .getAsJSONObject(new JSONObjectRequestListener() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    submitcontent submitcontent = new submitcontent();
                                    tvh.setText(submitcontent.toString());
                                    Toast.makeText(MainActivity2.this, "Success...", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onError(ANError anError) {
                                    tvh.setText(anError.toString());
                                    Toast.makeText(MainActivity2.this, "Failed...", Toast.LENGTH_SHORT).show();
                                }
                            });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


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