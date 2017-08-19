package com.example.nc_basic_ui.activity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.example.nc_basic_biz.utils.IntentUtils;
import com.example.nc_basic_biz.utils.TimeUtils;
import com.example.nc_basic_ui.R;
import com.example.nc_basic_ui.controller.EyepertorzerController;
import com.example.nc_basic_ui.view.IjkPlayer;
import com.example.nc_super_abs.activity.BaseActivity;
import com.example.uc_common_bean.vo.HotItemInfo;

import de.hdodenhof.circleimageview.CircleImageView;
import jp.wasabeef.glide.transformations.BlurTransformation;

import static android.view.KeyEvent.KEYCODE_BACK;

/**
 * @version : 1.0
 * @Description : 视频播放
 * @autho : dongyiming
 * @data : 2017/8/4 3:29
 */
public class AudioPlayerActivity extends BaseActivity {

    private RelativeLayout playerView;
    private String videoId;
    private EyepertorzerController eyepertorzerController;
    private IjkPlayer ijkPlayer;
    private HotItemInfo hotItemInfo;
    private TextView videoName;
    private TextView category;
    private TextView duration;
    private TextView content;
    private TextView love;
    private TextView share;
    private TextView msg;
    private CircleImageView img_author;
    private TextView txt_authorName;
    private TextView txt_authorDesc;
    private LinearLayout mContentView;
    private LinearLayout ayout_video_detail;

    @Override
    public void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_player);
        videoId = getIntent().getStringExtra("url");
    }

    @Override
    public void initWidget() {
        playerView = (RelativeLayout) findViewById(R.id.rlayout_player);
        mContentView = (LinearLayout) findViewById(R.id.llayout_content);
        ayout_video_detail = (LinearLayout) findViewById(R.id.layout_video_detail);
        videoName = (TextView) findViewById(R.id.txt_videoName);
        category = (TextView) findViewById(R.id.txt_category);
        duration = (TextView) findViewById(R.id.txt_duration);
        content = (TextView) findViewById(R.id.txt_content);
        love = (TextView) findViewById(R.id.txt_love);
        share = (TextView) findViewById(R.id.txt_share);
        msg = (TextView) findViewById(R.id.txt_msg);
        img_author = (CircleImageView) findViewById(R.id.cirImg_author);
        txt_authorName = (TextView) findViewById(R.id.txt_author_name);
        txt_authorDesc = (TextView) findViewById(R.id.txt_author_desc);
        getWindow().setReturnTransition(new Fade().setDuration(1000));
    }

    @Override
    public void initComponent() {
        eyepertorzerController = new EyepertorzerController(this);
        hotItemInfo = eyepertorzerController.selectByDateId(videoId);
        initData();
        ijkPlayer = new IjkPlayer(AudioPlayerActivity.this, playerView, hotItemInfo);
        ijkPlayer.hideOperateKey().loading()
                .setBackground().startPlay();
    }

    private void initData() {
        if (hotItemInfo != null) {
            Glide.with(AudioPlayerActivity.this)
                    .load(hotItemInfo.getBlurred())
                    /*设置高斯模糊效果,服务器请求的图片失真严重 sampling值越大颜色值会越浓*/
                    .bitmapTransform(new BlurTransformation(this, 25, 20))
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            mContentView.setVisibility(View.VISIBLE);
                            return false;
                        }
                    })
                    /*设置背景 glide3.7的为Bitmap,兼容高斯模糊的glide是GlideDrawable,getCurrent()再转换成drawable*/
                    .into(new SimpleTarget<GlideDrawable>() {
                        @Override
                        public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                mContentView.setBackground(resource.getCurrent());
                            }
                        }
                    });
            videoName.setText(hotItemInfo.getTitle());
            category.setText("#" + hotItemInfo.getCategory());
            duration.setText(TimeUtils.getTime((long) hotItemInfo.getDuration()));
            content.setText(hotItemInfo.getDescription());
            love.setText((int) hotItemInfo.getCollectionCount() + "");
            share.setText((int) hotItemInfo.getShareCount() + "");
            msg.setText((int) hotItemInfo.getReplyCount() + "");
            Glide.with(AudioPlayerActivity.this).load(hotItemInfo.getAuthorIcon()).into(img_author);
            txt_authorName.setText(hotItemInfo.getAuthorName());
            txt_authorDesc.setText(hotItemInfo.getAuthorDesc());
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (ijkPlayer != null) {
            ijkPlayer.onConfigurationChanged(newConfig);
            Log.e("dongyiming", "onConfigurationChanged");
        }

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if ((keyCode == KEYCODE_BACK) && ijkPlayer != null) {
            ijkPlayer.finishView();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public static void IntentActivity(Context mContext, String key, String value) {

        IntentUtils.startActivity(mContext, AudioPlayerActivity.class, key, value);
    }

    public static void startIntent(Activity mActivity, View view, String transName, String value) {

        Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(mActivity, view, transName).toBundle();
        Intent intent = new Intent();
        intent.setClass(mActivity, AudioPlayerActivity.class);
        intent.putExtra("url", value);
        mActivity.startActivity(intent, bundle);
    }
}