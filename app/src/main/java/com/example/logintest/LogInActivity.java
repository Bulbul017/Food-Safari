package com.example.logintest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogInActivity extends AppCompatActivity {

    EditText emailID,password,password_confm;
    Button btnSignIn;
    TextView tvSignUp;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        mFirebaseAuth = FirebaseAuth.getInstance();
        emailID = findViewById(R.id.editText2);
        password = findViewById(R.id.editText);
        password_confm = findViewById(R.id.editText3);
        btnSignIn = findViewById(R.id.button);
        tvSignUp = findViewById(R.id.textView);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if(mFirebaseUser != null){

                    Toast.makeText(LogInActivity.this, "You are logged in", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LogInActivity.this,HomeActivity.class);
                    startActivity(i);

                }
                else {

                    Toast.makeText(LogInActivity.this, "Please Login", Toast.LENGTH_SHORT).show();

                }
            }
        };

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = emailID.getText().toString();
                final String pwd = password.getText().toString();
                String pwd_con = password_confm.getText().toString();

                if(email.isEmpty()){

                    emailID.setError("Please enter your email ID");
                    emailID.requestFocus();

                }
                else if(pwd.isEmpty()){

                    password.setError("Please enter your password");
                    password.requestFocus();

                }
                else if(email.isEmpty() && pwd.isEmpty()){

                    Toast.makeText(LogInActivity.this,"Fields are empty",Toast.LENGTH_SHORT).show();
                }
                else if(!(email.isEmpty() && pwd.isEmpty())){

                    if(pwd.matches(pwd_con) && (Patterns.EMAIL_ADDRESS.matcher(email).matches())){

                        mFirebaseAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(LogInActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if(!task.isSuccessful()){

                                    Toast.makeText(LogInActivity.this,"Login failed, please try again",Toast.LENGTH_SHORT).show();

                                }else{

                                    Constant.res_owner_pass = pwd;

                                    Log.i("#constant_got",pwd);

                                    startActivity(new Intent(LogInActivity.this,Res_owner_dashboard.class));
                                }
                            }
                        });

                    }
                    if(!pwd.matches(pwd_con)){

                        password_confm.setError("Password doesn't match");
                        password_confm.requestFocus();

                    }
                    if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){

                        emailID.setError("Please enter a valid email ID");
                        emailID.requestFocus();

                    }

                }
                else{

                    Toast.makeText(LogInActivity.this,"Error Occured",Toast.LENGTH_SHORT).show();
                }
            }

        });

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(LogInActivity.this,MainActivity.class);
                startActivity(i);

            }
        });


    }

    @Override
    protected  void onStart(){

        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);

    }
}
