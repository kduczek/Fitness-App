package com.example.fitnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class Wybor extends AppCompatActivity {

    public TextView Name;
    public String nazwa;
    public Plan plan;
    FirebaseAuth mFirebaseAuth;
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    CollectionReference ref;
    String uID;
    List Lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wybor);

        Name = findViewById(R.id.textView3);
        Name.setText("ddd");


        FirebaseFirestore.getInstance()
                .collection("PLANY")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<DocumentSnapshot> Lista = task.getResult().getDocuments();
                            Name.setText(Lista.get(1).getString("cwiczenie1"));
                        }
                    }
                });
    }

    private void Pobierz() {
        FirebaseAuth mFirebaseAuth;
        FirebaseFirestore db=FirebaseFirestore.getInstance();
        DocumentReference ref;
        String uID;

        mFirebaseAuth = FirebaseAuth.getInstance();
        uID = mFirebaseAuth.getCurrentUser().getUid();

        ref = db.collection("users").document(uID);

        String n="";

        ref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            String n;
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                n = documentSnapshot.getString("username");
            }
        });
    }

}
