package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.ui.login.Repas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class create_a_meal extends AppCompatActivity {
    //variables for the class meal
    private EditText appetiser;
    private EditText main_course;
    private EditText dessert;
    private Button add_meal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_a_meal);


        add_meal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get the id's of the editfields and the button
                appetiser = findViewById(R.id.edit_entree);
                main_course = findViewById(R.id.edit_plat_principal);
                dessert = findViewById(R.id.edit_dessert);
                add_meal = findViewById(R.id.add_btn);
                Repas repas = new Repas(appetiser.getText().toString(), main_course.getText().toString(), dessert.getText().toString());

                //-----Task save this meal to firebase ------------//
                Intent intent = new Intent(create_a_meal.this, display_list_of_meals.class);

            }
        });


    }
}