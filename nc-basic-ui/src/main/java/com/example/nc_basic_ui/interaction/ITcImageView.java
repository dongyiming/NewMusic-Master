package com.example.nc_basic_ui.interaction;

import com.example.uc_common_bean.vo.tc.TuChong;

import java.util.List;

/**
 *  @Description :
 *  @autho : dongyiming
 *  @version : 1.0
 *  @data : 2017/8/31 2:10
 */
public interface ITcImageView {

    public void onRefresh(List<TuChong.FeedListBean> feedListBeen);

    public void onLoadMore(List<TuChong.FeedListBean> feedListBeen);

    public void onLoadFailed(String errorMsg);
}
