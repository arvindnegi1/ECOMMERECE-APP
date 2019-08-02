package com.example.lazeeztadka.hoseyspicey;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;

public class user extends AppCompatActivity implements View.OnClickListener {
TextView emai,nam;
Button logout;

    FirebaseAuth mAuth;
    FirebaseDatabase database;
    FirebaseStorage storage;
    StorageReference storageReference;
    DatabaseReference myref;
    String usname;
    ImageView img;
    Uri userimg;
    StorageReference rf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        emai=findViewById(R.id.emai);
        database=FirebaseDatabase.getInstance();
        mAuth=FirebaseAuth.getInstance();
        nam=findViewById(R.id.nam);
        img=findViewById(R.id.profile_img);
        FirebaseUser currentuser=mAuth.getCurrentUser();
        logout=findViewById(R.id.logout);
        storage=FirebaseStorage.getInstance();
        storageReference=storage.getReference("Profile_img");
        if(currentuser!=null)
        {
            String email=currentuser.getEmail();


            emai.setText(email);

        }
        myref=database.getReference();
        img.setOnClickListener(this);
        final String uid=mAuth.getUid();
        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                usname=dataSnapshot.child("Reg_user").child(uid).child("name").getValue(String.class);
                nam.setText(usname);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(user.this,"network error",Toast.LENGTH_SHORT).show();
            }
        });

        emai.setText(uid);
        logout.setOnClickListener(this);
        rf=storageReference.child("Profile_img").child(mAuth.getUid());
        try {
            final File localfile=File.createTempFile("images","jpg");
            rf.getFile(localfile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap bitmap= BitmapFactory.decodeFile(localfile.getAbsolutePath());
                    img.setImageBitmap(bitmap);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(user.this,"download failed",Toast.LENGTH_SHORT).show();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {
        if(v==logout) {
            mAuth.signOut();
            Intent obj = new Intent(user.this, login.class);
            startActivity(obj);
        }
        else if(v==img)
        {   String [] perm={Manifest.permission.READ_EXTERNAL_STORAGE};
            if(ActivityCompat.checkSelfPermission(user.this,perm[0])== PackageManager.PERMISSION_GRANTED)
            {
                Intent gallery=new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(gallery,"SELECT PROFILE IMAGE"),1);
            }
            else
            {
                ActivityCompat.requestPermissions(user.this,perm,35);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==35&&grantResults[0]==PackageManager.PERMISSION_GRANTED)
        {
            Intent gallery=new Intent();
            gallery.setType("image/*");
            gallery.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(gallery,"SELECT PROFILE IMAGE"),1);
        }
        else
        {
            Toast.makeText(user.this,"enable permission from setting",Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1&&resultCode==RESULT_OK)
        {


                userimg=data.getData();

                if (userimg != null) {
                    final ProgressDialog pd = new ProgressDialog(this);
                    pd.setTitle("uploading");
                    pd.show();

                    rf.putFile(userimg).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            pd.dismiss();
                            Toast.makeText(user.this, "uploaded", Toast.LENGTH_SHORT).show();
                            try {
                                Bitmap bt= MediaStore.Images.Media.getBitmap(getContentResolver(),userimg);
                                img.setImageBitmap(bt);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(user.this, "task failed", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress=(100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                            pd.setMessage("UPLOADED "+(int)progress+"%");
                        }
                    });


                }


        }
    }
}
