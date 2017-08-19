package com.example.nc_basic_ui.factory;

import com.example.nc_basic_ui.fragment.home.HomeFragment;
import com.example.nc_basic_ui.fragment.home.FoundFragment;
import com.example.nc_basic_ui.fragment.home.LoveHomeFragment;
import com.example.nc_basic_ui.fragment.home.MineHomeFragment;
import com.example.nc_super_abs.fragment.BaseFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/7/29 20:58
 */
public class OuterFragmentFactory {

    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;
    public static final int PAGE_FOUR = 3;

    public static Map<Integer, BaseFragment> fragments = new HashMap<>();


    /**
     * 生成相应的Fragment
     *
     * @param page
     * @return
     */
    public static BaseFragment buildFragment(int page) {

        BaseFragment mFragmentFactory = fragments.get(page);
        if (mFragmentFactory == null) {
            switch (page) {

                case PAGE_ONE:
                    mFragmentFactory = new HomeFragment();
                    break;
                case PAGE_TWO:
                    mFragmentFactory = new FoundFragment();
                    break;
                case PAGE_THREE:
                    mFragmentFactory = new LoveHomeFragment();
                    break;
                case PAGE_FOUR:
                    mFragmentFactory = new MineHomeFragment();
                    break;

            }
            fragments.put(page, mFragmentFactory);
        }
        return mFragmentFactory;
    }
}
