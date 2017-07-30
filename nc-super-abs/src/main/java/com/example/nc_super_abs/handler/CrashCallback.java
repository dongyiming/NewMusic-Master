package com.example.nc_super_abs.handler;

import java.util.Map;

/**
 *  @Description :
 *  @autho : dongyiming
 *  @version : 1.0
 *  @data : 2017/5/23 12:51
 */
public interface CrashCallback {
    void handleCrashResult(Throwable var1, Map<String, String> var2);
}