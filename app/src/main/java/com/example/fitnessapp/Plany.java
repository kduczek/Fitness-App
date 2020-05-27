package com.example.fitnessapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;


public class Plany extends Fragment {

    private TextView Nazwa,Tresc,Numer;
    private int i=0;
    private int c=1;
    private int t=0;
    private String cwiczenie1,cwiczenie2,cwiczenie3,cwiczenie4,cwiczenie5,cwiczenie6,cwiczenie7;
    private String plan1,plan2,plan3,plan4,plan5,plan6,plan7;
    private String licznik;
    private String username;
    private Konto urzytkownik;
    private FirebaseFirestore db=FirebaseFirestore.getInstance();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_plany, container, false);

        Nazwa = view.findViewById(R.id.NazwaPlanu2);
        Tresc = view.findViewById(R.id.CwiczenieOpis);
        Numer = view.findViewById(R.id.CwiczenieNumer2);
        final Button button = view.findViewById(R.id.Nastepne);

        FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();

        String uID = Objects.requireNonNull(mFirebaseAuth.getCurrentUser()).getUid();
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

                        switch (Objects.requireNonNull(licznik)) {
                            case "0":
                                nrplanu = plan1;
                                break;
                            case "1":
                                nrplanu = plan2;
                                break;
                            case "2":
                                nrplanu = plan3;
                                break;
                            case "3":
                                nrplanu = plan4;
                                break;
                            case "4":
                                nrplanu = plan5;
                                break;
                            case "5":
                                nrplanu = plan6;
                                break;
                            case "6":
                                nrplanu = plan7;
                                break;
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
                        });
                    }

                });

            }
        });

        return view;
    }

        private void SaveData() {
        t++;
            int w=Integer.parseInt(urzytkownik.getLicznik());
            w++;
            urzytkownik.setLicznik(String.valueOf(w));
        final FirebaseFirestore dba=FirebaseFirestore.getInstance();
        FirebaseFirestore dbb=FirebaseFirestore.getInstance();
            dbb.collection("Konta").document(urzytkownik.getKontoname()).update(
                   "licznik", urzytkownik.getLicznik());
            //Tresc.setText(urzytkownik.getLicznik()+urzytkownik.getKontoname());


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
                                    Toast.makeText(getContext(),"Postepy",Toast.LENGTH_SHORT).show();
                                }
                            });
                }



            });

    }
}
