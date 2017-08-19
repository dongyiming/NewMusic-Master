package com.example.nc_basic_biz.utils;

import android.content.Context;
import android.content.Intent;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/8/2 15:17
 */
public class IntentUtils {

    /**
     * 界面跳转，key前value后
     *
     * @param mContext
     * @param clazz
     * @param extras
     */
    public static void startActivity(Context mContext, Class<?> clazz, String... extras) {

        Intent intent = new Intent();
        if (extras != null) {
            if (extras.length % 2 != 0) {//必须配对的key 和 value
                throw new IllegalArgumentException("parameter illegal...");
            } else {
                for (int i = 0; i < extras.length; i += 2) {
                    intent.putExtra(extras[i], extras[i + 1]);
                }
            }
        }
        intent.setClass(mContext, clazz);
        mContext.startActivity(intent);
    }
}
