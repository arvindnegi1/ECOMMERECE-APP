package com.example.lazeeztadka.hoseyspicey;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.User;

public class signup extends AppCompatActivity implements View.OnClickListener {
EditText namee,emaiw,passo;
Button reg;
FirebaseAuth mAuth;
FirebaseDatabase database;
DatabaseReference myref;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    namee=findViewById(R.id.namee);
    emaiw=findViewById(R.id.email);
    passo=findViewById(R.id.password);
    reg=findViewById(R.id.register);
    reg.setOnClickListener(this);
    mAuth=FirebaseAuth.getInstance();
    database=FirebaseDatabase.getInstance();

    }

    @Override
    public void onClick(View v) {
        final String name;
        final String email;
        final String password;

        myref=database.getReference("Reg_user");

        name=namee.getText().toString().trim();
        email=emaiw.getText().toString().trim();



        password=passo.getText().toString().trim();
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(signup.this,"USER CREATED",Toast.LENGTH_SHORT).show();
                    User us=new User(name,email);
                    myref.child(mAuth.getUid()).setValue(us);
                    Intent obj=new Intent(signup.this,login.class);
                    startActivity(obj);
                }
                else
                {
                    Toast.makeText(signup.this,"failed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
