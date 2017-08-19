package com.example.nc_basic_ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.nc_basic_ui.factory.InFragmentFactory;
import com.example.nc_super_abs.viewpager.BaseStatePageAdapter;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/5/24 19:44
 */
public class CatFoundPageAdapter extends BaseStatePageAdapter {

    public CatFoundPageAdapter(FragmentManager fm, String[] titles) {

        super(fm, titles);
    }

    @Override
    public Fragment getItemFragment(int position) {
        return InFragmentFactory.buildFragment(position);
    }
}
