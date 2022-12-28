package com.example.fortunatefuture;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class fundraise extends AppCompatActivity {
    private FirebaseAuth mAuth;
    TextInputLayout Email,Password,xname,xphone,xuid,xage,xamount,xlocation,xstory;
    Spinner xspin;
    String donated="0";
    String curr="0";
    String ids[]={ "Select Fundraise Type",
            "Medical",
            "Education",
            "Memorial",
            "Other"
            };
    FirebaseAuth auth;
    FirebaseFirestore database;
    FirebaseDatabase rootNode;
    ProgressDialog dialog;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String MobilePattern = "[0-9]{10}";
    String patternage="[0-9]{2}";
    String uid = "[A-Z0-9]{14}";
    String namepattern = "[a-zA-Z ]+";
    DatabaseReference reference;
    String Status="0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fundraise);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        auth = FirebaseAuth.getInstance();
        database = FirebaseFirestore.getInstance();
        Email=findViewById(R.id.email);
        xage=findViewById(R.id.age);
        xname=findViewById(R.id.name);
        xphone=findViewById(R.id.phone);
        xuid=findViewById(R.id.uid);
        xage=findViewById(R.id.age);
        xamount=findViewById(R.id.amount);
        xspin=findViewById(R.id.spinner);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Please Wait, We're adding your new Fundraise...");
        xlocation=findViewById(R.id.location);
        xstory=findViewById(R.id.story);

    }
    public Boolean validatename(){
        String nameval = xname.getEditText().getText().toString();
        if(nameval.isEmpty()){
            xname.setError("Field cannot be empty");
            return false;
        } else if(!nameval.matches(namepattern)){
            xname.setError("Invalid Format");
            return false;
        }
        else {
            xname.setError(null);
            return true;
        }

    }
    public Boolean validateemail(){
        String nameval = Email.getEditText().getText().toString();
        if(nameval.isEmpty()){
            Email.setError("Field cannot be empty");
            return false;
        } else if(!nameval.matches(emailPattern)){
            Email.setError("Invalid Format");
            return false;
        }
        else {
            Email.setError(null);
            return true;
        }

    }
    public Boolean validateage(){
        String nameval = xage.getEditText().getText().toString();
        if(nameval.isEmpty()){
            xage.setError("Field cannot be empty");
            return false;
        } else if(!nameval.matches(patternage)){
            xage.setError("Invalid Format");
            return false;
        }
        else {
            xage.setError(null);
            return true;
        }

    }
    public Boolean validatephone(){
        String nameval = xphone.getEditText().getText().toString();
        if(nameval.isEmpty()){
            xphone.setError("Field cannot be empty");
            return false;
        } else if(!nameval.matches(MobilePattern)){
            xphone.setError("Invalid Format");
            return false;
        }
        else {
            xphone.setError(null);
            return true;
        }

    }
    private boolean validatetype() {
        String nameval =  ids[xspin.getSelectedItemPosition()];
        if(nameval.equals("Select Fundraise Type")){
            ((TextView)xspin.getSelectedView()).setError("Select Valid");
            return false;
        }
        else{
            return true;
        }
    }
    public Boolean validateroom(){
        String nameval = xuid.getEditText().getText().toString();
        if(nameval.isEmpty()){
            xuid.setError("Field cannot be empty");
            return false;
        } else if(!nameval.matches(uid)){
            xuid.setError("Invalid Format");
            return false;
        }
        else {
            xuid.setError(null);
            return true;
        }

    }
    //
    //
    //
    public void raisefund(View v) {
        if(!validatename() | !validateemail()   | !validatephone() | !validateroom() | !validateage() ){ return; }

        dialog.show();

        String email=Email.getEditText().getText().toString().trim();
        System.out.println(email);
        String name=xname.getEditText().getText().toString().trim();
        String phone=xphone.getEditText().getText().toString().trim();
        String uid=xuid.getEditText().getText().toString().trim();
        String age=xage.getEditText().getText().toString().trim();
        String type=ids[xspin.getSelectedItemPosition()];
        String amount=xamount.getEditText().getText().toString().trim();
        String story=xstory.getEditText().getText().toString().trim();
        String location=xlocation.getEditText().getText().toString().trim();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("fundraise").child(name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                } else {
                    rootNode = FirebaseDatabase.getInstance();
                    reference = rootNode.getReference("fundraise");
                    Userhelperclass2 userhelperclass = new Userhelperclass2(name, email, phone, uid,age,type,amount,Status,donated,story,location);
                    reference.child(name).setValue(userhelperclass);
                    done();

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    //
    //
    //
    private void done() {
        Intent i =new Intent(fundraise.this,dashboard.class);
        startActivity(i);
        finish();
    }


}