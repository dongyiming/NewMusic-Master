package com.example.uc_common_bean.eyepetizer.hot;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/8/1 18:08
 */
public class HorizontalScrollCardItem {

    private String type;
    private HorizontalScrollCardItemInfo data;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public HorizontalScrollCardItemInfo getData() {
        return data;
    }

    public void setData(HorizontalScrollCardItemInfo data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "HorizontalScrollCardItem{" +
                "type='" + type + '\'' +
                ", data=" + data +
                '}';
    }
}
