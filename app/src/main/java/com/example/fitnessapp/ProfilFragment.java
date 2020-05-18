package com.example.fitnessapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class ProfilFragment extends Fragment {

    private TextView Name;
    private String nazwa;

    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private DocumentReference ref;
    private String uID;
    private ProgressBar pgBic, pgPas, pgWaga;
    private TextView poczBic, poczPas, poczWaga, docBic, docPas, docWaga;
    public Button button;
    private EditText editWaga, editPas, editBiceps;
    private ImageView profileImage;
    private StorageReference storageReference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_profil, container, false);

        Name = view.findViewById(R.id.NazwaUrzytkownika);
        pgBic = view.findViewById(R.id.pgBic);
        pgPas = view.findViewById(R.id.pgPas);
        pgWaga = view.findViewById(R.id.pgWaga);
        poczBic = view.findViewById(R.id.bicPocz);
        poczPas = view.findViewById(R.id.pasPocz);
        poczWaga = view.findViewById(R.id.wagaPocz);
        docBic = view.findViewById(R.id.bicDoc);
        docPas = view.findViewById(R.id.pasDoc);
        docWaga = view.findViewById(R.id.wagaDoc);
        button = view.findViewById(R.id.buttonUpdate);
        profileImage = view.findViewById(R.id.imageViewProfil);

        FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
        uID = mFirebaseAuth.getCurrentUser().getUid();

        storageReference = FirebaseStorage.getInstance().getReference();
        StorageReference profileRef = storageReference.child("users/" + uID + "/profile.jpg"); //lokalna referencja
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(profileImage);
            }
        });

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

                        /*pgBic.setMax(Integer.parseInt(documentSnapshot.getString("docelowyObwodBicepsa")));
                        pgWaga.setMax(Integer.parseInt(documentSnapshot.getString("docelowaWaga")));
                        pgPas.setMax(Integer.parseInt(documentSnapshot.getString("docelowyObwodPasa")));

                        pgBic.setMin(Integer.parseInt(documentSnapshot.getString("poczatkowyObwodBicepsa")));
                        pgWaga.setMin(Integer.parseInt(documentSnapshot.getString("poczatkowaWaga")));
                        pgPas.setMin(Integer.parseInt(documentSnapshot.getString("poczatkowyObwodPasa")));*/
                    }
                });
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editWaga = view.findViewById(R.id.editTextWagaProfil);
                editPas = view.findViewById(R.id.editTextPasProfil);
                editBiceps = view.findViewById(R.id.editTextBicepsProfil);

                pgWaga.setProgress(Integer.parseInt(editWaga.getText().toString()));
                pgPas.setProgress(Integer.parseInt(editPas.getText().toString()));
                pgBic.setProgress(Integer.parseInt(editBiceps.getText().toString()));
            }
        });


        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openGaleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGaleryIntent, 2137);
            }
        });

        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 2137)
        {
            if(resultCode == Activity.RESULT_OK)
            {
                Uri imageUri = data.getData();

                uploadImageToFirebase(imageUri);
            }
        }
    }

    private void uploadImageToFirebase(Uri imageUri) {
        final StorageReference fileRef = storageReference.child("users/" + uID + "/profile.jpg");
        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(profileImage);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Nie udało się ustawić zdjęcia", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
