package com.example.nc_super_abs.view;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/8/9 23:06
 */
public class WrapCoordinatorLayout extends CoordinatorLayout {

    //上次的坐标
    private int mLastX = 0;
    private int mLastY = 0;

    public WrapCoordinatorLayout(Context context) {
        super(context);
    }

    public WrapCoordinatorLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WrapCoordinatorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastX = (int) event.getX();
                mLastY = (int) event.getY();
                return false;
            case MotionEvent.ACTION_MOVE:
                int deltaX = (int) (event.getX() - mLastX);
                int deltaY = (int) (event.getY() - mLastY);
                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    return false;
                } else {
                    return true;
                }
            case MotionEvent.ACTION_UP:
                return false;
        }
        return super.onInterceptTouchEvent(event);
    }
}
