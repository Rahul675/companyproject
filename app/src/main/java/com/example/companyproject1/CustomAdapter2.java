package com.example.companyproject1;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.google.android.material.imageview.ShapeableImageView;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CustomAdapter2 implements ListAdapter {
    Context context;
    ArrayList<String> caption;
    ArrayList<String> icon;
    View cview;
    ArrayList<String> arr1 = new ArrayList<>();

    public CustomAdapter2(Context context, ArrayList<String> caption, ArrayList<String> icon, ArrayList<String> arr1) {
        this.context = context;
        this.caption = caption;
        this.icon = icon;
        this.arr1 = arr1;
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
                    try {
                        JSONArray jsonArray = new JSONArray(arr1.get(0));
                        ArrayList<String> act2 = new ArrayList<>();
                        JSONObject jsonObject1 = jsonArray.getJSONObject(position);
                        String chkpid = jsonObject1.getString("checkpointId");
                        String[] strarr = chkpid.replace(":",",").split(",");
                        for (int k=0;k<strarr.length;k++){
                            act2.add(strarr[k]);
                        }
//                            Toast.makeText(MainActivity5.this, "checkpointid: "+act2.size(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context,MainActivity6.class);
                        intent.putStringArrayListExtra("chkpid1",act2);
                        context.startActivity(intent);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

            TextView tittle = convertView.findViewById(R.id.iat_tv);
            ShapeableImageView imag = convertView.findViewById(R.id.iat_iv);
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
