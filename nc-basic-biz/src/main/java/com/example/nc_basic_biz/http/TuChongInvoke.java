package com.example.nc_basic_biz.http;


import com.example.nc_basic_biz.http.builder.RetrofitBuilder;
import com.example.nc_basic_biz.service.TuChongService;
import com.example.uc_common_bean.vo.tc.TuChong;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.ResourceObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * @version : 1.0
 * @Description : 歌单的所有网络操作
 * @autho : dongyiming
 * @data : 2017/7/14 15:03
 */
public class TuChongInvoke implements ITuChongInvoke {

    private final TuChongService tuChongService;


    public TuChongInvoke() {

        Retrofit retrofit = RetrofitBuilder.getInstance().getTcRetrofit(BASE_URL);
        tuChongService = retrofit.create(TuChongService.class);
    }

    /**
     * 获取第一页歌单数据
     *
     * @param subscriber
     */
    @Override
    public void getRecommendRefresh(ResourceObserver<TuChong> subscriber, int page, String type) {

        tuChongService.getRecommendRefresh(page, type)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 获取加载更多数据
     *
     * @param subscriber
     * @param page
     * @param post_id
     * @param type
     */
    @Override
    public void getRecommendMore(ResourceObserver<TuChong> subscriber, int page, int post_id, String type) {

        tuChongService.getRecommendMore(page, post_id, type)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

}
