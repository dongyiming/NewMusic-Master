package com.example.uc_common_bean.eyepetizer.hot;

import com.example.uc_common_bean.eyepetizer.Author;
import com.example.uc_common_bean.eyepetizer.Consumption;
import com.example.uc_common_bean.eyepetizer.PlayInfo;
import com.example.uc_common_bean.eyepetizer.Provider;
import com.example.uc_common_bean.eyepetizer.WebUrl;

import java.util.Arrays;
import java.util.List;

/**
 * @version : 1.0
 * @Description : 视频数据
 * @autho : dongyiming
 * @data : 2017/7/31 21:35
 */
public class VideoModel3 {

    private Author author;
    private String category;//科普
    private boolean collected;
    private Consumption consumption;
    private VideoModel1Cover cover;
    private String dataType;//VideoBeanForClient(对应video)，TextFooter，TextHeader，ItemCollection(对应videoCollectionWithCover和videoCollectionOfFollow)
    private long date;
    private String description;
    private String descriptionEditor;
    private String descriptionPgc;
    private int duration;
    private int id;
    private int idx;
    private int[] labelList;//类型不确定
    private String library;
    private List<PlayInfo> playInfo;
    private String playUrl;
    private boolean played;
    private Provider provider;
    private long releaseTime;
    private String remark;
    private String slogan;
    private String[] subtitles;//类型不确定
    private String[] tags;//类型不确定
    private String title;
    private String titlePgc;
    private String type;
    private WebUrl webUrl;

    public static class VideoModel1Cover {
        private String blurred;
        private String detail;
        private String feed;

        public String getBlurred() {
            return blurred;
        }

        public void setBlurred(String blurred) {
            this.blurred = blurred;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getFeed() {
            return feed;
        }

        public void setFeed(String feed) {
            this.feed = feed;
        }

        @Override
        public String toString() {
            return "VideoModel1Cover{" +
                    "blurred='" + blurred + '\'' +
                    ", detail='" + detail + '\'' +
                    ", feed='" + feed + '\'' +
                    '}';
        }
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isCollected() {
        return collected;
    }

    public void setCollected(boolean collected) {
        this.collected = collected;
    }

    public Consumption getConsumption() {
        return consumption;
    }

    public void setConsumption(Consumption consumption) {
        this.consumption = consumption;
    }

    public VideoModel1Cover getCover() {
        return cover;
    }

    public void setCover(VideoModel1Cover cover) {
        this.cover = cover;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionEditor() {
        return descriptionEditor;
    }

    public void setDescriptionEditor(String descriptionEditor) {
        this.descriptionEditor = descriptionEditor;
    }

    public String getDescriptionPgc() {
        return descriptionPgc;
    }

    public void setDescriptionPgc(String descriptionPgc) {
        this.descriptionPgc = descriptionPgc;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public int[] getLabelList() {
        return labelList;
    }

    public void setLabelList(int[] labelList) {
        this.labelList = labelList;
    }

    public String getLibrary() {
        return library;
    }

    public void setLibrary(String library) {
        this.library = library;
    }

    public List<PlayInfo> getPlayInfo() {
        return playInfo;
    }

    public void setPlayInfo(List<PlayInfo> playInfo) {
        this.playInfo = playInfo;
    }

    public String getPlayUrl() {
        return playUrl;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    public boolean isPlayed() {
        return played;
    }

    public void setPlayed(boolean played) {
        this.played = played;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public long getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(long releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String[] getSubtitles() {
        return subtitles;
    }

    public void setSubtitles(String[] subtitles) {
        this.subtitles = subtitles;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitlePgc() {
        return titlePgc;
    }

    public void setTitlePgc(String titlePgc) {
        this.titlePgc = titlePgc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public WebUrl getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(WebUrl webUrl) {
        this.webUrl = webUrl;
    }

    @Override
    public String toString() {
        return "VideoModel3{" +
                "author=" + author +
                ", category='" + category + '\'' +
                ", collected=" + collected +
                ", consumption=" + consumption +
                ", cover=" + cover +
                ", dataType='" + dataType + '\'' +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", descriptionEditor='" + descriptionEditor + '\'' +
                ", descriptionPgc='" + descriptionPgc + '\'' +
                ", duration=" + duration +
                ", id=" + id +
                ", idx=" + idx +
                ", labelList=" + Arrays.toString(labelList) +
                ", library='" + library + '\'' +
                ", playInfo=" + playInfo +
                ", playUrl='" + playUrl + '\'' +
                ", played=" + played +
                ", provider=" + provider +
                ", releaseTime=" + releaseTime +
                ", remark='" + remark + '\'' +
                ", slogan='" + slogan + '\'' +
                ", subtitles=" + Arrays.toString(subtitles) +
                ", tags=" + Arrays.toString(tags) +
                ", title='" + title + '\'' +
                ", titlePgc='" + titlePgc + '\'' +
                ", type='" + type + '\'' +
                ", webUrl=" + webUrl +
                '}';
    }
}
