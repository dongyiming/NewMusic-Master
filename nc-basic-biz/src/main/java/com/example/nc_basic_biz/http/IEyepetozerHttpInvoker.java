package com.example.nc_basic_biz.http;

import com.example.uc_common_bean.eyepetizer.hot.HotPageBean;
import com.example.uc_common_bean.vo.HotItemInfo;

import java.util.HashMap;
import java.util.List;

import io.reactivex.observers.ResourceObserver;

/**
 *  @Description :
 *  @autho : dongyiming
 *  @version : 1.0
 *  @data : 2017/7/31 23:20
 */
public interface IEyepetozerHttpInvoker {

    public static final String BASE_URL = "http://baobab.kaiyanapp.com/api/v4/";


    void selectTabs(ResourceObserver<HotPageBean> subscriber);

    void discoveryHot(ResourceObserver<HashMap<String, List<HotItemInfo>>> subscriber);

    void discoveryHot(int startIndx, int count, ResourceObserver<HashMap<String, List<HotItemInfo>>> subscriber);
}
