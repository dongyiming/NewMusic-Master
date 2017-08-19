package com.example.nc_basic_biz.fun;


import android.util.Log;

import com.example.uc_common_bean.convert.MenuInfoConvert;
import com.example.uc_common_bean.vo.MenuInfo;
import com.example.uc_common_bean.vo.ncvo.NcMenu;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;


/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/7/11 17:41
 */
public class MenuInfoFun implements Function<List<NcMenu>, List<MenuInfo>> {

    @Override
    public List<MenuInfo> apply(@NonNull List<NcMenu> ncMenus) throws Exception {
        List<MenuInfo> menuInfos = new ArrayList<>();
        if (ncMenus != null && ncMenus.size() != 0) {
            MenuInfoConvert menuInfoConvert = new MenuInfoConvert();
            for (NcMenu ncMenu : ncMenus) {
                MenuInfo menuInfo = menuInfoConvert.convert(ncMenu);
                menuInfos.add(menuInfo);
            }
        }
        return menuInfos;
    }
}
