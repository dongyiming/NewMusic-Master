package com.example.nc_basic_ui.view;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dou361.ijkplayer.widget.IjkVideoView;
import com.dou361.ijkplayer.widget.PlayStateParams;
import com.example.nc_basic_biz.utils.TimeUtils;
import com.example.nc_basic_ui.R;
import com.example.uc_common_bean.vo.HotItemInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * @version : 1.0
 * @Description : ijkplayer框架的基于项目界面的简单封装，界面仿照开眼
 * @autho : dongyiming
 * @data : 2017/8/16 9:20
 */
public class IjkPlayer {

    protected Logger logger = LoggerFactory.getLogger(getClass());
    private View mView;
    private Context mContext;
    private AudioManager audioManager;
    private int mMaxVolume;
    //声音进度调节
    private SeekBar voiceBar;
    //亮度进度调节
    private SeekBar brightBar;
    //视频进度调节
    private SeekBar videoBar;
    //当前声音
    private int currentVoice;
    //当前亮度
    private float currentBright;
    //seekbar的最大进度
    private final int MAX_PG = 100;
    private final int MAX_VIDEO_PG = 1000;
    private IjkVideoView videoView;
    private TextView startTime;
    private TextView totleTime;
    private int totleDuration;

    //是否开始拖动
    private boolean isDragging;
    private ImageView backView;
    private RelativeLayout loveView;
    private RelativeLayout shareView;
    private RelativeLayout transView;
    private ImageView playView;
    private RelativeLayout switchView;
    private RelativeLayout rlayout_function;

    private HotItemInfo hotItemInfo;
    private ImageView bgView;
    private ProgressBar progressView;
    //视频的状态
    private int status = PlayStateParams.STATE_IDLE;
    private RelativeLayout rlayout_bg;
    //视频准备完成
    private boolean isAlready;
    /**
     * 当前声音大小
     */
    private int volume;
    private float brightness;

    /**
     * 获取当前设备的宽度
     */
    private int screenWidthPixels;
    /**
     * 记录播放器竖屏时的高度
     */
    private int initHeight;
    private RelativeLayout rlayout_voice;
    private RelativeLayout rlayout_bright;

    private boolean isFunctionShow;
    private GestureDetector gestureDetector;
    private final int MESSAGE_AUDIO_PLAY = 1;
    private boolean isPortrait;
    private RelativeLayout rlayout_switch_full;
    private LinearLayout rlayout_full_progress;
    private TextView txt_full_start_time;
    private TextView txt_full_totle_time;
    private SeekBar seekbar_full_video;
    private RelativeLayout rlayout_progress;
    private LinearLayout llayout_three;
    private LinearLayout llayout_time;
    private boolean isFullScreen;

    /**
     * @param mContext
     * @param mView    : 回显区域
     */
    public IjkPlayer(Context mContext, View mView, HotItemInfo hotItemInfo) {

        this.mContext = mContext;
        this.mView = mView;
        this.hotItemInfo = hotItemInfo;
        initPlayer();
        initWidget();
        initManager();
        initGesture();//手势操作
    }

