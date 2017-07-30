package com.example.nc_super_abs.handler;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 *  @Description :
 *  @autho : dongyiming
 *  @version : 1.0
 *  @data : 2017/5/23 12:51
 */
public class ApplicationCrashHandler implements Thread.UncaughtExceptionHandler {
    //private static Logger logger = LoggerFactory.getLogger(ApplicationCrashHandler.class);
    private CrashCallback crashCallback;
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    private Context mContext;

    public ApplicationCrashHandler(Context context) {
        this.mContext = context;
        this.mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    public void setCrashCallback(CrashCallback crashCallback) {
        this.crashCallback = crashCallback;
    }

    public void uncaughtException(Thread thread, Throwable ex) {
        if ((ex == null || this.crashCallback == null) && this.mDefaultHandler != null) {
            this.mDefaultHandler.uncaughtException(thread, ex);
        } else {
            this.handleException(ex);
        }

    }

    private void handleException(Throwable ex) {
        Map deviceInfo = this.collectDeviceInfo(this.mContext);
        //        logger.error("crash application info: {}", deviceInfo);
        //        logger.error("crash exception: ", ex);
        this.crashCallback.handleCrashResult(ex, deviceInfo);
    }

    public Map<String, String> collectDeviceInfo(Context ctx) {
        HashMap deviceInfo = new HashMap();

        try {
            PackageManager fields = ctx.getPackageManager();
            PackageInfo arr$ = fields.getPackageInfo(ctx.getPackageName(), 1);
            if (arr$ != null) {
                String len$ = arr$.versionName == null ? "null" : arr$.versionName;
                String i$ = arr$.versionCode + "";
                deviceInfo.put("versionName", len$);
                deviceInfo.put("versionCode", i$);
            }
        } catch (PackageManager.NameNotFoundException var10) {
            //logger.error("an error occured when collect package info", var10);
        }

        Field[] var11 = Build.class.getDeclaredFields();
        Field[] var12 = var11;
        int var13 = var11.length;

        for (int var14 = 0; var14 < var13; ++var14) {
            Field field = var12[var14];

            try {
                field.setAccessible(true);
                deviceInfo.put(field.getName(), field.get((Object) null).toString());
            } catch (Exception var9) {
                //logger.error("an error occured when collect crash info", var9);
            }
        }

        return deviceInfo;
    }
}
