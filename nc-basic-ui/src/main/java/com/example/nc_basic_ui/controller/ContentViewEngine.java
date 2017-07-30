package com.example.nc_basic_ui.controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.nc_basic_biz.core.MenuInfoManager;
import com.example.nc_basic_ui.adapter.content.LatestMucAdapter;
import com.example.nc_basic_ui.adapter.content.PerfectMenuAdapter;
import com.example.nc_basic_ui.adapter.content.RadioFmAdapter;
import com.example.nc_basic_ui.adapter.content.RecMenuAdapter;
import com.example.nc_basic_ui.adapter.content.RecMvAdapter;
import com.example.nc_basic_ui.adapter.content.UniqueAdapter;
import com.example.uc_common_bean.enums.MenuType;
import com.example.uc_common_bean.vo.MenuInfo;

import java.util.List;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/7/20 10:16
 */
public class ContentViewEngine {

    private final MenuInfoManager menuInfoManager;
    private Context mContext;

    public ContentViewEngine(Context mContext) {

        this.mContext = mContext;
        menuInfoManager = new MenuInfoManager();
    }

    public RecyclerView buildRecMenu() {

        List<MenuInfo> menuInfos = menuInfoManager.selectByType(MenuType.REC_MUC.getValue());
        if (menuInfos != null && menuInfos.size() != 0) {
            RecMenuAdapter recMenuAdapter = new RecMenuAdapter(mContext, menuInfos);
            for (MenuInfo menuInfo : menuInfos) {
                Log.e("dongyiming", "name = " + menuInfo.getMenuName());
            }
            return recMenuAdapter.build();
        }
        return null;
    }

    public RecyclerView buildUnp() {

        List<MenuInfo> menuInfos = menuInfoManager.selectByType(MenuType.UNP_MUC.getValue());
        if (menuInfos != null && menuInfos.size() != 0) {

            for (MenuInfo menuInfo : menuInfos) {
                Log.e("dongyiming", "name = " + menuInfo.getMenuName());
            }
            UniqueAdapter recMenuAdapter = new UniqueAdapter(mContext, menuInfos);
            return recMenuAdapter.build();
        }
        return null;
    }

    public RecyclerView buildLat() {

        List<MenuInfo> menuInfos = menuInfoManager.selectByType(MenuType.LAT_MUC.getValue());
        if (menuInfos != null && menuInfos.size() != 0) {

            for (MenuInfo menuInfo : menuInfos) {
                Log.e("dongyiming", "name = " + menuInfo.getMenuName());
            }
            LatestMucAdapter recMenuAdapter = new LatestMucAdapter(mContext, menuInfos);
            return recMenuAdapter.build();
        }
        return null;
    }

    public RecyclerView buildRecMv() {

        List<MenuInfo> menuInfos = menuInfoManager.selectByType(MenuType.REC_MV.getValue());
        if (menuInfos != null && menuInfos.size() != 0) {

            for (MenuInfo menuInfo : menuInfos) {
                Log.e("dongyiming", "name = " + menuInfo.getMenuName());
            }
            RecMvAdapter recMenuAdapter = new RecMvAdapter(mContext, menuInfos);
            return recMenuAdapter.build();
        }
        return null;
    }

    public RecyclerView buildPerf() {

        List<MenuInfo> menuInfos = menuInfoManager.selectByType(MenuType.PERF_MUC.getValue());
        if (menuInfos != null && menuInfos.size() != 0) {

            for (MenuInfo menuInfo : menuInfos) {
                Log.e("dongyiming", "name = " + menuInfo.getMenuName());
            }
            PerfectMenuAdapter recMenuAdapter = new PerfectMenuAdapter(mContext, menuInfos);
            return recMenuAdapter.build();
        }
        return null;
    }

    public RecyclerView buildRad() {

        List<MenuInfo> menuInfos = menuInfoManager.selectByType(MenuType.RAD_FM.getValue());
        if (menuInfos != null && menuInfos.size() != 0) {

            for (MenuInfo menuInfo : menuInfos) {
                Log.e("dongyiming", "name = " + menuInfo.getMenuName());
            }
            RadioFmAdapter recMenuAdapter = new RadioFmAdapter(mContext, menuInfos);
            return recMenuAdapter.build();
        }
        return null;
    }


}
