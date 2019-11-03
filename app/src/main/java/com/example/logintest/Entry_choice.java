package com.example.logintest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Entry_choice extends AppCompatActivity {

    Button btn_user,btn_res_owner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_choice);

        btn_user = (Button) findViewById(R.id.button_user);
        btn_res_owner = (Button) findViewById(R.id.button_res_owner);

        btn_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //startActivity(new Intent(Entry_choice.this,));
                startActivity(new Intent(Entry_choice.this,UserNewsFeed.class));
            }
        });

        btn_res_owner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Entry_choice.this,MainActivity.class));

            }
        });
    }
}
