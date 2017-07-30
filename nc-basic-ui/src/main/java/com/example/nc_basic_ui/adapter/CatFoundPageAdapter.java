package com.example.nc_basic_ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.example.nc_basic_ui.factory.InFragmentFactory;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/5/24 19:44
 */
public class CatFoundPageAdapter extends FragmentStatePagerAdapter {

    private int count;
    private String[] titles;

    public CatFoundPageAdapter(FragmentManager fm, String[] titles) {

        super(fm);
        this.titles = titles;
        count = 4;
    }

    public void setPagerCount(int count) {

        this.count = count;
    }

    public Fragment getItem(int position) {

        return InFragmentFactory.buildFragment(position);
    }

    public int getCount() {
        return this.count;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
