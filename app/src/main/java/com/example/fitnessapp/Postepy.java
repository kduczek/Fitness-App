package com.example.fitnessapp;

public class Postepy {

    private String NazwaUzytkownika;
    private String iloscTrenigow;
    private String iloscCwiczen;
    private String poczatkowyObwodPasa;
    private String poczatkowyObwodBicepsa;
    private String poczatkowaWaga;
    private String docelowyObwodPasa;
    private String docelowyObwodBicepsa;
    private String docelowaWaga;

    public Postepy() {
    }

    Postepy(String nazwaUzytkownika, String poczatkowyobwodpasa, String poczatkowyobwodbicepsa, String poczatkowawaga, String docelowyobwodpasa, String docelowyobwodbicepsa, String docelowawaga) {
        NazwaUzytkownika = nazwaUzytkownika;
        this.iloscTrenigow = "0";
        this.iloscCwiczen = "0";
        this.poczatkowyObwodPasa = poczatkowyobwodpasa;
        this.poczatkowyObwodBicepsa = poczatkowyobwodbicepsa;
        this.poczatkowaWaga = poczatkowawaga;
        this.docelowyObwodPasa = docelowyobwodpasa;
        this.docelowyObwodBicepsa = docelowyobwodbicepsa;
        this.docelowaWaga = docelowawaga;
    }

    Postepy(String nazwaUzytkownika, String poczatkowyobwodpasa, String poczatkowyobwodbicepsa, String poczatkowawaga, String docelowyobwodpasa, String docelowyobwodbicepsa, String docelowawaga, String ilosccwiczen, String ilosctrenigow) {
        NazwaUzytkownika = nazwaUzytkownika;
        this.iloscTrenigow = ilosctrenigow;
        this.iloscCwiczen = ilosccwiczen;
        this.poczatkowyObwodPasa = poczatkowyobwodpasa;
        this.poczatkowyObwodBicepsa = poczatkowyobwodbicepsa;
        this.poczatkowaWaga = poczatkowawaga;
        this.docelowyObwodPasa = docelowyobwodpasa;
        this.docelowyObwodBicepsa = docelowyobwodbicepsa;
        this.docelowaWaga = docelowawaga;
    }

    public String getNazwaUzytkownika() {
        return NazwaUzytkownika;
    }

    public void setNazwaUzytkownika(String nazwaUzytkownika) {
        NazwaUzytkownika = nazwaUzytkownika;
    }

    public String getIloscTrenigow() {
        return iloscTrenigow;
    }

    public void setIloscTrenigow(String iloscTrenigow) {
        this.iloscTrenigow = iloscTrenigow;
    }

    public String getIloscCwiczen() {
        return iloscCwiczen;
    }

    public void setIloscCwiczen(String iloscCwiczen) {
        this.iloscCwiczen = iloscCwiczen;
    }

    public String getPoczatkowyObwodPasa() {
        return poczatkowyObwodPasa;
    }

    public void setPoczatkowyObwodPasa(String poczatkowyObwodPasa) {
        this.poczatkowyObwodPasa = poczatkowyObwodPasa;
    }

    public String getPoczatkowyObwodBicepsa() {
        return poczatkowyObwodBicepsa;
    }

    public void setPoczatkowyObwodBicepsa(String poczatkowyObwodBicepsa) {
        this.poczatkowyObwodBicepsa = poczatkowyObwodBicepsa;
    }

    public String getPoczatkowaWaga() {
        return poczatkowaWaga;
    }

    public void setPoczatkowaWaga(String poczatkowaWaga) {
        this.poczatkowaWaga = poczatkowaWaga;
    }

    public String getDocelowyObwodPasa() {
        return docelowyObwodPasa;
    }

    public void setDocelowyObwodPasa(String docelowyObwodPasa) {
        this.docelowyObwodPasa = docelowyObwodPasa;
    }

    public String getDocelowyObwodBicepsa() {
        return docelowyObwodBicepsa;
    }

    public void setDocelowyObwodBicepsa(String docelowyObwodBicepsa) {
        this.docelowyObwodBicepsa = docelowyObwodBicepsa;
    }

    public String getDocelowaWaga() {
        return docelowaWaga;
    }

    public void setDocelowaWaga(String docelowaWaga) {
        this.docelowaWaga = docelowaWaga;
    }

    void trenigplus(int t){
        int v=Integer.parseInt(this.iloscTrenigow);
        v=v+t;
        this.iloscTrenigow=String.valueOf(v);
    }

    void cwiczeniaplus(int c){
        int v=Integer.parseInt(this.iloscCwiczen);
        v=v+c;
        this.iloscCwiczen=String.valueOf(v);
    }

}
