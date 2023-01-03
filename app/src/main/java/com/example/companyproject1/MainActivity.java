package com.example.companyproject1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import java.util.Arrays;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    String currentLanguage = "en", currentLang;
    Button loginbtn;
    TextView signupbtn,changelang;
    ImageButton googleimage,facebookimage;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    FirebaseAuth auth;
    CallbackManager callbackManager;
    String CLIENT_ID = "746376663225-umjp7fc72tkg4q1amefbvqjntio255nl.apps.googleusercontent.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        googleimage = findViewById(R.id.gimg);
        facebookimage = findViewById(R.id.fimg);
        signupbtn = findViewById(R.id.signup_btn);
        loginbtn = findViewById(R.id.loginbutton);
        changelang = findViewById(R.id.change_language);
        auth = FirebaseAuth.getInstance();

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SignupActivity.class);
                startActivity(intent);
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
            }
        });

        changelang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeLang();
            }
        });

        googlesetup();
        facebooksetup();

    }

    public void changeLang() {
        final String lang[] = {"English", "हिन्दी"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        mBuilder.setTitle("Change Language");
        mBuilder.setSingleChoiceItems(lang, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    setlocale("");
                    recreate();
                }
                else if (i == 1) {
                    setlocale("hi");
                    recreate();
                }
            }
        });

        mBuilder.create();
        mBuilder.show();
    }

    private void setlocale(String localeName) {
        if (!localeName.equals(currentLanguage)) {
            Locale myLocale;
            myLocale = new Locale(localeName);
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
            Intent refresh = new Intent(this, MainActivity.class);
            refresh.putExtra(currentLang, localeName);
            startActivity(refresh);
        } else {
            Toast.makeText(MainActivity.this, "string already updated", Toast.LENGTH_SHORT).show();
        }
    }

    private void facebooksetup() {

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(getApplication());

        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Toast.makeText(MainActivity.this, "enter loginmanager...", Toast.LENGTH_SHORT).show();
                        // App code
                        HomeActivity();
                        handleFacebookAccessToken(loginResult.getAccessToken());
                        Toast.makeText(MainActivity.this, "homeactivity called...", Toast.LENGTH_SHORT).show();
//                        startActivityForResult(intent,1001);
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });

        facebookimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "facebook clicked", Toast.LENGTH_SHORT).show();
                LoginManager.getInstance().logInWithReadPermissions(MainActivity.this, Arrays.asList("email","public_profile"));
                Toast.makeText(MainActivity.this, "clicked over...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void googlesetup() {
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(CLIENT_ID)
                .requestEmail()
                .build();

        gsc = GoogleSignIn.getClient(this,gso);

        googleimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                googlesignin();
            }
        });
    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d("TAG", "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithCredential:success");
                            FirebaseUser user = auth.getCurrentUser();
                            HomeActivity();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "signInWithCredential:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void googlesignin() {
        Intent intent = gsc.getSignInIntent();
        startActivityForResult(intent,1001);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
//        facebook callbackmanager
//        facebook end
        if (requestCode==1001){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            String idToken = task.getResult().getIdToken();
            try {
                task.getResult(ApiException.class);

                if (idToken !=  null) {
                    // Got an ID token from Google. Use it to authenticate
                    // with Firebase.
                    AuthCredential firebaseCredential = GoogleAuthProvider.getCredential(idToken, null);
                    auth.signInWithCredential(firebaseCredential)
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d("TAG", "signInWithCredential:success");
                                        FirebaseUser user = auth.getCurrentUser();
                                        HomeActivity();
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w("TAG", "signInWithCredential:failure", task.getException());
                                    }
                                }
                            });
                }else {
                    Toast.makeText(this, "Token is null", Toast.LENGTH_SHORT).show();
                }

            } catch (ApiException e) {
                e.printStackTrace();
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void HomeActivity() {
        finish();
        Intent intent = new Intent(MainActivity.this,MainActivity2.class);
        startActivity(intent);
    }
}