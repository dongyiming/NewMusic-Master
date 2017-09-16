package com.example.nc_basic_biz.http;

import com.example.nc_basic_biz.http.builder.RetrofitBuilder;
import com.example.nc_basic_biz.fun.EyepertozerFunction;
import com.example.nc_basic_biz.service.EyepetozerService;
import com.example.uc_common_bean.eyepetizer.hot.HotPageBean;
import com.example.uc_common_bean.vo.HotItemInfo;

import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.ResourceObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * @version : 1.0
 * @Description : 开眼APP网络请求
 * @autho : dongyiming
 * @data : 2017/7/31 23:18
 */
public class EyepetozerHttpInvoker implements IEyepetozerHttpInvoker {

    private final EyepetozerService eyepetozerService;

    public EyepetozerHttpInvoker() {

        Retrofit retrofit = RetrofitBuilder.getInstance().getEyeRetrofit(BASE_URL);
        eyepetozerService = retrofit.create(EyepetozerService.class);
    }

    @Override
    public void selectTabs(ResourceObserver<HotPageBean> subscriber) {

        eyepetozerService.selectTabs()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    @Override
    public void discoveryHot(ResourceObserver<HashMap<String, List<HotItemInfo>>> subscriber) {

        eyepetozerService.discoveryHot()
                .subscribeOn(Schedulers.io())
                .map(new EyepertozerFunction())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    @Override
    public void discoveryHot(int startIndx, int count, ResourceObserver<HashMap<String, List<HotItemInfo>>> subscriber) {

        eyepetozerService.discoveryHot(startIndx,count).subscribeOn(Schedulers.io())
                .map(new EyepertozerFunction())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
