package com.example.nc_basic_ui.controller;

import android.content.Context;

import com.example.uc_common_bean.vo.MenuInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @version : 1.0
 * @Description : 列表
 * @autho : dongyiming
 * @data : 2017/5/28 12:16
 */
public class ChatListController {

    private Context mContext;

    public ChatListController(Context mContext) {
        this.mContext = mContext;
    }

    public List<MenuInfo> getMenuInfos() {

        List<MenuInfo> menuInfos = new ArrayList<>();
        for (int i = 0; i < 50; i++) {

            /*MenuInfo menuInfo = new MenuInfo();
            menuInfo.setAuthorCode(i + 1 + "");
            menuInfo.setCreatorName("董一鸣" + i);
            menuInfo.setMenuId(i + 1 + "");
            if (i == 0) {
                menuInfo.setMenuName("老歌|青春是颗酸葡萄，再尝一口");
                menuInfo.setPlayCount(80281);
            } else if (i == 1) {
                menuInfo.setMenuName("炸！生命不息，蹦迪不止，爱他就带他去蹦迪");
                menuInfo.setPlayCount(233152);
            } else {
                menuInfo.setPlayCount(1516165);
                menuInfo.setMenuName("爸爸的录音机|怀旧华语");
            }
            menuInfo.setCreateTime(System.currentTimeMillis());
            menuInfo.setHeadUrl("http://www.cnblogs.com/liunanjava/p/5338364.html");
            menuInfos.add(menuInfo);*/
        }

        return menuInfos;
    }
}
