package com.example.nc_super_abs.interaction;

import android.view.View;

/**
 *  @Description :
 *  @autho : dongyiming
 *  @version : 1.0
 *  @data : 2017/5/23 12:52
 */
public interface IAdapterViewListener<T> {
    void adapterWidgetClick(View var1, int var2, T var3);

    void adapterWidgetLoad(View var1, int var2, T var3);
}
