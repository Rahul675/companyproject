package com.example.companyproject1;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.github.gcacace.signaturepad.views.SignaturePad;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Calendar;

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
//    int v;
    ArrayList<String> checkpointid = new ArrayList<>();
    ArrayList<String> description = new ArrayList<>();
    ArrayList<String> typeId = new ArrayList<>();
    ArrayList<String[]> value = new ArrayList<>();
    ArrayList<String> size = new ArrayList<>();
    ArrayList<String> editable = new ArrayList<>();
//    String size;
    //    int p;
    RecyclerView.ViewHolder h;
//    ArrayList<String> pos = new ArrayList<>();

    public MainAdapter(Context context,ArrayList<String> checkpointid,ArrayList<String> description, ArrayList<String> typeId,ArrayList<String[]> value, ArrayList<String> size,ArrayList<String> editable) {
        this.context = context;
        this.checkpointid = checkpointid;
        this.description = description;
        this.typeId = typeId;
        this.value = value;
        this.size = size;
        this.editable = editable;
    }
    public ArrayList<String> getCheckpointid() {
        return checkpointid;
    }

    @SuppressLint("ClickableViewAccessibility")
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        vt.add(String.valueOf(viewType));
//        v=viewType;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        if (typeId.get(viewType).equals("1")){
            View view = layoutInflater.inflate(R.layout.name_layout, parent, false);
            return new viewHolder(view);
        }else if (typeId.get(viewType).equals("2")){
            View view = layoutInflater.inflate(R.layout.address_layout, parent, false);
            return new viewHolder(view);
        }else if (typeId.get(viewType).equals("3")){
            View view = layoutInflater.inflate(R.layout.contact_no_layout, parent, false);
            return new viewHolder(view);
        }else if (typeId.get(viewType).equals("4")){
            View view = layoutInflater.inflate(R.layout.checkbox_layout, parent, false);
            View view1 = layoutInflater.inflate(R.layout.radiobtn_layout, parent, false);
            String[] v = null;
            v = value.get(viewType);
            RadioGroup radioGroup = view1.findViewById(R.id.radiogroup);
            String s = "1";

            if (size.get(viewType).equals("0")){
                GridLayout gridLayout = view.findViewById(R.id.gout);
                for (int i=0;i<v.length;i++){
                    LinearLayout.LayoutParams checkParams = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    checkParams.setMargins(10, 10, 70, 10);
                    checkParams.gravity = Gravity.CENTER;
                    CheckBox checkBox = new CheckBox(context);
                    checkBox.setText(v[i]);
                    gridLayout.addView(checkBox,checkParams);
                    if (editable.get(viewType).equals("0")){
                        checkBox.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                checkBox.setChecked(false);
                            }
                        });
                    }
                }
                return new viewHolder(view);
            }else {
                for (int j=0;j<v.length;j++){
                    RadioButton radioButton = new RadioButton(context);
                    radioButton.setText(v[j]);
                    radioGroup.addView(radioButton);
                    if (editable.get(viewType).equals("0")){
                        radioButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                radioGroup.clearCheck();
                            }
                        });
                    }
                }
                return new viewHolder(view1);
            }
        }else if (typeId.get(viewType).equals("5")){
            View view = layoutInflater.inflate(R.layout.camera_layout, parent, false);
            LinearLayout linearLayout = view.findViewById(R.id.camera_iv);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(100,100);
//            int s = Integer.getInteger(size.get(viewType));
            int s = Integer.parseInt(size.get(viewType));
            for (int i=0;i<s;i++){
                ImageView imageView = new ImageView(context);
                imageView.setImageDrawable(context.getDrawable(R.drawable.ic_baseline_photo_camera_24));
                linearLayout.addView(imageView,lp);
            }
            return new viewHolder(view);
        }else if (typeId.get(viewType).equals("6")){
            View view = layoutInflater.inflate(R.layout.signature_layout, parent, false);
            SignaturePad signaturePad = view.findViewById(R.id.signature_pad);
            if (editable.get(viewType).equals("0")){
                signaturePad.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        signaturePad.clear();
                        return false;
                    }
                });
            }
            return new viewHolder(view);
        }else if (typeId.get(viewType).equals("7")){
            View view = layoutInflater.inflate(R.layout.date_layout, parent, false);
            return new viewHolder(view);
        }else if (typeId.get(viewType).equals("8")){
            View view = layoutInflater.inflate(R.layout.rating_layout, parent, false);
            return new viewHolder(view);
        }else if (typeId.get(viewType).equals("9")){
            View view = layoutInflater.inflate(R.layout.slider_layout, parent, false);
            return new viewHolder(view);
        }else if (typeId.get(viewType).equals("10")){
            View view = layoutInflater.inflate(R.layout.spinner_layout, parent, false);
//            View view2 = layoutInflater.inflate(R.layout.smartspinner_layout, parent, false);
            String[] s = value.get(viewType);
            if (size.get(viewType).equals("0")){
                Spinner spinner = view.findViewById(R.id.spinner);
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(context,R.layout.simpletext_layout,R.id.tv_name,s);
                spinner.setAdapter(arrayAdapter);
            }else {
                Spinner spinner = view.findViewById(R.id.spinner);
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(context,R.layout.simpletext_layout,R.id.tv_name,s);
                spinner.setAdapter(arrayAdapter);
            }
//            else {
//                Dialog dialog = new Dialog(context);
//                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(350, ViewGroup.LayoutParams.WRAP_CONTENT);
//                lp.gravity= Gravity.CENTER;
//                dialog.addContentView(view,lp);
//            }
            return new viewHolder(view);
        }else if (typeId.get(viewType).equals("11")){
            View view = layoutInflater.inflate(R.layout.fingerprint_checkpoint_layout, parent, false);
            return new viewHolder(view);
        }else if (typeId.get(viewType).equals("12")){
            View view = layoutInflater.inflate(R.layout.video_capture_checkpoint_layout, parent, false);
            return new viewHolder(view);
        }else if (typeId.get(viewType).equals("13")){
            View view = layoutInflater.inflate(R.layout.adress_with_location_layout, parent, false);
            return new viewHolder(view);
        }else if (typeId.get(viewType).equals("14")){
            View view = layoutInflater.inflate(R.layout.mail_id_layout, parent, false);
            return new viewHolder(view);
        }else if (typeId.get(viewType).equals("15")){
            View view = layoutInflater.inflate(R.layout.bar_code_layout, parent, false);
            return new viewHolder(view);
        }else if (typeId.get(viewType).equals("16")){
            View view = layoutInflater.inflate(R.layout.url_layout, parent, false);
            return new viewHolder(view);
        }else if (typeId.get(viewType).equals("17")) {
            View view = layoutInflater.inflate(R.layout.name_layout, parent, false);
            return new viewHolder(view);
        }else if (typeId.get(viewType).equals("18")){
            View view = layoutInflater.inflate(R.layout.video_display_layout, parent, false);
            return new viewHolder(view);
        }else if (typeId.get(viewType).equals("19")){
            View view = layoutInflater.inflate(R.layout.image_display_layout, parent, false);
            return new viewHolder(view);
        }else if (typeId.get(viewType).equals("20")){
            View view = layoutInflater.inflate(R.layout.name_layout, parent, false);
            return new viewHolder(view);
        }else if (typeId.get(viewType).equals("21")){
            View view = layoutInflater.inflate(R.layout.name_layout, parent, false);
            return new viewHolder(view);
        }else if (typeId.get(viewType).equals("22")){
            View view = layoutInflater.inflate(R.layout.name_layout, parent, false);
            return new viewHolder(view);
        }else {
            View view = layoutInflater.inflate(R.layout.name_layout, parent, false);
            return new viewHolder(view);
        }
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        TextView t = holder.itemView.findViewById(R.id.tv_name);
        StringUtils.capitalize(description.get(position));
        t.setText(String.valueOf(position+1)+". "+description.get(position));
        t.setTextColor(Color.parseColor("#6A00F4"));
        t.setTextSize(17);
        TextView e = holder.itemView.findViewById(R.id.datetv1);

