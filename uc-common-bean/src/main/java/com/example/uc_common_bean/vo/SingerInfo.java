package com.example.uc_common_bean.vo;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class SingerInfo {
    @Id(autoincrement = true)
    private Long id;

    private Integer singerCode;

    private String singerName;

    private String picurl;

    private String createTime;

    private String updateTime;

    public String getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPicurl() {
        return this.picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public String getSingerName() {
        return this.singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public Integer getSingerCode() {
        return this.singerCode;
    }

    public void setSingerCode(Integer singerCode) {
        this.singerCode = singerCode;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Generated(hash = 1410013076)
    public SingerInfo(Long id, Integer singerCode, String singerName,
            String picurl, String createTime, String updateTime) {
        this.id = id;
        this.singerCode = singerCode;
        this.singerName = singerName;
        this.picurl = picurl;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    @Generated(hash = 1073277869)
    public SingerInfo() {
    }

}