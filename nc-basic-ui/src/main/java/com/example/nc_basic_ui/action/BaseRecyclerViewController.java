package com.example.nc_basic_ui.action;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.example.nc_basic_ui.R;
import com.example.nc_super_abs.adapter.MultiCommonAdapter;
import com.example.nc_super_abs.adapter.WrapperBean;
import com.example.nc_super_abs.adapter.viewholder.BaseViewHolder;
import com.example.nc_super_abs.interaction.IMultiRecyclerViewListener;
import com.example.uc_common_bean.vo.MenuInfo;

import java.util.List;

/**
 * @version : 1.0
 * @Description : 推荐界面的6个item的基类
 * @autho : dongyiming
 * @data : 2017/6/8 21:34
 */
public abstract class BaseRecyclerViewController implements IMultiRecyclerViewListener {

    public Context mContext;
    public int menuType;

    public BaseRecyclerViewController(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getLayoutId(int menuType) {
        this.menuType = menuType;
        return R.layout.recycler_common;
    }

    @Override
    public void convert(BaseViewHolder viewHolder, WrapperBean wrapperBean) {

        RecyclerView rcycler = viewHolder.getWidget(R.id.recycler_common);
        List<MenuInfo> menuList = (List<MenuInfo>) wrapperBean.getT();
        rcycler = buildRecyclerView(rcycler);
        MultiCommonAdapter multiCommonAdapter = setAdapter(menuList);
        rcycler.setAdapter(multiCommonAdapter);
    }

    public abstract RecyclerView buildRecyclerView(RecyclerView recyclerView);

    public abstract MultiCommonAdapter setAdapter(List<MenuInfo> menuList);

}
