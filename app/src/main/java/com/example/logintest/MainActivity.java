package com.example.logintest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {

    EditText emailID,password,password_confm;
    Button btnSignUp;
    TextView tvSignIn;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAuth = FirebaseAuth.getInstance();
        emailID = findViewById(R.id.emlSP);
        password = findViewById(R.id.psdSP);
        password_confm = findViewById(R.id.psdSP_confm);
        btnSignUp = findViewById(R.id.btnSP);
        tvSignIn = findViewById(R.id.tvALD);

        //String pas_in = password.getText().toString();
        //String pas_con = password_confm.getText().toString();



        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = emailID.getText().toString();
                String pwd = password.getText().toString();
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

                    Toast.makeText(MainActivity.this,"Fields are empty",Toast.LENGTH_SHORT).show();
                }
                else if((!email.isEmpty() && !pwd.isEmpty())){

                    if(pwd.matches(pwd_con) && (Patterns.EMAIL_ADDRESS.matcher(email).matches())){

                        mFirebaseAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if(!task.isSuccessful()){

                                    Toast.makeText(MainActivity.this,"SignUp failed, please try again",Toast.LENGTH_SHORT).show();

                                }else{


                                    //startActivity(new Intent(MainActivity.this,Res_owner_dashboard.class));
                                    //Toast.makeText(MainActivity.this,"SignUp ok",Toast.LENGTH_SHORT).show();

                                    sendEmailVerification();

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

                    Toast.makeText(MainActivity.this,"Error Occured",Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this,LogInActivity.class);
                startActivity(i);

            }
        });

    }

    public void sendEmailVerification() {

        mFirebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){

                    Toast.makeText(MainActivity.this,"Registration completed. Plsease check your email to verify account.",Toast.LENGTH_LONG).show();

                }
                else{

                    Toast.makeText(MainActivity.this,"Error Occured",Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

    /*
    public void createUser(){

        mFirebaseAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(!task.isSuccessful()){

                    Toast.makeText(MainActivity.this,"SignUp failed, please try again",Toast.LENGTH_SHORT).show();

                }else{


                    //startActivity(new Intent(MainActivity.this,Res_owner_dashboard.class));
                    //Toast.makeText(MainActivity.this,"SignUp ok",Toast.LENGTH_SHORT).show();

                    sendEmailVerification();

                }
            }
        });

    }
    */


}
