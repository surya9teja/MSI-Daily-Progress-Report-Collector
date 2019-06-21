package com.example.msi_dpr;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {

    int mNumOfTabs;
    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Fragment_p1 tab1 = new Fragment_p1();
                return tab1;
            case 1:
                Fragment_p2 tab2 = new Fragment_p2();
                return tab2;
            case 2:
                Fragment_p3 tab3 = new Fragment_p3();
                return tab3;
            case 3:
                Fragment_p4 tab4 = new Fragment_p4();
                return tab4;
            case 4:
                Fragment_p5 tab5 = new Fragment_p5();
                return tab5;
            case 5:
                Fragment_p6 tab6 = new Fragment_p6();
                return tab6;
            case 7:
                Fragment_p8 tab8 = new Fragment_p8();
                return tab8;
            case 8:
                Fragment_p9 tab9 = new Fragment_p9();
                return tab9;
            case 9:
                Fragment_p10 tab10 = new Fragment_p10();
                return tab10;
            case 10:
                Fragment_p11 tab11 = new Fragment_p11();
                return tab11;
            case 11:
                Fragment_p12 tab12 = new Fragment_p12();
                return tab12;
            case 12:
                Fragment_p13 tab13 = new Fragment_p13();
                return tab13;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
