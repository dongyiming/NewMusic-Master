package com.example.nc_basic_ui.adapter;

import android.content.Context;
import android.view.View;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/8/30 12:15
 */
public abstract class GridImageViewAdapter<T> {

    protected Logger logger = LoggerFactory.getLogger(getClass());
    private Context mContext;
    private List<T> list;
    private String url;

    public GridImageViewAdapter(Context mContext, List<T> list, String url) {

        this.url = url;
        this.list = list;
        this.mContext = mContext;
    }

    public List<T> getList() {
        return list;
    }

    public String getUrl() {
        return url;
    }

    public T getPositionItem(int position) {

        if (list != null && !list.isEmpty()) {
            return list.get(position);
        }
        logger.warn("tcImageView : the list is null");
        return null;
    }

    public View getItemView(int position) {

        T t = getPositionItem(position);
        return getItemView(position, t, list);
    }

    public int getCount() {
        return list != null ? list.size() : 0;
    }

    public abstract View getItemView(int position, T t, List<T> list);

}
