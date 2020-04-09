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
    String[] cwiczenia;
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
        cwiczenia=new String[7];
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
                {numer.setText("Cwiczenie drugie");}
                else if(i==1)
                {numer.setText("Cwiczenie trzecie");}
                else if(i==2)
                {numer.setText("Cwiczenie czwarte");}
                else if(i==3)
                {numer.setText("Cwiczenie piate");}
                else if(i==4)
                {numer.setText("Cwiczenie szuste");}
                else if(i==5)
                {numer.setText("Cwiczenie siudme");}
                if(i<7)
                {
                 cwiczenia[i]=editText2.getText().toString();
                    if(i!=6){editText2.setText("");}
                    i++;
                }
            }});
        if(editText1.getText().toString()!="Name"){
            nazwa=editText1.getText().toString();
            ref=db.collection("PLANY").document(nazwa);}

}

    private void SaveData(){
        String name=editText1.getText().toString();

       Plan plan=new Plan(name,cwiczenia[0],cwiczenia[1],cwiczenia[2],cwiczenia[3],cwiczenia[4],cwiczenia[5],cwiczenia[6]);
       ref.set(plan)
                .addOnSuccessListener(new OnSuccessListener<Void>(){
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(Kreator.this,"Zapisane",Toast.LENGTH_SHORT).show();
                    }
                });
    }

}