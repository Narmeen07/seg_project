package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserType extends AppCompatActivity {
    private Button BnCook;
    private Button BnUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_type);
        BnUser = findViewById(R.id.BtnUser);
        BnCook = findViewById(R.id.BtnCook);

        BnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserType.this, UserRegistration.class);
                startActivity(intent);

            }
        });
        BnCook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(UserType.this, CookRegistration.class);
               startActivity(intent);
            }
        });
    }
}