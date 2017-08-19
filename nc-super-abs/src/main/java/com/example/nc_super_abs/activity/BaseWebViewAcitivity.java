package com.example.nc_super_abs.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.example.nc_super_abs.view.WebViewBuilder;

import static android.view.KeyEvent.KEYCODE_BACK;

/**
 * @version : 1.0
 * @Description : webview的公共类
 * @autho : dongyiming
 * @data : 2017/8/15 0:52
 */
public abstract class BaseWebViewAcitivity extends AppCompatActivity implements View.OnClickListener{

    private LinearLayout contentView;
    protected Context mContext;
    private WebView webView;
    private WebViewBuilder webViewBuilder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = BaseWebViewAcitivity.this;
        View view = initView();
        setContentView(view);
        initValue();
        loadUrl(webView);
    }

    private View initView() {

        LinearLayout view = new LinearLayout(mContext);
        view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        view.setOrientation(LinearLayout.VERTICAL);
        //添加toolbar
        Toolbar toolBar = createToolBar();
        view.addView(toolBar);
        //添加webview
        contentView = new LinearLayout(mContext);
        contentView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        contentView.setOrientation(LinearLayout.VERTICAL);
        //添加webview
        webViewBuilder = new WebViewBuilder(mContext);
        webView = webViewBuilder.create();
        contentView.addView(webView);
        view.addView(contentView);
        return view;
    }

    @Override
    protected void onResume() {
        super.onResume();
        webViewBuilder.setJavaScriptEnabled(true);
    }

    @Override
    protected void onStop() {
        super.onStop();
        webViewBuilder.setJavaScriptEnabled(false);
    }

    @Override
    protected void onDestroy() {

        if (webView != null) {
            webView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            webView.clearHistory();
            ((ViewGroup) webView.getParent()).removeView(webView);
            webView.destroy();
            webView = null;
        }
        super.onDestroy();
    }

    /**
     * 返回
     *
     * @param keyCode
     * @param event
     * @return
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if ((keyCode == KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View view) {

    }

    public abstract Toolbar createToolBar();

    public abstract void initValue();

    public abstract void loadUrl(WebView webView);
}
