package com.example.nc_basic_ui.controller;

import android.content.Context;
import android.util.Log;

import com.example.nc_basic_ui.R;
import com.example.nc_super_abs.controller.BaseController;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/5/24 19:49
 */
public class CatFoundController extends BaseController {

    private Context mContext;

    public CatFoundController(Context mContext) {

        this.mContext = mContext;
    }

    /**
     * 获取tab列表
     *
     * @return
     */
    public String[] getTitles() {

        return new String[]{mContext.getResources().getString(R.string.fragment_tab_recommend)
                , mContext.getResources().getString(R.string.fragment_tab_chat)
                , mContext.getResources().getString(R.string.fragment_tab_owner)
                , mContext.getResources().getString(R.string.fragment_tab_hottop)};
    }
}
