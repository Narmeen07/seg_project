package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

public class LoggedInPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle status = getIntent().getExtras();
        EditText Status=(EditText)findViewById(R.id.Status);
        Status.setText(status.getString("status"));
        setContentView(R.layout.activity_logged_in_page);

        (findViewById(R.id.BtnLogout)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoggedInPage.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}