    //初始化控件
    private void initWidget() {

        voiceBar = (SeekBar) mView.findViewById(R.id.seekbar_voice);
        brightBar = (SeekBar) mView.findViewById(R.id.seekbar_bright);
        videoBar = (SeekBar) mView.findViewById(R.id.seekbar_video);
        progressView = (ProgressBar) mView.findViewById(R.id.progress);
        videoView = (IjkVideoView) mView.findViewById(R.id.video_view);
        startTime = (TextView) mView.findViewById(R.id.txt_time_start);
        totleTime = (TextView) mView.findViewById(R.id.txt_time_all);
        backView = (ImageView) mView.findViewById(R.id.img_video_back);
        playView = (ImageView) mView.findViewById(R.id.img_video_play);
        bgView = (ImageView) mView.findViewById(R.id.img_bg);
        loveView = (RelativeLayout) mView.findViewById(R.id.rlayout_video_love);
        shareView = (RelativeLayout) mView.findViewById(R.id.rlayout_video_share);
        transView = (RelativeLayout) mView.findViewById(R.id.rlayout_video_translate);
        switchView = (RelativeLayout) mView.findViewById(R.id.rlayout_switch);
        rlayout_voice = (RelativeLayout) mView.findViewById(R.id.rlayout_voice);
        rlayout_bright = (RelativeLayout) mView.findViewById(R.id.rlayout_bright);
        rlayout_function = (RelativeLayout) mView.findViewById(R.id.rlayout_function);
        rlayout_switch_full = (RelativeLayout) mView.findViewById(R.id.rlayout_switch_full);
        rlayout_full_progress = (LinearLayout) mView.findViewById(R.id.rlayout_full_progress);
        llayout_time = (LinearLayout) mView.findViewById(R.id.llayout_time);
        llayout_three = (LinearLayout) mView.findViewById(R.id.llayout_three);
        txt_full_start_time = (TextView) mView.findViewById(R.id.txt_full_start_time);
        txt_full_totle_time = (TextView) mView.findViewById(R.id.txt_full_totle_time);
        seekbar_full_video = (SeekBar) mView.findViewById(R.id.seekbar_full_video);
        rlayout_progress = (RelativeLayout) mView.findViewById(R.id.rlayout_progress);
        //整个播放界面
        rlayout_bg = (RelativeLayout) mView.findViewById(R.id.rlayout_bg);
        screenWidthPixels = mContext.getResources().getDisplayMetrics().widthPixels;
        setAttribute();
        registerWidgetEvent();
    }

    /**
     * 设置一些相关属性
     */
    private void setAttribute() {
        //seekbar默认有左右边距，必须设置padding值,布局设置无效
        videoBar.setPadding(0, 0, 0, 0);
        initHeight = mView.getLayoutParams().height;
        startTime.setText("00:00");
        if (hotItemInfo != null) {
            totleTime.setText(TimeUtils.generateTime((long) hotItemInfo.getDuration()));
        }
    }

    private void registerWidgetEvent() {

        backView.setOnClickListener(onClickListener);
        playView.setOnClickListener(onClickListener);
        loveView.setOnClickListener(onClickListener);
        shareView.setOnClickListener(onClickListener);
        transView.setOnClickListener(onClickListener);
        switchView.setOnClickListener(onClickListener);
        rlayout_switch_full.setOnClickListener(onClickListener);
        videoView.setOnInfoListener(onInfoListener);
    }

    /**
     * 手势
     */
    private void initGesture() {

        gestureDetector = new GestureDetector(mContext, new PlayerGestureListener());
        mView.setClickable(true);
        mView.setOnTouchListener(onTouchListener);
    }

    /**
     * 一些系统管理初始化
     *
     * @return
     */
    public void initManager() {

        initVoice();
        initBright();
        initVideo();
    }

    /*====界面处理===============================================================================================================*/

    /**
     * 隐藏所有的操作按键
     *
     * @return
     */
    public IjkPlayer hideOperateKey() {
        hideHalfScreen();
        return this;
    }

    public IjkPlayer loading() {

        setVisibe(progressView, true);
        return this;
    }

    /**
     * 设置背景图片
     *
     * @return
     */
    public IjkPlayer setBackground() {

        if (hotItemInfo != null && hotItemInfo.getDetail() != null) {
            Log.e("dongyiming", "setbackground");
            setVisibe(bgView, true);
            Glide.with(mContext).load(hotItemInfo.getDetail()).into(bgView);
        }
        return this;
    }

    /**
     * 屏幕切换时
     *
     * @param isFullScreen
     */
    private void fullScreenShow(boolean isFullScreen) {
        rlayout_function.setBackgroundResource(R.color.color_video_function_bg);
        setVisibe(llayout_three, true);
        setVisibe(playView, true);
        if (isFullScreen) {//全屏
            setVisibe(rlayout_progress, false);
            setVisibe(rlayout_full_progress, true);
            setVisibe(rlayout_switch_full, true);
            setVisibe(backView, false);
            setVisibe(llayout_time, false);
            setVisibe(switchView, false);
        } else {//半屏
            setVisibe(rlayout_progress, true);
            setVisibe(rlayout_full_progress, false);
            setVisibe(rlayout_switch_full, false);
            setVisibe(backView, true);
            setVisibe(llayout_time, true);
            setVisibe(switchView, true);
        }
    }

