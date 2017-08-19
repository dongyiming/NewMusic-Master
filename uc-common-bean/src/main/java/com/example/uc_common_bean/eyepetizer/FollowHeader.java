package com.example.uc_common_bean.eyepetizer;

import java.util.Arrays;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/7/31 22:33
 */
public class FollowHeader {

    private String actionUrl;
    private String cover;
    private String font;
    private String description;
    private String title;
    private String[] iconList;
    private int id;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getIconList() {
        return iconList;
    }

    public void setIconList(String[] iconList) {
        this.iconList = iconList;
    }

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
                ", description='" + description + '\'' +
                ", title='" + title + '\'' +
                ", iconList=" + Arrays.toString(iconList) +
                ", id=" + id +
                '}';
    }
}
