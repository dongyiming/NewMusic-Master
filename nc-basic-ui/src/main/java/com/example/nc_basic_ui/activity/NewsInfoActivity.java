package com.example.nc_basic_ui.activity;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.nc_basic_biz.utils.IntentUtils;
import com.example.nc_basic_ui.R;
import com.example.nc_basic_ui.controller.NewsInfoController;
import com.example.nc_common_resource.view.CommonToolBar;
import com.example.nc_super_abs.activity.BaseWebViewAcitivity;
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
    private CommonToolBar commonToolBar;
    private Handler mHandler = new Handler();
    @Override
    public Toolbar createToolBar() {

        commonToolBar = new CommonToolBar(mContext);
        commonToolBar.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                , (int) mContext.getResources().getDimension(R.dimen.dimens_webview_top_height)));
        commonToolBar.setLeftButtonIcon(mContext.getDrawable(R.drawable.img_back));
        commonToolBar.setRightButtonIcon(mContext.getDrawable(R.drawable.img_share));
        commonToolBar.setSubTitleName("加载中...");
        return commonToolBar;
    }

    @Override
    public void initValue() {

        if (getIntent() != null) {
            id = getIntent().getStringExtra("id");
        }
        newsInfoController = new NewsInfoController(mContext);
    }

    @Override
    public void loadUrl(final WebView webView) {

        newsInfoController.getInfos(id, new ICommonInvokeResult<NewsInfo, String>() {
            @Override
            public void onResult(final NewsInfo newsInfo) {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        commonToolBar.setSubTitleName(newsInfo.getTitle());
                        webView.loadUrl(newsInfo.getShare_url());
                    }
                },1000);
            }

            @Override
            public void onFailure(String errorMsg) {
                commonToolBar.setSubTitleName("网页无法打开");
            }

            @Override
            public void onCompleted() {

            }
        });

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {

                Log.e("dongyiming", error.toString());
            }
        });
    }

    public static void intentActivity(Context mContext, String key, String value) {

        IntentUtils.startActivity(mContext, NewsInfoActivity.class, key, value);
    }
}
