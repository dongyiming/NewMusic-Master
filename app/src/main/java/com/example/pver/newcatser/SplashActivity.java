package com.example.pver.newcatser;

import android.content.Context;
import android.os.Handler;
import android.transition.Explode;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.nc_common_resource.utils.AnimUtils;
import com.example.nc_common_resource.utils.IntentUtils;
import com.example.nc_common_resource.utils.TypeUtils;
import com.example.nc_common_resource.utils.WindowUtils;
import com.example.nc_basic_ui.activity.HomePageActivity;
import com.example.nc_super_abs.activity.BaseActivity;

/**
 * @version : 1.0
 * @Description : 开机启动界面
 * @autho : dongyiming
 * @data : 2017/9/1 23:24
 */
public class SplashActivity extends BaseActivity {

    private ImageView img_splash;
    private Handler handler = new Handler();
    private TextView txt_name;
    private Context mConetxt;
    private RelativeLayout rlayout_start;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_splash);
        this.mConetxt = SplashActivity.this;
        img_splash = (ImageView) findViewById(R.id.img_splash);
        txt_name = (TextView) findViewById(R.id.txt_name);
        rlayout_start = (RelativeLayout) findViewById(R.id.rlayout_start);
        WindowUtils.setStatusTransparent(this);
    }

    @Override
    public void registerWidgetEvent() {

        // 启动新 Activity ,此页面退出的动画
        getWindow().setExitTransition(new Explode().setDuration(1000));
        txt_name.setTypeface(TypeUtils.getType(mConetxt));
        Animation animation = AnimUtils.loadAnimation(mConetxt, R.anim.anim_splash);
        img_splash.startAnimation(animation);
        animation.setAnimationListener(listener);
        rlayout_start.setOnClickListener(this);
    }

    Animation.AnimationListener listener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    txt_name.setVisibility(View.VISIBLE);
                    txt_name.startAnimation(AnimUtils.loadAnimation(mConetxt, R.anim.anim_name));
                }
            }, 1000);
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            rlayout_start.setVisibility(View.VISIBLE);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }
    };

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.rlayout_start) {
            IntentUtils.startTransitionActivity(SplashActivity.this, HomePageActivity.class);
        }
    }
}
