package com.example.uc_common_bean.vo;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class UserInfo {
    @Id(autoincrement = true)
    private Long id;

    private String username;

    private Integer usercode;

    private String password;

    private String desc;

    private String picurl;

    private String update;

    private String create;

    private Integer level;

    public Integer getLevel() {
        return this.level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getCreate() {
        return this.create;
    }

    public void setCreate(String create) {
        this.create = create;
    }

    public String getUpdate() {
        return this.update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public String getPicurl() {
        return this.picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUsercode() {
        return this.usercode;
    }

    public void setUsercode(Integer usercode) {
        this.usercode = usercode;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Generated(hash = 568375790)
    public UserInfo(Long id, String username, Integer usercode, String password,
            String desc, String picurl, String update, String create, Integer level) {
        this.id = id;
        this.username = username;
        this.usercode = usercode;
        this.password = password;
        this.desc = desc;
        this.picurl = picurl;
        this.update = update;
        this.create = create;
        this.level = level;
    }

    @Generated(hash = 1279772520)
    public UserInfo() {
    }

}