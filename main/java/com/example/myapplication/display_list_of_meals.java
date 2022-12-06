package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.ui.login.Repas;

import android.os.Bundle;
import android.widget.ListView;
import com.example.myapplication.ui.login.Repas;
import com.example.myapplication.ui.login.CustomBaseAdapter;

import java.util.ArrayList;

public class display_list_of_meals extends AppCompatActivity {
    //grab the list of repas from the database
     public ArrayList<Repas> repas;

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_de_repas);
        listView = (ListView) findViewById(R.id.liste_de_repas);


        //--------change this so that it grabs item saved to the database Firebase---------------/
        Repas one = new Repas("entree1","platPrincipal1", "dessert1");
        Repas two = new Repas("entree2","platPrincipal2", "dessert2");
        Repas three = new Repas("entree3","platPrincipal3", "dessert3");
        repas.add(one);
        repas.add(two);
        repas.add(three);
        CustomBaseAdapter customBaseAdapt = new CustomBaseAdapter(getApplicationContext(),repas);
        listView.setAdapter(customBaseAdapt);
    }


}