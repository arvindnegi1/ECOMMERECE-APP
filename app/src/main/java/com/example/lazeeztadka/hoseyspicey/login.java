package com.example.lazeeztadka.hoseyspicey;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class login extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {
CheckBox cb;
EditText psw,emi;
Button sign,signup;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        cb=findViewById(R.id.showpass);
        psw=findViewById(R.id.passw);
        emi=findViewById(R.id.emi);
        signup=findViewById(R.id.signup);
        cb.setOnCheckedChangeListener(this);
        sign=findViewById(R.id.sigin);
        sign.setOnClickListener(this);
        signup.setOnClickListener(this);
        mAuth=FirebaseAuth.getInstance();

    }
    @Override
    public void onStart(){
        super.onStart();


        // Check if user is signed in (non-null) and update UI accordingly.
            FirebaseUser currentUser = mAuth.getCurrentUser();
            updateUI(currentUser);

    }

    private void updateUI(FirebaseUser currentUser) {

        if (currentUser!=null)
            {
                Intent obj=new Intent(login.this,front.class);
                startActivity(obj);
            }
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(buttonView==cb) {
            if(isChecked) {
                psw.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                psw.setSelection(psw.length());
            }
            else
            {
                psw.setTransformationMethod(PasswordTransformationMethod.getInstance());

                psw.setSelection(psw.length());
            }
        }
    }

    @Override
    public void onClick(View v) {
        if(v==signup)
        {
            Intent obj=new Intent(login.this,signup.class);
            startActivity(obj);
        }
        else if(v==sign)
        {
            String passw,email;
            passw=psw.getText().toString();
            email=emi.getText().toString();
            mAuth.signInWithEmailAndPassword(email,passw).addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    { Toast.makeText(login.this,"SUCCESSFULL",Toast.LENGTH_SHORT).show();
                        FirebaseUser currentUser = mAuth.getCurrentUser();
                        updateUI(currentUser);
                    }
                    else
                    {
                        Toast.makeText(login.this,"Failed",Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }
}
