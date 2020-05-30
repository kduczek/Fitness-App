package com.example.fitnessapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;
import java.util.Objects;

public class KreatorCwi extends Fragment {
    private EditText Nazwa;
    private TextView numer,cwiczenie;
    private String cwiczenie1="",cwiczenie2="",cwiczenie3="",cwiczenie4="",cwiczenie5="",cwiczenie6="",cwiczenie7="";
    private int i = 0,c=1;
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private DocumentReference ref;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_kreator_cwi, container, false);

        Nazwa=view.findViewById(R.id.Nazwa);
        numer=view.findViewById(R.id.Cwiczenienr);
        numer.setText(R.string.ex1Number);
        cwiczenie=view.findViewById(R.id.Cwiczenie);
        final Button buttonzapisz = view.findViewById(R.id.Zapisz);
        final Button buttoncofnij = view.findViewById(R.id.Cofnij);
        final Button buttondalej = view.findViewById(R.id.Dalej);
        final Button buttondodaj = view.findViewById(R.id.Dodaj);

        FirebaseFirestore.getInstance()
                .collection("Cwiczenia")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            final List<DocumentSnapshot> Lista = Objects.requireNonNull(task.getResult()).getDocuments();
                            cwiczenie.setText(Lista.get(i).getString("Opis"));
                            buttoncofnij.setOnClickListener(new View.OnClickListener(){
                                @Override
                                public void onClick(View v){
                                    if(i>0)
                                    {
                                        i--;
                                    }
                                    if(i>=0){
                                        cwiczenie.setText(Lista.get(i).getString("Opis"));
                                        String temp = "Cwiczenie ";
                                        temp += (i + 1);
                                        numer.setText(temp);
                                    }
                                }
                            });

                            buttondalej.setOnClickListener(new View.OnClickListener(){
                                @Override
                                public void onClick(View v){
                                    if(i<Lista.size()-1)
                                    {
                                        i++;
                                    }
                                    if(i<Lista.size())
                                    {
                                        cwiczenie.setText(Lista.get(i).getString("Opis"));
                                        String temp = "Cwiczenie ";
                                        temp += (i + 1);
                                        numer.setText(temp);
                                    }
                                }
                            });

                            buttondodaj.setOnClickListener(new View.OnClickListener(){
                                @Override
                                public void onClick(View v){
                                    String exercise = "Cwiczenie ";

                                    if(c==1)
                                    {
                                        cwiczenie1=cwiczenie.getText().toString();
                                        c++;
                                        exercise += c;
                                        numer.setText(exercise);
                                    }
                                    else if(c==2)
                                    {
                                        cwiczenie2=cwiczenie.getText().toString();
                                        c++;
                                        exercise += c;
                                        numer.setText(exercise);
                                    }
                                    else if(c==3)
                                    {
                                        cwiczenie3=cwiczenie.getText().toString();
                                        c++;
                                        exercise += c;
                                        numer.setText(exercise);
                                    }
                                    else if(c==4)
                                    {
                                        cwiczenie4=cwiczenie.getText().toString();
                                        c++;
                                        exercise += c;
                                        numer.setText(exercise);
                                    }
                                    else if(c==5)
                                    {
                                        cwiczenie5=cwiczenie.getText().toString();
                                        c++;
                                        exercise += c;
                                        numer.setText(exercise);
                                    }
                                    else if(c==6)
                                    {
                                        cwiczenie6=cwiczenie.getText().toString();
                                        c++;
                                        exercise += c;
                                        numer.setText(exercise);
                                    }
                                    else if(c==7)
                                    {
                                        cwiczenie7=cwiczenie.getText().toString();
                                        c++;
                                    }
                                }
                            });

                            buttonzapisz.setOnClickListener(new View.OnClickListener(){
                                @Override
                                public void onClick(View v){
                                    SaveData();
                                }
                            });
                        }
                    }
                });

        return view;
    }

    private void SaveData(){
        String name=Nazwa.getText().toString();

        if(!Nazwa.getText().toString().equals("NAZWA"))
        {
            String nazwa = Nazwa.getText().toString();
            ref=db.collection("PLANY").document(nazwa);
        }

        Plan plan=new Plan(name,cwiczenie1,cwiczenie2,cwiczenie3,cwiczenie4,cwiczenie5,cwiczenie6,cwiczenie7);
        ref.set(plan)
                .addOnSuccessListener(new OnSuccessListener<Void>(){
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getContext(),"Zapisane",Toast.LENGTH_SHORT).show();
                        Intent intToHome = new Intent(KreatorCwi.this.getActivity(), MainActivity.class);
                        startActivity(intToHome);
                    }
                });
    }

}