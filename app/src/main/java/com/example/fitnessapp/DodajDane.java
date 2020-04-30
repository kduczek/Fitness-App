package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class DodajDane extends AppCompatActivity {

    EditText editText1;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_dane);

        editText1=findViewById(R.id.Dane);
        button=findViewById(R.id.ZapiszDane);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                SaveData();
            }
        });

    }

    private void SaveData() {

        String dane=editText1.getText().toString();
        FirebaseFirestore db=FirebaseFirestore.getInstance();
        DocumentReference ref;

        ref=db.collection("Postepy").document("eee");
        Postepy postep=new Postepy("eee",dane,dane,dane,dane,dane,dane);

        ref.set(postep)
            .addOnSuccessListener(new OnSuccessListener<Void>(){
        @Override
        public void onSuccess(Void aVoid) {
            Toast.makeText(DodajDane.this,"Zapisane",Toast.LENGTH_SHORT).show();
        }
        });

    }
}
