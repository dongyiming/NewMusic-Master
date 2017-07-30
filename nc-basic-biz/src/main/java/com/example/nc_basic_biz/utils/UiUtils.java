package com.example.nc_basic_biz.utils;

import android.app.Activity;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager;

/**
 * @version : 1.0
 * @Description : 界面调整工具
 * @autho : dongyiming
 * @data : 2017/5/24 12:58
 */
public class UiUtils {

    /**
     * !系统通知栏背景色设置工具
     *
     * @param mContext
     * @param on
     */
    public static void setTranslucentStatus(Context mContext, boolean on) {

        Window win = ((Activity) mContext).getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

}
