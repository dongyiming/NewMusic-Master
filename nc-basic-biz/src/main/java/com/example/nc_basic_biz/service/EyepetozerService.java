package com.example.nc_basic_biz.service;

import com.example.uc_common_bean.eyepetizer.hot.HotPageBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @version : 1.0
 * @Description : 开眼APP所有网络接口
 * @autho : dongyiming
 * @data : 2017/7/31 23:22
 */
public interface EyepetozerService {

    /**
     * 获取首页所有数据
     *
     * @return
     */
    @GET("tabs/selected")
    public Observable<HotPageBean> selectTabs();

    /**
     * 获取热门分类数据: http://baobab.kaiyanapp.com/api/v4/discovery/hot
     *
     * @return
     */
    @GET("discovery/hot")
    public Observable<HotPageBean> discoveryHot();

    /**
     * 获取分页数据
     *
     * @param startIndex
     * @param count
     * @return
     */
    @GET("discovery/hot")
    public Observable<HotPageBean> discoveryHot(@Query("start") int startIndex, @Query("num") int count);
}
