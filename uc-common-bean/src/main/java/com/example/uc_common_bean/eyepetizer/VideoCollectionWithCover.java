package com.example.uc_common_bean.eyepetizer;

import com.example.uc_common_bean.eyepetizer.hot.HotPageItem;
import com.example.uc_common_bean.eyepetizer.hot.VideoModel1;

import java.util.List;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/7/31 22:32
 */
public class VideoCollectionWithCover {

    private int count;
    private String dataType;
    private CoverHeader header;
    private List<HotPageItem<VideoModel1>> itemList;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public CoverHeader getHeader() {
        return header;
    }

    public void setHeader(CoverHeader header) {
        this.header = header;
    }

    public List<HotPageItem<VideoModel1>> getItemList() {
        return itemList;
    }

    public void setItemList(List<HotPageItem<VideoModel1>> itemList) {
        this.itemList = itemList;
    }

    @Override
    public String toString() {
        return "VideoCollectionWithCover{" +
                "count=" + count +
                ", dataType='" + dataType + '\'' +
                ", header=" + header +
                ", itemList=" + itemList +
                '}';
    }
}
