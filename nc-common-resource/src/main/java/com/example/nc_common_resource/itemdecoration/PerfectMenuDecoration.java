package com.example.nc_common_resource.itemdecoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.nc_common_resource.R;

/**
 * @version : 1.0
 * @Description : perfect decoration
 * @autho : dongyiming
 * @data : 2017/6/4 19:38
 */
public class PerfectMenuDecoration extends RecyclerView.ItemDecoration {

    private Context mContext;
    private final int diverHeight;
    private final int diverPadding;
    private final Paint paint;

    public PerfectMenuDecoration(Context mContext) {

        this.mContext = mContext;
        paint = new Paint();
        paint.setColor(mContext.getResources().getColor(R.color.color_splitlint_bg));
        diverHeight = (int) mContext.getResources().getDimension(R.dimen.common_lh_0);
        diverPadding = (int) mContext.getResources().getDimension(R.dimen.common_lh_3);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int count = parent.getChildCount();
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        for (int i = 0; i < count; i++) {
            View view = parent.getChildAt(i);
            int top = view.getBottom();
            int bottom = view.getBottom() + diverHeight;
            c.drawRect(left, top, right, bottom, paint);
        }

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = diverHeight;
    }
}
