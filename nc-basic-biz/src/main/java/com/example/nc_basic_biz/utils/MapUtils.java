package com.example.nc_basic_biz.utils;

import com.example.uc_common_bean.vo.HotItemInfo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/8/14 9:09
 */
public class MapUtils {

    /**
     * 遍历得到value
     *
     * @param map
     * @param key
     * @return
     */
    public static List<HotItemInfo> getInfos(HashMap<String, List<HotItemInfo>> map, String key) {

        if (map != null) {
            Set<Map.Entry<String, List<HotItemInfo>>> entries = map.entrySet();
            Iterator<Map.Entry<String, List<HotItemInfo>>> iterator = entries.iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, List<HotItemInfo>> next = iterator.next();
                if (next.getKey().equals(key)) {
                    return next.getValue();
                }
            }
        }
        return null;
    }
}
