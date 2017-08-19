package com.example.uc_common_bean.eyepetizer;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/7/31 21:43
 */
public class Cover {
    private String blurred;
    private String detail;
    private String feed;
    private String homepage;

    public String getBlurred() {
        return blurred;
    }

    public void setBlurred(String blurred) {
        this.blurred = blurred;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getFeed() {
        return feed;
    }

    public void setFeed(String feed) {
        this.feed = feed;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    @Override
    public String toString() {
        return "Cover{" +
                "blurred='" + blurred + '\'' +
                ", detail='" + detail + '\'' +
                ", feed='" + feed + '\'' +
                ", homepage='" + homepage + '\'' +
                '}';
    }
}
