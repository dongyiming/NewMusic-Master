package com.example.nc_basic_ui.controller;

import android.content.Context;

import com.example.nc_basic_biz.core.MenuInfoManager;
import com.example.nc_super_abs.interaction.ICommonInvokeResult;
import com.example.uc_common_bean.vo.MenuInfo;

import java.util.List;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/7/27 4:12
 */
public class MenuListController {

    private Context mContext;
    private final MenuInfoManager menuInfoManager;

    public MenuListController(Context mContext) {

        menuInfoManager = new MenuInfoManager();
    }

    public void selectByType(int type, int startIndex, int pageCount, final ICommonInvokeResult<List<MenuInfo>, String> commonInvokeResult) {

        //从服务器获取数据
        //menuInfoManager.selectByType(type, startIndex, pageCount, commonInvokeResult);

        //从本地获取数据
        menuInfoManager.selectPageMenuInfo(type, startIndex, pageCount, commonInvokeResult);
    }

}
