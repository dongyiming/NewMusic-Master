package com.example.nc_basic_ui.fragment.home;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.example.nc_common_resource.utils.TypeUtils;
import com.example.nc_basic_ui.R;
import com.example.nc_basic_ui.adapter.CatFoundPageAdapter;
import com.example.nc_basic_ui.controller.CatFoundController;
import com.example.nc_common_resource.view.CommonToolBar;
import com.example.nc_super_abs.fragment.BaseFragment;

/**
 * @version : 1.0
 * @Description : 发现
 * @autho : dongyiming
 * @data : 2017/5/23 17:59
 */
public class HomePageFragment extends BaseFragment {


    ViewPager viewpagerCatfound;

    private TabLayout tablayout;
    private CatFoundController catFoundController;
    private CommonToolBar commonbar;

    @Override
    public View setRootView(LayoutInflater inflater) {

        View view = inflater.inflate(R.layout.fragment_catfound, null);
        viewpagerCatfound = (ViewPager) view.findViewById(R.id.viewpager_catfound);
        commonbar = (CommonToolBar) view.findViewById(R.id.commonbar);
        tablayout = (TabLayout) view.findViewById(R.id.tablayout_top);
        viewpagerCatfound.setOffscreenPageLimit(3);
        //设置背景和状态栏
        commonbar.setBackgroundColor(getActivity().getResources().getColor(R.color.color_bg_status_bar));
        commonbar.getTitleView().setTypeface(TypeUtils.getType(getActivity()));
        commonbar.getTitleView().setText(getActivity().getResources().getString(R.string.str_nc));
        return view;
    }

    @Override
    public void initComponent() {
        catFoundController = new CatFoundController(getActivity());
        viewpagerCatfound.setAdapter(new CatFoundPageAdapter(getChildFragmentManager(), catFoundController.getTitles()));
        tablayout.setupWithViewPager(viewpagerCatfound);
        tablayout.getTabAt(0).select();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}