package com.example.companyproject1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    TextView name,mail;
    Button btn;
    ListView listView;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        listView = findViewById(R.id.lv1);

        btn = findViewById(R.id.logout);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        gsc = GoogleSignIn.getClient(this,gso);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account!=null){
            String Name = account.getDisplayName();
            String Mail = account.getEmail();

            name.setText(Name);
            mail.setText(Mail);
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signout();
            }
        });


        callApi1(listView);

    }

    private void signout() {
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }


    public void callApi1(ListView listView){
        AndroidNetworking.initialize(this);
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
                                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(MainActivity2.this,R.layout.textviewlayout,R.id.tv1,caption1);
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
                                                Intent intent = new Intent(MainActivity2.this,MainActivity3.class);
                                                intent.putStringArrayListExtra("chkpid1",act2);
//                                                intent.putStringArrayListExtra("descrip",descrip);
                                                startActivity(intent);
                                            }else {
                                                act3.add(ja.toString());
                                                Intent intent = new Intent(MainActivity2.this,MainActivity4.class);
                                                intent.putStringArrayListExtra("jsonarray",act3);
                                                startActivity(intent);

                                            }
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

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }
}