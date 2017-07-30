package com.example.nc_basic_biz.http;

import com.example.uc_common_bean.vo.MenuInfo;

import org.reactivestreams.Subscriber;

import java.util.List;

import io.reactivex.observers.ResourceObserver;


/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/7/14 15:13
 */
public interface IMenuInfoHttpInvoker {

    void getAll(ResourceObserver<List<MenuInfo>> subscriber);

    void getRecInfos(ResourceObserver<List<MenuInfo>> subscriber);

    void getMenuByType(int type, int startIndex, int pageCount, ResourceObserver<List<MenuInfo>> subscriber);
}
