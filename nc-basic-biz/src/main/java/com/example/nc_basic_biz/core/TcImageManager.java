package com.example.nc_basic_biz.core;

import android.content.Context;

import com.example.nc_basic_biz.http.ITuChongInvoke;
import com.example.nc_basic_biz.http.TuChongInvoke;
import com.example.nc_super_abs.interaction.ICommonInvokeResult;
import com.example.uc_common_bean.vo.tc.TuChong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.ResourceObserver;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/8/31 1:50
 */
public class TcImageManager {

    protected Logger logger = LoggerFactory.getLogger(TcImageManager.class);
    private Context mContext;
    private final ITuChongInvoke tuChongInvoke;

    public TcImageManager(Context mContext) {
        this.mContext = mContext;
        tuChongInvoke = new TuChongInvoke();
    }

    /**
     * 获取更多
     *
     * @param page
     * @param post_id
     * @param type
     * @param commonInvokeResult
     */
    public void getRecommendMore(int page, int post_id, String type, final ICommonInvokeResult<TuChong, String> commonInvokeResult) {


        ResourceObserver<TuChong> subscriber = new ResourceObserver<TuChong>() {
            @Override
            public void onNext(@NonNull TuChong tuChong) {

                if (tuChong.getResult().equals("SUCCESS")) {
                    commonInvokeResult.onResult(tuChong);
                } else {
                    logger.error("tuchong invoke failed to getRecommendMore");
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {

                logger.error("failed to invoke by getRecommendMore .{}", e.toString());
                commonInvokeResult.onFailure(e.toString());
            }

            @Override
            public void onComplete() {

                commonInvokeResult.onCompleted();
            }
        };
        tuChongInvoke.getRecommendMore(subscriber, page, post_id, type);
    }

    /**
     * 获取首页和刷新数据
     *
     * @param page
     * @param type
     * @param commonInvokeResult
     */
    public void getRecommendRefresh(int page, String type, final ICommonInvokeResult<TuChong, String> commonInvokeResult) {


        ResourceObserver<TuChong> subscriber = new ResourceObserver<TuChong>() {
            @Override
            public void onNext(@NonNull TuChong tuChong) {

                if (tuChong.getResult().equals("SUCCESS")) {
                    commonInvokeResult.onResult(tuChong);
                } else {
                    logger.error("tuchong invoke failed to getRecommendRefresh");
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {

                logger.error("failed to invoke by getRecommendRefresh .{}", e.toString());
                commonInvokeResult.onFailure(e.toString());
            }

            @Override
            public void onComplete() {

                commonInvokeResult.onCompleted();
            }
        };
        tuChongInvoke.getRecommendRefresh(subscriber, page, type);
    }
}
