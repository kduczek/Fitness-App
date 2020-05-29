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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;
import java.util.Objects;

public class Wybor extends Fragment {

    private TextView Name,cw1,cw2,nr;
    private String nazwap;
    private Button button1,button2,button3;
    private int i=0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_wybor, container, false);

        Name = view.findViewById(R.id.textViewNazwaPlanu);
        cw1=view.findViewById(R.id.cw1);
        cw2=view.findViewById(R.id.cw2);
        nr=view.findViewById(R.id.numer);
        button1=view.findViewById(R.id.cofnij);
        button2=view.findViewById(R.id.dalej);
        button3=view.findViewById(R.id.Zapisz);

        FirebaseFirestore.getInstance()
                .collection("PLANY")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            final List<DocumentSnapshot> Lista = Objects.requireNonNull(task.getResult()).getDocuments();
                            Name.setText(Lista.get(i).getString("name"));
                            cw1.setText(Lista.get(i).getString("cwiczenie1"));
                            cw2.setText(Lista.get(i).getString("cwiczenie2"));
                            button1.setOnClickListener(new View.OnClickListener(){
                                @Override
                                public void onClick(View v){
                                    if(i>0){
                                    i--;}
                                    if(i>=0){
                                    Name.setText(Lista.get(i).getString("name"));
                                    cw1.setText(Lista.get(i).getString("cwiczenie1"));
                                    cw2.setText(Lista.get(i).getString("cwiczenie2"));}
                                }
                            });

                            button2.setOnClickListener(new View.OnClickListener(){
                                @Override
                                public void onClick(View v){
                                    if(i<Lista.size()-1){
                                        i++;}
                                    if(i<Lista.size()){
                                        Name.setText(Lista.get(i).getString("name"));
                                        cw1.setText(Lista.get(i).getString("cwiczenie1"));
                                        cw2.setText(Lista.get(i).getString("cwiczenie2"));}
                                }
                            });

                            button3.setOnClickListener(new View.OnClickListener(){
                                @Override
                                public void onClick(View v){
                                    nazwap=Lista.get(i).getString("name");
                                    SaveData();
                                }
                            });
                        }
                    }
                });

        return view;
    }

    private void SaveData() {
        if(!nr.getText().toString().equals("1")&&!nr.getText().toString().equals("2")&&!nr.getText().toString().equals("3")
                &&!nr.getText().toString().equals("4")&&!nr.getText().toString().equals("5")&&!nr.getText().toString().equals("6")
                &&!nr.getText().toString().equals("7"))
        {
            Toast.makeText(getContext(),"ZLY NUMER", Toast.LENGTH_SHORT).show();
        }
        else
        {
        FirebaseAuth mFirebaseAuth;
        final FirebaseFirestore db=FirebaseFirestore.getInstance();
        final String uID;

        mFirebaseAuth = FirebaseAuth.getInstance();
        uID = Objects.requireNonNull(mFirebaseAuth.getCurrentUser()).getUid();

        DocumentReference ref = db.collection("users").document(uID);
        ref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                final String username = documentSnapshot.getString("username");

                DocumentReference refkonto=db.collection("Konta").document(Objects.requireNonNull(username));

                refkonto.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshotk) {
                        FirebaseFirestore dbb=FirebaseFirestore.getInstance();
                        switch(nr.getText().toString()) {
                            case "1":
                                dbb.collection("Konta").document(username).update(
                                        "plan1",nazwap);
                                break;
                            case "2":
                                dbb.collection("Konta").document(username).update(
                                        "plan2",nazwap);
                                break;
                            case "3":
                                dbb.collection("Konta").document(username).update(
                                        "plan3",nazwap);
                                break;
                            case "4":
                                dbb.collection("Konta").document(username).update(
                                        "plan4",nazwap);
                                break;
                            case "5":
                                dbb.collection("Konta").document(username).update(
                                        "plan5",nazwap);
                                break;
                            case "6":
                                dbb.collection("Konta").document(username).update(
                                        "plan6",nazwap);
                                break;
                            case "7":
                                dbb.collection("Konta").document(username).update(
                                        "plan7",nazwap);
                                break;

                        }
                        Toast.makeText(getContext(),"ZAPISANO", Toast.LENGTH_SHORT).show();

                    }

                });

            }
        });

    }
    }

}
