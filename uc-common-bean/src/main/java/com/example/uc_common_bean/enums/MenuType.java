package com.example.uc_common_bean.enums;

/**
 * @version : 1.0
 * @Description : 歌单类型
 * @autho : dongyiming
 * @data : 2017/6/5 22:24
 */
public enum MenuType {


    REC_MUC("推荐歌单", 2), UNP_MUC("独家放送", 3), LAT_MUC("最新音乐", 4), REC_MV("推荐MV", 5), PERF_MUC("精选专栏", 6), RAD_FM("主播电台", 7);

    private String name;
    private int value;

    private MenuType(String name, int value) {

        this.name = name;
        this.value = value;
    }

    public static String valueOf(int type) {

        for (MenuType menuType : MenuType.values()) {

            if (menuType.value == type) {

                return menuType.name;
            }
        }
        throw new IllegalArgumentException("not support menu type");
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}
