package com.example.fitnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Document;

import java.util.Map;

public class Plany extends AppCompatActivity {

    TextView Nazwa,Tresc,Numer;
    int i=0;
    int c=1;
    int t=0;
    Button button;
    String cwiczenie1,cwiczenie2,cwiczenie3,cwiczenie4,cwiczenie5,cwiczenie6,cwiczenie7;
    String plan1,plan2,plan3,plan4,plan5,plan6,plan7;
    String licznik;
    String nazwa;
    String username;
    String mail;
    Konto urzytkownik;
    //Postepy postep;
    String uID;
    FirebaseAuth mFirebaseAuth;
    FirebaseFirestore db=FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plany);

        Nazwa=findViewById(R.id.NazwaPlanu2);
        Tresc=findViewById(R.id.CwiczenieOpis);
        Numer=findViewById(R.id.CwiczenieNumer2);
        button=findViewById(R.id.Nastepne);

        mFirebaseAuth = FirebaseAuth.getInstance();

        uID = mFirebaseAuth.getCurrentUser().getUid();
        DocumentReference ref = db.collection("users").document(uID);
        ref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                username = documentSnapshot.getString("username");

                DocumentReference refkonto=db.collection("Konta").document(username);

                refkonto.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshotk) {

                        urzytkownik=new Konto(documentSnapshotk.getString("kontoname"),
                                documentSnapshotk.getString("email"),
                                documentSnapshotk.getString("plan1"),
                                documentSnapshotk.getString("plan2"),
                                documentSnapshotk.getString("plan3"),
                                documentSnapshotk.getString("plan4"),
                                documentSnapshotk.getString("plan5"),
                                documentSnapshotk.getString("plan6"),
                                documentSnapshotk.getString("plan7"),
                                documentSnapshotk.getString("licznik"));

                        plan1=documentSnapshotk.getString("plan1");
                        plan2=documentSnapshotk.getString("plan2");
                        plan3=documentSnapshotk.getString("plan3");
                        plan4=documentSnapshotk.getString("plan4");
                        plan5=documentSnapshotk.getString("plan5");
                        plan6=documentSnapshotk.getString("plan6");
                        plan7=documentSnapshotk.getString("plan7");
                        licznik=documentSnapshotk.getString("licznik");
                        String nrplanu="";

                        if(licznik.equals("0"))
                        {
                            nrplanu=plan1;
                        }
                        else if(licznik.equals("1"))
                        {
                            nrplanu=plan2;
                        }
                        else if(licznik.equals("2"))
                        {
                            nrplanu=plan3;
                        }
                        else if(licznik.equals("3"))
                        {
                            nrplanu=plan4;
                        }
                        else if(licznik.equals("4"))
                        {
                            nrplanu=plan5;
                        }
                        else if(licznik.equals("5"))
                        {
                            nrplanu=plan2;
                        }
                        else if(licznik.equals("6"))
                        {
                            nrplanu=plan7;
                        }
                        if(!nrplanu.equals("")){}
                        else{nrplanu=plan1;licznik="0";urzytkownik.setLicznik("0");}

                        DocumentReference refplan=db.collection("PLANY").document(nrplanu);

                        refplan.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                Nazwa.setText(documentSnapshot.getString("name"));
                                cwiczenie1=documentSnapshot.getString("cwiczenie1");
                                cwiczenie2=documentSnapshot.getString("cwiczenie2");
                                cwiczenie3=documentSnapshot.getString("cwiczenie3");
                                cwiczenie4=documentSnapshot.getString("cwiczenie4");
                                cwiczenie5=documentSnapshot.getString("cwiczenie5");
                                cwiczenie6=documentSnapshot.getString("cwiczenie6");
                                cwiczenie7=documentSnapshot.getString("cwiczenie7");
                                if(i==0)
                                {Numer.setText("Cwiczenie pierwsze");
                                    Tresc.setText(cwiczenie1);}
                                i++;
                            }
                        });
                    }

                });

            }
        });

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
               if(i==1)
                {Numer.setText("Cwiczenie drugie");
                    Tresc.setText(cwiczenie2);
                    }
                else if(i==2)
                {
                    if(cwiczenie3.equals(""))
                    {
                        Numer.setText("Koniec");
                        SaveData();
                    }
                    else
                    {
                    Numer.setText("Cwiczenie trzecie");
                    Tresc.setText(cwiczenie3);
                    }
                }
                else if(i==3)
                {
                    if(cwiczenie4.equals(""))
                    {
                        Numer.setText("Koniec");
                        SaveData();
                    }
                    else
                    {
                      Numer.setText("Cwiczenie czwarte");
                      Tresc.setText(cwiczenie4);
                    }
                }
                else if(i==4)
                {
                    if(cwiczenie5.equals(""))
                    {
                        Numer.setText("Koniec");
                        SaveData();
                    }
                    else
                    {
                     Numer.setText("Cwiczenie piate");
                     Tresc.setText(cwiczenie5);
                    }
                }
                else if(i==5)
                {if(cwiczenie6.equals(""))
                {
                    Numer.setText("Koniec");
                    SaveData();
                }
                else
                {
                    Numer.setText("Cwiczenie szuste");
                    Tresc.setText(cwiczenie6);
                }}
                else if(i==6){


                    Numer.setText("Cwiczenie siudme");
                    Tresc.setText(cwiczenie7);
                    SaveData();
                }

                i++;
                c++;
            }});


    }

        private void SaveData() {
        t++;
            int w=Integer.parseInt(urzytkownik.getLicznik());
            w++;
            urzytkownik.setLicznik(String.valueOf(w));
        final FirebaseFirestore dba=FirebaseFirestore.getInstance();
        DocumentReference refkontoaktualizacja = dba.collection("Konta").document(urzytkownik.getKontoname());
        refkontoaktualizacja.set(urzytkownik)
                .addOnSuccessListener(new OnSuccessListener<Void>(){
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(Plany.this,"Zapisane",Toast.LENGTH_SHORT).show();
                    }
                });

            DocumentReference refpostepy=dba.collection("Postepy").document(urzytkownik.getKontoname());


            refpostepy.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {

                    Postepy postep;
                            String dane1=documentSnapshot.getString("nazwaUzytkownika");
                            String dane2=documentSnapshot.getString("poczatkowyObwodPasa");
                            String dane3= documentSnapshot.getString("poczatkowyObwodBicepsa");
                            String dane4 =documentSnapshot.getString("poczatkowaWaga");
                            String dane5=documentSnapshot.getString("docelowyObwodPasa");
                            String dane6=documentSnapshot.getString("docelowyObwodBicepsa");
                            String dane7=documentSnapshot.getString("docelowaWaga");
                            String dane8=documentSnapshot.getString("iloscCwiczen");
                            String dane9=documentSnapshot.getString("iloscTrenigow");


                    postep=new Postepy(dane1,dane2,dane3,dane4,dane5,dane6,dane7,dane8,dane9);
                    postep.cwiczeniaplus(c);
                    postep.trenigplus(t);

                    DocumentReference refpostepaktualizacja = dba.collection("Postepy").document(urzytkownik.getKontoname());
                    refpostepaktualizacja.set(postep)
                            .addOnSuccessListener(new OnSuccessListener<Void>(){
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(Plany.this,"Postepy",Toast.LENGTH_SHORT).show();
                                }
                            });
                }



            });

    }
}
