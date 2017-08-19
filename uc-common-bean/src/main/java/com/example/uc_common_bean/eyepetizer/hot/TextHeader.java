package com.example.uc_common_bean.eyepetizer.hot;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/7/31 22:24
 */
public class TextHeader {
    private String dataType;//dataType:TextHeader
    private String font;
    private String text;

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "TextFooter{" +
                "dataType='" + dataType + '\'' +
                ", font='" + font + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
