package com.example.nc_super_abs.adapter.viewholder;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.nc_super_abs.interaction.ICommonRecyclerListener;

import java.util.List;

/**
 *  @Description : 通过继承的方式使用
 *  @autho : dongyiming
 *  @version : 1.0
 *  @data : 2017/7/27 17:27
 */
public abstract class MultiBaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    public Context mContext;
    private List<T> datas;
    private ICommonRecyclerListener iCommonRecyclerListener;

    public MultiBaseAdapter(Context mContext, List<T> datas) {

        this.mContext = mContext;
        this.datas = datas;
    }

    @Override
    public int getItemViewType(int position) {

        return getViewType(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        int layoutId = getLayoutId(viewType);
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
        convert(holder, data, position, itemViewType);
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

                    return setSpanSize(position, getItemViewType(position));
                }
            });
        }
    }

    public abstract int getViewType(int position);

    public abstract int getLayoutId(int viewType);

    public abstract void convert(BaseViewHolder holder, T t, int position, int itemViewType);

    public abstract int setSpanSize(int position, int itemViewType);
}
