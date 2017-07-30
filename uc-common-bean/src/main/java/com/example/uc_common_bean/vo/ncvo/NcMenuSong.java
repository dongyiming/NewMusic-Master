package com.example.uc_common_bean.vo.ncvo;

public class NcMenuSong {
    private Integer id;

    private Integer songCode;

    private Integer menuCode;

    private String createTime;

    private String upStringTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSongCode() {
        return songCode;
    }

    public void setSongCode(Integer songCode) {
        this.songCode = songCode;
    }

    public Integer getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(Integer menuCode) {
        this.menuCode = menuCode;
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