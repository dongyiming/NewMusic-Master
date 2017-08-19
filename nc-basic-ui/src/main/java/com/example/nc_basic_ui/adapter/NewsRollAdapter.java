package com.example.nc_basic_ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.nc_basic_ui.R;
import com.example.nc_super_abs.interaction.IWidgetClickListener;
import com.example.uc_common_bean.vo.news.TitleNews;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;

import java.util.List;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/8/1 15:27
 */
public class NewsRollAdapter extends LoopPagerAdapter implements View.OnClickListener {

    private List<TitleNews> news;
    private Context mContext;
    private IWidgetClickListener clickListener;

    public NewsRollAdapter(Context mContext, RollPagerView viewPager, List<TitleNews> news, IWidgetClickListener clickListener) {
        super(viewPager);
        this.news = news;
        this.mContext = mContext;
        this.clickListener = clickListener;
    }

    @Override
    public View getView(ViewGroup container, int position) {

        View view = View.inflate(mContext, R.layout.rollpager_news_items, null);
        final ImageView imageView = (ImageView) view.findViewById(R.id.img_pager);
        String images = news.get(position).getImage();
        if (images != null) {
            Glide.with(mContext).load(images).asBitmap().into(imageView);
        }
        imageView.setTag(R.string.app_name, news.get(position).getId());
        imageView.setOnClickListener(this);
        return view;
    }

    @Override
    public int getRealCount() {
        return news.size();
    }

    @Override
    public void onClick(View view) {
        clickListener.click(view);
    }

}
