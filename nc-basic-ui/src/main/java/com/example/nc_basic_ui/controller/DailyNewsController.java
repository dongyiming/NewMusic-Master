package com.example.nc_basic_ui.controller;

import android.content.Context;

import com.example.nc_basic_biz.core.NewsManager;
import com.example.nc_super_abs.interaction.ICommonInvokeResult;
import com.example.uc_common_bean.vo.news.DailyNews;
import com.example.uc_common_bean.vo.news.LatestNews;
import com.example.uc_common_bean.vo.news.NewsInfo;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/8/5 22:32
 */
public class DailyNewsController {

    private Context mContext;
    private final NewsManager newsManager;

    public DailyNewsController(Context mContext) {
        this.mContext = mContext;
        newsManager = new NewsManager();
    }

    public void getLatestNews(ICommonInvokeResult<LatestNews, String> commonInvokeResult) {

        newsManager.getLatestNews(commonInvokeResult);
    }

    public void getDailyNews(String dayTime, ICommonInvokeResult<DailyNews, String> commonInvokeResult) {

        newsManager.getDailyNews(dayTime, commonInvokeResult);
    }

    public void getNewsInfo(String id, ICommonInvokeResult<NewsInfo, String> commonInvokeResult) {

        newsManager.getNewsInfo(id, commonInvokeResult);
    }
}
