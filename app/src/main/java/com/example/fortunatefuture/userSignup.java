package com.example.fortunatefuture;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class userSignup extends AppCompatActivity {
    private FirebaseAuth mAuth;
    TextInputLayout Email,Password,xname,xphone,xuid,xage;


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_signup);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        auth = FirebaseAuth.getInstance();
        database = FirebaseFirestore.getInstance();
        Email=findViewById(R.id.email);
        Password=findViewById(R.id.password);
        xname=findViewById(R.id.name);
        xphone=findViewById(R.id.phone);
        xuid=findViewById(R.id.uid);
        xage=findViewById(R.id.age);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Please Wait, We're creating your new account...");
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
    public Boolean validatepassword() {
        String nameval = Password.getEditText().getText().toString();
        if (nameval.isEmpty()) {
            Password.setError("Field cannot be empty");
            return false;
        } else if (nameval.length() < 6) {
            Password.setError("should be greater than 6 digits");
            return false;
        } else {
            Password.setError(null);
            return true;
        }
    }




        public void go(View v) {
            if(!validatename() | !validateemail() | !validatepassword() | !validatephone() | !validateroom() | !validateage()){ return; }

            dialog.show();

         String email=Email.getEditText().getText().toString().trim();
        System.out.println(email);
        String password=Password.getEditText().getText().toString();
        String name=xname.getEditText().getText().toString().trim();
        String phone=xphone.getEditText().getText().toString().trim();
        String uid=xuid.getEditText().getText().toString().trim();
        String age=xage.getEditText().getText().toString().trim();

            DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
            ref.child("register").child(name).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {

                    } else {
                        rootNode = FirebaseDatabase.getInstance();
                        reference = rootNode.getReference("register");
                        Userhelperclass userhelperclass = new Userhelperclass(name, email, phone, password, uid,age);
                        reference.child(name).setValue(userhelperclass);

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                               // String uid = task.getResult().getUser().getUid();
                                dialog.dismiss();

                              FirebaseAuth.getInstance().signOut();
                                finish();
                } else {
                    dialog.dismiss();
                    Toast.makeText(userSignup.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });




    }

    public void back(View view) {
        Intent i=new Intent(userSignup.this,userLogin.class);
        startActivity(i);
        finish();
    }
}