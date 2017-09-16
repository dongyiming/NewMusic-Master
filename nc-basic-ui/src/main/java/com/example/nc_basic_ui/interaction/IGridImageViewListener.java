package com.example.nc_basic_ui.interaction;

import android.view.View;

import java.util.List;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/8/28 1:13
 */
public interface IGridImageViewListener<T> {

    public List<T> getList();

    public int getCount();

    public View getItemView(int position);

    public T getPositionItem(int position);

}
