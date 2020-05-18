package com.example.fitnessapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                SaveData();
            }
        });
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(i==0)
                {numer.setText("Cwiczenie drugie");
                cwiczenie1=editText2.getText().toString();
                editText2.setText(""); }
                 if(i==1)
                {numer.setText("Cwiczenie trzecie");
                cwiczenie2=editText2.getText().toString();
                editText2.setText("");}
                else if(i==2)
                {numer.setText("Cwiczenie czwarte");
                cwiczenie3=editText2.getText().toString();
                editText2.setText("");}
                else if(i==3)
                {numer.setText("Cwiczenie piate");
                cwiczenie4=editText2.getText().toString();
                editText2.setText("");}
                else if(i==4)
                {numer.setText("Cwiczenie szuste");
                cwiczenie5=editText2.getText().toString();
                editText2.setText("");}
                else if(i==5)
                {numer.setText("Cwiczenie siudme");
                cwiczenie6=editText2.getText().toString();
                editText2.setText("");
                }
                else if(i==6)
                {numer.setText("Cwiczenie siudme");
                cwiczenie7=editText2.getText().toString();}

                i++;

            }});
        return view;
}

    private void SaveData(){
        String name=editText1.getText().toString();

        if(editText1.getText().toString()!="Name"){
            String nazwa = editText1.getText().toString();
            ref=db.collection("PLANY").document(nazwa);}
       Plan plan=new Plan(name,cwiczenie1,cwiczenie2,cwiczenie3,cwiczenie4,cwiczenie5,cwiczenie6,cwiczenie7);
       ref.set(plan)
                .addOnSuccessListener(new OnSuccessListener<Void>(){
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getContext(),"Zapisane",Toast.LENGTH_SHORT).show();
                    }
                });
    }

}