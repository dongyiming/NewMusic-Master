package com.example.nc_basic_ui.adapter;

import android.content.Context;

import com.example.nc_basic_ui.action.LatestMucController;
import com.example.nc_basic_ui.action.PerfectMenuController;
import com.example.nc_basic_ui.action.RadioFmController;
import com.example.nc_basic_ui.action.RecMenuController;
import com.example.nc_basic_ui.action.RecMvController;
import com.example.nc_basic_ui.action.UniqueMusicController;
import com.example.nc_super_abs.adapter.MultiTypeRecyclerAdapter;
import com.example.nc_super_abs.adapter.WrapperBean;
import com.example.uc_common_bean.enums.MenuType;

import java.util.List;

/**
 * @version : 1.0
 * @Description : 推荐歌单
 * @autho : dongyiming
 * @data : 2017/6/4 14:46
 */
public class RecommendMusicAdapter extends MultiTypeRecyclerAdapter {


    public RecommendMusicAdapter(Context mContext, List<WrapperBean> dataList) {
        super(mContext, dataList);

        addItems(new RecMenuController(mContext), MenuType.REC_MUC.getValue());
        addItems(new UniqueMusicController(mContext), MenuType.UNP_MUC.getValue());
        addItems(new LatestMucController(mContext), MenuType.LAT_MUC.getValue());
        addItems(new RecMvController(mContext), MenuType.REC_MV.getValue());
        addItems(new PerfectMenuController(mContext), MenuType.PERF_MUC.getValue());
        addItems(new RadioFmController(mContext), MenuType.RAD_FM.getValue());

    }
}
