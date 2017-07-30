package com.example.nc_basic_ui.application;

import android.app.Application;
import android.content.Context;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/7/14 22:21
 */
public class BasicUiApplication extends Application {

    public static Context mContext;


    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();
    }

    public static Context getContext() {

        return mContext;
    }
}
