package com.example.myapplication;

public class Client extends AbstractPerson{
    private String name, firstName, email, adress, password, creditName, creditNumber, securityNumber, date;

    public Client(String name, String firstName, String email, String adress, String password, String creditName,
                  String creditNumber, String securityNumber, String date){
        this.name=name;
        this.firstName=firstName;
        this.email=email;
        this.adress=adress;
        this.password=password;
        this.creditName=creditName;
        this.creditNumber=creditNumber;
        this.securityNumber=securityNumber;
        this.date=date;
    }
    @Override
    public void add(String a, String b, String c, String d, String e, String f, String g, String h, String i) {
        new Client(a, b, c, d, e, f, g, h, i);
    }

    public String getPassword(){
        return password;
    }

    public String getUsername(){
        return email;
    }

    public String getInfo(){
        return creditNumber+" "+securityNumber+" "+date;
    }
}
