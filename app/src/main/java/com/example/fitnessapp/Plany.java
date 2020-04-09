package com.example.fitnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
    Button button;
    Plan plan;
    FirebaseFirestore db=FirebaseFirestore.getInstance();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plany);
        Nazwa=findViewById(R.id.NazwaPlanu2);
        Tresc=findViewById(R.id.CwiczenieOpis);
        Numer=findViewById(R.id.CwiczenieNumer2);
        button=findViewById(R.id.Nastepne);

        DocumentReference ref=db.collection("PLANY").document("Name");
        //ref.get().
       // plan=documentSnapshot.toObject(Plan.class);

        ref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
plan=documentSnapshot.toObject(Plan.class);
                Nazwa.setText(plan.getName());
            }
        });


    }
}
