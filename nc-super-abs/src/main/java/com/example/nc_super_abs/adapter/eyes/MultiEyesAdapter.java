package com.example.nc_super_abs.adapter.eyes;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.nc_super_abs.adapter.viewholder.BaseViewHolder;

import java.util.List;

/**
 * @version : 1.0
 * @Description : 头部为rollview
 * @autho : dongyiming
 * @data : 2017/6/7 0:51
 */
public abstract class MultiEyesAdapter<T,E> extends RecyclerView.Adapter<BaseViewHolder> implements View.OnClickListener {


    public Context mContext;
    public List<T> headers;
    public List<E> list;

    public MultiEyesAdapter(Context mContext, List<T> headers, List<E> list) {

        this.mContext = mContext;
        this.headers = headers;
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {

        return getItemType(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        int layoutId = getLayoutId(viewType);
        return BaseViewHolder.build(mContext, layoutId, parent, null);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {

        convert(holder, position);
    }

    @Override
    public int getItemCount() {

        return getCount();
    }

    public abstract int getItemType(int position);

    public abstract int getLayoutId(int type);

    public abstract void convert(BaseViewHolder holder, int position);

    public abstract int getCount();
}
