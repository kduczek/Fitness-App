package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Kreator extends AppCompatActivity {
    EditText editText1,editText2;
    Button button;
    DatabaseReference ref;
    //FirebaseFirestore db=FirebaseFirestore.getInstance();
    //final DocumentReference ref=db.collection("PLANY").document("PLAN1");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kreator);

        editText1=findViewById(R.id.editText);
        editText2=findViewById(R.id.editText2);
        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                SaveData();
            }
        });
        ref= FirebaseDatabase.getInstance().getReference().child("Fittnes_centerFragment");
}

    private void SaveData(){
        String name=editText1.getText().toString();
        String desc=editText2.getText().toString();

       Fittnes_centerFragment plan=new Fittnes_centerFragment("e","eee");
       ref.push().setValue(plan);
       //ref.set(plan);
                //.addOnSuccessListener(new OnSuccessListener<void>(){
                  // @Override
                    //public void onSucces(Void aVoid){
                      // Toast.makeText(Kreator.this,"Zapisane",Toast.LENGTH_SHORT).show();
                    //}

                //});
        //db.collection("PLANY").document("PLAN1");//doda samo dokument
    }

}