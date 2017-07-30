package com.example.nc_basic_ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.nc_basic_ui.factory.OuterFragmentFactory;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/5/23 18:10
 */
public class HomePageAdapter extends FragmentPagerAdapter {

    private int count;

    public void setPagerCount(int count) {

        this.count = count;
    }

    public HomePageAdapter(FragmentManager fm) {
        super(fm);
    }

    public Fragment getItem(int position) {

        return OuterFragmentFactory.buildFragment(position);
    }

    public int getCount() {
        return this.count;
    }
}
