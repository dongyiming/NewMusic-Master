package com.example.nc_basic_biz.http;


import com.example.uc_common_bean.vo.tc.TuChong;

import io.reactivex.observers.ResourceObserver;

/**
 *  @Description :
 *  @autho : dongyiming
 *  @version : 1.0
 *  @data : 2017/8/31 1:52
 */
public interface ITuChongInvoke {

    public String BASE_URL = "https://api.tuchong.com/";

    void getRecommendRefresh(ResourceObserver<TuChong> subscriber, int page, String type);

    void getRecommendMore(ResourceObserver<TuChong> subscriber, int page, int post_id, String type);
}
