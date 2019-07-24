package com.example.lazeeztadka.hoseyspicey;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class gridall extends AppCompatActivity {
GridView gr;
int[] alls={R.drawable.tas1,R.drawable.tas2,R.drawable.tas3,R.drawable.tas4,R.drawable.ev1,R.drawable.ev2,R.drawable.ev3,R.drawable.ev4};
  String[] allstext={"TASTY1","TASTY2","TASTY3","TASTY4","EVEREST 1","EVEREST 2","EVEREST 3","EVEREST 4"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridall);
        gr=findViewById(R.id.gri);
        Myadap or=new Myadap();
        gr.setAdapter(or);

    }
    class Myadap extends BaseAdapter {

        @Override
        public int getCount() {
            return alls.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView=getLayoutInflater().inflate(R.layout.cusgird,null);
            ImageView ig=convertView.findViewById(R.id.gd1);
            TextView ti=convertView.findViewById(R.id.gd2);
            ig.setImageResource(alls[position]);
            ti.setText(allstext[position]);
            return convertView;
        }
    }
}
