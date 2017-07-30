package com.example.nc_super_abs.controller;

import android.util.SparseArray;

import com.example.nc_super_abs.adapter.WrapperBean;
import com.example.nc_super_abs.adapter.viewholder.BaseViewHolder;
import com.example.nc_super_abs.interaction.IMultiRecyclerViewListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version : 1.0
 * @Description : 管理RecyclerView的多type的Item，用于将每个item的操作解耦，并且对扩展开放
 * @autho : dongyiming
 * @data : 2017/6/6 15:34
 */
public class BaseItemViewController<T extends WrapperBean> {

    protected Logger logger = LoggerFactory.getLogger(getClass());
    private SparseArray<IMultiRecyclerViewListener<T>> items = new SparseArray<>();

    /**
     * 添加类型
     *
     * @param item
     * @param type
     * @return
     */
    public BaseItemViewController<T> addItemView(IMultiRecyclerViewListener<T> item, int type) {

        if (items != null && item != null) {
            items.put(type, item);
            logger.error("items.size = " + items.size());
        }
        return this;
    }

    /**
     * 获取该类型的layoutId
     *
     * @param viewType
     * @return
     */
    public int getItemLayout(int viewType) {

        logger.error("the size :{}", items.size());
        if (items != null) {

            IMultiRecyclerViewListener<T> tiMultiRecyclerViewListener = items.valueAt(viewType);

            return tiMultiRecyclerViewListener.getLayoutId(viewType);
        }
        throw new IllegalArgumentException("no such type = " + viewType);
    }

    /**
     * @param holder
     * @param t
     * @param viewType
     */
    public void convert(BaseViewHolder holder, T t, int viewType) {


        if (items != null) {
            IMultiRecyclerViewListener<T> tiMultiRecyclerViewListener = items.get(viewType);
            tiMultiRecyclerViewListener.convert(holder, t);
            return;
        }
        throw new IllegalArgumentException("no such type = " + viewType);
    }
}
