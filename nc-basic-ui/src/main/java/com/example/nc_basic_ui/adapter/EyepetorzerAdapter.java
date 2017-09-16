package com.example.nc_basic_ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.nc_common_resource.utils.TimeUtils;
import com.example.nc_basic_ui.R;
import com.example.nc_basic_ui.view.RecommendHintView;
import com.example.nc_super_abs.adapter.eyes.MultiEyesAdapter;
import com.example.nc_super_abs.adapter.viewholder.BaseViewHolder;
import com.example.nc_super_abs.interaction.IWidgetClickListener;
import com.example.uc_common_bean.vo.HotItemInfo;
import com.jude.rollviewpager.RollPagerView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/8/2 18:52
 */
public class EyepetorzerAdapter extends MultiEyesAdapter<HotItemInfo, HotItemInfo> {

    private final int TYPE_HEADER = 999;//轮播图
    private final int TYPE_LIST = 998;
    private final int TYPE_TITLE = 997;
    private IWidgetClickListener clickListener;

    public EyepetorzerAdapter(Context mContext, List<HotItemInfo> headers, List<HotItemInfo> list, IWidgetClickListener clickListener) {
        super(mContext, headers, list);
        this.clickListener = clickListener;
    }

    public void add(List<HotItemInfo> infos) {

        list.addAll(infos);
        notifyDataSetChanged();
    }

    @Override
    public int getItemType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        } else if (position == 1) {
            return TYPE_TITLE;
        }
        return TYPE_LIST;
    }

    @Override
    public int getLayoutId(int type) {
        if (type == TYPE_HEADER) {
            return R.layout.adapter_item_header_rollview;
        } else if (type == TYPE_TITLE) {
            return R.layout.adapter_eye_title;
        }
        return R.layout.adapter_item_eye;
    }

    @Override
    public void convert(BaseViewHolder holder, int position) {

        if (position == 0) {
            RollPagerView rollpager_eye = (RollPagerView) holder.getWidget(R.id.rollpager_eye);
            rollpager_eye.setAdapter(new EyepertorzerRollAdapter(mContext, rollpager_eye, headers, clickListener));
            rollpager_eye.setHintView(new RecommendHintView(mContext
                    , mContext.getResources().getColor(R.color.color_rollpager_hint_bg)
                    , mContext.getResources().getColor(R.color.color_bg_white)
                    , 4, 5));
        } else if (position > 1) {

            int pos = position - 2;
            HotItemInfo hotItemInfo = list.get(pos);
            ImageView img_eye = (ImageView) holder.getWidget(R.id.img_eye);
            CircleImageView profile_image = (CircleImageView) holder.getWidget(R.id.profile_image);
            Glide.with(mContext).load(hotItemInfo.getDetail()).asBitmap().into(img_eye);
            Glide.with(mContext).load(hotItemInfo.getAuthorIcon()).asBitmap().into(profile_image);
            holder.setText(R.id.txt_title, hotItemInfo.getTitle());

            String desc = hotItemInfo.getAuthorName() + " / #" + hotItemInfo.getCategory() + " / " + TimeUtils.getTime((long) hotItemInfo.getDuration());
            holder.setText(R.id.txt_desc, desc);
            String time = TimeUtils.format((long) hotItemInfo.getData());
            holder.setText(R.id.txt_time, time);

            img_eye.setTag(R.string.video_str, String.valueOf(hotItemInfo.getDateId()));
            img_eye.setOnClickListener(this);
        }

    }

    @Override
    public int getCount() {
        return list != null ? list.size() + 2 : 0;
    }

    @Override
    public void onClick(View view) {
        clickListener.click(view);
    }
}
