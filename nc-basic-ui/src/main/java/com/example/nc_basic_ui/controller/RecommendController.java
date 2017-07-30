package com.example.nc_basic_ui.controller;

import android.content.Context;

import com.example.nc_basic_biz.core.MenuInfoManager;
import com.example.nc_super_abs.controller.BaseController;
import com.example.nc_super_abs.interaction.ICommonInvokeResult;
import com.example.uc_common_bean.vo.MenuInfo;

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

}
