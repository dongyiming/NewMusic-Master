package com.example.uc_common_bean.eyepetizer.hot;

import java.util.Arrays;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/8/1 18:09
 */
public class HorizontalScrollCardItemInfo {

    private String actionUrl;
    private String dataType;
    private String description;
    private int id;
    private String image;
    private String title;
    private String[] labelList;
    private boolean shade;
    private Label label;
    private Header header;

    public static class Label {
        private String card;
        private String text;

        public String getCard() {
            return card;
        }

        public void setCard(String card) {
            this.card = card;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return "Label{" +
                    "card='" + card + '\'' +
                    ", text='" + text + '\'' +
                    '}';
        }
    }

    private static class Header {

        private int id;

        public int getId() {
            return id;
        }

        @Override
        public String toString() {
            return "Header{" +
                    "id=" + id +
                    '}';
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public String getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getLabelList() {
        return labelList;
    }

    public void setLabelList(String[] labelList) {
        this.labelList = labelList;
    }

    public boolean isShade() {
        return shade;
    }

    public void setShade(boolean shade) {
        this.shade = shade;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    @Override
    public String toString() {
        return "HorizontalScrollCardItemInfo{" +
                "actionUrl='" + actionUrl + '\'' +
                ", dataType='" + dataType + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", image='" + image + '\'' +
                ", title='" + title + '\'' +
                ", labelList=" + Arrays.toString(labelList) +
                ", shade=" + shade +
                ", label=" + label +
                ", header=" + header +
                '}';
    }
}
