package com.example.uc_common_bean.vo.tc;

import java.io.Serializable;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/8/24 2:05
 */
public class ImageBean implements Serializable{

    private String description;
    private int height;
    private int width;
    private int showHeight;
    private int showWidth;
    private int img_id;
    private int user_id;
    private String title;

    public int getShowHeight() {
        return showHeight;
    }

    public void setShowHeight(int showHeight) {
        this.showHeight = showHeight;
    }

    public int getShowWidth() {
        return showWidth;
    }

    public void setShowWidth(int showWidth) {
        this.showWidth = showWidth;
    }

    public int getImg_id() {
        return img_id;
    }

    public void setImg_id(int img_id) {
        this.img_id = img_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
