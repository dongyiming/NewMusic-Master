package com.example.nc_super_abs.view;

import android.content.Context;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.example.nc_super_abs.handler.NetWorkUtils;


/**
 * @version : 1.0
 * @Description : webview的一些可用的属性和常规属性
 * @autho : dongyiming
 * @data : 2017/8/15 1:14
 */
public class WebViewBuilder {

    private Context mContext;
    private WebView mWebView;
    private WebSettings settings;

    public WebViewBuilder(Context mContext) {
        this.mContext = mContext;
        initWebView();
    }

    /**
     * @Description: 初始化webview, 避免webview内存泄漏，不在xml文件中注册
     * @author: dongyiming
     * @date: 2017/9/6  19:51
     * @param:
     */
    private void initWebView() {

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mWebView = new WebView(mContext.getApplicationContext());
        mWebView.setLayoutParams(params);
        settings = mWebView.getSettings();
    }

    /**
     * @Description: 加载界面
     * @author: dongyiming
     * @date: 2017/9/7  2:29
     * @param: url
     */
    public WebViewBuilder loadUrl(String url) {

        if (mWebView != null) {
            mWebView.loadUrl(url);
        }
        return this;
    }

    /**
     * @Description: 加载url, 具体的url交给子类去实现
     * @author: dongyiming
     * @date: 2017/9/7  2:36
     * @param: listener
     */
    public WebViewBuilder loadUrl(UrlListener listener) {

        if (listener != null) {
            String url = listener.loadUrl();
            if (url != null && mWebView != null) {
                mWebView.loadUrl(url);
            }
        }
        return this;
    }

    /**
     * @Description: 支持和js交互或者本身希望js完成一定的功能，默认为false
     * @author: dongyiming
     * @date: 2017/9/6  19:52
     * @param: isSupport
     */
    public WebViewBuilder supportJs(boolean isSupport) {
        if (settings != null) {
            settings.setJavaScriptEnabled(isSupport);
        }
        return this;
    }

    /**
     * @Description: 设置是否支持缩放, 默认为true
     * @author: dongyiming
     * @date: 2017/9/6  19:52
     * @param: isFit
     */
    public WebViewBuilder isFitView(boolean isFit) {

        if (settings != null) {

            //不支持缩放，默认为true
            settings.setSupportZoom(isFit);
        }
        return this;
    }

    /**
     * @Description : 常规设置，一般都需要设置的属性
     * @author : dongyiming
     * @date :  2017/9/6  19:54
     */
    public WebViewBuilder setNormalAttribute() {

        if (settings != null) {
            settings.setDefaultTextEncodingName("utf-8");//设置编码格式
            //加载网页有时候会左右滑动，没法自适应屏幕，就加上下面的两句话
            settings.setUseWideViewPort(true); //将图片调整到适合webview的大小
            settings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
            if (NetWorkUtils.getAPNType(mContext) == 0) {//没网，则从本地获取，即离线加载
                settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            } else {//根据cache-control决定是否从网络上取数据。
                settings.setCacheMode(WebSettings.LOAD_DEFAULT);
            }
        }
        return this;
    }

    /**
     * @Description: 销毁webview界面
     * @author: dongyiming
     * @date: 2017/9/7  3:18
     * @param:
     */
    public WebViewBuilder destory() {

        if (mWebView != null) {
            mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebView.clearHistory();
            mWebView.destroy();
            mWebView = null;
        }
        return this;
    }

    /**
     * @Description: TODO
     * @author: dongyiming
     * @date: 2017/9/7  3:21
     * @param:
     */
    public boolean canGoBack() {

        boolean isCanGoBack = false;
        if (mWebView != null) {
            isCanGoBack = mWebView.canGoBack();
        }
        return isCanGoBack;
    }

    /**
     * @Description: TODO
     * @author: dongyiming
     * @date: 2017/9/7  3:23
     * @param:
     */
    public WebViewBuilder onGoBack() {
        mWebView.goBack();
        return this;
    }

    /**
     * @Description : 是否实现现实水平滚动条
     * @author : dongyiming
     * @date :  2017/9/6  19:54
     */
    public WebViewBuilder setHorizontalScrollBarEnabled(boolean isHorizontalEnble) {

        if (mWebView != null) {
            mWebView.setHorizontalScrollBarEnabled(isHorizontalEnble);
        }
        return this;
    }

    /**
     * @Description : 是否实现现实垂直滚动条
     * @author : dongyiming
     * @date :  2017/9/6  19:54
     */
    public WebViewBuilder setVerticalScrollBarEnabled(boolean isVerticalEnble) {

        if (mWebView != null) {
            mWebView.setVerticalScrollBarEnabled(isVerticalEnble);
        }
        return this;
    }

    /**
     * 内测：View.SCROLLBARS_OUTSIDE_OVERLAY
     * 外侧：View.SCROLLBARS_INSIDE_OVERLAY
     *
     * @Description : 滚动条显示位置
     * @author : dongyiming
     * @date :  2017/9/6  19:55
     */
    public WebViewBuilder setScrollBarStyle(int style) {
        if (mWebView != null) {

            mWebView.setScrollBarStyle(style);
        }
        return this;
    }

    /**
     * @Description: 处理各种通知、请求事件的回调(开始加载，结束加载，加载错误...)
     * @author: dongyiming
     * @date: 2017/9/7  1:12
     * @param: webViewClient
     */
    public WebViewBuilder setWebViewClient(WebViewClient webViewClient) {

        if (mWebView != null) {
            mWebView.setWebViewClient(webViewClient);
        }

        return this;
    }

    /**
     * @Description: 高级功能： JavaScript 的对话框、网站图标、网站title、加载进度...
     * @author: dongyiming
     * @date: 2017/9/7  1:15
     * @param: webChromeClient
     */
    public WebViewBuilder setWebChromeClient(WebChromeClient webChromeClient) {

        if (mWebView != null) {
            mWebView.setWebChromeClient(webChromeClient);
        }

        return this;
    }


    /**
     * @Description: create
     * @author: dongyiming
     * @date: 2017/9/6  19:55
     * @param:
     */
    public WebView create() {
        return mWebView;
    }

    public interface UrlListener {
        String loadUrl();
    }
}
