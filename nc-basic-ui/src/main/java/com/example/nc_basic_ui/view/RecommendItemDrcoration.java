package com.example.nc_basic_ui.view;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.nc_basic_ui.R;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/8/8 15:34
 */
public class RecommendItemDrcoration extends RecyclerView.ItemDecoration {

    private Context mContext;

    public RecommendItemDrcoration(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parente) {
        RecyclerView.LayoutManager layoutManager = parente.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {

            if (parente != null && parente.getAdapter() != null) {
                int itemViewType = parente.getAdapter().getItemViewType(itemPosition);
                if (itemViewType == 2 || itemViewType == 3 || itemViewType == 4 || itemViewType == 5 || itemViewType == 7 || itemViewType == 8) {
                    outRect.set(0, 0, 0, (int) mContext.getResources().getDimension(R.dimen.dimens_common_decoration_bottomdividerheight));
                } else if (itemViewType == 6) {
                    outRect.set(0, 0, 0, (int) mContext.getResources().getDimension(R.dimen.dimens_common_decoration_leftdividerwidth));
                }
            }
        }
    }
}
