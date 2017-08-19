package com.example.nc_basic_biz.fun;

import com.example.uc_common_bean.eyepetizer.hot.HotPageBean;
import com.example.uc_common_bean.eyepetizer.hot.HotPageItem;
import com.example.uc_common_bean.vo.HotItemInfo;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * @version : 1.0
 * @Description : 解析响应数据(嵌套的泛型解析问题一直解决不了，用笨方法了)
 * @autho : dongyiming
 * @data : 2017/8/2 2:08
 */
public class EyepertozerFunction implements Function<HotPageBean, HashMap<String, List<HotItemInfo>>> {

    @Override
    public HashMap<String, List<HotItemInfo>> apply(@NonNull HotPageBean hotPageBean) throws Exception {

        List<HotItemInfo> cardInfos = new ArrayList<>();
        List<HotItemInfo> videoInfos = new ArrayList<>();
        List<HotPageItem> itemList = hotPageBean.getItemList();
        HashMap<String, List<HotItemInfo>> hotItemMap = new HashMap<>();

        for (HotPageItem hotpageItem : itemList) {

            String type = hotpageItem.getType();
            if (type.equals("horizontalScrollCard")) {

                LinkedTreeMap<Integer, Object> map = (LinkedTreeMap<Integer, Object>) hotpageItem.getData();

                ArrayList<LinkedTreeMap> arrayList = (ArrayList<LinkedTreeMap>) map.get("itemList");
                String nextPageUrl = (String) map.get("nextPageUrl");
                for (int i = 0; i < arrayList.size(); i++) {
                    HotItemInfo hotItemInfo = new HotItemInfo();
                    LinkedTreeMap<Integer, Object> linkedTreeMap = (LinkedTreeMap<Integer, Object>) arrayList.get(i).get("data");
                    String image = (String) linkedTreeMap.get("image");
                    String actionUrl = (String) linkedTreeMap.get("actionUrl");
                    Double id = (Double) linkedTreeMap.get("id");
                    hotItemInfo.setActionUrl(actionUrl);
                    hotItemInfo.setImage(image);
                    hotItemInfo.setDateId(id);
                    hotItemInfo.setDataType("HorizontalScrollCard");
                    cardInfos.add(hotItemInfo);
                }
            } else if (type.equals("video")) {

                HotItemInfo hotItemInfo = new HotItemInfo();
                hotItemInfo.setDataType("VideoBeanForClient");
                LinkedTreeMap<Integer, Object> map = (LinkedTreeMap<Integer, Object>) hotpageItem.getData();
                LinkedTreeMap<Integer, Object> authorMap = (LinkedTreeMap<Integer, Object>) map.get("author");
                LinkedTreeMap<Integer, Object> consumptionMap = (LinkedTreeMap<Integer, Object>) map.get("consumption");
                LinkedTreeMap<Integer, Object> coverMap = (LinkedTreeMap<Integer, Object>) map.get("cover");
                ArrayList<LinkedTreeMap> playInfoList = (ArrayList<LinkedTreeMap>) map.get("playInfo");
                LinkedTreeMap<Integer, Object> webUrlMap = (LinkedTreeMap<Integer, Object>) map.get("webUrl");
                if (authorMap != null) {
                    hotItemInfo.setAuthorIcon((String) authorMap.get("icon"));
                    hotItemInfo.setAuthorDesc((String) authorMap.get("description"));
                    hotItemInfo.setAuthorName((String) authorMap.get("name"));
                } else {
                    continue;
                }
                if (consumptionMap != null) {
                    hotItemInfo.setCollectionCount((double) consumptionMap.get("collectionCount"));
                    hotItemInfo.setReplyCount((double) consumptionMap.get("replyCount"));
                    hotItemInfo.setShareCount((double) consumptionMap.get("shareCount"));
                }
                if (coverMap != null) {

                    hotItemInfo.setBlurred((String) coverMap.get("blurred"));
                    hotItemInfo.setDetail((String) coverMap.get("detail"));
                    hotItemInfo.setFeed((String) coverMap.get("feed"));
                }
                if (playInfoList != null) {
                    int size = playInfoList.size();
                    if (size == 1) {
                        LinkedTreeMap sdPlayInfoMap = playInfoList.get(0);
                        if (sdPlayInfoMap != null) {
                            hotItemInfo.setSdHeight((double) sdPlayInfoMap.get("height"));
                            hotItemInfo.setSdWidth((double) sdPlayInfoMap.get("width"));
                            hotItemInfo.setSdName((String) sdPlayInfoMap.get("name"));
                            hotItemInfo.setSdUrl((String) sdPlayInfoMap.get("url"));
                        }
                    } else if (size == 2) {
                        LinkedTreeMap sdPlayInfoMap = playInfoList.get(0);
                        LinkedTreeMap hdPlayInfoMap = playInfoList.get(1);
                        if (sdPlayInfoMap != null) {
                            hotItemInfo.setSdHeight((double) sdPlayInfoMap.get("height"));
                            hotItemInfo.setSdWidth((double) sdPlayInfoMap.get("width"));
                            hotItemInfo.setSdName((String) sdPlayInfoMap.get("name"));
                            hotItemInfo.setSdUrl((String) sdPlayInfoMap.get("url"));
                        }
                        if (hdPlayInfoMap != null) {
                            hotItemInfo.setHdHeight((double) hdPlayInfoMap.get("height"));
                            hotItemInfo.setHdWidth((double) hdPlayInfoMap.get("width"));
                            hotItemInfo.setHdName((String) hdPlayInfoMap.get("name"));
                            hotItemInfo.setHdUrl((String) hdPlayInfoMap.get("url"));
                        }
                    }
                }
                if (webUrlMap != null) {
                    hotItemInfo.setForWeibo((String) webUrlMap.get("forWeibo"));
                    hotItemInfo.setRaw((String) webUrlMap.get("raw"));
                }
                hotItemInfo.setCategory((String) map.get("category"));
                hotItemInfo.setDescription((String) map.get("description"));
                hotItemInfo.setPlayUrl((String) map.get("playUrl"));
                hotItemInfo.setTitle((String) map.get("title"));
                hotItemInfo.setData((double) map.get("date"));
                hotItemInfo.setDuration((double) map.get("duration"));
                hotItemInfo.setDateId((Double) map.get("id"));
                videoInfos.add(hotItemInfo);
            }
        }
        if (!cardInfos.isEmpty()) {
            hotItemMap.put("HorizontalScrollCard", cardInfos);
        }
        hotItemMap.put("VideoBeanForClient", videoInfos);
        return hotItemMap;
    }

}
