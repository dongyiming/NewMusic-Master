package com.example.uc_common_bean.eyepetizer;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/7/31 21:38
 */
public class Follow {

    private boolean followed;
    private int itemId;
    private String itemType;//type为"author"

    public boolean isFollowed() {
        return followed;
    }

    public void setFollowed(boolean followed) {
        this.followed = followed;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    @Override
    public String toString() {
        return "Follow{" +
                "followed=" + followed +
                ", itemId=" + itemId +
                ", itemType='" + itemType + '\'' +
                '}';
    }
}
