package com.example.nc_super_abs.adapter;

/**
 * @version : 1.0
 * @Description : getItemViewType的抽取没有找到好的方法
 * @autho : dongyiming
 * @data : 2017/6/6 20:25
 */
public class WrapperBean<T> {

    T t;

    int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
