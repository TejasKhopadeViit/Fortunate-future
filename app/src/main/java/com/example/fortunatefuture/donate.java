package com.example.fortunatefuture;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import android.os.Bundle;
import android.view.WindowManager;

import com.google.firebase.database.FirebaseDatabase;

public class donate extends AppCompatActivity {
    RecyclerView recyclerView;
    com.example.fortunatefuture.myfirebaseadapter myadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        recyclerView=(RecyclerView)findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Userhelperclass2> options = new FirebaseRecyclerOptions.Builder<Userhelperclass2>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("fundraise"), Userhelperclass2.class)
                .build();
        myadapter= new com.example.fortunatefuture.myfirebaseadapter(options);
        recyclerView.setAdapter(myadapter);

    }
    @Override
    protected void onStart() {
        super.onStart();
        myadapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        myadapter.stopListening();
    }

}