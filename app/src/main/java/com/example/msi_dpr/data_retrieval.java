package com.example.msi_dpr;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class data_retrieval extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_retrieval);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        viewPager=findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout=findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.data_retrieval_color));
        }
        
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        String project[] = new String[]{"AP- 1132 & 636km", "AP- 242km", "BSPTCL", "GETCO- 2226", "GETCO- 2274 P1", "GETCO- 2274 P2", "GETCO- 2275", "GETCO- 2276", "GETCO- 2278", "PGCIL-1851km", "PGCIL-721km", "PTCUL", "TS-216km"};
        adapter.addFrag(new OneFragment(), project[0]);
        adapter.addFrag(new TwoFragment(), project[1]);
        adapter.addFrag(new ThreeFragment(), project[2]);
        adapter.addFrag(new FourFragment(), project[3]);
        adapter.addFrag(new FiveFragment(), project[4]);
        adapter.addFrag(new SixFragment(), project[5]);
        adapter.addFrag(new SevenFragment(), project[6]);
        adapter.addFrag(new EightFragment(), project[7]);
        adapter.addFrag(new NineFragment(), project[8]);
        adapter.addFrag(new TenFragment(), project[9]);
        adapter.addFrag(new ElevenFragment(), project[10]);
        adapter.addFrag(new TwelveFragment(), project[11]);
        adapter.addFrag(new ThirteenFragment(), project[12]);
        viewPager.setAdapter(adapter);
    }
}
