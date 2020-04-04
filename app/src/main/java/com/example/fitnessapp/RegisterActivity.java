package com.example.fitnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    EditText emailId, password, username;
    Button btnReg;
    TextView tvReg;
    FirebaseAuth mFirebaseAuth;
    FirebaseFirestore fStore;
    String userID;
    private static final String TAG = "MyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFirebaseAuth = FirebaseAuth.getInstance(); //otwarcie polaczenia z systemem autoryzacji Firebase
        fStore = FirebaseFirestore.getInstance(); //otwarcie polaczenia z Firestore
        emailId = findViewById(R.id.editText6);
        password = findViewById(R.id.editText7);
        username = findViewById(R.id.editText5);
        btnReg = findViewById(R.id.button3);
        tvReg = findViewById(R.id.textView5);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = emailId.getText().toString();
                String pwd = password.getText().toString();
                final String usernm = username.getText().toString();
                if(usernm.isEmpty())
                {
                    username.setError("Podaj nazwę użytkownika");
                    username.requestFocus();
                }
                else if(email.isEmpty())
                {
                    emailId.setError("Podaj adres E-mail");
                    emailId.requestFocus();
                }
                else if(pwd.isEmpty())
                {
                    password.setError("Podaj hasło");
                    password.requestFocus();
                }
                else if(pwd.length() < 6)
                {
                    password.setError("Hasło musi mieć conajmniej 6 znaków");
                    password.requestFocus();
                }
                else if(email.isEmpty() && pwd.isEmpty())
                {
                    Toast.makeText(RegisterActivity.this, "Uzupełnij oba pola!", Toast.LENGTH_SHORT).show();
                }
                else if(!(email.isEmpty() && pwd.isEmpty()))
                {   //rejestracja uzytkownika
                    mFirebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful())
                            {
                                Toast.makeText(RegisterActivity.this, "Nie udało się stworzyć konta, spróbuj ponownie", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                userID = mFirebaseAuth.getCurrentUser().getUid();
                                DocumentReference documentReference = fStore.collection("users").document(userID);
                                Map<String, Object> user = new HashMap<>();
                                user.put("username", usernm);
                                user.put("email", email);
                                documentReference.set(user).addOnFailureListener(new OnFailureListener() {
                                    @Override //log w razie niepowodzenia
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d(TAG, "onFailure " + e.toString());
                                    }
                                });
                                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(RegisterActivity.this, "Wystąpił błąd!", Toast.LENGTH_SHORT).show();
                }
            }

        });

        tvReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToLog = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intToLog);
            }
        });
    }
}
