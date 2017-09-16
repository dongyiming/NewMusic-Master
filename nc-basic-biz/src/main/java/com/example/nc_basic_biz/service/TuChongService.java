package com.example.nc_basic_biz.service;


import com.example.uc_common_bean.vo.tc.TuChong;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/8/31 1:42
 */
public interface TuChongService {

    /**
     * @param page : page = 1时刷新type="refresh",加载更多时type="loadmore"
     * @param type
     * @return
     */
    @GET("feed-app")
    public Observable<TuChong> getRecommendRefresh(@Query("page") int page, @Query("type") String type);

    /**
     * @param page    : page = 1时刷新type="refresh",加载更多时type="loadmore"
     * @param post_id : page = 1 时不需要该字段,加载更多时上一页数据的最后一条的json字段post_id附带
     * @param type
     * @return
     */
    @GET("feed-app")
    public Observable<TuChong> getRecommendMore(@Query("page") int page, @Query("post_id") int post_id, @Query("type") String type);


}
