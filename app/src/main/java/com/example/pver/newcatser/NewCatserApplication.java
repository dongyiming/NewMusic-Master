package com.example.pver.newcatser;

import android.app.Application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *  @Description : 全局控制
 *  @autho : dongyiming
 *  @version : 1.0
 *  @data : 2017/5/22 16:33
 */
public class NewCatserApplication extends Application {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
