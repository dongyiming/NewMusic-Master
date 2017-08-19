package com.example.nc_super_abs.view;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.example.nc_super_abs.handler.NetWorkUtils;


/**
 * @version : 1.0
 * @Description : webview build
 * @autho : dongyiming
 * @data : 2017/8/15 1:14
 */
public class WebViewBuilder {

    private Context mContext;
    private WebView mWebView;
    private WebSettings settings;

    public WebViewBuilder(Context mContext) {
        this.mContext = mContext;
    }

    public WebView create() {

        //避免webview内存泄漏，不在xml文件中注册
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mWebView = new WebView(mContext.getApplicationContext());
        mWebView.setLayoutParams(params);
        convert(mWebView);
        return mWebView;
    }

    public void convert(WebView mWebView) {

        settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDefaultTextEncodingName("utf-8");//设置编码格式
        //设置自适应屏幕，两者合用
        settings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        settings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

        if (NetWorkUtils.getAPNType(mContext) == 0) {//没网，则从本地获取，即离线加载
            settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        } else {//根据cache-control决定是否从网络上取数据。
            settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        }
        //使得打开网页时不调用系统浏览器， 而是在本WebView中显示
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {

            }
        });
    }

    public void setJavaScriptEnabled(boolean flag) {

        if (settings != null) {
            settings.setJavaScriptEnabled(flag);
        }
    }
}
