package com.example.lazeeztadka.hoseyspicey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class user extends AppCompatActivity implements View.OnClickListener {
TextView emai,nam;
Button logout;

    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference myref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        emai=findViewById(R.id.emai);
        database=FirebaseDatabase.getInstance();
        mAuth=FirebaseAuth.getInstance();
        nam=findViewById(R.id.nam);
        FirebaseUser currentuser=mAuth.getCurrentUser();
        logout=findViewById(R.id.logout);
        if(currentuser!=null)
        {
            String email=currentuser.getEmail();


            emai.setText(email);

        }
        logout.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        mAuth.signOut();
        Intent obj=new Intent(user.this,login.class);
        startActivity(obj);
    }
}
