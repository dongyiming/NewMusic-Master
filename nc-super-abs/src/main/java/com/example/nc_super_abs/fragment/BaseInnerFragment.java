package com.example.nc_super_abs.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.nc_super_abs.R;
import com.example.nc_super_abs.interaction.IFragmentView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.ref.WeakReference;

/**
 * @version : 1.0
 * @Description : viewpager里嵌套的viewpager的公共fragment
 * @autho : dongyiming
 * @data : 2017/7/29 19:53
 */
public abstract class BaseInnerFragment extends Fragment implements IFragmentView, View.OnClickListener {

    protected Logger logger = LoggerFactory.getLogger(getClass());
    protected boolean isVisible;
    protected boolean isPrepared;
    protected boolean isFirst;//第一次加载
    private ImageView loadingView;
    private AnimationDrawable animationDrawable;
    private LinearLayout loadingContent;
    private View bindView;
    protected WeakReference<Context> mContext;

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mContext = new WeakReference(activity);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_loading_layout, null);
        //加载子布局
        bindView = this.setRootView(inflater);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        bindView.setLayoutParams(params);
        RelativeLayout content = (RelativeLayout) contentView.findViewById(R.id.rlayout_show);
        loadingContent = (LinearLayout) contentView.findViewById(R.id.llayout_loading);
        loadingView = (ImageView) contentView.findViewById(R.id.img_progress);
        content.addView(bindView);
        bindView.setVisibility(View.GONE);
        return contentView;
    }

    //加载动画效果
    public void loading() {
        if (isVisible) {
            if (animationDrawable == null) {
                animationDrawable = (AnimationDrawable) loadingView.getDrawable();
            }
            if (!animationDrawable.isRunning()) {
                animationDrawable.start();
            }
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化controller
        this.initComponent();
        //开放数据处理
        isPrepared = true;
        onVisible();
    }

    //第一次加载界面时，最开始调用的生命周期方法
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.initWidget();
        this.registerWidgetEvent();
    }

    //界面展示才加载数据
    public void onVisible() {
        if (!isVisible || !isPrepared) {
            return;
        }
        if (!isFirst) {//第一次加载，其他使用缓存数据
            loading();
            initData();
            isFirst = true;
        }
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public Object getInteractionView() {
        return null;
    }

    @Override
    public void initWidget() {

    }

    @Override
    public void registerWidgetEvent() {

    }

    @Override
    public void widgetClick(View var1) {

    }

    public void onInvisible() {

    }

    /**
     * 关闭动画并且展示界面
     */
    protected void closeLoadingView() {
        if (loadingView.getVisibility() != View.GONE) {
            loadingView.setVisibility(View.GONE);
        }
        // 停止动画
        if (animationDrawable != null && animationDrawable.isRunning()) {
            animationDrawable.stop();
        }
        if (loadingContent != null && loadingContent.getVisibility() != View.GONE) {
            loadingContent.setVisibility(View.GONE);
        }
        if (bindView != null && bindView.getVisibility() != View.VISIBLE) {
            bindView.setVisibility(View.VISIBLE);
        }
    }

    public abstract View setRootView(LayoutInflater inflater);

    public abstract void initComponent();

    public abstract void initData();
}
