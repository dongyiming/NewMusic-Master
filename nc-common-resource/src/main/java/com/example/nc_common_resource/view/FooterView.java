package com.example.nc_common_resource.view;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nc_common_resource.R;

/**
 * @version : 1.0
 * @Description : footer
 * @autho : dongyiming
 * @data : 2017/8/14 9:35
 */
public class FooterView extends FrameLayout {

    private TextView mTextView;
    private ImageView loadingView;
    private AnimationDrawable animationDrawable;

    public FooterView(Context context) {
        super(context);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.recyclerview_footer_layout, this);
        mTextView = (TextView) view.findViewById(R.id.txt_content);
        loadingView = (ImageView) view.findViewById(R.id.image_loading);
        setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
    }

    public void setCompleteState() {
        mTextView.setText(R.string.loading);
        setVisibility(GONE);
    }

    public void setLoadingState() {
        loadingView.setVisibility(View.VISIBLE);
        if (animationDrawable == null && loadingView != null) {
            animationDrawable = (AnimationDrawable) loadingView.getDrawable();
        }
        if (!animationDrawable.isRunning()) {
            animationDrawable.start();
        }
        mTextView.setText(R.string.loading);
        setVisibility(VISIBLE);
    }

    public void setNoMoreState() {
        mTextView.setText(R.string.nomore_loading);
        if (animationDrawable != null && animationDrawable.isRunning()) {
            animationDrawable.stop();
        }
        loadingView.setVisibility(View.GONE);
        setVisibility(VISIBLE);
    }
}
