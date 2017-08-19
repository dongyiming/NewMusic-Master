package com.example.uc_common_bean.eyepetizer.hot;

import java.util.List;

/**
 * @version : 1.0
 * @Description : 热门rollpager
 * @autho : dongyiming
 * @data : 2017/8/1 18:06
 */
public class HorizontalScrollCard {

    private double count;
    private String dataType;
    private List<HorizontalScrollCardItem> itemList;

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public List<HorizontalScrollCardItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<HorizontalScrollCardItem> itemList) {
        this.itemList = itemList;
    }

    @Override
    public String toString() {
        return "HorizontalScrollCard{" +
                "count=" + count +
                ", dataType='" + dataType + '\'' +
                ", itemList=" + itemList +
                '}';
    }
}
