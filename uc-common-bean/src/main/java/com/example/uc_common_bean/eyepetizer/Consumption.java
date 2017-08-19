package com.example.uc_common_bean.eyepetizer;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/7/31 21:42
 */
public class Consumption {

    private double collectionCount;
    private double replyCount;
    private double shareCount;

    public double getCollectionCount() {
        return collectionCount;
    }

    public void setCollectionCount(double collectionCount) {
        this.collectionCount = collectionCount;
    }

    public double getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(double replyCount) {
        this.replyCount = replyCount;
    }

    public double getShareCount() {
        return shareCount;
    }

    public void setShareCount(double shareCount) {
        this.shareCount = shareCount;
    }

    @Override
    public String toString() {
        return "Consumption{" +
                "collectionCount=" + collectionCount +
                ", replyCount=" + replyCount +
                ", shareCount=" + shareCount +
                '}';
    }
}
