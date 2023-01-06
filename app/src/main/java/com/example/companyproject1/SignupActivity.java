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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    TextView loginback;
    EditText uname,email,pass,conpass;
    Button signupbtn;
    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    String adminemailID = "admin@gmail.com";
    String adminpassword = "345678";
    String adminusername = "admin";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        uname = findViewById(R.id.uname);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        conpass = findViewById(R.id.conpass);
        signupbtn = findViewById(R.id.button);
        loginback = findViewById(R.id.loginback);
        auth = FirebaseAuth.getInstance();
        getSupportActionBar().hide();

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!conpass.getText().toString().equals(pass.getText().toString())) {
                    conpass.setError("Password is not matching");
                }
                else if(uname.getText().toString().isEmpty() | email.getText().toString().isEmpty() |
                pass.getText().toString().isEmpty()){
                    uname.setError("Missing field");
                }
                else{
                    signUp();
                }
            }
        });

        loginback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, Loginpageactivity.class));
            }
        });

    }

    private void signUp() {
        if(email.getText().toString().isEmpty()){
            email.setError("Missing field");
        }else {
            if (email.getText().toString().equals(adminemailID)) {
                email.setError("Already Taken");
                Toast.makeText(SignupActivity.this, R.string.newEmail, Toast.LENGTH_SHORT).show();
            } else {

                auth.createUserWithEmailAndPassword(email.getText().toString(),pass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseDatabase database = FirebaseDatabase.getInstance("https://companyproject1-34b3e-default-rtdb.asia-southeast1.firebasedatabase.app/");
                            DatabaseReference myRef = database.getReference().child("Login");
                            myRef.child("admin").child("email").setValue(adminemailID);
                            myRef.child("admin").child("password").setValue(adminpassword);
                            myRef.child("admin").child("username").setValue(adminusername);
                            myRef.child("admin").child("as").setValue("admin");

                            myRef.child("users").child(task.getResult().getUser().getUid());
                            myRef.child("users").child(task.getResult().getUser().getUid()).child("email").setValue(email.getText().toString());
                            myRef.child("users").child(task.getResult().getUser().getUid()).child("Password").setValue(pass.getText().toString());
                            myRef.child("users").child(task.getResult().getUser().getUid()).child("as").setValue("user");
                            Toast.makeText(SignupActivity.this, "Signup successfull",
                                    Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(SignupActivity.this, "Unable to signup",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
//                firebaseDatabase = FirebaseDatabase.getInstance();
//                DatabaseReference databaseReference;
//                databaseReference = firebaseDatabase.getReference().child("Login").child("users");
////                                            databaseReference = firebaseDatabase.getReference().child("Login").child("users")
////                                                    .child(task.getResult().getUser().getUid());
////                                            databaseReference.child("email").setValue(email.getText().toString());
////                                            databaseReference.child("Password").setValue(pass.getText().toString());
//                databaseReference.child("value").setValue("user");

//                auth.createUserWithEmailAndPassword(email.getText().toString(), conpass.getText().toString())
//                        .addOnCompleteListener
//                                (this, new OnCompleteListener<AuthResult>() {
//                                    @Override
//                                    public void onComplete(@NonNull Task<AuthResult> task) {
//                                        Toast.makeText(SignupActivity.this, "task: "+task.getException().toString(), Toast.LENGTH_SHORT).show();
//                                        try {
//
//                                            firebaseDatabase = FirebaseDatabase.getInstance();
//                                            DatabaseReference databaseReference;
//                                            databaseReference = firebaseDatabase.getReference().child("Login").child("users");
////                                            databaseReference = firebaseDatabase.getReference().child("Login").child("users")
////                                                    .child(task.getResult().getUser().getUid());
////                                            databaseReference.child("email").setValue(email.getText().toString());
////                                            databaseReference.child("Password").setValue(pass.getText().toString());
//                                            databaseReference.child("value").setValue("user");

//                                            if (task.isSuccessful()) {
//                                                Toast.makeText(SignupActivity.this, "enter in success", Toast.LENGTH_SHORT).show();
//                                                Toast.makeText(SignupActivity.this, R.string.SuccessSign,
//                                                        Toast.LENGTH_SHORT).show();
//
//                                                startActivity(new Intent(SignupActivity.this,MainActivity2.class));
//                                            }
//                                            else {
//                                                Toast.makeText(SignupActivity.this, "unsuccessful",
//                                                        Toast.LENGTH_SHORT).show();
//                                            }
//                                        } catch (Exception e) {
//                                            e.printStackTrace();
//                                            Toast.makeText(SignupActivity.this, "Exception "+e.toString(), Toast.LENGTH_SHORT).show();
//                                        }
//                                        catch ( e) {
//                                            e.printStackTrace();
//                                            Toast.makeText(SignupActivity.this, "error: " + e.toString(), Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                });
            }
        }
    }
}