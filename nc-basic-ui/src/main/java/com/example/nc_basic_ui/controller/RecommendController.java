package com.example.nc_basic_ui.controller;

import android.content.Context;
import android.util.Log;

import com.example.nc_basic_biz.core.MenuInfoManager;
import com.example.nc_super_abs.controller.BaseController;
import com.example.nc_super_abs.interaction.ICommonInvokeResult;
import com.example.uc_common_bean.enums.MenuType;
import com.example.uc_common_bean.vo.MenuInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @version : 1.0
 * @Description : 个性推荐界面
 * @autho : dongyiming
 * @data : 2017/6/2 1:30
 */
public class RecommendController extends BaseController {

    private Context mContext;
    private final MenuInfoManager menuInfoManager;

    public RecommendController(Context mContext) {
        this.mContext = mContext;
        menuInfoManager = new MenuInfoManager();
    }

    public void getRecommend(ICommonInvokeResult<List<MenuInfo>, String> commonInvokeResult) {

        menuInfoManager.getRecMenuInfos(commonInvokeResult);
    }

    /**
     * 获取分类数据
     *
     * @return
     */
    public List<MenuInfo> getList() {

        List<MenuInfo> menuInfos = new ArrayList<>();
        for (int i = 2; i <= 7; i++) {
            MenuInfo menuInfo = buildTitleMenuInfo(i);
            menuInfos.add(menuInfo);
            List<MenuInfo> infos = menuInfoManager.selectByType(i);
            if (infos != null && !infos.isEmpty()) {
                menuInfos.addAll(infos);
            }
        }
        return menuInfos;
    }

    public MenuInfo buildTitleMenuInfo(int type) {
        MenuInfo menuInfo = new MenuInfo();
        menuInfo.setMenuName(MenuType.valueOf(type));
        menuInfo.setMenuType(998);
        return menuInfo;
    }
}
