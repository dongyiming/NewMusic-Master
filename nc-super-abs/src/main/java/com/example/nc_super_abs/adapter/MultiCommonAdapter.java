package com.example.nc_super_abs.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.nc_super_abs.adapter.viewholder.BaseViewHolder;
import com.example.nc_super_abs.interaction.ICommonRecyclerListener;

import java.util.List;

/**
 * @version : 1.0
 * @Description : 头部header
 * @autho : dongyiming
 * @data : 2017/6/7 0:51
 */
public class MultiCommonAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {


    private Context mContext;
    private List<T> datas;
    private ICommonRecyclerListener iCommonRecyclerListener;

    public MultiCommonAdapter(Context mContext, List<T> datas, ICommonRecyclerListener iCommonRecyclerListener) {

        this.mContext = mContext;
        this.datas = datas;
        this.iCommonRecyclerListener = iCommonRecyclerListener;
    }

    @Override
    public int getItemViewType(int position) {

        return iCommonRecyclerListener.getItemViewType(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        int layoutId = iCommonRecyclerListener.getLayoutId(viewType);
        return BaseViewHolder.build(mContext, layoutId, parent, null);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {

        T data;
        int itemViewType = getItemViewType(position);

        if (position == 0) {
            data = null;
        } else {
            data = datas.get(position - 1);
        }
        iCommonRecyclerListener.convert(holder, data, position, itemViewType);
    }

    @Override
    public int getItemCount() {

        return datas != null ? datas.size() + 1 : 0;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            //header的span size = 3,item1和item2的span size = 1,item3的span size = 2;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {

                    return iCommonRecyclerListener.setSpanCount(position, getItemViewType(position));
                }
            });
        }
    }
}
