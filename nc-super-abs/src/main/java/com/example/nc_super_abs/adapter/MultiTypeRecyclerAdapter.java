package com.example.nc_super_abs.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import com.example.nc_super_abs.adapter.viewholder.BaseViewHolder;
import com.example.nc_super_abs.controller.BaseItemViewController;
import com.example.nc_super_abs.interaction.IMultiRecyclerViewListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @version : 1.0
 * @Description : RecyclerView多类型的item
 * @autho : dongyiming
 * @data : 2017/6/6 12:31
 */
public class MultiTypeRecyclerAdapter<T extends WrapperBean<T>> extends RecyclerView.Adapter<BaseViewHolder> {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    private List<WrapperBean<T>> dataList;
    private Context mContext;
    private BaseItemViewController baseItemViewController;

    public MultiTypeRecyclerAdapter(Context mContext, List<WrapperBean<T>> dataList) {

        this.dataList = dataList;
        this.mContext = mContext;
        baseItemViewController = new BaseItemViewController();
    }

    @Override
    public int getItemViewType(int position) {

        return dataList.get(position).getType();
    }

    @Override
    public int getItemCount() {

        return dataList.size();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        int layoutId = baseItemViewController.getItemLayout(viewType);
        BaseViewHolder viewHolder = BaseViewHolder.build(mContext, layoutId, parent, null);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {

        baseItemViewController.convert(holder, dataList.get(position), dataList.get(position).getType());
    }

    public void addItems(IMultiRecyclerViewListener<T> item, int type) {
        baseItemViewController.addItemView(item, type);
    }
}
