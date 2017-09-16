package com.example.nc_basic_biz.http;

import com.example.nc_basic_biz.http.builder.RetrofitBuilder;
import com.example.nc_basic_biz.service.DailyNewsService;
import com.example.uc_common_bean.vo.news.DailyNews;
import com.example.uc_common_bean.vo.news.LatestNews;
import com.example.uc_common_bean.vo.news.NewsInfo;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.ResourceObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/8/5 8:50
 */
public class DailyNewsHttpInvoker implements IDailyNewsHttpInvoker {

    private final Retrofit newsRetrofit;
    private final DailyNewsService dailyNewsService;

    public DailyNewsHttpInvoker() {

        newsRetrofit = RetrofitBuilder.getInstance().getNewsRetrofit(BASE_URL);
        dailyNewsService = newsRetrofit.create(DailyNewsService.class);
    }

    @Override
    public void getLatestNews(ResourceObserver<LatestNews> subscriber) {

        dailyNewsService.getLatestNews()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    @Override
    public void getDailyNews(String daytime, ResourceObserver<DailyNews> subscriber) {

        dailyNewsService.getDailyNews(daytime)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    @Override
    public void getNewsInfo(String id, ResourceObserver<NewsInfo> subscriber) {

        dailyNewsService.getNewsInfo(id)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
