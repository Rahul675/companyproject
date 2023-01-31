package com.example.companyproject1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPass extends AppCompatActivity {

    TextView backtologin;
    String email;
    EditText txtemail;
    Button submit;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);
        txtemail = findViewById(R.id.forgetemail);
        submit = findViewById(R.id.submitforgetpass);
        backtologin = findViewById(R.id.backtologin);
        auth = FirebaseAuth.getInstance();

        getSupportActionBar().hide();
        backtologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForgetPass.this,Loginpageactivity.class));
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validatedata();
            }
        });


    }

    private void validatedata() {
        email = txtemail.getText().toString();
        if (email.isEmpty()){
            txtemail.setError("Required");
        }else {
            forgetpass();
        }
    }

    private void forgetpass() {
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(ForgetPass.this, "Check Your Email", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ForgetPass.this,Loginpageactivity.class));
                    finish();
                }else {
                    Toast.makeText(ForgetPass.this, "Error: "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}