    private void hideHalfScreen() {
        rlayout_function.setBackground(null);
        setVisibe(llayout_three, false);
        setVisibe(playView, false);
        setVisibe(rlayout_progress, true);
        setVisibe(rlayout_full_progress, false);
        setVisibe(rlayout_switch_full, false);
        setVisibe(backView, false);
        setVisibe(llayout_time, false);
        setVisibe(switchView, false);
    }

    private void showHalfScreen() {
        rlayout_function.setBackgroundResource(R.color.color_video_function_bg);
        setVisibe(llayout_three, true);
        setVisibe(playView, true);
        setVisibe(rlayout_progress, true);
        setVisibe(backView, true);
        setVisibe(llayout_time, true);
        setVisibe(switchView, true);
    }

    private void hideFullScreen() {
        rlayout_function.setBackground(null);
        setVisibe(llayout_three, false);
        setVisibe(playView, false);
        setVisibe(rlayout_full_progress, false);
        setVisibe(rlayout_switch_full, false);
    }

    private void showFullScreen() {
        rlayout_function.setBackgroundResource(R.color.color_video_function_bg);
        setVisibe(llayout_three, true);
        setVisibe(playView, true);
        setVisibe(rlayout_full_progress, true);
        setVisibe(rlayout_switch_full, true);
    }

    /*====调用方法===============================================================================================================*/

    /**
     * 开始播放
     *
     * @return
     */
    public IjkPlayer startPlay() {

        if (hotItemInfo != null && hotItemInfo.getPlayUrl() != null) {
            videoView.setVideoPath(hotItemInfo.getPlayUrl());
            videoView.start();
        }
        return this;
    }

    /*====私有方法===============================================================================================================*/

