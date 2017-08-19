package com.example.nc_basic_ui.fragment.home;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.example.nc_basic_ui.R;
import com.example.nc_basic_ui.adapter.MinePageAdapter;
import com.example.nc_super_abs.fragment.BaseFragment;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @version : 1.0
 * @Description : 我的信息
 * @autho : dongyiming
 * @data : 2017/5/23 18:02
 */
public class MineHomeFragment extends BaseFragment {

    private CircleImageView circleImageView;
    private TabLayout tabView;
    private ViewPager pagerView;
    private String[] titles = new String[]{
            "音乐", "动态", "关于我"
    };

    @Override
    public View setRootView(LayoutInflater inflater) {

        View view = inflater.inflate(R.layout.fragment_mine_layout, null);
        circleImageView = (CircleImageView) view.findViewById(R.id.circleimg_mine);
        tabView = (TabLayout) view.findViewById(R.id.tab_mine);
        pagerView = (ViewPager) view.findViewById(R.id.viewpager_mine);
        return view;
    }

    @Override
    public void registerWidgetEvent() {
        circleImageView.setOnClickListener(this);
    }

    @Override
    public void initComponent() {

        pagerView.setAdapter(new MinePageAdapter(getChildFragmentManager(), titles));
        tabView.setupWithViewPager(pagerView);
        tabView.getTabAt(0).select();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void widgetClick(View view) {

    }
}
