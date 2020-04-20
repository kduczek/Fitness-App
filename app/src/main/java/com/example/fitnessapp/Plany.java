package com.example.fitnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Plany extends AppCompatActivity {

    TextView Nazwa,Tresc,Numer;
    int i=0;
    Button button;
    String cwiczenie1,cwiczenie2,cwiczenie3,cwiczenie4,cwiczenie5,cwiczenie6,cwiczenie7;
    Plan plan;
    FirebaseFirestore db=FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plany);
        Nazwa=findViewById(R.id.NazwaPlanu2);
        Tresc=findViewById(R.id.CwiczenieOpis);
        Numer=findViewById(R.id.CwiczenieNumer2);
        button=findViewById(R.id.Nastepne);

        final DocumentReference ref=db.collection("PLANY").document("Plan1");
        //ref.get().
        //plan=documentSnapshot.toObject(Plan.class);

        ref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
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
                    Tresc.setText(cwiczenie7);}

                i++;

            }});


    }
}
