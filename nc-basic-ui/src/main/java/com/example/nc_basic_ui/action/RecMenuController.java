package com.example.nc_basic_ui.action;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.nc_basic_ui.R;
import com.example.nc_common_resource.builder.RecyclerViewBuilder;
import com.example.nc_super_abs.adapter.MultiCommonAdapter;
import com.example.nc_super_abs.adapter.viewholder.BaseViewHolder;
import com.example.nc_super_abs.interaction.ICommonRecyclerListener;
import com.example.uc_common_bean.enums.MenuType;
import com.example.uc_common_bean.decoration.DecorationInfo;
import com.example.uc_common_bean.vo.MenuInfo;

import java.util.List;

/**
 * @version : 1.0
 * @Description : 推荐歌单的item处理
 * @autho : dongyiming
 * @data : 2017/6/7 1:16
 */
public class RecMenuController extends BaseRecyclerViewController {

    public RecMenuController(Context mContext) {

        super(mContext);
    }

    @Override
    public RecyclerView buildRecyclerView(RecyclerView recyclerView) {
        DecorationInfo decorationInfo = getDecoration();
        return new RecyclerViewBuilder(mContext, recyclerView).setGridLayoutManager(3).setItemDecoration(decorationInfo, true).create();
    }

    @Override
    public MultiCommonAdapter setAdapter(List<MenuInfo> menuList) {
        MultiCommonAdapter adapter = new MultiCommonAdapter<MenuInfo>(mContext, menuList, new ICommonRecyclerListener<MenuInfo>() {
            @Override
            public int getLayoutId(int type) {
                if (type == 999) {

                    return R.layout.header_recommend_item;
                } else {

                    return R.layout.adapter_latest_item;
                }
            }

            @Override
            public int getItemViewType(int position) {

                return 0;
            }

            @Override
            public void convert(BaseViewHolder holder, MenuInfo menuInfo, int position, int type) {

                if (type == 999) {
                    holder.setText(R.id.txt_title, MenuType.valueOf(menuType));
                } else {
                    holder.setText(R.id.txt_menu_name, menuInfo.getMenuName());
                    holder.getWidget(R.id.txt_playcount).setVisibility(View.VISIBLE);
                    holder.getWidget(R.id.img_playcount).setVisibility(View.VISIBLE);
                    holder.setText(R.id.txt_playcount, menuInfo.getPlaycount() + "");
                }
            }

            @Override
            public int setSpanCount(int position, int viewType) {

                return viewType == 999 ? 3 : 1;
            }
        });
        return adapter;
    }

    public DecorationInfo getDecoration() {

        DecorationInfo decorationInfo = new DecorationInfo();
        decorationInfo.setColorResource(mContext.getResources().getColor(R.color.color_padding_chatlist));
        decorationInfo.setLeftDividerWidth((int) mContext.getResources().getDimension(R.dimen.dimens_common_decoration_leftdividerwidth));
        decorationInfo.setBottomDividerHeight((int) mContext.getResources().getDimension(R.dimen.dimens_common_decoration_bottomdividerheight));
        return decorationInfo;
    }

}
