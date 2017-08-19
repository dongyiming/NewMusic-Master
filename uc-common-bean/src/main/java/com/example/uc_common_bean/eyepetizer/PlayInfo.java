package com.example.uc_common_bean.eyepetizer;

import java.util.List;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/7/31 21:59
 */
public class PlayInfo {

    private double height;
    private double width;
    private String name;
    private String type;
    private String url;
    private List<UrlList> urlList;

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<UrlList> getUrlList() {
        return urlList;
    }

    public void setUrlList(List<UrlList> urlList) {
        this.urlList = urlList;
    }

    @Override
    public String toString() {
        return "PlayInfo{" +
                "height=" + height +
                ", width=" + width +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", urlList=" + urlList +
                '}';
    }
}
