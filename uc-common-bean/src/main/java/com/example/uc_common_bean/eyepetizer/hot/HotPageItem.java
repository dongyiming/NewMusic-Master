package com.example.uc_common_bean.eyepetizer.hot;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/7/31 21:32
 */

public class HotPageItem<T> {

    private T data; //根据type来解析
    private String type;//video，horizontalScrollCard，squareCardCollection，textHeader

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "HotPageItem{" +
                "data=" + data +
                ", type='" + type + '\'' +
                '}';
    }
}
