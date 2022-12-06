package com.example.myapplication.ui.login;

public class Repas {
    public String entree;
    public String platPrincipal;
    public String dessert;

    public Repas(String entree, String platPrincipal, String dessert) {
        this.entree = entree;
        this.platPrincipal = platPrincipal;
        this.dessert = dessert;
    }

    public String getEntree() {
        return entree;
    }

    public String getPlatPrincipal() {
        return platPrincipal;
    }

    public String getDessert() {
        return dessert;
    }

}
