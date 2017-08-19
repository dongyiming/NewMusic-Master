package com.example.nc_basic_biz.cache;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/8/2 2:11
 */
public class EyepetorzerCache {

    private static EyepetorzerCache instance;
    private String nextPageUrl;
    private boolean isFirst = true;

    public static EyepetorzerCache getInstance() {

        if (instance == null) {
            synchronized (RetrofitBuilder.class) {
                if (instance == null) {
                    instance = new EyepetorzerCache();
                }
            }
        }
        return instance;
    }

    public void setNextUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public boolean isFirst() {
        return isFirst;
    }

    public void setFirst(boolean first) {
        isFirst = first;
    }
}
