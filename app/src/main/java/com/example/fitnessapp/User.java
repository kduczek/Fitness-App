package com.example.fitnessapp;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;


public class User {
    private String username;
    private String email;

    public User()
    {
        FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
        String uID = Objects.requireNonNull(mFirebaseAuth.getCurrentUser()).getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        final DocumentReference ref= db.collection("users").document(uID);
        ref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                username = documentSnapshot.getString("username");
                email = documentSnapshot.getString("email");
            }
        });
    }

    User(String u, String e)
    {
        username = u;
        email = e;
    }

    String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
