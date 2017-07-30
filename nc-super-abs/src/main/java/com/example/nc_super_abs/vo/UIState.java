package com.example.nc_super_abs.vo;

/**
 *  @Description :
 *  @autho : dongyiming
 *  @version : 1.0
 *  @data : 2017/5/23 12:52
 */
public enum UIState {
    ATTACH,
    CREATE,
    CREATE_VIEW,
    VIEW_CREATED,
    ACTIVITY_CREATED,
    RESTART,
    START,
    RESUME,
    PAUSE,
    STOP,
    DESTROY_VIEW,
    DESTROY,
    DETACH;

    private UIState() {
    }
}
