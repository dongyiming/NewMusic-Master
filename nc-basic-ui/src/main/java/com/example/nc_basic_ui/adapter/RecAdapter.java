package com.example.nc_basic_ui.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.nc_basic_ui.R;
import com.example.nc_basic_ui.view.RecommendHintView;
import com.example.nc_super_abs.adapter.viewholder.BaseViewHolder;
import com.example.uc_common_bean.enums.MenuType;
import com.example.uc_common_bean.vo.MenuInfo;
import com.jude.rollviewpager.RollPagerView;

import java.util.List;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/8/7 22:10
 */
public class RecAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private Context mContext;
    private List<MenuInfo> menuInfos;
    private final int TYPE_HEADER = 0;
    private final int TYPE_TITLE = 1;//标题
    private final int TYPE_REC_MENU = 2;//推荐歌单
    private final int TYPE_UNIQ_NORMAL = 3;//独家放送
    private final int TYPE_LATEST = 4;//最新音乐
    private final int TYPE_REC_MV = 5;//推荐MV
    private final int TYPE_PERFECT = 6;//精选专栏
    private final int TYPE_RADIO = 7;//主播电台
    private final int TYPE_UNIQ_LAST = 8;//独家放送
    private final int TYPE_FOOTER = 9;

    public RecAdapter(Context mContext, List<MenuInfo> menuInfos) {
        this.mContext = mContext;
        this.menuInfos = menuInfos;

    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0) {
            return TYPE_HEADER;
        } else if (menuInfos != null && position == menuInfos.size() + 1) {
            return TYPE_FOOTER;
        }
        MenuInfo menuInfo = menuInfos.get(position - 1);
        if (menuInfo.getMenuType() == 998) {
            return TYPE_TITLE;
        } else if (menuInfo.getMenuType() == MenuType.REC_MUC.getValue()) {
            return TYPE_REC_MENU;
        } else if (menuInfo.getMenuType() == MenuType.UNP_MUC.getValue()) {
            return TYPE_UNIQ_NORMAL;
        } else if (menuInfo.getMenuType() == 999) {
            return TYPE_UNIQ_LAST;
        } else if (menuInfo.getMenuType() == MenuType.LAT_MUC.getValue()) {
            return TYPE_LATEST;
        } else if (menuInfo.getMenuType() == MenuType.REC_MV.getValue()) {
            return TYPE_REC_MV;
        } else if (menuInfo.getMenuType() == MenuType.PERF_MUC.getValue()) {
            return TYPE_PERFECT;
        } else if (menuInfo.getMenuType() == MenuType.RAD_FM.getValue()) {
            return TYPE_RADIO;
        }
        return super.getItemViewType(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        int layoutId = 0;
        if (viewType == TYPE_HEADER) {
            layoutId = R.layout.header_recommend_fragment;
        } else if (viewType == TYPE_TITLE) {
            layoutId = R.layout.header_recommend_item;
        } else if (viewType == TYPE_REC_MENU) {
            layoutId = R.layout.adapter_latest_item;
        } else if (viewType == TYPE_UNIQ_NORMAL) {
            layoutId = R.layout.adapter_unique_item;
        } else if (viewType == TYPE_UNIQ_LAST) {
            layoutId = R.layout.adapter_unique_item;
        } else if (viewType == TYPE_LATEST) {
            layoutId = R.layout.adapter_latest_item;
        } else if (viewType == TYPE_REC_MV) {
            layoutId = R.layout.adapter_unique_item;
        } else if (viewType == TYPE_PERFECT) {
            layoutId = R.layout.adapter_perfect;
        } else if (viewType == TYPE_RADIO) {
            layoutId = R.layout.adapter_latest_item;
        } else if (viewType == TYPE_FOOTER) {
            layoutId = R.layout.footer_recommend_fragment;
        }
        return BaseViewHolder.build(mContext, layoutId, parent, null);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {

        int pos = 0;
        MenuInfo menuInfo = null;
        int itemViewType = getItemViewType(position);
        if (itemViewType != TYPE_HEADER && itemViewType != TYPE_FOOTER) {
            pos = position - 1;
            menuInfo = menuInfos.get(pos);
        }
        if (position == 0) {
            RollPagerView rollPagerView = (RollPagerView) holder.getWidget(R.id.rollpager_recommend);
            //配置轮播图
            rollPagerView.setAdapter(new MyRollViewPager(mContext, rollPagerView));
            rollPagerView.setHintView(new RecommendHintView(mContext
                    , mContext.getResources().getColor(R.color.color_rollpager_hint_bg)
                    , mContext.getResources().getColor(R.color.color_bg_top_white)
                    , 4, 5));
        } else if (itemViewType == TYPE_TITLE) {
            holder.setText(R.id.txt_title, menuInfo.getMenuName());
        } else if (itemViewType == TYPE_REC_MENU) {
            holder.setVisible(R.id.txt_menu_name);
            holder.setText(R.id.txt_menu_name, menuInfo.getMenuName());
            holder.getWidget(R.id.txt_playcount).setVisibility(View.VISIBLE);
            holder.getWidget(R.id.img_playcount).setVisibility(View.VISIBLE);
            String playcount = menuInfo.getPlaycount().toString().substring(0, 2);
            holder.setText(R.id.txt_playcount, playcount + "万");
            ImageView menuPic = (ImageView) holder.getWidget(R.id.img_latest_menu);
            if (menuInfo.getMenuPicurl() != null) {
                Glide.with(mContext).load(menuInfo.getMenuPicurl()).override(357, 357).placeholder(R.drawable.img_default_357_357).into(menuPic);
            }
        } else if (itemViewType == TYPE_UNIQ_NORMAL) {
            holder.setText(R.id.txt_menu_name, menuInfo.getMenuName());
            ImageView menuPic = (ImageView) holder.getWidget(R.id.img_latest_menu);

            if (menuInfo.getMenuPicurl() != null) {
                Glide.with(mContext).load(menuInfo.getMenuPicurl()).override(537, 303).placeholder(R.drawable.img_default_unique).into(menuPic);
            }
        } else if (itemViewType == TYPE_UNIQ_LAST) {
            holder.setText(R.id.txt_menu_name, menuInfo.getMenuName());
            ImageView menuPic = (ImageView) holder.getWidget(R.id.img_latest_menu);

            if (menuInfo.getMenuPicurl() != null) {
                Glide.with(mContext).load(menuInfo.getMenuPicurl()).override(1080, 420).placeholder(R.drawable.img_default_unique).into(menuPic);
            }
        } else if (itemViewType == TYPE_LATEST) {
            holder.setGone(R.id.txt_menu_name);
            holder.setVisible(R.id.txt_menu_desc);
            holder.setVisible(R.id.txt_menu_author);
            holder.setText(R.id.txt_menu_desc, menuInfo.getMenuDesc());
            holder.setText(R.id.txt_menu_author, menuInfo.getAuthorName());
            ImageView menuPic = (ImageView) holder.getWidget(R.id.img_latest_menu);
            if (menuInfo.getMenuPicurl() != null) {
                Glide.with(mContext).load(menuInfo.getMenuPicurl()).override(357, 357).placeholder(R.drawable.img_default_357_357).into(menuPic);
            }
        } else if (itemViewType == TYPE_REC_MV) {
            holder.setText(R.id.txt_menu_name, menuInfo.getMenuName());
            holder.setText(R.id.txt_menu_author, menuInfo.getAuthorName());
            ImageView menuPic = (ImageView) holder.getWidget(R.id.img_latest_menu);

            if (menuInfo.getMenuPicurl() != null) {
                Glide.with(mContext).load(menuInfo.getMenuPicurl()).override(537, 303).placeholder(R.drawable.img_default_unique).into(menuPic);
            }
        } else if (itemViewType == TYPE_PERFECT) {
            holder.setText(R.id.txt_desc, menuInfo.getMenuName());
            holder.setText(R.id.txt_capacity, "阅读 " + menuInfo.getPlaycount() + "");
            ImageView menuPic = (ImageView) holder.getWidget(R.id.img_perfect);

            if (menuInfo.getMenuPicurl() != null) {
                Glide.with(mContext).load(menuInfo.getMenuPicurl()).placeholder(R.drawable.img_default_357_237).override(357, 237).into(menuPic);
            }
        } else if (itemViewType == TYPE_RADIO) {
            holder.setVisible(R.id.txt_menu_name);
            holder.setText(R.id.txt_menu_name, menuInfo.getMenuName());
            holder.setText(R.id.txt_videoname, menuInfo.getAuthorName());
            ImageView menuPic = (ImageView) holder.getWidget(R.id.img_latest_menu);
            if (menuInfo.getMenuPicurl() != null) {
                Glide.with(mContext).load(menuInfo.getMenuPicurl()).override(357, 357).placeholder(R.drawable.img_default_357_357).into(menuPic);
            }
        } else if (itemViewType == TYPE_FOOTER) {

        }
    }

    @Override
    public int getItemCount() {
        return menuInfos == null ? 0 : menuInfos.size() + 2;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {

            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int itemViewType = getItemViewType(position);
                    switch (itemViewType) {
                        case TYPE_HEADER:
                            return 6;
                        case TYPE_TITLE:
                            return 6;
                        case TYPE_REC_MENU:
                            return 2;
                        case TYPE_UNIQ_NORMAL:
                            return 3;
                        case TYPE_UNIQ_LAST:
                            return 6;
                        case TYPE_LATEST:
                            return 2;
                        case TYPE_REC_MV:
                            return 3;
                        case TYPE_PERFECT:
                            return 6;
                        case TYPE_RADIO:
                            return 2;
                        case TYPE_FOOTER:
                            return 6;
                    }
                    return 0;
                }
            });
        }
    }
}
