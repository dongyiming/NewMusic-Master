package com.example.nc_super_abs.interaction;

import android.view.View;

/**
 *  @Description :
 *  @autho : dongyiming
 *  @version : 1.0
 *  @data : 2017/5/23 12:52
 */
public interface IView<T> {
    T getInteractionView();

    void initWidget();

    void registerWidgetEvent();

    void widgetClick(View var1);

    void initComponent();
}