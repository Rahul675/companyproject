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
import android.widget.EditText;
import android.widget.ImageButton;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.google.firebase.auth.OAuthProvider;
import com.shantanudeshmukh.linkedinsdk.LinkedInAuthenticationActivity;
import com.shantanudeshmukh.linkedinsdk.LinkedInBuilder;
import com.shantanudeshmukh.linkedinsdk.helpers.LinkedInUser;

import java.util.Arrays;
import java.util.Locale;


public class Loginpageactivity extends AppCompatActivity {

    String currentLanguage = "en", currentLang;
    Button loginbtn;
    EditText username,pass;
    TextView signupbtn,changelang,forgetpass;
    ImageButton googleimage,facebookimage,twitterimage,linkedinimage;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    FirebaseAuth auth;
    CallbackManager callbackManager;
    private static final String LINKEDIN_CLIENT_ID = "772jdy8slouwtp";
    private static final String LINKEDIN_CLIENT_SECRET = "CTFU8cFRRxxDpJLQ";
    private static final String LINKEDIN_REDIRECTION_URL = "https://www.linkedin.com/developers/tools/oauth/redirect";
    private static final int LINKEDIN_REQUEST_CODE = 1005;
    String adminemailID = "admin@gmail.com";
    String adminpassword = "345678";
    String adminusername = "admin";
    String CLIENT_ID = "746376663225-umjp7fc72tkg4q1amefbvqjntio255nl.apps.googleusercontent.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginpage_layout);
        username = findViewById(R.id.username);
        pass = findViewById(R.id.pass);
        googleimage = findViewById(R.id.gimg);
        facebookimage = findViewById(R.id.fimg);
        twitterimage = findViewById(R.id.timg);
        linkedinimage = findViewById(R.id.limg);
        signupbtn = findViewById(R.id.signup_btn);
        loginbtn = findViewById(R.id.loginbutton);
        forgetpass = findViewById(R.id.forgetpass);
        changelang = findViewById(R.id.change_language);
        auth = FirebaseAuth.getInstance();
//        getSupportActionBar().hide();

        String uname = username.getText().toString();
        String pas = pass.getText().toString();

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Loginpageactivity.this,SignupActivity.class);
                startActivity(intent);
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().equals(adminusername) || pass.getText().toString().equals(adminpassword)){
                    startActivity(new Intent(Loginpageactivity.this,MainActivity.class));
                }else if (username.getText().toString().isEmpty() || pass.getText().toString().isEmpty()){
                    if (username.getText().toString().isEmpty()) {
                        username.setError("username is empty!");
                    }else if (pass.getText().toString().isEmpty()){
                        pass.setError("password is empty!");
                    }
                }else{
                    Toast.makeText(Loginpageactivity.this, "Incorrect username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        forgetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Loginpageactivity.this,ForgetPass.class));
            }
        });

        twitterimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                twitterSignin();
            }
        });

        linkedinimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinkedInBuilder.getInstance(Loginpageactivity.this)
                        .setClientID(LINKEDIN_CLIENT_ID)
                        .setClientSecret(LINKEDIN_CLIENT_SECRET)
                        .setRedirectURI(LINKEDIN_REDIRECTION_URL)
                        .authenticate(LINKEDIN_REQUEST_CODE);
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
        final String lang[] = {"English", "हिन्दी(Hindi)", "বাংলা(Bangla)", "தமிழ்(Tamil)"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        mBuilder.setTitle("Change Language");
        mBuilder.setSingleChoiceItems(lang, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    setlocale("");
                    recreate();
                    finish();
                }
                else if (i == 1) {
                    setlocale("hi");
                    recreate();
                    finish();
                }
                else if (i == 2) {
                    setlocale("bg");
                    recreate();
                    finish();
                }
                else if (i == 3) {
                    setlocale("tm");
                    recreate();
                    finish();
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
            Intent refresh = new Intent(this, Loginpageactivity.class);
            refresh.putExtra(currentLang, localeName);
            startActivity(refresh);
        } else {
            Toast.makeText(Loginpageactivity.this, "string already updated", Toast.LENGTH_SHORT).show();
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
                        // App code
                        HomeActivity();
                        handleFacebookAccessToken(loginResult.getAccessToken());
//                          startActivityForResult(intent,1001);
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
                LoginManager.getInstance().logInWithReadPermissions(Loginpageactivity.this, Arrays.asList("email","public_profile"));
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
                            Toast.makeText(Loginpageactivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void googlesignin() {
        Intent intent = gsc.getSignInIntent();
        startActivityForResult(intent,1001);
    }


    private void twitterSignin(){
        OAuthProvider.Builder provider = OAuthProvider.newBuilder("twitter.com");
        // Target specific email with login hint.
        provider.addCustomParameter("lang",currentLanguage);
        Task<AuthResult> pendingResultTask = auth.getPendingAuthResult();
        if (pendingResultTask != null) {
            // There's something already here! Finish the sign-in for your user.
            pendingResultTask
                    .addOnSuccessListener(
                            new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    startActivity(new Intent(Loginpageactivity.this,MainActivity.class));
                                    Toast.makeText(Loginpageactivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                                }
                            })
                    .addOnFailureListener(
                            new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(Loginpageactivity.this,"This is if " +e.toString() , Toast.LENGTH_LONG).show();
                                }
                            });
        } else {
            auth.startActivityForSignInWithProvider(Loginpageactivity.this, provider.build())
                    .addOnSuccessListener(
                            new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    startActivity(new Intent(Loginpageactivity.this,MainActivity.class));
                                    Toast.makeText(Loginpageactivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                                }
                            })
                    .addOnFailureListener(
                            new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e)
                                {
                                    Toast.makeText(Loginpageactivity.this, "This is else "+e.toString(), Toast.LENGTH_LONG).show();
                                }
                            });
        }
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

        if (requestCode == LINKEDIN_REQUEST_CODE && data != null) {
            if (resultCode == RESULT_OK) {
                //Successfully signed in
                LinkedInUser user = data.getParcelableExtra("social_login");
                //acessing user info
                Log.i("LinkedInLogin", user.getFirstName());
                if (user != null){
                    startActivity(new Intent(Loginpageactivity.this,MainActivity.class));
                    Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
                }


            } else {

                if (data.getIntExtra("err_code", 0) == LinkedInBuilder.ERROR_USER_DENIED) {
                    //Handle : user denied access to account

                } else if (data.getIntExtra("err_code", 0) == LinkedInBuilder.ERROR_FAILED) {

                    //Handle : Error in API : see logcat output for details
                    Log.e("LINKEDIN ERROR", data.getStringExtra("err_message"));
                }
            }
        }
    }

    private void HomeActivity() {
        finish();
        Intent intent = new Intent(Loginpageactivity.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//
//    }


//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        finish();
//        System.exit(0);
//        return;
//    }
}