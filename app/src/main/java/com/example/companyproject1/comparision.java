package com.example.companyproject1;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class comparision {

    String descrip;
    Context context;
    ArrayList<String> checkpointid = new ArrayList<>();
    ArrayList<String> description = new ArrayList<>();
    ArrayList<String> v = new ArrayList<>();
    ArrayList<String> t = new ArrayList<>();
    ArrayList<String> m  = new ArrayList<>();
    ArrayList<String> e = new ArrayList<>();
    ArrayList<String> c = new ArrayList<>();
    ArrayList<String> si = new ArrayList<>();
    ArrayList<String> sc = new ArrayList<>();
    ArrayList<String> la = new ArrayList<>();
    ArrayList<String> ac = new ArrayList<>();
    ArrayList<String> in = new ArrayList<>();
    ArrayList<String> lo = new ArrayList<>();
    ArrayList<String> ingeo = new ArrayList<>();
    ArrayList<String> action = new ArrayList<>();
    ArrayList<String> an = new ArrayList<>();
    String url = "http://www.trinityapplab.in/DemoOneNetwork/checkpoint.php?&empId=9716744965&roleId=10";

    JsonArrayRequest jar = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
        @Override
        public void onResponse(JSONArray response) {
//                checkpoint_model chm = new checkpoint_model(MainActivity.this);
//                Toast.makeText(MainActivity.this, ""+response.length(), Toast.LENGTH_SHORT).show();

            try {
                for (int i = 0;i<response.length();i++){
                    JSONObject jsonObject = response.getJSONObject(i);
                    String chi = jsonObject.getString("chkpId");
                    String des = jsonObject.getString("description");
                    String val = jsonObject.getString("value");
                    String tyi = jsonObject.getString("typeId");
                    String man = jsonObject.getString("mandatory");
                    String edi = jsonObject.getString("editable");
                    String cor = jsonObject.getString("correct");
                    String siz = jsonObject.getString("size");
                    String sco = jsonObject.getString("Score");
                    String lan = jsonObject.getString("language");
                    String act = jsonObject.getString("Active");
                    String ind = jsonObject.getString("Is_Dept");
                    String log = jsonObject.getString("Logic");
                    String ing = jsonObject.getString("isGeofence");
                    String acti = jsonObject.getString("action");
                    String ans = jsonObject.getString("answer");
//                        chm.setChkpId(jsonObject.getString("chkpId"));
                    checkpointid.add(chi);
                    description.add(des);
                    v.add(val);
                    t.add(tyi);
                    m.add(man);
                    e.add(edi);
                    c.add(cor);
                    si.add(siz);
                    sc.add(sco);
                    la.add(lan);
                    ac.add(act);
                    in.add(ind);
                    lo.add(log);
                    ingeo.add(ing);
                    action.add(acti);
                    an.add(ans);
                }


//                    itemClasses.add(new ItemClass(ItemClass.LayoutOne,"Item Type 1"));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            error.printStackTrace();
        }
    });


    public String calldescription(String chk){
        for (int i = 0;i<checkpointid.size();i++){
            if (chk.equals(checkpointid.get(i))){
                descrip = description.get(i);
            }
        }
        return descrip;
    }

//    public comparision(Context context, ArrayList<String> chkpId, ArrayList<String> typeId) {
//        this.context = context;
//        this.chkpId = chkpId;
//        this.typeId = typeId;
//    }


}
