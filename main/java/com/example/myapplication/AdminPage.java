package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminPage extends AppCompatActivity {
    private Button btnComplaints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);

        btnComplaints = findViewById(R.id.BtnComplaints);

        btnComplaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //it sends you to the complaints page
                Intent intent = new Intent(AdminPage.this, ComplaintPage.class);
                startActivity(intent);

            }
        });
    }
}