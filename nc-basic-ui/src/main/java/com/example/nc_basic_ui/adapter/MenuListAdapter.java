package com.example.nc_basic_ui.adapter;

import android.content.Context;
import android.os.Build;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.nc_basic_ui.R;
import com.example.nc_super_abs.adapter.viewholder.BaseViewHolder;
import com.example.nc_super_abs.adapter.viewholder.MultiBaseAdapter;
import com.example.uc_common_bean.vo.MenuInfo;

import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * @version : 1.0
 * @Description : chatlist adapter
 * @autho : dongyiming
 * @data : 2017/5/27 13:19
 */
public class MenuListAdapter extends MultiBaseAdapter<MenuInfo> {

    public static final int TYPE_HEADER = 999;
    public static final int TYPE_NORMAL = 998;
    private List<MenuInfo> datas;

    public MenuListAdapter(final Context mContext, List<MenuInfo> datas) {
        super(mContext, datas);
        this.datas = datas;
    }

    @Override
    public int getViewType(int position) {
        return position == 0 ? TYPE_HEADER : TYPE_NORMAL;
    }

    public void addData(List<MenuInfo> menuInfos) {

        datas.addAll(menuInfos);
        notifyDataSetChanged();
    }

    @Override
    public int getLayoutId(int viewType) {
        if (viewType == TYPE_HEADER) {
            return R.layout.fragment_list_header;
        } else if (viewType == TYPE_NORMAL) {
            return R.layout.adapter_chatlist_item;
        }
        return 0;
    }

    @Override
    public void convert(BaseViewHolder holder, MenuInfo menuInfo, int position, int itemViewType) {

        if (itemViewType == TYPE_NORMAL) {

            if (menuInfo != null) {
                holder.getWidget(R.id.txt_author).setAlpha(1.0f);
                holder.setText(R.id.txt_author, menuInfo.getAuthorName());
                holder.setText(R.id.txt_playcount, menuInfo.getPlaycount() + "");
                holder.setText(R.id.txt_item_desc, menuInfo.getMenuDesc());
                if (menuInfo.getMenuPicurl() != null) {

                    ImageView menuImage = (ImageView) holder.getWidget(R.id.img_chatlist_item);
                    Glide.with(mContext)
                            .load(menuInfo.getMenuPicurl())
                            .asBitmap()
                            .placeholder(R.drawable.img_chatlist_item_default)
                            .into(menuImage);
                }
            }
        } else {

            final RelativeLayout rlayout_header = (RelativeLayout)holder.getWidget(R.id.rlayout_header);
            ImageView img_header = (ImageView) holder.getWidget(R.id.img_header);
            Glide.with(mContext).load(R.drawable.img_menu_top).asBitmap().centerCrop().into(img_header);
            Glide.with(mContext)
                    .load(R.drawable.img_menu_top)
                    /*设置高斯模糊效果,服务器请求的图片失真严重 sampling值越大颜色值会越浓*/
                    .bitmapTransform(new BlurTransformation(mContext, 25, 20))
                    /*设置背景 glide3.7的为Bitmap,兼容高斯模糊的glide是GlideDrawable,getCurrent()再转换成drawable*/
                    .into(new SimpleTarget<GlideDrawable>() {
                        @Override
                        public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                rlayout_header.setBackground(resource.getCurrent());
                            }
                        }
                    });
        }
    }

    @Override
    public int setSpanSize(int position, int itemViewType) {
        return getItemViewType(position) == TYPE_HEADER ? 2 : 1;
    }

}
