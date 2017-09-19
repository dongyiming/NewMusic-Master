package com.example.nc_basic_ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.example.nc_basic_ui.R;
import com.example.nc_basic_ui.activity.ImageDetailActivity;
import com.example.nc_basic_ui.view.GridImageView;
import com.example.nc_common_resource.utils.TimeUtils;
import com.example.nc_super_abs.adapter.CommonAdapter;
import com.example.nc_super_abs.adapter.viewholder.BaseViewHolder;
import com.example.uc_common_bean.vo.tc.ImageBean;
import com.example.uc_common_bean.vo.tc.TuChong;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @version : 1.0
 * @Description : 图片
 * @autho : dongyiming
 * @data : 2017/8/24 0:36
 */
public class TuChongAdapter extends CommonAdapter<TuChong.FeedListBean> {

    private Context mContext;
    private List<TuChong.FeedListBean> dataList;
    private TuChong.FeedListBean.SiteBean site;
    private List<ImageBean> imageList;

    public TuChongAdapter(Context mContext, List<TuChong.FeedListBean> dataList) {
        super(mContext, dataList);
        this.mContext = mContext;
        this.dataList = dataList;
    }

    public void addAll(List<TuChong.FeedListBean> list) {

        if (dataList != null) {
            dataList.addAll(list);
            notifyDataSetChanged();
        }
    }

    @Override
    public void convert(BaseViewHolder holder, final TuChong.FeedListBean feedListBean, int position) {

        if (feedListBean != null) {
            imageList = feedListBean.getImages();
            site = feedListBean.getSite();
            if (site != null) {
                CircleImageView authorView = (CircleImageView) holder.getWidget(R.id.circle_author);
                Glide.with(mContext).load(site.getIcon()).asBitmap().into(authorView);
                holder.setText(R.id.txt_author_name, site.getName());
            }
            if (imageList != null && imageList.size() > 6) {
                RelativeLayout rlayout_more = (RelativeLayout) holder.getWidget(R.id.rlayout_more);
                rlayout_more.setVisibility(View.VISIBLE);
                holder.setText(R.id.txt_pic_size, "共" + imageList.size() + "张照片");
                //错位
                rlayout_more.setTag(R.id.rlayout_more, imageList);
                rlayout_more.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        List<ImageBean> list = (List<ImageBean>) view.getTag(R.id.rlayout_more);
                        ImageDetailActivity.startActivity(mContext, ImageDetailActivity.class, list, 0);
                    }
                });
            } else {
                holder.setGone(R.id.rlayout_more);
            }
            //设置时间或者热门信息
            holder.setText(R.id.txt_time, TimeUtils.Dateformat(feedListBean.getPublished_at()));
            //设置标题
            if (feedListBean.getExcerpt() != null && !feedListBean.getExcerpt().equals("")) {
                holder.setVisible(R.id.rlayout_title);
                holder.setText(R.id.txt_title, feedListBean.getExcerpt());
            } else {
                holder.setGone(R.id.rlayout_title);
            }
            //设置描述
            if (feedListBean.getTitle() != null && !feedListBean.getTitle().equals("")) {
                holder.setVisible(R.id.rlayout_desc);
                holder.setText(R.id.txt_desc, feedListBean.getTitle());
            } else {
                holder.setGone(R.id.rlayout_desc);
            }
            //设置评论和喜欢
            holder.setText(R.id.txt_love, feedListBean.getFavorites() + "个喜欢");
            holder.setText(R.id.txt_comment, feedListBean.getComments() + "条评论");
            GridImageView gridImageView = (GridImageView) holder.getWidget(R.id.gridview_content);
            gridImageView.setTag(R.string.app_name, feedListBean.getUrl());
            gridImageView.addView(new GridImageViewAdapter<ImageBean>(mContext, imageList, feedListBean.getUrl()) {

                @Override
                public View getItemView(final int position, ImageBean imageBean, final List<ImageBean> list) {
                    if (getCount() == 0) {
                        return null;
                    }
                    ImageView imageView = new ImageView(mContext);
                    if (getCount() == 1) {
                        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    } else {
                        imageView.setScaleType(ImageView.ScaleType.CENTER);
                    }
                    final String url = "https://photo.tuchong.com/" + "/" + imageBean.getUser_id() + "/f/" + imageBean.getImg_id() + ".jpg";
                    imageView.setTag(R.string.app_name, url);
                    Glide.with(mContext).load(url).asBitmap().placeholder(R.drawable.img_tc_item_normal).error(R.drawable.img_tc_item_normal).into(imageView);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (view != null && view.getTag(R.string.app_name).equals(url)) {
                                ImageDetailActivity.startActivity(mContext, ImageDetailActivity.class, list, position);
                            }
                        }
                    });
                    return imageView;
                }
            });
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_tuchong;
    }

}
