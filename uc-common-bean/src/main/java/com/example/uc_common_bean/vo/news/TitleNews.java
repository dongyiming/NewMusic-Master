package com.example.uc_common_bean.vo.news;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/8/5 23:42
 */
public class TitleNews {

    private String ga_prefix;
    private int id;
    private String title;
    private int type;
    private String image;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "TitleNews{" +
                "ga_prefix='" + ga_prefix + '\'' +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", image='" + image + '\'' +
                '}';
    }
}
