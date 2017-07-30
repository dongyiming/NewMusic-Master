package com.example.nc_basic_ui.factory;

import com.example.nc_basic_ui.fragment.inner.CatHotTopFragment;
import com.example.nc_basic_ui.fragment.inner.CatOwnerFragment;
import com.example.nc_basic_ui.fragment.inner.MenuListFragment;
import com.example.nc_basic_ui.fragment.inner.RecommendFragment;
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
                    mFragmentFactory = new RecommendFragment();
                    break;
                case PAGE_TWO:
                    mFragmentFactory = new MenuListFragment();
                    break;
                case PAGE_THREE:
                    mFragmentFactory = new CatOwnerFragment();
                    break;
                case PAGE_FOUR:
                    mFragmentFactory = new CatHotTopFragment();
                    break;

            }
            fragments.put(page, mFragmentFactory);
        }
        return mFragmentFactory;
    }
}
