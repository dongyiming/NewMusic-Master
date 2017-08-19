package com.example.uc_common_bean.eyepetizer;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/7/31 22:07
 */
public class WebUrl {

    private String forWeibo;
    private String raw;

    public String getForWeibo() {
        return forWeibo;
    }

    public void setForWeibo(String forWeibo) {
        this.forWeibo = forWeibo;
    }

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    @Override
    public String toString() {
        return "WebUrl{" +
                "forWeibo='" + forWeibo + '\'' +
                ", raw='" + raw + '\'' +
                '}';
    }
}
