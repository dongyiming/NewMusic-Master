package com.example.nc_basic_ui.factory;

import com.example.nc_basic_ui.fragment.inner.DailyNewsFragment;
import com.example.nc_basic_ui.fragment.inner.EyepetorzerFragment;
import com.example.nc_basic_ui.fragment.inner.MenusFragment;
import com.example.nc_basic_ui.fragment.inner.RecommendMenuFragment;
import com.example.nc_super_abs.fragment.BaseInnerFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/5/24 19:41
 */
public class InFragmentFactory {

    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;
    public static final int PAGE_FOUR = 3;

    public static Map<Integer, BaseInnerFragment> fragments = new HashMap<>();


    /**
     * 生成相应的Fragment
     *
     * @param page
     * @return
     */
    public static BaseInnerFragment buildFragment(int page) {

        BaseInnerFragment mFragmentFactory = fragments.get(page);
        if (mFragmentFactory == null) {
            switch (page) {

                case PAGE_ONE:
                    mFragmentFactory = new RecommendMenuFragment();
                    break;
                case PAGE_TWO:
                    mFragmentFactory = new MenusFragment();
                    break;
                case PAGE_THREE:
                    mFragmentFactory = new EyepetorzerFragment();
                    break;
                case PAGE_FOUR:
                    mFragmentFactory = new DailyNewsFragment();
                    break;

            }
            fragments.put(page, mFragmentFactory);
        }
        return mFragmentFactory;
    }
}
