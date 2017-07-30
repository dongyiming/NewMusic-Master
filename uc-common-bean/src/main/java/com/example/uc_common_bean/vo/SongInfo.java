package com.example.uc_common_bean.vo;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class SongInfo {
    @Id(autoincrement = true)
    private Long id;

    private String songName;

    private Integer songId;

    private String songDesc;

    private String cover;

    private Integer menuCode;

    private String menuName;

    private Integer singerCode;

    private String singerName;

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

    public String getMenuName() {
        return this.menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Integer getMenuCode() {
        return this.menuCode;
    }

    public void setMenuCode(Integer menuCode) {
        this.menuCode = menuCode;
    }

    public String getCover() {
        return this.cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getSongDesc() {
        return this.songDesc;
    }

    public void setSongDesc(String songDesc) {
        this.songDesc = songDesc;
    }

    public Integer getSongId() {
        return this.songId;
    }

    public void setSongId(Integer songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return this.songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Generated(hash = 1031596931)
    public SongInfo(Long id, String songName, Integer songId, String songDesc,
            String cover, Integer menuCode, String menuName, Integer singerCode,
            String singerName, String createTime, String updateTime) {
        this.id = id;
        this.songName = songName;
        this.songId = songId;
        this.songDesc = songDesc;
        this.cover = cover;
        this.menuCode = menuCode;
        this.menuName = menuName;
        this.singerCode = singerCode;
        this.singerName = singerName;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    @Generated(hash = 1061935912)
    public SongInfo() {
    }

}