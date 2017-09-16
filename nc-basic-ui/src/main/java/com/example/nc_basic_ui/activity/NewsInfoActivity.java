package com.example.nc_basic_ui.activity;

import android.content.Context;
import android.util.Log;

import com.example.nc_common_resource.utils.IntentUtils;
import com.example.nc_basic_ui.controller.NewsInfoController;
import com.example.nc_common_resource.activity.BaseWebViewAcitivity;
import com.example.nc_super_abs.interaction.ICommonInvokeResult;
import com.example.uc_common_bean.vo.news.NewsInfo;

/**
 * @version : 1.0
 * @Description : 每日新闻详情
 * @autho : dongyiming
 * @data : 2017/8/15 0:11
 */
public class NewsInfoActivity extends BaseWebViewAcitivity {

    private String id;
    private NewsInfoController newsInfoController;
    /*@Override
    public Toolbar createToolBar() {

        commonToolBar = new CommonToolBar(mContext);
        commonToolBar.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                , (int) mContext.getResources().getDimension(R.dimen.dimens_webview_top_height)));
        commonToolBar.setLeftButtonIcon(mContext.getDrawable(R.drawable.img_back));
        commonToolBar.setRightButtonIcon(mContext.getDrawable(R.drawable.img_share));
        commonToolBar.setSubTitleName("加载中...");
        return commonToolBar;
    }*/

    @Override
    public void initValue() {

        if (getIntent() != null) {
            id = getIntent().getStringExtra("id");
        }
        newsInfoController = new NewsInfoController(mContext);
    }

    @Override
    public boolean isLoadHttpUrl() {
        return true;
    }

    @Override
    public void loadHttpUrlResult() {

        newsInfoController.getInfos(id, new ICommonInvokeResult<NewsInfo, String>() {
            @Override
            public void onResult(final NewsInfo newsInfo) {

                //commonToolBar.setSubTitleName(newsInfo.getTitle());
                if (newsInfo != null) {
                    Log.e("dongyiming", "loadurl_success");
                    loadUrlSuccessed(newsInfo.getShare_url());
                } else {
                    Log.e("dongyiming", "loadurl_error");
                    loadUrlFailed();
                }
                //return newsInfo.getShare_url();
            }

            @Override
            public void onFailure(String errorMsg) {

                loadUrlFailed();
            }

            @Override
            public void onCompleted() {

            }
        });
    }

    public static void intentActivity(Context mContext, String key, String value) {

        IntentUtils.startActivity(mContext, NewsInfoActivity.class, key, value);
    }

    @Override
    public String loadFinished() {
        return null;
    }

    @Override
    public String loadWebUrl() {

        return null;
    }
}
