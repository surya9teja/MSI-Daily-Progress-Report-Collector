package com.example.msi_dpr;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


public class data_retrieval extends AppCompatActivity{

    Button ap1132,ap242,bsptcl,getco2226,getco2274p1,getco2274p2,getco2275,getco2278,pgcil1721,ts216,getco2276,pgcil1851,ptcul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_retrieval);
        ap1132= findViewById(R.id.ap1132);
        ap242=findViewById(R.id.ap242);
        bsptcl=findViewById(R.id.bsptcl);
        getco2226=findViewById(R.id.getco2226);
        getco2274p1=findViewById(R.id.getco2274p1);
        getco2274p2=findViewById(R.id.getco2274p2);
        getco2275=findViewById(R.id.getco2275);
        getco2278=findViewById(R.id.getco2278);
        pgcil1721=findViewById(R.id.pgcil1721);
        ts216=findViewById(R.id.ts216);
        getco2276=findViewById(R.id.getco2276);
        pgcil1851=findViewById(R.id.pgcil1851);
        ptcul=findViewById(R.id.ptcul);
        ap1132.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(data_retrieval.this,ap1132.class);
                startActivity(intent);
            }
        });
        ap242.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(data_retrieval.this,ap242.class);
                startActivity(intent);
            }
        });
        bsptcl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent (data_retrieval.this,bsptcl.class);
            startActivity(intent);
            }
        });
        getco2276.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(data_retrieval.this,getco2226.class);
                startActivity(intent);
            }
        });
        getco2274p1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(data_retrieval.this,getco2274p1.class);
                startActivity(intent);
            }
        });

        getco2274p2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(data_retrieval.this,getco2274p2.class);
                startActivity(intent);
            }
        });
        getco2275.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(data_retrieval.this,getco2225.class);
                startActivity(intent);
            }
        });
        getco2278.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(data_retrieval.this,getco2228.class);
                startActivity(intent);
            }
        });
        pgcil1721.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(data_retrieval.this,pgcil1721.class);
                startActivity(intent);
            }
        });
        ts216.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(data_retrieval.this,ts216.class);
                startActivity(intent);
            }
        });
        getco2276.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(data_retrieval.this,getco2276.class);
                startActivity(intent);
            }
        });
        pgcil1851.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(data_retrieval.this,pgcil1851.class);
                startActivity(intent);
            }
        });
        ptcul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(data_retrieval.this,ptcul.class);
                startActivity(intent);
            }
        });
        ap1132.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(data_retrieval.this,ap1132.class);
                startActivity(intent);
            }
        });




    }
}
