package com.example.nc_basic_ui.view;

import android.content.Context;
import android.graphics.PointF;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : $data$ 12$
 */
public class ChildrenViewPager extends ViewPager {

    PointF downP = new PointF();
    PointF curP = new PointF();
    OnSingleTouchListener onSingleTouchListener;

    public ChildrenViewPager(Context mContext) {
        super(mContext);
    }

    public ChildrenViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        curP.x = event.getX();
        curP.y = event.getY();
        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                //记录按下时候的坐标
                //切记不可用 downP = curP ，这样在改变curP的时候，downP也会改变
                downP.x = event.getX();
                downP.y = event.getY();
                //此句代码是为了通知他的父ViewPager现在进行的是本控件的操作，不要对我的操作进行干扰
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_UP:
                if (downP.x == curP.x && downP.y == curP.y) {
                    onSingleTouch();
                    return true;
                }
                break;
        }


        return super.dispatchTouchEvent(event);
    }

    public void onSingleTouch() {
        if (onSingleTouchListener != null) {
            onSingleTouchListener.onSingleTouch();
        }
    }

    public interface OnSingleTouchListener {
        public void onSingleTouch();
    }

    public void setOnSingleTouchListener(OnSingleTouchListener onSingleTouchListener) {
        this.onSingleTouchListener = onSingleTouchListener;
    }
}
