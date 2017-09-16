package com.example.nc_basic_ui.controller;

import android.content.Context;
import android.util.Log;

import com.example.nc_basic_biz.core.TcImageManager;
import com.example.nc_basic_ui.interaction.ITcImageView;
import com.example.nc_super_abs.controller.BaseController;
import com.example.nc_super_abs.interaction.ICommonInvokeResult;
import com.example.uc_common_bean.vo.tc.TuChong;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/8/31 1:57
 */
public class TcImageController extends BaseController{

    private Context mContext;
    private final TcImageManager tcImageManager;
    private WeakReference<ITcImageView> tcImageView;
    private int post_id;

    public TcImageController(Context mContext, ITcImageView iTcImageView) {

        this.mContext = mContext;
        this.tcImageView = new WeakReference<ITcImageView>(iTcImageView);
        tcImageManager = new TcImageManager(mContext);
    }

    public void onInitFinished() {

        //加载首页数据
        getRefreshRecommendList();
    }

    public void getRefreshRecommendList() {

        int page = 1;
        String type = "refresh";
        tcImageManager.getRecommendRefresh(page, type, new ICommonInvokeResult<TuChong, String>() {
            @Override
            public void onResult(TuChong tuChong) {

                List<TuChong.FeedListBean> feedList = tuChong.getFeedList();
                if (feedList != null && !feedList.isEmpty()) {
                    post_id = feedList.get(feedList.size() - 1).getPost_id();
                    tcImageView.get().onRefresh(feedList);
                } else {
                    logger.warn("successed to getRecommendRefresh datas but the list is null");
                }
            }

            @Override
            public void onFailure(String errorMsg) {

                tcImageView.get().onLoadFailed(errorMsg);
            }

            @Override
            public void onCompleted() {

            }
        });
    }

    public void getMoreRecommendList(int page) {

        Log.e("dongyiming", "post_id = " + post_id + "   ___ page = " + page);
        tcImageManager.getRecommendMore(page, post_id, "loadmore", new ICommonInvokeResult<TuChong, String>() {
            @Override
            public void onResult(TuChong tuChong) {

                List<TuChong.FeedListBean> feedList = tuChong.getFeedList();
                if (feedList != null && !feedList.isEmpty()) {
                    post_id = feedList.get(feedList.size() - 1).getPost_id();
                    tcImageView.get().onLoadMore(feedList);
                } else {
                    logger.warn("successed to getRecommendRefresh datas but the list is null");
                }
            }

            @Override
            public void onFailure(String errorMsg) {

                tcImageView.get().onLoadFailed(errorMsg);
            }

            @Override
            public void onCompleted() {
            }
        });
    }
}
