package com.example.nc_basic_biz.repository;

import com.example.uc_common_bean.vo.HotItemInfo;

import java.util.List;

/**
 *  @Description :
 *  @autho : dongyiming
 *  @version : 1.0
 *  @data : 2017/8/3 3:14
 */
public interface IEyepetorzerRepository {
    boolean insertList(List<HotItemInfo> hotItemInfos);

    void update(HotItemInfo hotItemInfo);

    void insert(HotItemInfo hotItemInfo);

    void addOrUpdate(HotItemInfo hotItemInfo);

    HotItemInfo selectByDateId(double dataId);

    List<HotItemInfo> selectByType(String dataType);

    void deleteAll();

    HotItemInfo selectById(int id);
}
