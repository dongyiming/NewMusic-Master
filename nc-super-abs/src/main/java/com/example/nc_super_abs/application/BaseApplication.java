package com.example.nc_super_abs.application;

import android.app.Application;

import com.example.nc_super_abs.activity.ActivityStack;
import com.example.nc_super_abs.handler.ApplicationCrashHandler;
import com.example.nc_super_abs.handler.CrashCallback;


/**
 *  @Description :
 *  @autho : dongyiming
 *  @version : 1.0
 *  @data : 2017/5/23 12:50
 */
public class BaseApplication extends Application {
    //protected Logger logger = LoggerFactory.getLogger(this.getClass());
    private ApplicationCrashHandler crashHandler;

    public BaseApplication() {
    }

    public void onCreate() {
        super.onCreate();
        this.crashHandler = new ApplicationCrashHandler(this);
    }

    public void setCrashCallback(CrashCallback crashCallback) {
        this.crashHandler.setCrashCallback(crashCallback);
    }

    public void onTerminate() {
        super.onTerminate();
    }

    public void exitApp() {
        ActivityStack.getInstance().finishAllActivity();
        System.exit(0);
    }
}