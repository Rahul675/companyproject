package com.example.companyproject1;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class android_networking_connection {
    Context context;

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
                        ArrayList<String> caption1 = new ArrayList<>();
                        ArrayList<String> icon1 = new ArrayList<>();

                        try {
                            JSONArray jsonArray = response.getJSONArray("menu");
                            for (int i = 0;i<jsonArray.length();i++){
                                JSONObject obj = jsonArray.getJSONObject(i);
                                String avatar = obj.getString("Icon");
                                String str1 = obj.getString("Caption");
                                caption1.add(str1);
                                icon1.add(avatar);
                                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(context,R.layout.textviewlayout,R.id.tv1,caption1);
                                listView.setAdapter(arrayAdapter);
                                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                        try {
                                            ArrayList<String> act2 = new ArrayList<>();
                                            ArrayList<String> act3 = new ArrayList<>();
                                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                                            JSONArray ja = jsonObject.getJSONArray("subCategoryList");
                                            if (ja.length()==0) {
                                                String chkpid = jsonObject.getString("checkpointId");
                                                String[] strarr = chkpid.split(",");
                                                for (int k=0;k<strarr.length;k++){
                                                    act2.add(strarr[k]);
                                                }
                                                Intent intent = new Intent(context,MainActivity2.class);
                                                intent.putStringArrayListExtra("chkpid1",act2);
//                                                intent.putStringArrayListExtra("descrip",descrip);
                                                context.startActivity(intent);
                                            }else {
                                                act3.add(ja.toString());
                                                Intent intent = new Intent(context,MainActivity3.class);
                                                intent.putStringArrayListExtra("jsonarray",act3);
                                                context.startActivity(intent);

                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                            Toast.makeText(context, "e: "+e, Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
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
