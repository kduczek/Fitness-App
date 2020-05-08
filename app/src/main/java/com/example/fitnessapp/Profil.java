package com.example.fitnessapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.Map;

public class Profil extends AppCompatActivity {

    public TextView Name;
    public String nazwa;
//    String mail;
//    Konto konto;
//    Postepy postep;
    FirebaseAuth mFirebaseAuth;
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    DocumentReference ref;
    String uID;
    public Postepy postepy;
    public ProgressBar pgBic, pgPas, pgWaga;
    public TextView poczBic, poczPas, poczWaga, docBic, docPas, docWaga;
    public Button button;
    public EditText editWaga, editPas, editBiceps;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        Name = findViewById(R.id.NazwaUrzytkownika);
        pgBic = findViewById(R.id.pgBic);
        pgPas = findViewById(R.id.pgPas);
        pgWaga = findViewById(R.id.pgWaga);
        poczBic = findViewById(R.id.bicPocz);
        poczPas = findViewById(R.id.pasPocz);
        poczWaga = findViewById(R.id.wagaPocz);
        docBic = findViewById(R.id.bicDoc);
        docPas = findViewById(R.id.pasDoc);
        docWaga = findViewById(R.id.wagaDoc);
        button = findViewById(R.id.buttonUpdate);

        mFirebaseAuth = FirebaseAuth.getInstance();
        uID = mFirebaseAuth.getCurrentUser().getUid();
        ref = db.collection("users").document(uID);
        ref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                nazwa = documentSnapshot.getString("username");
                Name.setText(nazwa);
                ref = db.collection("Postepy").document(nazwa);
                ref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        poczBic.setText(documentSnapshot.getString("poczatkowyObwodBicepsa"));
                        poczPas.setText(documentSnapshot.getString("poczatkowyObwodPasa"));
                        poczWaga.setText(documentSnapshot.getString("poczatkowaWaga"));
                        docBic.setText(documentSnapshot.getString("docelowyObwodBicepsa"));
                        docPas.setText(documentSnapshot.getString("docelowyObwodPasa"));
                        docWaga.setText(documentSnapshot.getString("docelowaWaga"));

                        pgBic.setMax(Integer.parseInt(documentSnapshot.getString("docelowyObwodBicepsa")));
                        pgWaga.setMax(Integer.parseInt(documentSnapshot.getString("docelowaWaga")));
                        pgPas.setMax(Integer.parseInt(documentSnapshot.getString("docelowyObwodPasa")));

                        pgBic.setMin(Integer.parseInt(documentSnapshot.getString("poczatkowyObwodBicepsa")));
                        pgWaga.setMin(Integer.parseInt(documentSnapshot.getString("poczatkowaWaga")));
                        pgPas.setMin(Integer.parseInt(documentSnapshot.getString("poczatkowyObwodPasa")));


                    }
                });
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Update();
            }
        });

        /*To narazie nie jest uzywane ale bedzie chyba
        FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
        DocumentReference docIdRef = rootRef.collection("Konta").document(nazwa);
        docIdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Name.setText("Jest");
                    } else {
                        Name.setText("NieJest");
                    }
                }
            }
        });*/

    }

    private void Update() {
        editWaga = findViewById(R.id.editTextWagaProfil);
        editPas = findViewById(R.id.editTextPasProfil);
        editBiceps = findViewById(R.id.editTextBicepsProfil);

        pgWaga.setProgress(Integer.parseInt(editWaga.getText().toString()));
        pgPas.setProgress(Integer.parseInt(editPas.getText().toString()));
        pgBic.setProgress(Integer.parseInt(editBiceps.getText().toString()));
    }
}