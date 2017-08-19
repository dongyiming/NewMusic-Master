package com.example.nc_basic_biz.service;

import com.example.uc_common_bean.vo.news.DailyNews;
import com.example.uc_common_bean.vo.news.LatestNews;
import com.example.uc_common_bean.vo.news.NewsInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/8/5 8:15
 */
public interface DailyNewsService {

    /**
     * 获得当天的最新消息:https://news-at.zhihu.com/api/4/news/latest
     *
     * @return
     */
    @GET("latest")
    public Observable<LatestNews> getLatestNews();

    /**
     * 获取新闻的具体内容:https://news-at.zhihu.com/api/4/news/news_id
     *
     * @param id
     * @return
     */
    @GET("{news_id}")
    public Observable<NewsInfo> getNewsInfo(@Path("news_id") String id);

    /**
     * 获取daytime之前一天的新闻:https://news-at.zhihu.com/api/4/news/before/news_id
     *
     * @param daytime
     * @return
     */
    @GET("before/{daytime}")
    public Observable<DailyNews> getDailyNews(@Path("daytime") String daytime);
}
