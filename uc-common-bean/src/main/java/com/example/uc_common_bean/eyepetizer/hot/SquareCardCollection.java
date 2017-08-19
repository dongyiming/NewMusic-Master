package com.example.uc_common_bean.eyepetizer.hot;

import java.util.List;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/8/1 18:42
 */
public class SquareCardCollection {

    private int count;
    private String dataType;
    private SquareCardCollectionHeader header;
    private List<SquareCardCollectionItem> itemList;

    public static class SquareCardCollectionHeader {

        private String actionUrl;
        private String font;
        private int id;
        private String title;

        public String getActionUrl() {
            return actionUrl;
        }

        public void setActionUrl(String actionUrl) {
            this.actionUrl = actionUrl;
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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return "SquareCardCollectionHeader{" +
                    "actionUrl='" + actionUrl + '\'' +
                    ", font='" + font + '\'' +
                    ", id=" + id +
                    ", title='" + title + '\'' +
                    '}';
        }
    }

    public static class SquareCardCollectionItem<T> {

        private T data;//squareCard和actionCard
        private String type;

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "SquareCardCollectionItem{" +
                    "data=" + data +
                    ", type='" + type + '\'' +
                    '}';
        }
    }

    public static class SquareCard {
        private String actionUrl;
        private String dataType;
        private int id;
        private String image;
        private boolean shade;
        private String title;

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

        public boolean isShade() {
            return shade;
        }

        public void setShade(boolean shade) {
            this.shade = shade;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return "SquareCard{" +
                    "actionUrl='" + actionUrl + '\'' +
                    ", dataType='" + dataType + '\'' +
                    ", id=" + id +
                    ", image='" + image + '\'' +
                    ", shade=" + shade +
                    ", title='" + title + '\'' +
                    '}';
        }
    }

    public static class ActionCard {
        private String actionUrl;
        private String dataType;
        private String text;
        private int id;

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

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "ActionCard{" +
                    "actionUrl='" + actionUrl + '\'' +
                    ", dataType='" + dataType + '\'' +
                    ", text='" + text + '\'' +
                    ", id=" + id +
                    '}';
        }
    }
}
