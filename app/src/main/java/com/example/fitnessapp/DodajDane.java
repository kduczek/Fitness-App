package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class DodajDane extends AppCompatActivity {

    EditText editTextWaga1, editTextWaga2, editTextBiceps1, editTextBiceps2, editTextPas1, editTextPas2;
    Button button;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference ref;
    FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    String name, waga1, waga2, biceps1, biceps2, pas1, pas2, uID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_dane);

        uID = mFirebaseAuth.getCurrentUser().getUid();
        ref = db.collection("users").document(uID);
        ref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                name = documentSnapshot.getString("username");
            }
        });

        button = findViewById(R.id.ZapiszDane);
        editTextBiceps1 = findViewById(R.id.editTextBiceps1);
        editTextBiceps2 = findViewById(R.id.editTextBiceps2);
        editTextPas1 = findViewById(R.id.editTextPas1);
        editTextPas2 = findViewById(R.id.editTextPas2);
        editTextWaga1 = findViewById(R.id.editTextWaga1);
        editTextWaga2 = findViewById(R.id.editTextWaga2);




        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                SaveData();
            }
        });

    }

    private void SaveData() {

        waga1 = editTextWaga1.getText().toString();
        waga2 = editTextWaga2.getText().toString();
        biceps1 = editTextBiceps1.getText().toString();
        biceps2 = editTextBiceps2.getText().toString();
        pas1 = editTextPas1.getText().toString();
        pas2 = editTextPas2.getText().toString();




            if(waga1.isEmpty())
            {
                editTextWaga1.setError("Podaj wagę");
                editTextWaga1.requestFocus();
            }
            else if(waga2.isEmpty())
            {
                editTextWaga2.setError("Podaj wagę");
                editTextWaga2.requestFocus();
            }
            else if(biceps1.isEmpty())
            {
                editTextBiceps1.setError("Podaj obwód");
                editTextBiceps1.requestFocus();
            }
            else if(biceps2.isEmpty())
            {
                editTextBiceps2.setError("Podaj obwód");
                editTextBiceps2.requestFocus();
            }
            else if(pas1.isEmpty())
            {
                editTextPas1.setError("Podaj obwód");
                editTextPas1.requestFocus();
            }
            else if(pas2.isEmpty())
            {
                editTextPas2.setError("Podaj obwód");
                editTextPas2.requestFocus();
            }
            else if(!(waga1.isEmpty()) && !(waga2.isEmpty()) && !(biceps1.isEmpty()) && !(biceps2.isEmpty()) && !(pas1.isEmpty()) && !(pas2.isEmpty()))
            {
                ref=db.collection("Postepy").document(name);
                Postepy postep = new Postepy(name, pas1, biceps1, waga1, pas2, biceps2, waga2);

                ref.set(postep)
                        .addOnSuccessListener(new OnSuccessListener<Void>(){
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(DodajDane.this,"Zapisano",Toast.LENGTH_SHORT).show();
                                editTextBiceps1.setText("");
                                editTextBiceps2.setText("");
                                editTextWaga1.setText("");
                                editTextWaga2.setText("");
                                editTextPas1.setText("");
                                editTextPas2.setText("");
                                Intent intToHome = new Intent(DodajDane.this, MainActivity.class);
                                startActivity(intToHome);
                            }
                        });
            }







    }
}
