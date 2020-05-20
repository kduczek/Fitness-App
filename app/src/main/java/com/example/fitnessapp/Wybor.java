package com.example.fitnessapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;
import java.util.Objects;

public class Wybor extends Fragment {

    private TextView Name;
    public String nazwa;
    public Plan plan;
    FirebaseAuth mFirebaseAuth;
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    CollectionReference ref;
    String uID;
    List Lista;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_wybor, container, false);

        Name = view.findViewById(R.id.textView3);
        Name.setText("ddd");


        FirebaseFirestore.getInstance()
                .collection("PLANY")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<DocumentSnapshot> Lista = Objects.requireNonNull(task.getResult()).getDocuments();
                            Name.setText(Lista.get(1).getString("cwiczenie1"));
                        }
                    }
                });
        return view;
    }

    //nie jest narazie nigdzie uzywana ta metoda wiec ja zakomentowalem
//    private void Pobierz() {
//        FirebaseAuth mFirebaseAuth;
//        FirebaseFirestore db=FirebaseFirestore.getInstance();
//        DocumentReference ref;
//        String uID;
//
//        mFirebaseAuth = FirebaseAuth.getInstance();
//        uID = mFirebaseAuth.getCurrentUser().getUid();
//
//        ref = db.collection("users").document(uID);
//
//        String n="";
//
//        ref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//            String n;
//            @Override
//            public void onSuccess(DocumentSnapshot documentSnapshot) {
//                n = documentSnapshot.getString("username");
//            }
//        });
//    }

}
