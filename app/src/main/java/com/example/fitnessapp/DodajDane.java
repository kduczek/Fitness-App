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
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class DodajDane extends Fragment {

    private EditText editTextWaga1, editTextWaga2, editTextBiceps1, editTextBiceps2, editTextPas1, editTextPas2;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference ref;
    private FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    private String name;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_dodaj_dane, container, false);

        String uID = mFirebaseAuth.getCurrentUser().getUid();
        ref = db.collection("users").document(uID);
        ref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                name = documentSnapshot.getString("username");
            }
        });

        Button button = view.findViewById(R.id.ZapiszDane);
        editTextBiceps1 = view.findViewById(R.id.editTextBiceps1);
        editTextBiceps2 = view.findViewById(R.id.editTextBiceps2);
        editTextPas1 = view.findViewById(R.id.editTextPas1);
        editTextPas2 = view.findViewById(R.id.editTextPas2);
        editTextWaga1 = view.findViewById(R.id.editTextWaga1);
        editTextWaga2 = view.findViewById(R.id.editTextWaga2);




        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                SaveData();
            }
        });

        return view;
    }

    private void SaveData() {

        String waga1 = editTextWaga1.getText().toString();
        String waga2 = editTextWaga2.getText().toString();
        String biceps1 = editTextBiceps1.getText().toString();
        String biceps2 = editTextBiceps2.getText().toString();
        String pas1 = editTextPas1.getText().toString();
        String pas2 = editTextPas2.getText().toString();




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
                                Toast.makeText(getContext(),"Zapisano",Toast.LENGTH_SHORT).show();
                                editTextBiceps1.setText("");
                                editTextBiceps2.setText("");
                                editTextWaga1.setText("");
                                editTextWaga2.setText("");
                                editTextPas1.setText("");
                                editTextPas2.setText("");
                                Intent intToHome = new Intent(DodajDane.this.getActivity(), MainActivity.class);
                                startActivity(intToHome);
                            }
                        });
            }







    }
}
