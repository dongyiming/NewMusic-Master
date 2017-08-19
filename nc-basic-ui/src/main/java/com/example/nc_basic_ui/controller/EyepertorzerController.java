package com.example.nc_basic_ui.controller;

import android.content.Context;

import com.example.nc_basic_biz.core.EyepetizerHomePageManager;
import com.example.uc_common_bean.vo.HotItemInfo;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/8/5 1:41
 */
public class EyepertorzerController {

    private Context mContext;
    private final EyepetizerHomePageManager manager;

    public EyepertorzerController(Context mContext) {
        this.mContext = mContext;
        manager = new EyepetizerHomePageManager();
    }

    public HotItemInfo getHotItemInfoById(String id) {

        return manager.selectById(Integer.valueOf(id));
    }

    public HotItemInfo selectByDateId(String dateId) {

        return manager.selectByDateId(Double.valueOf(dateId));
    }

}
