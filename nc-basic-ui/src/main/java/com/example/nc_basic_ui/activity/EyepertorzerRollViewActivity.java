package com.example.nc_basic_ui.activity;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.nc_basic_ui.R;
import com.example.nc_common_resource.view.CommonToolBar;
import com.example.nc_super_abs.activity.BaseWebViewAcitivity;

/**
 * @version : 1.0
 * @Description : webview界面
 * @autho : dongyiming
 * @data : 2017/8/2 14:28
 */
public class EyepertorzerRollViewActivity extends BaseWebViewAcitivity {

    @Override
    public Toolbar createToolBar() {
        CommonToolBar toolbar = new CommonToolBar(mContext);
        toolbar.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                , (int) mContext.getResources().getDimension(R.dimen.dimens_webview_top_height)));
        toolbar.setTitleName(mContext.getResources().getString(R.string.activity_bottom_button1_str));
        toolbar.setBackgroundResource(R.color.color_bg_white);
        toolbar.setLeftButtonIcon(mContext.getDrawable(R.drawable.img_back));
        toolbar.getmLeftIcon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        return toolbar;
    }

    @Override
    public void initValue() {

    }

    @Override
    public void loadUrl(WebView webView) {
        webView.loadUrl("http://www.eyepetizer.net/article.html?vn=3.9.0&vc=3210&shareable=true&deviceModel=iPhone&udid=db22916827ff549ee5f393ad9e2dd833874351e6&nid=1111&from=singlemessage&isappinstalled=1");
    }

}
