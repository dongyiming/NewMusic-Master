package com.example.nc_super_abs.interaction;

import android.widget.AbsListView;

/**
 *  @Description :
 *  @autho : dongyiming
 *  @version : 1.0
 *  @data : 2017/5/23 12:51
 */
public interface IAdapterScrollListener {
    void scrollStateChanged(AbsListView var1, int var2);

    void scroll(AbsListView var1, int var2, int var3, int var4);
}