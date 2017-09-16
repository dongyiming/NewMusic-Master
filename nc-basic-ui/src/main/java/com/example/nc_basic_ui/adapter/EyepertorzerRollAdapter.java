package com.example.nc_basic_ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.nc_basic_ui.R;
import com.example.nc_super_abs.interaction.IWidgetClickListener;
import com.example.uc_common_bean.vo.HotItemInfo;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;

import java.util.List;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/8/1 15:27
 */
public class EyepertorzerRollAdapter extends LoopPagerAdapter implements View.OnClickListener {

    private List<HotItemInfo> hotItemInfos;
    private Context mContext;
    private IWidgetClickListener clickListener;

    public EyepertorzerRollAdapter(Context mContext, RollPagerView viewPager, List<HotItemInfo> hotItemInfos, IWidgetClickListener clickListener) {
        super(viewPager);
        this.hotItemInfos = hotItemInfos;
        this.mContext = mContext;
        this.clickListener = clickListener;
    }

    @Override
    public View getView(ViewGroup container, int position) {
        ImageView view = new ImageView(mContext);
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        if (hotItemInfos != null && !hotItemInfos.isEmpty()) {

            String imageUrl = hotItemInfos.get(position).getImage();
//            if (imageUrl.endsWith(".jpeg")) {
//
//                Glide.with(mContext).load(imageUrl).asGif().diskCacheStrategy(DiskCacheStrategy.NONE).into(view);
//            } else {
//                Glide.with(mContext).load(imageUrl).asBitmap().into(view);
//            }
            Glide.with(mContext).load(imageUrl).asBitmap().into(view);
            view.setTag(R.string.card_str, imageUrl);
            view.setOnClickListener(this);
        }
        return view;
    }

    @Override
    public int getRealCount() {
        return hotItemInfos == null ? 0 : hotItemInfos.size();
    }

    @Override
    public void onClick(View view) {
        clickListener.click(view);
    }
}
