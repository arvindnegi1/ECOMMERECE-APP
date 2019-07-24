package com.example.lazeeztadka.hoseyspicey;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
ImageView l;
LinearLayout l2;
Animation an,an2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        l2=findViewById(R.id.sec);
        l=findViewById(R.id.logo);
        an= AnimationUtils.loadAnimation(MainActivity.this,R.anim.cus);
        l2.setAnimation(an);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                an2=new AlphaAnimation(1,(float)0.4);
                an2.setDuration(1200);
                an2.setInterpolator(new LinearInterpolator());
                an2.setRepeatCount(an2.INFINITE);
                an2.setRepeatMode(an2.REVERSE);
                l.setAnimation(an2);
            }
        },100);

        Handler h=new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent front=new Intent(MainActivity.this,login.class);
                startActivity(front);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_in);
                finish();
            }
        },3500);
    }
}