//        if (checkpointid.get(position).equals("19")){
//
//        }

//        if (typeId.get(position).equals("4")){
//
//            LinearLayout linearLayout = holder.itemView.findViewById(R.id.lout);
//            String[] s = value.get(position);
//            CheckBox checkBox = new CheckBox(context);
//            checkBox.setText(s[0]);
//            linearLayout.addView(checkBox);

//            String[] s =value.get(v);
//            makeText(context, "values: "+value, Toast.LENGTH_SHORT).show();
//            LinearLayout linearLayout = holder.itemView.findViewById(R.id.lout);
//            linearLayout = linearLayout.getParent();
//            int length = s.length;
//            int i =0;
//            int j =0;


//                for (int i=0;i<s.length;i++){
//                    CheckBox checkBox = new CheckBox(context);
//                    checkBox.setText(s[i]);
//                    linearLayout.addView(checkBox);
//                    i++;
//                }

//        }

        if(typeId.get(position).equals("7")){
            ImageView btn = holder.itemView.findViewById(R.id.datebtn);
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            e.setText(day+"/"+(month+1)+"/"+year);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                            e.setText(d+"/"+(m+1)+"/"+y);
                        }
                    },year,month,day);
                    datePickerDialog.show();
                }
            });
        }
        else if (typeId.get(position).equals("15")){

            Button btn = holder.itemView.findViewById(R.id.scan);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
//                            Intent intent = new Intent(context,Scanner_Activity.class);
                            context.startActivity(new Intent(context,Scanner_Activity.class));
                        } else {
                            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CAMERA}, 100);
                        }
                    }
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return checkpointid.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        public viewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
    public class viewHolder1 extends RecyclerView.ViewHolder{
        public viewHolder1(@NonNull View itemView) {
            super(itemView);
        }
    }



}
