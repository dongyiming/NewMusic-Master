package com.example.nc_basic_ui.controller;

import android.content.Context;

import com.example.nc_basic_biz.core.NewsManager;
import com.example.nc_super_abs.controller.BaseController;
import com.example.nc_super_abs.interaction.ICommonInvokeResult;
import com.example.uc_common_bean.vo.news.NewsInfo;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/8/15 2:09
 */
public class NewsInfoController extends BaseController {

    private Context mContext;
    private final NewsManager newsManager;

    public NewsInfoController(Context mContext) {

        this.mContext = mContext;
        newsManager = new NewsManager();
    }

    /**
     * detail news
     * @param id
     * @param commonInvokeResult
     */
    public void getInfos(String id, final ICommonInvokeResult<NewsInfo, String> commonInvokeResult) {
        newsManager.getNewsInfo(id, commonInvokeResult);
    }
}
