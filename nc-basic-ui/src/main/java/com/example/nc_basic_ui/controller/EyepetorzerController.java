package com.example.nc_basic_ui.controller;

import android.content.Context;

import com.example.nc_basic_biz.core.EyepetizerHomePageManager;
import com.example.nc_super_abs.controller.BaseController;
import com.example.nc_super_abs.interaction.ICommonInvokeResult;
import com.example.uc_common_bean.vo.HotItemInfo;

import java.util.HashMap;
import java.util.List;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/7/31 23:35
 */
public class EyepetorzerController extends BaseController {

    private Context mContext;
    private final EyepetizerHomePageManager eyepetizerHomePageManager;

    public EyepetorzerController(Context mContext) {
        this.mContext = mContext;
        eyepetizerHomePageManager = new EyepetizerHomePageManager();
    }

    public void discoveryHot(int startIndx, int count, ICommonInvokeResult<HashMap<String, List<HotItemInfo>>, String> commonInvokeResult) {

        eyepetizerHomePageManager.deiscoveryHot(startIndx, count, commonInvokeResult);

    }

    /**
     * HorizontalScrollCard和VideoBeanForClient
     *
     * @param dataType
     * @return
     */
    public List<HotItemInfo> selectByType(String dataType) {

        return eyepetizerHomePageManager.selectByType(dataType);
    }

}
