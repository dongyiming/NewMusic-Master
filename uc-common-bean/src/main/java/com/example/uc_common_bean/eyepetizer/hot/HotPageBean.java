package com.example.uc_common_bean.eyepetizer.hot;

import java.util.List;

/**
 * @version : 1.0
 * @Description :开眼APP--首页数据
 * @autho : dongyiming
 * @data : 2017/7/31 17:49
 */
public class HotPageBean {

    private int count;
    private List<HotPageItem> itemList;
    private String nextPageUrl;
    private long total;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<HotPageItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<HotPageItem> itemList) {
        this.itemList = itemList;
    }

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }


    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "HotPageBean{" +
                "count=" + count +
                ", itemList=" + itemList +
                ", nextPageUrl='" + nextPageUrl + '\'' +
                ", total=" + total +
                '}';
    }
}
