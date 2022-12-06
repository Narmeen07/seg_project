package com.example.myapplication.ui.login;

import android.widget.BaseAdapter;
import android.widget.TextView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.myapplication.ui.login.Repas;
import com.example.myapplication.R;
import java.util.ArrayList;

public class CustomBaseAdapter extends BaseAdapter {
    //This class shows all the meals that the cook has added on the page "activity_liste_de_repas"
    //The list of meals is displayed once the cook adds a meal
    Context context;
    ArrayList<Repas> repas;
    LayoutInflater inflater;

    public CustomBaseAdapter(Context ctx, ArrayList<Repas> repas) {
        //fill in context
        this.context = ctx;
        this.repas = repas;
        inflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return repas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.item_de_repas, null);
        TextView entree = (TextView) convertView.findViewById(R.id.appetiser);
        TextView platPrincipal = (TextView) convertView.findViewById(R.id.main_dish);
        TextView dessert = (TextView) convertView.findViewById(R.id.dessert);
        entree.setText(repas.get(position).getEntree());
        platPrincipal.setText(repas.get(position).getPlatPrincipal());
        dessert.setText(repas.get(position).getDessert());
         return convertView;
    }
}
