package com.example.uc_common_bean.vo.ncvo;

public class NcSinger {
    private Integer id;

    private Integer singerCode;

    private String singerName;

    private String picurl;

    private String createTime;

    private String upStringTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSingerCode() {
        return singerCode;
    }

    public void setSingerCode(Integer singerCode) {
        this.singerCode = singerCode;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName == null ? null : singerName.trim();
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl == null ? null : picurl.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpStringTime() {
        return upStringTime;
    }

    public void setUpStringTime(String upStringTime) {
        this.upStringTime = upStringTime;
    }
}