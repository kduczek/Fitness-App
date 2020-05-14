package com.example.fitnessapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class MainActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    FirebaseAuth mFirebaseAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference ref;
    TextView user;
    String uID;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFirebaseAuth = FirebaseAuth.getInstance();
        uID = mFirebaseAuth.getCurrentUser().getUid();
        ref = db.collection("users").document(uID);
        ref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                user = findViewById(R.id.textViewUser);
                user.setText(documentSnapshot.getString("username"));
            }
        });

        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

        //domyslny fragment

        getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, new MainFragment()).commit();

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch(menuItem.getItemId())
        {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, new MainFragment()).commit();
                break;
            case R.id.nav_plans:
                Intent intent = new Intent(this,Plany.class);
                startActivity(intent);
                break;
            case R.id.nav_creator:
                Intent intent2 = new Intent(this,Kreator.class);
                startActivity(intent2);
                break;
            case R.id.nav_profil:
                Intent intent3 = new Intent(this,Profil.class);
                startActivity(intent3);
                break;
            case R.id.nav_dane:
                Intent intent4 = new Intent(this,DodajDane.class);
                startActivity(intent4);
                break;
            case R.id.nav_wybor:
                Intent intent5 = new Intent(this,Wybor.class);
                startActivity(intent5);
                break;
            case R.id.nav_settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, new SettingsFragment()).commit();
                break;
            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(this, "Wylogowano", Toast.LENGTH_SHORT).show();
                Intent intToLogin = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intToLogin);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return  true;
    }

}
