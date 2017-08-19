package com.example.uc_common_bean.eyepetizer;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/7/31 21:37
 */
public class Author {

    private int approvedNotReadyVideoCount;
    private String description;
    private Follow follow;
    private String icon;
    private int id;
    private long latestReleaseTime;
    private String link;
    private String name;
    private Shield shield;
    private int videoNum;

    public int getApprovedNotReadyVideoCount() {
        return approvedNotReadyVideoCount;
    }

    public void setApprovedNotReadyVideoCount(int approvedNotReadyVideoCount) {
        this.approvedNotReadyVideoCount = approvedNotReadyVideoCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Follow getFollow() {
        return follow;
    }

    public void setFollow(Follow follow) {
        this.follow = follow;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getLatestReleaseTime() {
        return latestReleaseTime;
    }

    public void setLatestReleaseTime(long latestReleaseTime) {
        this.latestReleaseTime = latestReleaseTime;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Shield getShield() {
        return shield;
    }

    public void setShield(Shield shield) {
        this.shield = shield;
    }

    public int getVideoNum() {
        return videoNum;
    }

    public void setVideoNum(int videoNum) {
        this.videoNum = videoNum;
    }

    @Override
    public String toString() {
        return "Author{" +
                "approvedNotReadyVideoCount=" + approvedNotReadyVideoCount +
                ", description='" + description + '\'' +
                ", follow=" + follow +
                ", icon='" + icon + '\'' +
                ", id=" + id +
                ", latestReleaseTime=" + latestReleaseTime +
                ", link='" + link + '\'' +
                ", name='" + name + '\'' +
                ", shield=" + shield +
                ", videoNum=" + videoNum +
                '}';
    }
}
