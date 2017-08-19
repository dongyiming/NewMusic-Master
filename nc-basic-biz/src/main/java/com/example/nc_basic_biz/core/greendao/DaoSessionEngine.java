package com.example.nc_basic_biz.core.greendao;

import android.util.Log;

import com.example.nc_basic_biz.application.MyApplication;
import com.example.uc_common_bean.greedao.DaoMaster;
import com.example.uc_common_bean.greedao.DaoSession;


/**
 * @version : 1.0
 * @Description : 数据库内存存储，基本存储
 * @autho : dongyiming
 * @data : 2017/7/10 1:15
 */
public class DaoSessionEngine {

    private static DaoSessionEngine mInstance;

    private final String DB_NAME = "nc_db";
    private DaoMaster.DevOpenHelper devOpenHelper;

    private DaoSessionEngine() {
    }

    public static DaoSessionEngine getInstance() {

        if (mInstance == null) {
            synchronized (DaoSessionEngine.class) {
                if (mInstance == null) {
                    mInstance = new DaoSessionEngine();
                }
            }
        }
        return mInstance;
    }

    private DaoMaster.DevOpenHelper getOpenHelper() {

        if (devOpenHelper == null) {
            devOpenHelper = new DaoMaster.DevOpenHelper(MyApplication.getContext(), DB_NAME, null);
        }
        return devOpenHelper;
    }

    public DaoSession getWriteableSession() {

        DaoMaster.DevOpenHelper openHelper = getOpenHelper();
        DaoMaster daoMaster = new DaoMaster(openHelper.getWritableDatabase());
        return daoMaster.newSession();
    }

    public DaoSession getReadableSession() {

        DaoMaster.DevOpenHelper openHelper = getOpenHelper();
        DaoMaster daoMaster = new DaoMaster(openHelper.getReadableDatabase());
        return daoMaster.newSession();
    }
}
