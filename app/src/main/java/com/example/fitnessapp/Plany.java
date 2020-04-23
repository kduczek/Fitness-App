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
    Button button;
    String cwiczenie1,cwiczenie2,cwiczenie3,cwiczenie4,cwiczenie5,cwiczenie6,cwiczenie7;
    String nazwa;
    String username;
    String mail;
    Konto urzytkownik;
    int c=0;
    Plan plan;
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
                Map<String, Object> user = documentSnapshot.getData();
                username = (String) user.get("username");
                mail=(String) user.get("email");

                DocumentReference refkonto=db.collection("Konta").document((String) user.get("username"));

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

                        String nrplanu="";

                        if(documentSnapshotk.getString("licznik")=="0")
                        {
                            nrplanu=documentSnapshotk.getString("plan1");
                        }
                        else if(documentSnapshotk.getString("licznik")=="1")
                        {
                            nrplanu=documentSnapshotk.getString("plan2");
                        }
                        else if(documentSnapshotk.getString("licznik")=="2")
                        {
                            nrplanu=documentSnapshotk.getString("plan3");
                        }
                        /* else if(urzytkownik.getLicznik()=="3")
                        {
                            nrplanu=urzytkownik.getPlan4();
                        }
                        else if(urzytkownik.getLicznik()=="4")
                        {
                            nrplanu=urzytkownik.getPlan5();
                        }
                        else if(urzytkownik.getLicznik()=="5")
                        {
                            nrplanu=urzytkownik.getPlan6();
                        }
                        else if(urzytkownik.getLicznik()=="6")
                        {
                            nrplanu=urzytkownik.getPlan7();
                        }*/

                        if(nrplanu!=""){
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
                    }}

                });

            }
        });

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
               if(i==1)
                {Numer.setText("Cwiczenie drugie");
                    Tresc.setText(cwiczenie2);}
                else if(i==2)
                {Numer.setText("Cwiczenie trzecie");
                    Tresc.setText(cwiczenie3);}
                else if(i==3)
                {Numer.setText("Cwiczenie czwarte");
                    Tresc.setText(cwiczenie4);}
                else if(i==4)
                {Numer.setText("Cwiczenie piąte");
                    Tresc.setText(cwiczenie5);}
                else if(i==5)
                {Numer.setText("Cwiczenie szóste");
                    Tresc.setText(cwiczenie6);}
                else if(i==6)
                {Numer.setText("Cwiczenie siódme");
                    Tresc.setText(cwiczenie7);

                    int w=Integer.parseInt(urzytkownik.getLicznik());
                    w++;

                    urzytkownik.setLicznik(String.valueOf(w));
                    SaveData();}

                i++;

            }});


    }

        private void SaveData() {
        FirebaseFirestore dba=FirebaseFirestore.getInstance();
        final DocumentReference refkontoaktualizacja = dba.collection("Konta").document(urzytkownik.getKontoname());
        refkontoaktualizacja.set(urzytkownik)
                .addOnSuccessListener(new OnSuccessListener<Void>(){
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(Plany.this,"Zapisane",Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
