package com.example.fitnessapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class Usun extends Fragment {
    private int i = 0;
    private String[] plany={"","","","","","",""};
    private String username;
    private CheckBox p1,p2,p3,p4,p5,p6,p7;
    private FirebaseFirestore db=FirebaseFirestore.getInstance();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_usun, container, false);

        p1=view.findViewById(R.id.P1);
        p2=view.findViewById(R.id.P2);
        p3=view.findViewById(R.id.P3);
        p4=view.findViewById(R.id.P4);
        p5=view.findViewById(R.id.P5);
        p6=view.findViewById(R.id.P6);
        p7=view.findViewById(R.id.P7);

        final Button button = view.findViewById(R.id.Usun);

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

                        p1.setText(documentSnapshotk.getString("plan1"));
                        p2.setText(documentSnapshotk.getString("plan2"));
                        p3.setText(documentSnapshotk.getString("plan3"));
                        p4.setText(documentSnapshotk.getString("plan4"));
                        p5.setText(documentSnapshotk.getString("plan5"));
                        p6.setText(documentSnapshotk.getString("plan6"));
                        p7.setText(documentSnapshotk.getString("plan7"));

                        button.setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View v){
                                Kasuj();
                            }
                        });
                    }

                });

            }
        });



        return view;
    }

    private void Kasuj(){
        if(!p1.isChecked())
        {
            if(!p1.getText().toString().equals(""))
            {
            plany[i]=p1.getText().toString();
            i++;}
        }
        if(!p2.isChecked())
        {
            if(!p2.getText().toString().equals(""))
            {plany[i]=p2.getText().toString();
            i++;}
        }
        if(!p3.isChecked())
        {
            if(!p3.getText().toString().equals(""))
            {plany[i]=p3.getText().toString();
            i++;}
        }
        if(!p4.isChecked())
        {
            if(!p4.getText().toString().equals(""))
            {plany[i]=p4.getText().toString();
            i++;}
        }
        if(!p5.isChecked())
        {
            if(!p5.getText().toString().equals(""))
            {plany[i]=p5.getText().toString();
            i++;}
        }
        if(!p6.isChecked())
        {
            if(!p6.getText().toString().equals(""))
            {plany[i]=p6.getText().toString();
            i++;}
        }
        if(!p7.isChecked())
        {
            if(!p7.getText().toString().equals(""))
            {plany[i]=p7.getText().toString();
            i++;}
        }

        FirebaseFirestore dbb=FirebaseFirestore.getInstance();
        dbb.collection("Konta").document(username).update(
                "plan1", plany[0]);
        dbb.collection("Konta").document(username).update(
                "plan2", plany[1]);
        dbb.collection("Konta").document(username).update(
                "plan3", plany[2]);
        dbb.collection("Konta").document(username).update(
                "plan4", plany[3]);
        dbb.collection("Konta").document(username).update(
                "plan5", plany[4]);
        dbb.collection("Konta").document(username).update(
                "plan6", plany[5]);
        dbb.collection("Konta").document(username).update(
                "plan7", plany[6]);

    }

}
