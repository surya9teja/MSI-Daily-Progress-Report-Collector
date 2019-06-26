package com.example.msi_dpr;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {


    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int i) {
        Fragment fragment=null;
        if(i==0){
            fragment=new ap_1_fragment();
        }
        else if(i==1)
            fragment = new ap_2_fragment();
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
    @Override
    public CharSequence getPageTitle(int i){
        String title=null;
        if(i==0){
            title="Ap-1132Km & Ap-636Km";
        }
        else if(i==1){
            title="AP-242Km";
        }
        return title;
    }
}
