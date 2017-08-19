package com.example.uc_common_bean.eyepetizer;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/7/31 22:33
 */
public class CoverHeader {

    private String actionUrl;
    private String cover;
    private String font;
    private int id;

    public String getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "FollowHeader{" +
                "actionUrl='" + actionUrl + '\'' +
                ", cover='" + cover + '\'' +
                ", font='" + font + '\'' +
                ", id=" + id +
                '}';
    }
}
