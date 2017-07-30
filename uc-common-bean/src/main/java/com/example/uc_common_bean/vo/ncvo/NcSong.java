package com.example.uc_common_bean.vo.ncvo;

public class NcSong {
    private Integer id;

    private String songName;

    private Integer songId;

    private String songDesc;

    private String cover;

    private Integer menuCode;

    private String menuName;

    private Integer singerCode;

    private String singerName;

    private String createTime;

    private String upStringTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName == null ? null : songName.trim();
    }

    public Integer getSongId() {
        return songId;
    }

    public void setSongId(Integer songId) {
        this.songId = songId;
    }

    public String getSongDesc() {
        return songDesc;
    }

    public void setSongDesc(String songDesc) {
        this.songDesc = songDesc == null ? null : songDesc.trim();
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover == null ? null : cover.trim();
    }

    public Integer getmenuCode() {
        return menuCode;
    }

    public void setmenuCode(Integer menuCode) {
        this.menuCode = menuCode;
    }

    public String getmenuName() {
        return menuName;
    }

    public void setmenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
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