package com.example.nc_basic_ui.adapter.content;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.nc_basic_ui.R;
import com.example.nc_common_resource.builder.RecyclerViewBuilder;
import com.example.nc_super_abs.adapter.MultiCommonAdapter;
import com.example.nc_super_abs.adapter.viewholder.BaseViewHolder;
import com.example.nc_super_abs.interaction.ICommonRecyclerListener;
import com.example.uc_common_bean.decoration.DecorationInfo;
import com.example.uc_common_bean.enums.MenuType;
import com.example.uc_common_bean.vo.MenuInfo;

import java.util.List;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/7/20 10:24
 */
public class LatestMucAdapter {

    private RecyclerView recyclerView;
    private Context mContext;

    public LatestMucAdapter(final Context mContext, final List<MenuInfo> menuInfos) {

        this.mContext = mContext;
        recyclerView = new RecyclerView(mContext);
        recyclerView.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));

        DecorationInfo decorationInfo = getDecoration();
        recyclerView = new RecyclerViewBuilder(mContext, recyclerView).setGridLayoutManager(3).setItemDecoration(decorationInfo, true).create();
        recyclerView.setAdapter(new MultiCommonAdapter<MenuInfo>(mContext, menuInfos, new ICommonRecyclerListener<MenuInfo>() {
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

                return position == 0 ? 999 : MenuType.LAT_MUC.getValue();
            }

            @Override
            public void convert(BaseViewHolder holder, MenuInfo menuInfo, int position, int type) {

                if (type == 999) {
                    holder.setText(R.id.txt_title, MenuType.LAT_MUC.getName());
                } else {
                    holder.setGone(R.id.txt_menu_name);
                    holder.setVisible(R.id.txt_menu_desc);
                    holder.setVisible(R.id.txt_menu_author);
                    holder.setText(R.id.txt_menu_desc, menuInfo.getMenuDesc());
                    holder.setText(R.id.txt_menu_author, menuInfo.getAuthorName());
                    ImageView menuPic = (ImageView) holder.getWidget(R.id.img_latest_menu);
                    if (menuInfo.getMenuPicurl() != null) {
                        Glide.with(mContext).load(menuInfo.getMenuPicurl()).override(357, 357).placeholder(R.drawable.img_default_357_357).into(menuPic);
                    }
                }
            }

            @Override
            public int setSpanCount(int position, int viewType) {

                return viewType == 999 ? 3 : 1;
            }
        }));
    }

    public DecorationInfo getDecoration() {

        DecorationInfo decorationInfo = new DecorationInfo();
        decorationInfo.setColorResource(mContext.getResources().getColor(R.color.color_padding_chatlist));
        decorationInfo.setLeftDividerWidth((int) mContext.getResources().getDimension(R.dimen.dimens_common_decoration_leftdividerwidth));
        decorationInfo.setBottomDividerHeight((int) mContext.getResources().getDimension(R.dimen.dimens_common_decoration_bottomdividerheight));
        return decorationInfo;
    }

    public RecyclerView build() {

        return recyclerView;
    }
}
