package com.example.nc_basic_ui.fragment.inner;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.example.nc_basic_ui.R;
import com.example.nc_super_abs.fragment.BaseFragment;
import com.example.nc_super_abs.fragment.BaseInnerFragment;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/5/24 19:37
 */
public class CatOwnerFragment extends BaseInnerFragment {


    @Override
    public View setRootView(LayoutInflater inflater) {

        View view = inflater.inflate(R.layout.fragment_catrecommend, null);
        return view;
    }

    @Override
    public void initComponent() {

    }

    @Override
    public void initData() {

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
