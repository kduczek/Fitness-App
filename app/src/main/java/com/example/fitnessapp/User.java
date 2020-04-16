package com.example.fitnessapp;

public class User {
    private String Name;
    private String email;
    private String password;

    public User(){}

    public User(String n,String e,String p)
    {
        Name=n;
        email=e;
        password=p;
    }

    public String getName(){
        return Name;
    }

    private void setName(String n) {
        Name = n;
    }

    private String getEmail() {
        return email;
    }

    private void setEmail(String e){
        email=e;
    }

    private String getPassword(){
        return password;
    }

    private void setPassword(String p){
        password=p;
    }
}
