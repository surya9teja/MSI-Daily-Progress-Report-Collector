package com.example.msi_dpr;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


public class data_retrieval extends AppCompatActivity{

    private float[] yData = { 78,22 };
    private String[] xData = { "Work Done","Pending" };
    PieChart pieChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_retrieval);
        //pieChart=findViewById(R.id.piechart);
//        pieChart.setUsePercentValues(true);
//        pieChart.setDrawHoleEnabled(false);
//        pieChart.setRotationAngle(0);
//        pieChart.setRotationEnabled(true);
//        ArrayList<PieEntry> entries= new ArrayList<>();
//            entries.add(new PieEntry(50,0));
//            entries.add(new PieEntry(60,1));
//        PieDataSet dataSet=new PieDataSet(entries,"Work Progress");
//        ArrayList<String> xVal= new ArrayList<>();
//            xVal.add("Work Done");
//            xVal.add("Pending");
//            PieData data = new PieData(dataSet);
//        data.setValueFormatter(new PercentFormatter());
//        pieChart.setData(data);
//        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
//        data.setValueTextSize(13f);
//        data.setValueTextColor(Color.DKGRAY);
//        pieChart.animateXY(1400, 1400);
    }
}
