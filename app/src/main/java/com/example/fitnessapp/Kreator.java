package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Kreator extends AppCompatActivity {
    EditText editText1,editText2;
    TextView numer;
    String cwiczenie1,cwiczenie2,cwiczenie3,cwiczenie4,cwiczenie5,cwiczenie6,cwiczenie7;
    String nazwa;
    int i = 0;
    Button button;
    Button button2;
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    DocumentReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kreator);

        editText1=findViewById(R.id.NazwaPlanu);
        editText2=findViewById(R.id.Cwiczenie);
        numer=findViewById(R.id.CwiczenieNumer);
        button=findViewById(R.id.Zapisz);
        button2=findViewById(R.id.Dalej);

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

}

    private void SaveData(){
        String name=editText1.getText().toString();

        if(editText1.getText().toString()!="Name"){
            nazwa=editText1.getText().toString();
            ref=db.collection("PLANY").document(nazwa);}

       Plan plan=new Plan(name,cwiczenie1,cwiczenie2,cwiczenie3,cwiczenie4,cwiczenie5,cwiczenie6,cwiczenie7);
       ref.set(plan)
                .addOnSuccessListener(new OnSuccessListener<Void>(){
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(Kreator.this,"Zapisane",Toast.LENGTH_SHORT).show();
                    }
                });
    }

}