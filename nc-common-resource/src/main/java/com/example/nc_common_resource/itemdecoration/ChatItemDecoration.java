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
 * @Description : 歌单列表gridview的间隔
 * @autho : dongyiming
 * @data : 2017/5/26 15:38
 */
public class ChatItemDecoration extends RecyclerView.ItemDecoration {

    private Context mContext;
    private final int diverHeight;
    private final int diverPadding;
    private final Paint paint;
    private int count;

    public ChatItemDecoration(Context mContext, int count) {

        this.mContext = mContext;
        this.count = count;
        paint = new Paint();
        paint.setColor(mContext.getResources().getColor(R.color.color_padding_chatlist));
        diverHeight = (int) mContext.getResources().getDimension(R.dimen.common_lh_18);
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
        outRect.left = diverPadding;
        //每一行的最左边的那个不需要左边距
        int childLayoutPosition = parent.getChildLayoutPosition(view);
        childLayoutPosition = childLayoutPosition++;
        if (childLayoutPosition % count == 0) {
            outRect.left = 0;
        }
    }
}
