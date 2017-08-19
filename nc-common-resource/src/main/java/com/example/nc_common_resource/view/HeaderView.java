package com.example.nc_common_resource.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.nc_common_resource.R;

/**
 *  @Description : header
 *  @autho : dongyiming
 *  @version : 1.0
 *  @data : 2017/8/14 9:39
 */
public class HeaderView  extends LinearLayout {

    private static final int STATE_NORMAL = 0;
    private static final int STATE_RELEASE_TO_REFRESH = 1;
    private static final int STATE_REFRESHING = 2;
    private static final int STATE_DONE = 3;
    private int mState = STATE_NORMAL;
    private LinearLayout mContainer;
    private ImageView mArrowImageView;
    private ProgressBar mProgressBar;
    private TextView mStatusTextView;
    private int mMeasuredHeight;

    public HeaderView(Context context) {
        super(context);
        init();
    }

    private void init() {
        mContainer = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.recyclerview_header_layout, null);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 0, 0, 0);
        this.setLayoutParams(lp);
        this.setPadding(0, 0, 0, 0);
        // 初始情况，设置下拉刷新view高度为0
        addView(mContainer, new LayoutParams(LayoutParams.MATCH_PARENT, 0));
        setGravity(Gravity.BOTTOM);

        mArrowImageView = (ImageView) findViewById(R.id.header_view_arrow);
        mStatusTextView = (TextView) findViewById(R.id.header_view_status);
        mProgressBar = (ProgressBar) findViewById(R.id.header_view_progressbar);

        measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mMeasuredHeight = getMeasuredHeight();
    }

    private void setState(int state) {
        if (state == mState) return;
        if (state == STATE_REFRESHING) {    // 显示进度
            mArrowImageView.clearAnimation();
            mArrowImageView.setVisibility(INVISIBLE);
            mProgressBar.setVisibility(VISIBLE);
        } else if (state == STATE_DONE) {
            mArrowImageView.setVisibility(INVISIBLE);
            mProgressBar.setVisibility(INVISIBLE);
        } else {    // 显示箭头图片
            mArrowImageView.setVisibility(VISIBLE);
            mProgressBar.setVisibility(INVISIBLE);
        }
        switch (state) {
            case STATE_NORMAL:
                if (mState == STATE_RELEASE_TO_REFRESH) {
                    Animation rotateDownAnim = new RotateAnimation(-180.0f, 0.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    rotateDownAnim.setDuration(180);
                    rotateDownAnim.setFillAfter(true);
                    mArrowImageView.startAnimation(rotateDownAnim);
                }
                if (mState == STATE_REFRESHING) {
                    mArrowImageView.clearAnimation();
                }
                mStatusTextView.setText(R.string.listview_header_hint_normal);
                break;
            case STATE_RELEASE_TO_REFRESH:
                if (mState != STATE_RELEASE_TO_REFRESH) {
                    Animation rotateUpAnim = new RotateAnimation(0.0f, -180.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    rotateUpAnim.setDuration(180);
                    rotateUpAnim.setFillAfter(true);
                    mArrowImageView.clearAnimation();
                    mArrowImageView.startAnimation(rotateUpAnim);
                    mStatusTextView.setText(R.string.listview_header_hint_release);
                }
                break;
            case STATE_REFRESHING:
                mStatusTextView.setText(R.string.refreshing);
                break;
            case STATE_DONE:
                mStatusTextView.setText(R.string.refresh_done);
                break;
            default:
        }
        mState = state;
    }

    public void refreshComplete() {
        setState(STATE_DONE);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                reset();
            }
        }, 200);
    }

    public void setRefreshingState() {
        setState(STATE_REFRESHING);
    }

    public boolean isRefreshing() {
        return mState == STATE_REFRESHING;
    }

    public void setVisibleHeight(int height) {
        if (height < 0)
            height = 0;
        LayoutParams lp = (LayoutParams) mContainer.getLayoutParams();
        lp.height = height;
        mContainer.setLayoutParams(lp);
    }

    public int getVisibleHeight() {
        LayoutParams lp = (LayoutParams) mContainer.getLayoutParams();
        return lp.height;
    }

    public void onMove(float delta) {
        if (getVisibleHeight() > 0 || delta > 0) {
            setVisibleHeight((int) delta + getVisibleHeight());
            if (mState <= STATE_RELEASE_TO_REFRESH) { // 未处于刷新状态，更新箭头
                if (getVisibleHeight() > mMeasuredHeight) {
                    setState(STATE_RELEASE_TO_REFRESH);
                } else {
                    setState(STATE_NORMAL);
                }
            }
        }
    }

    public boolean releaseAction() {
        boolean isOnRefresh = false;
        int height = getVisibleHeight();
        if (height == 0) // not visible.
            isOnRefresh = false;

        if (getVisibleHeight() > mMeasuredHeight && mState < STATE_REFRESHING) {
            setState(STATE_REFRESHING);
            isOnRefresh = true;
        }
        // refreshing and header isn't shown fully. do nothing.
        if (mState == STATE_REFRESHING && height <= mMeasuredHeight) {
            //return;
        }
        int destHeight = 0; // default: scroll back to dismiss header.
        // is refreshing, just scroll back to show all the header.
        if (mState == STATE_REFRESHING) {
            destHeight = mMeasuredHeight;
        }
        smoothScrollTo(destHeight);

        return isOnRefresh;
    }

    public void reset() {
        smoothScrollTo(0);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                setState(STATE_NORMAL);
            }
        }, 500);
    }

    private void smoothScrollTo(int destHeight) {
        ValueAnimator va = ValueAnimator.ofInt(getVisibleHeight(), destHeight);
        va.setDuration(300).start();
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                setVisibleHeight((int) animation.getAnimatedValue());
            }
        });
        va.start();
    }
}
