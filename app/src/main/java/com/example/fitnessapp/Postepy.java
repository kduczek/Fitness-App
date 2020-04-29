package com.example.fitnessapp;

public class Postepy {

    private String NazwaUzytkownika;
    private int iloscTrenigow;
    private int iloscCwiczen;
    private int poczatkowyObwodPasa;
    //private int poczatkowyObwodKlaty;
    private int poczatkowyObwodBicepsa;
    private int poczatkowaWaga;
    private int docelowyObwodPasa;
    //private int docelowyoObwodKlaty;
    private int docelowyObwodBicepsa;
    private int docelowaWaga;

    public Postepy() {
    }

    public Postepy(String nazwaUzytkownika, int poczatkowyobwodpasa, int poczatkowyobwodbicepsa, int poczatkowawaga, int docelowyobwodpasa, int docelowyobwodbicepsa, int docelowawaga) {
        NazwaUzytkownika = nazwaUzytkownika;
        this.iloscTrenigow = 0;
        this.iloscCwiczen = 0;
        this.poczatkowyObwodPasa = poczatkowyobwodpasa;
        //this.poczatkowyObwodKlaty = poczatkowyobwodklaty;
        this.poczatkowyObwodBicepsa = poczatkowyobwodbicepsa;
        this.poczatkowaWaga = poczatkowawaga;
        this.docelowyObwodPasa = docelowyobwodpasa;
        //this.docelowyoObwodKlaty = docelowyobwodklaty;
        this.docelowyObwodBicepsa = docelowyobwodbicepsa;
        this.docelowaWaga = docelowawaga;
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

    public int getPoczatkowyObwodPasa() {
        return poczatkowyObwodPasa;
    }

    public void setPoczatkowyObwodPasa(int poczatkowyObwodPasa) {
        this.poczatkowyObwodPasa = poczatkowyObwodPasa;
    }

//    public int getPoczatkowyObwodKlaty() {
//        return poczatkowyObwodKlaty;
//    }
//
//    public void setPoczatkowyObwodKlaty(int poczatkowyObwodKlaty) {
//        this.poczatkowyObwodKlaty = poczatkowyObwodKlaty;
//    }

    public int getPoczatkowyObwodBicepsa() {
        return poczatkowyObwodBicepsa;
    }

    public void setPoczatkowyObwodBicepsa(int poczatkowyObwodBicepsa) {
        this.poczatkowyObwodBicepsa = poczatkowyObwodBicepsa;
    }

    public int getPoczatkowaWaga() {return poczatkowaWaga;}

    public void setPoczatkowaWaga(int poczatkowawaga) {this.poczatkowaWaga = poczatkowawaga;}

    public int getDocelowyObwodPasa() {
        return docelowyObwodPasa;
    }

    public void setDocelowyObwodPasa(int obwodPasa) {
        this.docelowyObwodPasa = obwodPasa;
    }

//    public int getDocelowyoObwodKlaty() {
//        return docelowyoObwodKlaty;
//    }
//
//    public void setDocelowyoObwodKlaty(int obwodKlaty) {
//        this.docelowyoObwodKlaty = obwodKlaty;
//    }

    public int getDocelowyObwodBicepsa() {
        return docelowyObwodBicepsa;
    }

    public void setDocelowyObwodBicepsa(int obwodBicepsa) { this.docelowyObwodBicepsa = obwodBicepsa; }

    public int getDocelowaWaga() {return docelowaWaga;}

    public void setDocelowaWaga(int docelowawaga) {this.docelowaWaga = docelowawaga;}

    public void Trenigplus(){
    iloscTrenigow++;
    }

    public void Cwiczeniaplus(){
        iloscCwiczen++;
    }
}
