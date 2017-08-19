package com.example.nc_basic_ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.example.nc_basic_ui.R;
import com.example.nc_basic_ui.view.RecommendHintView;
import com.example.nc_super_abs.adapter.eyes.MultiEyesAdapter;
import com.example.nc_super_abs.adapter.viewholder.BaseViewHolder;
import com.example.nc_super_abs.interaction.IWidgetClickListener;
import com.example.uc_common_bean.vo.news.News;
import com.example.uc_common_bean.vo.news.TitleNews;
import com.jude.rollviewpager.RollPagerView;

import java.util.List;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/8/5 21:36
 */
public class DailyNewsAdapter extends MultiEyesAdapter<TitleNews, News> {

    private final int TYPE_HEADER = 999;//轮播图
    private final int TYPE_LIST = 998;
    private IWidgetClickListener clickListener;

    public DailyNewsAdapter(Context mContext, List<TitleNews> headers, List<News> list, IWidgetClickListener clickListener) {
        super(mContext, headers, list);
        this.clickListener = clickListener;
    }

    @Override
    public int getItemType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        }
        return TYPE_LIST;
    }

    public void add(List<News> newsInfo) {

        this.list.addAll(newsInfo);
        notifyDataSetChanged();
    }

    @Override
    public int getLayoutId(int type) {
        if (type == TYPE_HEADER) {
            return R.layout.adapter_header_news;
        }
        return R.layout.adapter_item_daily;
    }

    @Override
    public void convert(BaseViewHolder holder, int position) {

        if (position == 0) {
            RollPagerView rollpager_news = (RollPagerView) holder.getWidget(R.id.rollpager_news);
            rollpager_news.setAdapter(new NewsRollAdapter(mContext, rollpager_news, headers, clickListener));
            rollpager_news.setHintView(new RecommendHintView(mContext
                    , mContext.getResources().getColor(R.color.color_rollpager_hint_bg)
                    , mContext.getResources().getColor(R.color.color_bg_white)
                    , 4, 5));
        } else {
            int pos = position - 1;
            News news = list.get(pos);
            holder.setText(R.id.txt_title, news.getTitle());
            ImageView imgView = (ImageView) holder.getWidget(R.id.img_item);
            RelativeLayout itemView = (RelativeLayout) holder.getWidget(R.id.rlayout_item);
            Glide.with(mContext).load(news.getImages()[0]).into(imgView);
            itemView.setTag(R.string.app_name, news.getId());
            itemView.setOnClickListener(this);
        }
    }

    @Override
    public int getCount() {
        return list != null ? list.size() + 1 : 0;
    }

    @Override
    public void onClick(View view) {
        clickListener.click(view);
    }
}
