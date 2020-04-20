package com.example.fitnessapp;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class User {
    private String username;
    private String email;
    String uID;
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();

    public User()
    {
        uID = mFirebaseAuth.getCurrentUser().getUid();
        final DocumentReference ref=db.collection("users").document(uID);
        ref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                username = documentSnapshot.getString("username");
                email = documentSnapshot.getString("email");
            }
        });
    }

    public User(String u,String e)
    {
        username = u;
        email = e;

    }

    public String getName(){
        return username;
    }

    private void setName(String u) { username = u; }

    public String getEmail() {
        return email;
    }

    private void setEmail(String e){
        email = e;
    }

}
