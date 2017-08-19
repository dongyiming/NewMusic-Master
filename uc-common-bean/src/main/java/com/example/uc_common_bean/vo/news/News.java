package com.example.uc_common_bean.vo.news;

import java.util.Arrays;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/8/5 8:25
 */
public class News {

    private String ga_prefix;
    private int id;
    private String title;
    private int type;
    private String[] images;
    private boolean multipic;

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public boolean isMultipic() {
        return multipic;
    }

    public void setMultipic(boolean multipic) {
        this.multipic = multipic;
    }

    @Override
    public String toString() {
        return "News{" +
                "ga_prefix='" + ga_prefix + '\'' +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", images=" + Arrays.toString(images) +
                ", multipic=" + multipic +
                '}';
    }
}
