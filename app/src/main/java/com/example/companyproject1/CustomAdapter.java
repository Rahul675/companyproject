package com.example.companyproject1;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CustomAdapter implements ListAdapter {
    Context context;
    ArrayList<String> caption;
    ArrayList<String> icon;
    View cview;

    public CustomAdapter(Context context, ArrayList<String> caption, ArrayList<String> icon){
        this.context = context;
        this.caption = caption;
        this.icon = icon;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return caption.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        cview = convertView;
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.imageandtextview_layout, parent, false);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AndroidNetworking.initialize(context);
                    AndroidNetworking.get("http://www.trinityapplab.in/DemoOneNetwork/checklist.php?&empId=9716744965&roleId=10")
                            .setPriority(Priority.HIGH).build()
                            .getAsJSONObject(new JSONObjectRequestListener() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        JSONArray jsonArray = response.getJSONArray("menu");
                                        ArrayList<String> act2 = new ArrayList<>();
                                        ArrayList<String> act3 = new ArrayList<>();
                                        JSONObject jsonObject = jsonArray.getJSONObject(position);
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
                                @Override
                                public void onError(ANError anError) {

                                }
                            });
                }
            });

            TextView tittle = convertView.findViewById(R.id.iat_tv);
            ImageView imag = convertView.findViewById(R.id.iat_iv);
            tittle.setText(caption.get(position));
            Picasso.get().load(icon.get(position)).into(imag);

        }
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return caption.size();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}