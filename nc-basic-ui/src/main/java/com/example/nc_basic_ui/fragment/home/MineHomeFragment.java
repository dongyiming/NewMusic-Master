package com.example.nc_basic_ui.fragment.home;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.nc_common_resource.utils.TypeUtils;
import com.example.nc_basic_ui.R;
import com.example.nc_super_abs.fragment.BaseFragment;

/**
 * @version : 1.0
 * @Description : 我的信息
 * @autho : dongyiming
 * @data : 2017/5/23 18:02
 */
public class MineHomeFragment extends BaseFragment {

    @Override
    public View setRootView(LayoutInflater inflater) {

        View view = inflater.inflate(R.layout.fragment_mine_layout, null);
        ((TextView) view.findViewById(R.id.txt_name)).setTypeface(TypeUtils.getType(getActivity()));
        return view;
    }

    @Override
    public void registerWidgetEvent() {
    }

    @Override
    public void initComponent() {

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
