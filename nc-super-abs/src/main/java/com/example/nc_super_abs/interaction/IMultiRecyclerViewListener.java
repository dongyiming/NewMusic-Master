package com.example.nc_super_abs.interaction;

import com.example.nc_super_abs.adapter.WrapperBean;
import com.example.nc_super_abs.adapter.viewholder.BaseViewHolder;

import java.util.List;

/**
 * @version : 1.0
 * @Description : 多种样式的监听,把每种类型所带来的变化封装到一个单独的对象里
 * @autho : dongyiming
 * @data : 2017/6/6 13:10
 */
public interface IMultiRecyclerViewListener<T extends WrapperBean> {

    /**
     * 得到当前类型的layoutId,用于创建Viewholder
     *
     * @param type
     * @return
     */
    public int getLayoutId(int type);

    /**
     * 得到当前item的类型
     *
     * @param datas
     * @param position
     * @return
     */
    //public int getItemType(List<T> datas, int position);

    /**
     * 通过当前item的类型，得到holder和数据，做界面数据的填充
     *
     * @param viewHolder
     * @param t
     */
    public void convert(BaseViewHolder viewHolder, T t);
}
