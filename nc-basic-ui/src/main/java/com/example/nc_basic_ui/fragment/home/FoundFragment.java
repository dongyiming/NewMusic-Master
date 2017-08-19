package com.example.nc_basic_ui.fragment.home;

import android.view.LayoutInflater;
import android.view.View;

import com.example.nc_basic_ui.R;
import com.example.nc_super_abs.fragment.BaseFragment;

/**
 * @version : 1.0
 * @Description : 发现
 * @autho : dongyiming
 * @data : 2017/5/23 18:00
 */
public class FoundFragment extends BaseFragment {


    @Override
    public View setRootView(LayoutInflater inflater) {

        View view = inflater.inflate(R.layout.fragment_catrecommend, null);
        return view;
    }

    @Override
    public void initComponent() {

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
