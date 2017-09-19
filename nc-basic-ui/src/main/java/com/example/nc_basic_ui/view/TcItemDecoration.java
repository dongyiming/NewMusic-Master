package com.example.nc_basic_ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.example.nc_basic_ui.R;

/**
 * @version : 1.0
 * @Description : TODO
 * @autho : dongyiming
 * @data : 2017/9/19 18:10
 * @package : com.example.nc_basic_ui.view
 */
public class TcItemDecoration extends RecyclerView.ItemDecoration {

    private int verticalDividerHeight;
    private int colorResource;
    private final Paint paint;

    public TcItemDecoration(Context mContext) {
        paint = new Paint();
        verticalDividerHeight = 24;
        colorResource = mContext.getResources().getColor(R.color.color_tc_splitlint_bg);
    }

    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        super.getItemOffsets(outRect, itemPosition, parent);
        if (parent.getLayoutManager() instanceof LinearLayoutManager) {
            if (itemPosition != 0) {
                outRect.set(0, 0, 0, verticalDividerHeight);
            } else {
                outRect.set(0, 0, 0, 1);
            }
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if (parent.getLayoutManager() instanceof LinearLayoutManager) {
            final int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                if (parent.getChildAt(i) != null && parent.getChildAt(i) instanceof LinearLayout) {
                    final View child = parent.getChildAt(i);
                    final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                            .getLayoutParams();
                    final int top = child.getBottom() + params.bottomMargin;
                    final int bottom = top + verticalDividerHeight;
                    paint.setColor(colorResource);
                    c.drawRect(0, top, parent.getWidth(), bottom, paint);
                }
            }
        }
    }
}
