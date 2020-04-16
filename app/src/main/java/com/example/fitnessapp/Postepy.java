package com.example.fitnessapp;

public class Postepy {

    private String NazwaUrzytkownika;
    private int ilosctrenigow;
    private int ilosccwiczen;
    private double poczatkowyobwodpasa;
    private double poczatkowyodbwodklaty;
    private double poczatkowyobwodbiecpsa;
    private double obwodpasa;
    private double odbwodklaty;
    private double obwodbiecpsa;

    public Postepy() {
    }

    public Postepy(String nazwaUrzytkownika, double poczatkowyobwodpasa, double poczatkowyodbwodklaty, double poczatkowyobwodbiecpsa) {
        NazwaUrzytkownika = nazwaUrzytkownika;
        this.ilosctrenigow = 0;
        this.ilosccwiczen = 0;
        this.poczatkowyobwodpasa = poczatkowyobwodpasa;
        this.poczatkowyodbwodklaty = poczatkowyodbwodklaty;
        this.poczatkowyobwodbiecpsa = poczatkowyobwodbiecpsa;
        this.obwodpasa = poczatkowyobwodpasa;
        this.odbwodklaty = poczatkowyodbwodklaty;
        this.obwodbiecpsa = poczatkowyobwodbiecpsa;
    }

    public String getNazwaUrzytkownika() {
        return NazwaUrzytkownika;
    }

    public void setNazwaUrzytkownika(String nazwaUrzytkownika) {
        NazwaUrzytkownika = nazwaUrzytkownika;
    }

    public int getIlosctrenigow() {
        return ilosctrenigow;
    }

    public void setIlosctrenigow(int ilosctrenigow) {
        this.ilosctrenigow = ilosctrenigow;
    }

    public int getIlosccwiczen() {
        return ilosccwiczen;
    }

    public void setIlosccwiczen(int ilosccwiczen) {
        this.ilosccwiczen = ilosccwiczen;
    }

    public double getPoczatkowyobwodpasa() {
        return poczatkowyobwodpasa;
    }

    public void setPoczatkowyobwodpasa(double poczatkowyobwodpasa) {
        this.poczatkowyobwodpasa = poczatkowyobwodpasa;
    }

    public double getPoczatkowyodbwodklaty() {
        return poczatkowyodbwodklaty;
    }

    public void setPoczatkowyodbwodklaty(double poczatkowyodbwodklaty) {
        this.poczatkowyodbwodklaty = poczatkowyodbwodklaty;
    }

    public double getPoczatkowyobwodbiecpsa() {
        return poczatkowyobwodbiecpsa;
    }

    public void setPoczatkowyobwodbiecpsa(double poczatkowyobwodbiecpsa) {
        this.poczatkowyobwodbiecpsa = poczatkowyobwodbiecpsa;
    }

    public double getObwodpasa() {
        return obwodpasa;
    }

    public void setObwodpasa(double obwodpasa) {
        this.obwodpasa = obwodpasa;
    }

    public double getOdbwodklaty() {
        return odbwodklaty;
    }

    public void setOdbwodklaty(double odbwodklaty) {
        this.odbwodklaty = odbwodklaty;
    }

    public double getObwodbiecpsa() {
        return obwodbiecpsa;
    }

    public void setObwodbiecpsa(double obwodbiecpsa) {
        this.obwodbiecpsa = obwodbiecpsa;
    }

    public void Trenigplsu(){
    ilosctrenigow++;
    }

    public void Cwiczeniaplus(){
        ilosccwiczen++;
    }
}
