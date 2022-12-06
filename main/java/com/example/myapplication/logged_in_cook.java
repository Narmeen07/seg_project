package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class logged_in_cook extends AppCompatActivity {
    //This activity allows us to create a meal
  private Button addMeal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Grab the details of the cook from firebase and populate the textfields
        //nom, prenom et courriel
        addMeal.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {

                //check if the cook has been suspended then create a repas
                //create a meal
                Intent intent = new Intent(logged_in_cook.this, create_a_meal.class);

            }
        });
    }
}