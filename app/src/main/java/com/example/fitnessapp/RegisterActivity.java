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

public class RegisterActivity extends AppCompatActivity {

    EditText emailId, password, username;
    Button btnReg;
    TextView tvReg;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFirebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.editText6);
        password = findViewById(R.id.editText7);
        username = findViewById(R.id.editText5);
        btnReg = findViewById(R.id.button3);
        tvReg = findViewById(R.id.textView5);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailId.getText().toString();
                String pwd = password.getText().toString();
                String user = username.getText().toString();
                if(user.isEmpty())
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
                else if(email.isEmpty() && pwd.isEmpty())
                {
                    Toast.makeText(RegisterActivity.this, "Uzupełnij oba pola!", Toast.LENGTH_SHORT).show();
                }
                else if(!(email.isEmpty() && pwd.isEmpty()))
                {
                    mFirebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful())
                            {
                                Toast.makeText(RegisterActivity.this, "Nie udało się stworzyć konta, spróbuj ponownie", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
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