    /**
     * 处理界面消息
     */
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_AUDIO_PLAY:
                    long pos = syncProgress();
                    if (!isDragging) {
                        msg = obtainMessage(MESSAGE_AUDIO_PLAY);
                        sendMessageDelayed(msg, 1000 - (pos % 1000));
                    }
                    break;
            }
        }
    };


    /**
     * 初始化sdk并加载动态库
     *
     * @return
     */
    private void initPlayer() {

        try {
            IjkMediaPlayer.loadLibrariesOnce(null);
            IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        } catch (Throwable e) {
            logger.error("failed to init ijkplayer with .{}", e);
        }
    }

    private void initVideo() {

        videoBar.setMax(MAX_VIDEO_PG);
        seekbar_full_video.setMax(MAX_VIDEO_PG);
        videoBar.setOnSeekBarChangeListener(videoListener);
        seekbar_full_video.setOnSeekBarChangeListener(videoListener);

    }

    //声音初始化
    private void initVoice() {

        //声音管理器，控制声音调节
        audioManager = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
        //最大声音
        mMaxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        voiceBar.setMax(MAX_PG);
        voiceBar.setOnSeekBarChangeListener(voiceListener);
    }

    //亮度初始化
    private void initBright() {

        brightBar.setMax(MAX_PG);
        brightBar.setOnSeekBarChangeListener(brightListener);
    }

    /**
     * 获取视频播放总时长
     *
     * @return
     */
    private int getDuration() {
        totleDuration = videoView.getDuration();
        return totleDuration;
    }

    /**
     * 切换屏幕
     *
     * @return
     */
    private IjkPlayer screenSwitch() {
        if (getScreenOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            ((Activity) mContext).setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            isFullScreen = false;
        } else {
            isFullScreen = true;
            ((Activity) mContext).setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        return this;
    }

    /**
     * 界面横竖屏界面的回调
     *
     * @param newConfig
     * @return
     */
    public IjkPlayer onConfigurationChanged(final Configuration newConfig) {
        isPortrait = newConfig.orientation == Configuration.ORIENTATION_PORTRAIT;
        doOnConfigurationChanged(isPortrait);
        return this;
    }

    /**
     * 界面方向改变是刷新界面
     */
    private void doOnConfigurationChanged(final boolean portrait) {
        if (mView != null) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    if (portrait) {
                        setStatusBarVisibility(false);
                        Log.e("dongyiming", "portrait");
                        ViewGroup.LayoutParams layoutParams = (ViewGroup.LayoutParams) mView.getLayoutParams();
                        layoutParams.height = initHeight;
                        mView.setLayoutParams(layoutParams);
                    } else {
                        setStatusBarVisibility(true);
                        int heightPixels = mContext.getResources().getDisplayMetrics().heightPixels;
                        int widthPixels = mContext.getResources().getDisplayMetrics().widthPixels;
                        Log.e("dongyiming", "!portrait" + heightPixels + "  " + widthPixels);
                        ViewGroup.LayoutParams layoutParams = (ViewGroup.LayoutParams) mView.getLayoutParams();
                        layoutParams.height = Math.min(heightPixels, widthPixels);
                        mView.setLayoutParams(layoutParams);
                    }
                }
            });
        }
    }

    /**
     * 获取当前屏幕方向
     *
     * @return
     */
    private int getScreenOrientation() {

        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        if (height > width) {//竖屏
            return ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        } else {//横屏
            return ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
        }
    }

    /**
     * 状态栏的隐藏和显示
     *
     * @param enable
     */
    private void setStatusBarVisibility(boolean enable) {
        WindowManager.LayoutParams lp = ((Activity) mContext).getWindow().getAttributes();
        if (enable) {
            lp.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        } else {
            lp.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        ((Activity) mContext).getWindow().setAttributes(lp);
        ((Activity) mContext).getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }


    /**
     * visible/gone
     *
     * @param view
     * @param isVisible
     */
    private void setVisibe(View view, boolean isVisible) {

        if (view != null) {
            if (isVisible) {
                view.setVisibility(View.VISIBLE);
            } else {
                view.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 声音设置
     *
     * @param percent
     */
    private void onVolumeSlide(float percent) {

        if (volume == -1) {
            volume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            if (volume < 0)
                volume = 0;
        }
        int index = (int) (percent * mMaxVolume) + volume;
        if (index > mMaxVolume)
            index = mMaxVolume;
        else if (index < 0)
            index = 0;

        // 变更声音
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, index, 0);
        // 变更进度条
        int i = (int) (index * 1.0 / mMaxVolume * 100);
        String s = i + "%";
        if (i == 0) {
            s = "off";
        }
    }

    /**
     * 亮度滑动改变亮度
     *
     * @param percent
     */
    private void onBrightnessSlide(float percent) {
        if (brightness < 0) {
            brightness = ((Activity) mContext).getWindow().getAttributes().screenBrightness;
            if (brightness <= 0.00f) {
                brightness = 0.50f;
            } else if (brightness < 0.01f) {
                brightness = 0.01f;
            }
        }
        WindowManager.LayoutParams lpa = ((Activity) mContext).getWindow().getAttributes();
        lpa.screenBrightness = brightness + percent;
        if (lpa.screenBrightness > 1.0f) {
            lpa.screenBrightness = 1.0f;
        } else if (lpa.screenBrightness < 0.01f) {
            lpa.screenBrightness = 0.01f;
        }
        //显示
        ((Activity) mContext).getWindow().setAttributes(lpa);
    }

    /**
     * 结束手势
     */
    private void endGesture() {
        volume = -1;
        brightness = -1f;
    }

    private long syncProgress() {

        long position = videoView.getCurrentPosition();
        long duration = videoView.getDuration();
        if (videoBar != null && !isFullScreen) {
            if (duration > 0) {
                long pos = 1000L * position / duration;
                videoBar.setProgress((int) pos);
            }
            int percent = videoView.getBufferPercentage();
            videoBar.setSecondaryProgress(percent * 10);
            if (isFunctionShow) {
                startTime.setText(TimeUtils.generateTime(position));
                totleTime.setText(TimeUtils.generateTime(duration));
            }
        } else if (seekbar_full_video != null && isFullScreen) {
            if (duration > 0) {
                long pos = 1000L * position / duration;
                seekbar_full_video.setProgress((int) pos);
            }
            int percent = videoView.getBufferPercentage();
            seekbar_full_video.setSecondaryProgress(percent * 10);
            if (isFunctionShow) {
                txt_full_start_time.setText(TimeUtils.generateTime(position));
                txt_full_totle_time.setText(TimeUtils.generateTime(duration));
            }
        }
        return position;
    }

    /*==监听方法=======================================================================================================================*/

    private IMediaPlayer.OnInfoListener onInfoListener = new IMediaPlayer.OnInfoListener() {

        @Override
        public boolean onInfo(IMediaPlayer iMediaPlayer, int what, int extra) {
            logger.info("now . the status of video is .{}", what);
            if (what == PlayStateParams.STATE_COMPLETED) {//播放完成
                setVisibe(backView, true);
                setVisibe(switchView, true);
                setBackground();
            } else if (what == PlayStateParams.STATE_PREPARING
                    || what == PlayStateParams.MEDIA_INFO_BUFFERING_START) {//播放准备中
                status = PlayStateParams.STATE_PREPARING;
                setVisibe(progressView, true);
                hideOperateKey();
            } else if (what == PlayStateParams.MEDIA_INFO_VIDEO_RENDERING_START
                    || what == PlayStateParams.STATE_PLAYING
                    || what == PlayStateParams.STATE_PREPARED
                    || what == PlayStateParams.MEDIA_INFO_BUFFERING_END
                    || what == PlayStateParams.STATE_PAUSED) {//准备完成
                if (status == PlayStateParams.STATE_PAUSED) {
                    status = PlayStateParams.STATE_PAUSED;
                } else {
                    status = PlayStateParams.STATE_PLAYING;
                }
                isAlready = true;
                //隐藏背景图片和加载
                setVisibe(rlayout_bg, false);
                setVisibe(progressView, false);
                handler.sendEmptyMessage(MESSAGE_AUDIO_PLAY);
            } else if (what == PlayStateParams.MEDIA_INFO_VIDEO_INTERRUPT) {//数据连接中断
                status = PlayStateParams.STATE_ERROR;
                //重新播放
            }
            return true;
        }
    };

    public void finishView() {

        if (videoView != null) {
            videoView.stopPlayback();
            ((Activity) mContext).finishAfterTransition();
        }
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if (view.getId() == R.id.img_video_back) {//左上角返回按钮
                finishView();
            } else if (view.getId() == R.id.img_video_play) {//中间的播放和暂停按钮

                if (videoView.isPlaying()) {
                    videoView.pause();
                    playView.setImageResource(R.drawable.img_video_pause);
                } else {
                    videoView.start();
                    playView.setImageResource(R.drawable.img_video_start);
                }
            } else if (view.getId() == R.id.rlayout_video_love) {//添加视频为喜欢

            } else if (view.getId() == R.id.rlayout_video_share) {//分享视频

            } else if (view.getId() == R.id.rlayout_video_translate) {//切换分辨率

            } else if (view.getId() == R.id.rlayout_switch) {//半屏放大为全屏
                screenSwitch();
                fullScreenShow(true);
            } else if (view.getId() == R.id.rlayout_switch_full) {//半屏放大为全屏
                screenSwitch();
                fullScreenShow(false);
            }
        }
    };

    private SeekBar.OnSeekBarChangeListener voiceListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            int index = (int) (mMaxVolume * progress * 0.01);
            if (index > mMaxVolume)
                index = mMaxVolume;
            else if (index < 0)
                index = 0;
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, index, 0);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            currentVoice = -1;
        }
    };

    private SeekBar.OnSeekBarChangeListener brightListener = new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            android.view.WindowManager.LayoutParams layout = ((Activity) mContext).getWindow().getAttributes();
            if (currentBright < 0) {
                currentBright = ((Activity) mContext).getWindow().getAttributes().screenBrightness;
                if (currentBright <= 0.00f) {
                    currentBright = 0.50f;
                } else if (currentBright < 0.01f) {
                    currentBright = 0.01f;
                }
            }
            if (progress < 1) {
                progress = 1;
            }
            if (progress > 100) {
                progress = 100;
            }
            layout.screenBrightness = 1.0F * (float) progress / 100.0F;
            if (layout.screenBrightness > 1.0f) {
                layout.screenBrightness = 1.0f;
            } else if (layout.screenBrightness < 0.01f) {
                layout.screenBrightness = 0.01f;
            }
            ((Activity) mContext).getWindow().setAttributes(layout);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            currentBright = -1;
        }
    };

    private SeekBar.OnSeekBarChangeListener videoListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            //系统自动播放进度，非手动调节进度
            if (!fromUser) {
                return;
            } else {
                int totleDuration = getDuration();
                int position = (int) ((totleDuration * progress * 1.0) / 1000);
                String time = TimeUtils.generateTime(position);
                startTime.setText(time);
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

            isDragging = true;
            handler.removeMessages(MESSAGE_AUDIO_PLAY);
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

            int totleDutation = getDuration();
            videoView.seekTo((int) ((totleDutation * seekBar.getProgress() * 1.0) / 1000));
            isDragging = false;
            handler.sendEmptyMessageDelayed(MESSAGE_AUDIO_PLAY, 1000);
        }
    };

    public class PlayerGestureListener extends GestureDetector.SimpleOnGestureListener {

        /**
         * 是否是按下的标识，默认为其他动作，true为按下标识，false为其他动作
         */
        private boolean isDownTouch;
        /**
         * 是否声音控制,默认为亮度控制，true为声音控制，false为亮度控制
         */
        private boolean isVolume;
        /**
         * 是否横向滑动，默认为纵向滑动，true为横向滑动，false为纵向滑动
         */
        private boolean isLandscape;

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

            float mOldX = e1.getX(), mOldY = e1.getY();
            float deltaY = mOldY - e2.getY();
            float deltaX = mOldX - e2.getX();
            //左半屏滑动为声音右边为亮度
            if (isDownTouch) {
                isLandscape = Math.abs(distanceX) >= Math.abs(distanceY);
                isVolume = mOldX > screenWidthPixels * 0.5f;
                isDownTouch = false;
            }
            if (!isLandscape) {
                float percent = deltaY / videoView.getHeight();
                if (isVolume) {
                    /**声音设置*/
                    onVolumeSlide(percent);
                } else {
                    /**亮度设置*/
                    onBrightnessSlide(percent);
                }
            }
            return super.onScroll(e1, e2, distanceX, distanceY);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return super.onFling(e1, e2, velocityX, velocityY);
        }

        @Override
        public boolean onDown(MotionEvent e) {

            Log.e("dongyiming", "ondown");
            isDownTouch = true;
            return super.onDown(e);
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {

            //双击播放或者暂停
            return true;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {

            Log.e("dongyiming", "onSingleTapUp");
            //显示,关闭所有功能键
            if (isAlready) {
                if (isFullScreen) {
                    if (isFunctionShow) {
                        hideFullScreen();
                        isFunctionShow = false;
                    } else {
                        showFullScreen();
                        isFunctionShow = true;
                    }
                } else {
                    if (isFunctionShow) {
                        hideHalfScreen();
                        isFunctionShow = false;
                    } else {
                        showHalfScreen();
                        isFunctionShow = true;
                    }
                }
            }
            return true;
        }
    }

    private View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent motionEvent) {
            if (gestureDetector.onTouchEvent(motionEvent))
                return true;
            // 处理手势结束
            switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_UP:
                    endGesture();
                    break;
            }
            return false;
        }
    };
}
