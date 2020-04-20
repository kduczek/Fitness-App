package com.example.fitnessapp;

public class Postepy {

    private String NazwaUzytkownika;
    private int iloscTrenigow;
    private int iloscCwiczen;
    private double poczatkowyObwodPasa;
    private double poczatkowyObwodKlaty;
    private double poczatkowyObwodBicepsa;
    private double obwodPasa;
    private double obwodKlaty;
    private double obwodBicepsa;

    public Postepy() {
    }

    public Postepy(String nazwaUzytkownika, double poczatkowyobwodpasa, double poczatkowyobwodklaty, double poczatkowyobwodbicepsa) {
        NazwaUzytkownika = nazwaUzytkownika;
        this.iloscTrenigow = 0;
        this.iloscCwiczen = 0;
        this.poczatkowyObwodPasa = poczatkowyobwodpasa;
        this.poczatkowyObwodKlaty = poczatkowyobwodklaty;
        this.poczatkowyObwodBicepsa = poczatkowyobwodbicepsa;
        this.obwodPasa = poczatkowyobwodpasa;
        this.obwodKlaty = poczatkowyobwodklaty;
        this.obwodBicepsa = poczatkowyobwodbicepsa;
    }

    public String getNazwaUzytkownika() {
        return NazwaUzytkownika;
    }

    public void setNazwaUzytkownika(String nazwaUzytkownika) {
        NazwaUzytkownika = nazwaUzytkownika;
    }

    public int getIloscTrenigow() {
        return iloscTrenigow;
    }

    public void setIloscTrenigow(int iloscTrenigow) {
        this.iloscTrenigow = iloscTrenigow;
    }

    public int getIloscCwiczen() {
        return iloscCwiczen;
    }

    public void setIloscCwiczen(int iloscCwiczen) {
        this.iloscCwiczen = iloscCwiczen;
    }

    public double getPoczatkowyObwodPasa() {
        return poczatkowyObwodPasa;
    }

    public void setPoczatkowyObwodPasa(double poczatkowyObwodPasa) {
        this.poczatkowyObwodPasa = poczatkowyObwodPasa;
    }

    public double getPoczatkowyObwodKlaty() {
        return poczatkowyObwodKlaty;
    }

    public void setPoczatkowyObwodKlaty(double poczatkowyObwodKlaty) {
        this.poczatkowyObwodKlaty = poczatkowyObwodKlaty;
    }

    public double getPoczatkowyObwodBicepsa() {
        return poczatkowyObwodBicepsa;
    }

    public void setPoczatkowyObwodBicepsa(double poczatkowyObwodBicepsa) {
        this.poczatkowyObwodBicepsa = poczatkowyObwodBicepsa;
    }

    public double getObwodPasa() {
        return obwodPasa;
    }

    public void setObwodPasa(double obwodPasa) {
        this.obwodPasa = obwodPasa;
    }

    public double getObwodKlaty() {
        return obwodKlaty;
    }

    public void setObwodKlaty(double obwodKlaty) {
        this.obwodKlaty = obwodKlaty;
    }

    public double getObwodBicepsa() {
        return obwodBicepsa;
    }

    public void setObwodBicepsa(double obwodBicepsa) {
        this.obwodBicepsa = obwodBicepsa;
    }

    public void Trenigplsu(){
    iloscTrenigow++;
    }

    public void Cwiczeniaplus(){
        iloscCwiczen++;
    }
}
