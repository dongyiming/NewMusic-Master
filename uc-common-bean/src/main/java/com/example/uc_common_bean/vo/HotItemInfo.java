package com.example.uc_common_bean.vo;

import com.example.uc_common_bean.eyepetizer.Author;
import com.example.uc_common_bean.eyepetizer.Consumption;
import com.example.uc_common_bean.eyepetizer.Cover;
import com.example.uc_common_bean.eyepetizer.PlayInfo;
import com.example.uc_common_bean.eyepetizer.WebUrl;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @version : 1.0
 * @Description : 本地需要的数据
 * @autho : dongyiming
 * @data : 2017/8/2 1:54
 */
@Entity
public class HotItemInfo {

    @Id(autoincrement = true)
    private Long id;
    private String dataType;//类型
    private String actionUrl;//拼接路径
    private String image;//图片路径
    private String authorDesc;//作者信息
    private String authorIcon;//作者图片
    private String authorName;//作者名称
    private String description;//描述信息
    private double data;//发布时间
    private double duration;//时长
    private double dateId;

    private String playUrl;
    private String title;
    private String category;//类型
    private double collectionCount;
    private double replyCount;
    private double shareCount;

    private String blurred;
    private String detail;
    private String feed;

    private String forWeibo;
    private String raw;

    private double sdHeight;
    private double sdWidth;
    private String sdName;
    private String sdUrl;

    private double hdHeight;
    private double hdWidth;
    private String hdName;
    private String hdUrl;
    public String getHdUrl() {
        return this.hdUrl;
    }
    public void setHdUrl(String hdUrl) {
        this.hdUrl = hdUrl;
    }
    public String getHdName() {
        return this.hdName;
    }
    public void setHdName(String hdName) {
        this.hdName = hdName;
    }
    public double getHdWidth() {
        return this.hdWidth;
    }
    public void setHdWidth(double hdWidth) {
        this.hdWidth = hdWidth;
    }
    public double getHdHeight() {
        return this.hdHeight;
    }
    public void setHdHeight(double hdHeight) {
        this.hdHeight = hdHeight;
    }
    public String getSdUrl() {
        return this.sdUrl;
    }
    public void setSdUrl(String sdUrl) {
        this.sdUrl = sdUrl;
    }
    public String getSdName() {
        return this.sdName;
    }
    public void setSdName(String sdName) {
        this.sdName = sdName;
    }
    public double getSdWidth() {
        return this.sdWidth;
    }
    public void setSdWidth(double sdWidth) {
        this.sdWidth = sdWidth;
    }
    public double getSdHeight() {
        return this.sdHeight;
    }
    public void setSdHeight(double sdHeight) {
        this.sdHeight = sdHeight;
    }
    public String getRaw() {
        return this.raw;
    }
    public void setRaw(String raw) {
        this.raw = raw;
    }
    public String getForWeibo() {
        return this.forWeibo;
    }
    public void setForWeibo(String forWeibo) {
        this.forWeibo = forWeibo;
    }
    public String getFeed() {
        return this.feed;
    }
    public void setFeed(String feed) {
        this.feed = feed;
    }
    public String getDetail() {
        return this.detail;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }
    public String getBlurred() {
        return this.blurred;
    }
    public void setBlurred(String blurred) {
        this.blurred = blurred;
    }
    public double getShareCount() {
        return this.shareCount;
    }
    public void setShareCount(double shareCount) {
        this.shareCount = shareCount;
    }
    public double getReplyCount() {
        return this.replyCount;
    }
    public void setReplyCount(double replyCount) {
        this.replyCount = replyCount;
    }
    public double getCollectionCount() {
        return this.collectionCount;
    }
    public void setCollectionCount(double collectionCount) {
        this.collectionCount = collectionCount;
    }
    public String getCategory() {
        return this.category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getPlayUrl() {
        return this.playUrl;
    }
    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }
    public double getDuration() {
        return this.duration;
    }
    public void setDuration(double duration) {
        this.duration = duration;
    }
    public double getData() {
        return this.data;
    }
    public void setData(double data) {
        this.data = data;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getAuthorName() {
        return this.authorName;
    }
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
    public String getAuthorIcon() {
        return this.authorIcon;
    }
    public void setAuthorIcon(String authorIcon) {
        this.authorIcon = authorIcon;
    }
    public String getAuthorDesc() {
        return this.authorDesc;
    }
    public void setAuthorDesc(String authorDesc) {
        this.authorDesc = authorDesc;
    }
    public String getImage() {
        return this.image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getActionUrl() {
        return this.actionUrl;
    }
    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }
    public String getDataType() {
        return this.dataType;
    }
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public double getDateId() {
        return this.dateId;
    }
    public void setDateId(double dateId) {
        this.dateId = dateId;
    }
    @Generated(hash = 1961954768)
    public HotItemInfo(Long id, String dataType, String actionUrl, String image,
            String authorDesc, String authorIcon, String authorName,
            String description, double data, double duration, double dateId,
            String playUrl, String title, String category, double collectionCount,
            double replyCount, double shareCount, String blurred, String detail,
            String feed, String forWeibo, String raw, double sdHeight,
            double sdWidth, String sdName, String sdUrl, double hdHeight,
            double hdWidth, String hdName, String hdUrl) {
        this.id = id;
        this.dataType = dataType;
        this.actionUrl = actionUrl;
        this.image = image;
        this.authorDesc = authorDesc;
        this.authorIcon = authorIcon;
        this.authorName = authorName;
        this.description = description;
        this.data = data;
        this.duration = duration;
        this.dateId = dateId;
        this.playUrl = playUrl;
        this.title = title;
        this.category = category;
        this.collectionCount = collectionCount;
        this.replyCount = replyCount;
        this.shareCount = shareCount;
        this.blurred = blurred;
        this.detail = detail;
        this.feed = feed;
        this.forWeibo = forWeibo;
        this.raw = raw;
        this.sdHeight = sdHeight;
        this.sdWidth = sdWidth;
        this.sdName = sdName;
        this.sdUrl = sdUrl;
        this.hdHeight = hdHeight;
        this.hdWidth = hdWidth;
        this.hdName = hdName;
        this.hdUrl = hdUrl;
    }
    @Generated(hash = 292817358)
    public HotItemInfo() {
    }

}
