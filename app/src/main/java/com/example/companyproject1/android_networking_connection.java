package com.example.companyproject1;

import static com.facebook.FacebookSdk.getApplicationContext;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class android_networking_connection {
    Context context;
    ArrayList<String> caption1 = new ArrayList<>();
    ArrayList<String> icon1 = new ArrayList<>();

    public android_networking_connection(Context context) {
        this.context = context;
    }

    public void callApi1(ListView listView){
        AndroidNetworking.initialize(context);
        AndroidNetworking.get("http://www.trinityapplab.in/DemoOneNetwork/checklist.php?&empId=9716744965&roleId=10")
                .setPriority(Priority.HIGH).build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonArray = response.getJSONArray("menu");
                            for (int i = 0;i<jsonArray.length();i++){
                                JSONObject obj = jsonArray.getJSONObject(i);
                                String avatar = obj.getString("Icon");
                                String str1 = obj.getString("Caption");
                                caption1.add(StringUtils.capitalize(str1));
                                icon1.add(avatar);
                            }
                            CustomAdapter customAdapter = new CustomAdapter(context,caption1,icon1);
                            listView.setAdapter(customAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "e: "+e, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(context, "anError: "+anError, Toast.LENGTH_SHORT).show();
                    }
                });
    }

//    public void callApi2(RecyclerView recyclerView){
//
//        RequestQueue requestQueue = Volley.newRequestQueue(context);
//        String url = "http://www.trinityapplab.in/DemoOneNetwork/checkpoint.php?&empId=9716744965&roleId=10";
//        JsonArrayRequest jar = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//
//                try {
//                    for (int i = 0;i<response.length();i++){
//                        JSONObject jsonObject = response.getJSONObject(i);
//                        String chi = jsonObject.getString("chkpId");
//                        String des = jsonObject.getString("description");
//                        String val = jsonObject.getString("value");
//                        String tyi = jsonObject.getString("typeId");
//                        String man = jsonObject.getString("mandatory");
//                        String edi = jsonObject.getString("editable");
//                        String cor = jsonObject.getString("correct");
//                        String siz = jsonObject.getString("size");
//                        String sco = jsonObject.getString("Score");
//                        String lan = jsonObject.getString("language");
//                        String act = jsonObject.getString("Active");
//                        String ind = jsonObject.getString("Is_Dept");
//                        String log = jsonObject.getString("Logic");
//                        String ing = jsonObject.getString("isGeofence");
//                        String acti = jsonObject.getString("action");
//                        String ans = jsonObject.getString("answer");
////                        chm.setChkpId(jsonObject.getString("chkpId"));
//                        s.add(chi);
//                        d.add(des);
//                        v.add(val);
//                        t.add(tyi);
//                        m.add(man);
//                        e.add(edi);
//                        c.add(cor);
//                        si.add(siz);
//                        sc.add(sco);
//                        la.add(lan);
//                        ac.add(act);
//                        in.add(ind);
//                        lo.add(log);
//                        ingeo.add(ing);
//                        action.add(acti);
//                        an.add(ans);
//                    }
////                    comp comp = new comp(MainActivity.this,c);
//
////                    textView.append(s.toString());
//                    recyclerView.setLayoutManager(new LinearLayoutManager(context));
//                    MainAdapter recyclerAdapter = new MainAdapter(context,s,d,t);
//                    recyclerView.setAdapter(recyclerAdapter);
//
////                    itemClasses.add(new ItemClass(ItemClass.LayoutOne,"Item Type 1"));
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

}
