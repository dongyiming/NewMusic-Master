package com.example.nc_basic_ui.factory;

import com.example.nc_basic_ui.fragment.home.HomePageFoundFragment;
import com.example.nc_basic_ui.fragment.home.HomePageFriendFragment;
import com.example.nc_basic_ui.fragment.home.HomePageOwnerFragment;
import com.example.nc_basic_ui.fragment.home.HomePageSettingFragment;
import com.example.nc_super_abs.fragment.BaseFragment;

import java.util.HashMap;
import java.util.Map;

/**
 *  @Description :
 *  @autho : dongyiming
 *  @version : 1.0
 *  @data : 2017/7/29 20:58
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
                    mFragmentFactory = new HomePageFoundFragment();
                    break;
                case PAGE_TWO:
                    mFragmentFactory = new HomePageOwnerFragment();
                    break;
                case PAGE_THREE:
                    mFragmentFactory = new HomePageFriendFragment();
                    break;
                case PAGE_FOUR:
                    mFragmentFactory = new HomePageSettingFragment();
                    break;

            }
            fragments.put(page, mFragmentFactory);
        }
        return mFragmentFactory;
    }
}
