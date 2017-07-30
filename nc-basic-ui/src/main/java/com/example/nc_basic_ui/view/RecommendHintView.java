package com.example.nc_basic_ui.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

import com.jude.rollviewpager.Util;
import com.jude.rollviewpager.hintview.ShapeHintView;

/**
 * @version : 1.0
 * @Description : 自定义hint
 * @autho : dongyiming
 * @data : 2017/6/2 3:19
 */
public class RecommendHintView extends ShapeHintView {

    private float width;
    private float radius;
    private int focusColor;
    private int normalColor;

    public RecommendHintView(Context context, int focusColor, int normalColor, float radius, float width) {
        super(context);
        this.width = width;
        this.radius = radius;
        this.focusColor = focusColor;
        this.normalColor = normalColor;
    }

    @Override
    public Drawable makeFocusDrawable() {
        GradientDrawable dot_focus = new GradientDrawable();
        dot_focus.setColor(focusColor);
        dot_focus.setCornerRadius(Util.dip2px(getContext(), radius));
        dot_focus.setSize(Util.dip2px(getContext(), width), Util.dip2px(getContext(), width));
        return dot_focus;
    }

    @Override
    public Drawable makeNormalDrawable() {
        GradientDrawable dot_normal = new GradientDrawable();
        dot_normal.setColor(normalColor);
        dot_normal.setCornerRadius(Util.dip2px(getContext(), 4));
        dot_normal.setSize(Util.dip2px(getContext(), width), Util.dip2px(getContext(), width));
        return dot_normal;
    }
}
