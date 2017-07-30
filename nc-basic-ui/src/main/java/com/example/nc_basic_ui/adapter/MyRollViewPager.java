package com.example.nc_basic_ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.nc_basic_ui.R;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;

/**
 * @version : 1.0
 * @Description : 推荐界面轮播图
 * @autho : dongyiming
 * @data : 2017/6/2 1:26
 */
public class MyRollViewPager extends LoopPagerAdapter {

    private Context mContext;
    String baseUrl = "http://192.168.1.102:8080/images/";
    private String[] imgs = new String[]{
            "rollpager_1.jpg"
            , "rollpager_2.jpg"
            , "rollpager_3.jpg"
            , "rollpager_4.jpg"
            , "rollpager_5.jpg"
            , "rollpager_2.jpg"
            , "rollpager_6.jpg"
            , "rollpager_7.jpg"

    };
    //private List<Drawable> imageList;

    public MyRollViewPager(Context mContext, RollPagerView viewPager) {
        super(viewPager);
        this.mContext = mContext;
        this.imgs = imgs;
        /*if (imageList == null) {
            imageList = new ArrayList<>();
            for (int i = 0; i < 7; i++) {
                imageList.add(mContext.getResources().getDrawable(R.drawable.img_recommend_rollpager));
            }
        }*/
    }

    @Override
    public View getView(ViewGroup container, int position) {
        ImageView view = new ImageView(mContext);
        //view.setImageDrawable(imageList.get(position));
        Glide.with(mContext).load(baseUrl + imgs[position]).asBitmap().placeholder(R.drawable.img_rollpager_2).into(view);
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return view;
    }

    @Override
    public int getRealCount() {
        return imgs.length;
    }
}
