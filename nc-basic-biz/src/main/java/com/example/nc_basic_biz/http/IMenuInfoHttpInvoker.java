package com.example.nc_basic_biz.http;

import com.example.uc_common_bean.vo.MenuInfo;

import java.util.List;

import io.reactivex.observers.ResourceObserver;


/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/7/14 15:13
 */
public interface IMenuInfoHttpInvoker {

    //本地服务器路径，简单的SSM框架
    public static final String BASE_URL = "http://192.168.1.102:8080/menu/";

    void getAll(ResourceObserver<List<MenuInfo>> subscriber);

    void getRecInfos(ResourceObserver<List<MenuInfo>> subscriber);

    void getMenuByType(int type, int startIndex, int pageCount, ResourceObserver<List<MenuInfo>> subscriber);
}
