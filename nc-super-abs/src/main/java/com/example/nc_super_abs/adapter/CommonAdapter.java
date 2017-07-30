package com.example.nc_super_abs.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.nc_super_abs.adapter.viewholder.BaseViewHolder;

import java.util.List;

/**
 * @version : 1.0
 * @Description : 用于嵌套
 * @autho : dongyiming
 * @data : 2017/6/5 23:25
 */
public abstract class CommonAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    private List<T> dataList;
    private Context mContext;

    public CommonAdapter(Context mContext, List<T> dataList) {

        this.mContext = mContext;
        this.dataList = dataList;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        int layoutId = getLayoutId();
        BaseViewHolder viewHolder = BaseViewHolder.build(mContext, layoutId, parent, null);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {

        convert(holder, dataList.get(position), position);

    }

    public abstract void convert(BaseViewHolder holder, T data, int position);

    public abstract int getLayoutId();

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

}
