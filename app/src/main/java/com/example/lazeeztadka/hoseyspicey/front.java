package com.example.lazeeztadka.hoseyspicey;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;



public class front extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
ViewPager vp;
int[] tasty={R.drawable.tas1,R.drawable.tas2,R.drawable.tas3,R.drawable.tas4};
String[] tastytext={"TASTY1","TASTY2","TASTY3","TASTY4"};
int[] topimg={R.drawable.allcat,R.drawable.offer,R.drawable.norind,R.drawable.sout,R.drawable.veg,R.drawable.nonveg,R.drawable.magicmasala};
String []bottext={"All categories","OFFER","NORTH INDIAN","SOUTH INDIAN","VEGETARIAN","NON VEGETERIAN","MASALA MAGIC"};
Integer []img={R.drawable.ar,R.drawable.br,R.drawable.cr,R.drawable.dr};
ArrayList <Integer>al=new ArrayList<Integer>();
int[] everest={R.drawable.ev1,R.drawable.ev2,R.drawable.ev3,R.drawable.ev4};
String[] everesttext={"EVEREST 1","EVEREST 2","EVEREST 3","EVEREST 4"};
EditText sea;
int currentpage=0;
RecyclerView r,r1,r2;
RecyclerView.LayoutManager layoutManager,layoutManager2,layoutManager3;
RecyclerView.Adapter rad,rad2,rad3;
DrawerLayout dl;
ActionBarDrawerToggle abdt;
ListView lve;
ImageView accou;
FirebaseAuth mAuth;
NavigationView nvw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front);
        android.support.v7.widget.Toolbar tba=this.findViewById(R.id.toolba);
        setSupportActionBar(tba);
        getSupportActionBar().setTitle(null);
        accou=findViewById(R.id.accou);
        vp=findViewById(R.id.pager);
        r1=findViewById(R.id.recsub);
        for(int i=0;i<img.length;i++)
        {
            al.add(img[i]);
        }
        accou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent obj=new Intent(front.this,user.class);
                startActivity(obj);
            }
        });
            View v=findViewById(R.id.toolba);
            EditText ed=v.findViewById(R.id.search);
          dl=findViewById(R.id.dl);
          lve=findViewById(R.id.lisd);
        ArrayAdapter ad=new ArrayAdapter(front.this,android.R.layout.simple_list_item_1,bottext);
            lve.setAdapter(ad);
            abdt=new ActionBarDrawerToggle(front.this,dl,R.string.app_name,R.string.app_name);
            nvw=findViewById(R.id.navdra);
            r=findViewById(R.id.recvw);

            r2=findViewById(R.id.recsub2);
            nvw.setNavigationItemSelectedListener(this);
            layoutManager2=new LinearLayoutManager(front.this,LinearLayoutManager.HORIZONTAL,false);
        layoutManager3=new LinearLayoutManager(front.this,LinearLayoutManager.HORIZONTAL,false);
            layoutManager =new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
            r.setLayoutManager(layoutManager);
            r1.setLayoutManager(layoutManager2);
            r2.setLayoutManager(layoutManager3);
            rad=new recAdapter();
            rad2=new r2Adapter();
            rad3=new r3Adapter();
            r1.setAdapter(rad2);
            r2.setAdapter(rad3);
            r.setAdapter(rad);
            sliderviewer();


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent obj=new Intent(front.this,gridall.class);
        startActivity(obj);
        dl.closeDrawer(GravityCompat.START);
        return true;
    }


    class r3Adapter extends  RecyclerView.Adapter<r3Adapter.MyViewHolder3>implements View.OnClickListener {

    @NonNull
    @Override
    public MyViewHolder3 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View d=LayoutInflater.from(parent.getContext()).inflate(R.layout.cusrec2,parent,false);
        MyViewHolder3 f=new MyViewHolder3(d);
        return f;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder3 holder, int position) {
            holder.ter.setImageResource(tasty[position]);
            holder.texr.setText(tastytext[position]);
            holder.ter.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return tasty.length;
    }

    @Override
    public void onClick(View v) {
        Intent obj=new Intent(front.this,gridall.class);
        startActivity(obj);
    }

    class MyViewHolder3 extends RecyclerView.ViewHolder {
        ImageView ter;
        TextView texr;
        public MyViewHolder3(@NonNull View itemView) {
                super(itemView);
                ter=itemView.findViewById(R.id.imgrec2);
                texr=itemView.findViewById(R.id.tir2);
            }
        }
}
    class r2Adapter extends RecyclerView.Adapter<r2Adapter.MyViewHolder2> implements  View.OnClickListener{

        @NonNull
        @Override
        public MyViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.cusrec2,parent,false);
            MyViewHolder2 vr=new MyViewHolder2(v);
            return vr;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder2 holder, int position) {
            holder.igr.setImageResource(everest[position]);
            holder.it.setText(everesttext[position]);
            holder.igr.setOnClickListener(this);
        }

        @Override
        public int getItemCount() {
            return everest.length;
        }

        public class MyViewHolder2 extends RecyclerView.ViewHolder{
                ImageView igr;
                TextView it;
            public MyViewHolder2(@NonNull View itemView) {
                super(itemView);
                igr=itemView.findViewById(R.id.imgrec2);
                it=itemView.findViewById(R.id.tir2);
            }
        }
        @Override
        public void onClick(View v) {
            Intent obj=new Intent(front.this,gridall.class);
            startActivity(obj);
        }


    }

    class recAdapter extends RecyclerView.Adapter<recAdapter.MyViewHolder>{

     @NonNull
     @Override
     public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.cusrec,parent,false);
         MyViewHolder vh=new MyViewHolder(v);
         return vh;
     }

     @Override
     public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
            holder.ig.setImageResource(topimg[position]);
            holder.tw.setText(bottext[position]);
            holder.ig.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                if(bottext[position]=="All categories")
                {
                    dl.addDrawerListener(abdt);
                   dl.openDrawer(Gravity.LEFT);

                    Toast.makeText(front.this,"selected "+bottext[position],Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent obj=new Intent(front.this,gridall.class);
                    startActivity(obj);
                }
                }
            });

     }

     @Override
     public int getItemCount() {
         return topimg.length;
     }

     public class MyViewHolder extends RecyclerView.ViewHolder{
            ImageView ig;
            TextView tw;
            MyViewHolder(View v)
            {
                super(v);
                ig=v.findViewById(R.id.imgrec);
                tw=v.findViewById(R.id.tir);
            }
        }
 }
    void sliderviewer(){

        vp.setAdapter(new Myadapter(front.this,al));
        final Handler hn=new Handler();
        final Runnable update=new Runnable() {
            @Override
            public void run() {
                if(currentpage==img.length)
                {
                    currentpage=0;
                }
                vp.setCurrentItem(currentpage++,true);
            }
        };
        Timer swipeTimer=new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                hn.post(update);
            }
        },2500,2500);
    }


    class Myadapter extends PagerAdapter{

        private ArrayList <Integer>images;
        private LayoutInflater inflater;
        private Context context;
        Myadapter(Context context,ArrayList images){
            this.context=context;
            this.images=images;
            inflater =LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View)object);
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View myimglayout=inflater.inflate(R.layout.slide,container,false);
            ImageView myimg=myimglayout.findViewById(R.id.image);
            myimg.setImageResource((images.get(position)));
            container.addView(myimglayout,0);
            return myimglayout;
        }



        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view.equals(object);
        }
    }
}
