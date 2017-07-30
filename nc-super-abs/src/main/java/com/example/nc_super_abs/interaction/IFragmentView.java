package com.example.nc_super_abs.interaction;

import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;

/**
 *  @Description :
 *  @autho : dongyiming
 *  @version : 1.0
 *  @data : 2017/5/23 12:52
 */
public interface IFragmentView<T> extends IView<Fragment> {
    View setRootView(LayoutInflater var1);
}

