package com.example.fitnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

import org.w3c.dom.Text;

import java.util.Map;

public class Profil extends AppCompatActivity {

    TextView Name;
    String nazwa;
    String mail;
    Konto konto;
    Postepy postep;
    FirebaseAuth mFirebaseAuth;
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    FirebaseFirestore dbr=FirebaseFirestore.getInstance();
    DocumentReference ref;
    DocumentReference refk;
    String uID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        mFirebaseAuth = FirebaseAuth.getInstance();

        uID = mFirebaseAuth.getCurrentUser().getUid();
        final DocumentReference ref = db.collection("users").document(uID);
        ref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Map<String, Object> user = documentSnapshot.getData();
                nazwa = (String) user.get("username");
                mail = (String) user.get("email");
                Name = findViewById(R.id.NazwaUrzytkownika);
                Name.setText(nazwa);
            }
        });

        //DocumentReference refkonto=db.collection("Konta").document(name);

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
}