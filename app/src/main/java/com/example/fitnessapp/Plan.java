package com.example.fitnessapp;

public class Plan{

    private String Name;
    private String cwiczenie1;
    private String cwiczenie2;
    private String cwiczenie3;
    private String cwiczenie4;
    private String cwiczenie5;
    private String cwiczenie6;
    private String cwiczenie7;


    public Plan(String Name,String cwi1,String cwi2,String cwi3,String cwi4,String cwi5,String cwi6,String cwi7) {
        this.Name=Name;
        this.cwiczenie1=cwi1;
        this.cwiczenie2=cwi2;
        this.cwiczenie3=cwi3;
        this.cwiczenie4=cwi4;
        this.cwiczenie5=cwi5;
        this.cwiczenie6=cwi6;
        this.cwiczenie7=cwi7;
    }

    public Plan(){
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getcwiczenie1() {
        return cwiczenie1;
    }
    public String getcwiczenie2() {
        return cwiczenie2;
    }
    public String getcwiczenie3() {
        return cwiczenie3;
    }
    public String getcwiczenie4() {
        return cwiczenie4;
    }
    public String getcwiczenie5() {
        return cwiczenie5;
    }
    public String getcwiczenie6() {
        return cwiczenie6;
    }
    public String getcwiczenie7() {
        return cwiczenie7;
    }

    public void setcwiczenie1(String cwi1) { cwiczenie1= cwi1; }
    public void setcwiczenie2(String cwi2) { cwiczenie1= cwi2; }
    public void setcwiczenie3(String cwi3) { cwiczenie1= cwi3; }
    public void setcwiczenie4(String cwi4) { cwiczenie1= cwi4; }
    public void setcwiczenie5(String cwi5) { cwiczenie1= cwi5; }
    public void setcwiczenie6(String cwi6) { cwiczenie1= cwi6; }
    public void setcwiczenie7(String cwi7) { cwiczenie1= cwi7; }
}
