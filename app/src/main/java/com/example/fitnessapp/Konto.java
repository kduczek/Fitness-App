package com.example.fitnessapp;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Konto {
    private String username;
    private String email;
    String plan1;
    String plan2;
    String plan3;
    String plan4;
    String plan5;
    String plan6;
    String plan7;
    String licznik;

    public Konto() {
    }

    public Konto(String u,String e,String p1,String p2,String p3,String p4,String p5,String p6,String p7,String l) {
        username = u;
        email = e;
        plan1=p1;
        plan2=p2;
        plan3=p3;
        plan4=p4;
        plan5=p5;
        plan6=p6;
        plan7=p7;
        licznik=l;
    }

    public String getKontoname() {
        return username;
    }

    private void setKontoname(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    private void setEmail(String email) {
        this.email = email;
    }

    public String getPlan1() {
        return plan1;
    }

    public void setPlan1(String plan1) {
        this.plan1 = plan1;
    }

    public String getPlan2() {
        return plan2;
    }

    public void setPlan2(String plan2) {
        this.plan2 = plan2;
    }

    public String getPlan3() {
        return plan3;
    }

    public void setPlan3(String plan3) {
        this.plan3 = plan3;
    }

    public String getPlan4() {
        return plan4;
    }

    public void setPlan4(String plan4) {
        this.plan4 = plan4;
    }

    public String getPlan5() {
        return plan5;
    }

    public void setPlan5(String plan5) {
        this.plan5 = plan5;
    }

    public String getPlan6() {
        return plan6;
    }

    public void setPlan6(String plan6) {
        this.plan6 = plan6;
    }

    public String getPlan7() {
        return plan7;
    }

    public void setPlan7(String plan7) {
        this.plan7 = plan7;
    }

    public String getLicznik() {
        return licznik;
    }

    public void setLicznik(String licznik) {
        this.licznik = licznik;
    }


}