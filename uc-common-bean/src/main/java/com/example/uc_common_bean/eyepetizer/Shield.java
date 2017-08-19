package com.example.uc_common_bean.eyepetizer;

/**
 *  @Description :
 *  @autho : dongyiming
 *  @version : 1.0
 *  @data : 2017/7/31 21:40
 */
public class Shield {

    private int itemId;
    private String itemType;
    private boolean shielded;

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

    public boolean isShielded() {
        return shielded;
    }

    public void setShielded(boolean shielded) {
        this.shielded = shielded;
    }

    @Override
    public String toString() {
        return "Shield{" +
                "itemId=" + itemId +
                ", itemType='" + itemType + '\'' +
                ", shielded=" + shielded +
                '}';
    }
}
