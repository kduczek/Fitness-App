package com.example.fitnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    EditText emailId, password;
    Button btnLogin;
    TextView tvLogin;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.editText3);
        password = findViewById(R.id.editText4);
        btnLogin = findViewById(R.id.button2);
        tvLogin = findViewById(R.id.textView5);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if(mFirebaseUser != null)
                {
                    Toast.makeText(LoginActivity.this, "Zalogowano", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Proszę się zalogować", Toast.LENGTH_SHORT).show();
                }
            }
        };

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailId.getText().toString();
                String pwd = password.getText().toString();

                if(email.isEmpty())
                {
                    emailId.setError("Podaj adres E-mail");
                    emailId.requestFocus();
                }
                else if(pwd.isEmpty())
                {
                    password.setError("Podaj hasło");
                    password.requestFocus();
                }
                else if(email.isEmpty() && pwd.isEmpty())
                {
                    Toast.makeText(LoginActivity.this, "Uzupełnij oba pola!", Toast.LENGTH_SHORT).show();
                }
                else if(!(email.isEmpty() && pwd.isEmpty()))
                {
                    mFirebaseAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful())
                            {
                                Toast.makeText(LoginActivity.this, "Nie udało się zalogować, spróbuj jeszcze raz", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Intent intToHome = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intToHome);
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Wystąpił błąd!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToReg = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intToReg);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }
}
