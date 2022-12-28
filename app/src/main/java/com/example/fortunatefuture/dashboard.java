package com.example.fortunatefuture;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }





    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        finish();
    }

    public void fundraise(View view) {
        Intent i=new Intent(dashboard.this,fundraise.class);
        startActivity(i);
    }

    public void donate(View view) {
        Intent i=new Intent(dashboard.this,donate.class);
        startActivity(i);
    }
}