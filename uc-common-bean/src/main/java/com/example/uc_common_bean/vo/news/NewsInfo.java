package com.example.uc_common_bean.vo.news;

import java.util.Arrays;

/**
 * @version : 1.0
 * @Description : 新闻的具体内容
 * @autho : dongyiming
 * @data : 2017/8/5 8:39
 */
public class NewsInfo {

    private int id;
    private String body;
    private String[] css;
    private String ga_prefix;
    private String image;
    private String image_source;
    private String[] images;
    private String[] js;
    private String share_url;
    private String title;
    private int type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String[] getCss() {
        return css;
    }

    public void setCss(String[] css) {
        this.css = css;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage_source() {
        return image_source;
    }

    public void setImage_source(String image_source) {
        this.image_source = image_source;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public String[] getJs() {
        return js;
    }

    public void setJs(String[] js) {
        this.js = js;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
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

    @Override
    public String toString() {
        return "NewsInfo{" +
                "id=" + id +
                ", body='" + body + '\'' +
                ", css=" + Arrays.toString(css) +
                ", ga_prefix='" + ga_prefix + '\'' +
                ", image='" + image + '\'' +
                ", image_source='" + image_source + '\'' +
                ", images=" + Arrays.toString(images) +
                ", js=" + Arrays.toString(js) +
                ", share_url='" + share_url + '\'' +
                ", title='" + title + '\'' +
                ", type=" + type +
                '}';
    }
}
