package com.example.logintest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Res_owner_dashboard extends AppCompatActivity {
    Button info,settings,addFood,showMenu,helpLine,logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_owner_dashboard);
        info=(Button)findViewById(R.id.btn_restaurant_info);
        settings=(Button)findViewById(R.id.btn_restaurant_settings);
        addFood=(Button)findViewById(R.id.btn_add_food);
        showMenu=(Button)findViewById(R.id.btn_restaurant_show_menu);
        helpLine=(Button)findViewById(R.id.btn_restaurant_helpline);
        logout=(Button)findViewById(R.id.btn_restaurant_logout);

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Res_owner_dashboard.this,"Best Seller",Toast.LENGTH_SHORT).show();
                changeActivity(RestaurantInfo.class);

            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Res_owner_dashboard.this,"Settings",Toast.LENGTH_SHORT).show();
                changeActivity(RestaurantSettings.class);
            }
        });
        addFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Res_owner_dashboard.this,"Add Food",Toast.LENGTH_SHORT).show();
                changeActivity(UpdateFood.class);
            }
        });
        showMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Res_owner_dashboard.this,"Menu",Toast.LENGTH_SHORT).show();
                changeActivity(ImagesActivity.class);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this,"Logout",Toast.LENGTH_SHORT).show();

            }
        });
        helpLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(Res_owner_dashboard.this,"Helpline",Toast.LENGTH_SHORT).show();
                changeActivity(SignUpOwnerInfo.class);

            }
        });

    }
    public void changeActivity(Class destination)
    {
        Intent intent=new Intent(this,destination);
        //intent.putExtra("Player Choice",result);
        startActivity(intent);
    }
}


/*
public class Res_owner_dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_owner_dashboard);
    }
}
*/
