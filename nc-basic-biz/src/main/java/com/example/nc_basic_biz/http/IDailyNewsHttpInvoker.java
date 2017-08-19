package com.example.nc_basic_biz.http;

import com.example.uc_common_bean.vo.news.DailyNews;
import com.example.uc_common_bean.vo.news.LatestNews;
import com.example.uc_common_bean.vo.news.NewsInfo;

import io.reactivex.observers.ResourceObserver;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/8/5 8:57
 */
public interface IDailyNewsHttpInvoker {

    public static final String BASE_URL = "https://news-at.zhihu.com/api/4/news/";

    void getLatestNews(ResourceObserver<LatestNews> subscriber);

    void getDailyNews(String dayTime, ResourceObserver<DailyNews> subscriber);

    void getNewsInfo(String id, ResourceObserver<NewsInfo> subscriber);
}
