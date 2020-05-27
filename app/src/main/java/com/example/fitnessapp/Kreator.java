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

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Kreator extends Fragment {
    private EditText editText1,editText2;
    private TextView numer;
    private String cwiczenie1,cwiczenie2,cwiczenie3,cwiczenie4,cwiczenie5,cwiczenie6,cwiczenie7;
    private int i = 0;
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private DocumentReference ref;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_kreator, container, false);

        editText1 = view.findViewById(R.id.NazwaPlanu);
        editText2 = view.findViewById(R.id.Cwiczenie);
        numer = view.findViewById(R.id.CwiczenieNumer);
        Button button = view.findViewById(R.id.Zapisz);
        Button button2 = view.findViewById(R.id.Dalej);
        numer.setText(R.string.ex1);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                SaveData();
            }
        });
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //tu wprowadzilem zmiany poniewaz po pierwszym klinieciu w DALEJ nic sie nie dzialo na ekranie
                //trzeba bylo kliknac dwa razy zeby przejsc dalej
                if(i==0)
                {
                    cwiczenie1=editText2.getText().toString();
                    editText2.setText("");
                    numer.setText(R.string.ex2);
                }

                 if(i==1)
                {
                    cwiczenie2=editText2.getText().toString();
                    editText2.setText("");
                    numer.setText(R.string.ex3);
                }
                else if(i==2)
                {
                cwiczenie3=editText2.getText().toString();
                editText2.setText("");
                    numer.setText(R.string.ex4);}
                else if(i==3)
                {
                cwiczenie4=editText2.getText().toString();
                editText2.setText("");
                    numer.setText(R.string.ex5);}
                else if(i==4)
                {
                cwiczenie5=editText2.getText().toString();
                editText2.setText("");
                    numer.setText(R.string.ex6);}
                else if(i==5)
                {
                cwiczenie6=editText2.getText().toString();
                editText2.setText("");
                    numer.setText(R.string.ex7);
                }
                else if(i==6)
                {
                cwiczenie7=editText2.getText().toString();}

                i++;

            }});
        return view;
}

    private void SaveData(){
        String name=editText1.getText().toString();

        if(!editText1.getText().toString().equals("Name")){
            String nazwa = editText1.getText().toString();
            ref=db.collection("PLANY").document(nazwa);}
       Plan plan=new Plan(name,cwiczenie1,cwiczenie2,cwiczenie3,cwiczenie4,cwiczenie5,cwiczenie6,cwiczenie7);
       ref.set(plan)
                .addOnSuccessListener(new OnSuccessListener<Void>(){
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getContext(),"Zapisane",Toast.LENGTH_SHORT).show();
                        Intent intToHome = new Intent(Kreator.this.getActivity(), MainActivity.class);
                        startActivity(intToHome);
                    }
                });
    }

}