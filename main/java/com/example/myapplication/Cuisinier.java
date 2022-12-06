package com.example.myapplication;

public class Cuisinier extends AbstractPerson{
    String name, firstName, email, password, adress, description;
    boolean suspended;
    public Cuisinier(String name, String firstName, String email, String password, String adress,
                    String description){
        this.name=name;
        this.firstName=firstName;
        this.email=email;
        this.password=password;
        this.adress=adress;
        this.description=description;
        this.suspended = false;
    }
    public void add(String a, String b, String c, String d, String e, String f){
        new Cuisinier(a, b, c, d, e, f);
    }
    public String getUsername(){
        return email;
    }
    public String getPassword(){
        return password;
    }
}
