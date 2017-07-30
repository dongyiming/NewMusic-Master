package com.example.uc_common_bean.vo;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;


@Entity
public class MenuSong {
    @Id(autoincrement = true)
    private Long id;

    private Integer songCode;

    private Integer menuCode;

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

    public Integer getMenuCode() {
        return this.menuCode;
    }

    public void setMenuCode(Integer menuCode) {
        this.menuCode = menuCode;
    }

    public Integer getSongCode() {
        return this.songCode;
    }

    public void setSongCode(Integer songCode) {
        this.songCode = songCode;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Generated(hash = 689809084)
    public MenuSong(Long id, Integer songCode, Integer menuCode, String createTime,
            String updateTime) {
        this.id = id;
        this.songCode = songCode;
        this.menuCode = menuCode;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    @Generated(hash = 1847241711)
    public MenuSong() {
    }

}