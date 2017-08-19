package com.example.nc_basic_biz.core;

import com.example.nc_basic_biz.http.DailyNewsHttpInvoker;
import com.example.nc_basic_biz.http.IDailyNewsHttpInvoker;
import com.example.nc_super_abs.interaction.ICommonInvokeResult;
import com.example.uc_common_bean.vo.news.DailyNews;
import com.example.uc_common_bean.vo.news.LatestNews;
import com.example.uc_common_bean.vo.news.NewsInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.ResourceObserver;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/8/5 8:59
 */
public class NewsManager {

    private Logger logger = LoggerFactory.getLogger(NewsManager.class);
    private final IDailyNewsHttpInvoker dailyNewsHttpInvoker;

    public NewsManager() {

        dailyNewsHttpInvoker = new DailyNewsHttpInvoker();
    }

    /**
     * 最新数据
     *
     * @param commonInvokeResult
     */
    public void getLatestNews(final ICommonInvokeResult<LatestNews, String> commonInvokeResult) {

        ResourceObserver<LatestNews> subscriber = new ResourceObserver<LatestNews>() {
            @Override
            public void onNext(@NonNull LatestNews latestNews) {
                commonInvokeResult.onResult(latestNews);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                logger.error("http failed to get : getLatestNews .{}", e.toString());
                commonInvokeResult.onFailure(e.toString());
            }

            @Override
            public void onComplete() {
                commonInvokeResult.onCompleted();
            }
        };

        dailyNewsHttpInvoker.getLatestNews(subscriber);
    }

    /**
     * 每日数据
     *
     * @param dayTime
     * @param commonInvokeResult
     */
    public void getDailyNews(String dayTime, final ICommonInvokeResult<DailyNews, String> commonInvokeResult) {

        ResourceObserver<DailyNews> subscriber = new ResourceObserver<DailyNews>() {
            @Override
            public void onNext(@NonNull DailyNews dailyNews) {
                commonInvokeResult.onResult(dailyNews);
            }

            @Override
            public void onError(@NonNull Throwable e) {

                logger.error("http failed to get : getDailyNews .{}", e.toString());
                commonInvokeResult.onFailure(e.toString());
            }

            @Override
            public void onComplete() {
                commonInvokeResult.onCompleted();
            }
        };
        dailyNewsHttpInvoker.getDailyNews(dayTime, subscriber);
    }

    /**
     * 具体内容
     *
     * @param id
     * @param commonInvokeResult
     */
    public void getNewsInfo(String id, final ICommonInvokeResult<NewsInfo, String> commonInvokeResult) {

        ResourceObserver<NewsInfo> subscriber = new ResourceObserver<NewsInfo>() {
            @Override
            public void onNext(@NonNull NewsInfo newsInfo) {
                commonInvokeResult.onResult(newsInfo);
            }

            @Override
            public void onError(@NonNull Throwable e) {

                logger.error("http failed to get : getNewsInfo .{}", e.toString());
                commonInvokeResult.onFailure(e.toString());
            }

            @Override
            public void onComplete() {
                commonInvokeResult.onCompleted();
            }
        };
        dailyNewsHttpInvoker.getNewsInfo(id, subscriber);
    }
}
