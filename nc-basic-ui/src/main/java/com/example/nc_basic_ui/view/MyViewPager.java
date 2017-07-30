package com.example.nc_basic_ui.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/5/23 14:05
 */
public class MyViewPager extends ViewPager {

    public boolean isIntercept;

    public MyViewPager(Context context) {
        super(context);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setIntercept(boolean isIntercept) {
        this.isIntercept = isIntercept;
    }

    //屏蔽事件的消耗
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        if (!isIntercept) {

            return false;
        }
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        if (!isIntercept) {

            return false;
        }
        return true;
    }
}
