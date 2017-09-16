package com.example.nc_super_abs.activity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.nc_super_abs.interaction.IActivityView;
import com.example.nc_super_abs.vo.UIState;

import java.lang.ref.WeakReference;
import java.util.List;


/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/5/23 12:50
 */
public abstract class BaseActivity extends AppCompatActivity implements IActivityView, View.OnClickListener {
    //protected Logger logger = LoggerFactory.getLogger(this.getClass());
    protected WeakReference<Context> mContext;
    protected int visible = 4;
    protected UIState state;

    public BaseActivity() {
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.state = UIState.CREATE;
        this.mContext = new WeakReference(this);
        ActivityStack.getInstance().addActivity(this);
        //this.logger.debug("UI Instance: {}, UIState: {}", this.mContext.get(), this.state);
        this.requestWindowFeature(1);
        this.setRootView();
        this.initWidget();
        this.registerWidgetEvent();
        this.initComponent();
        List<String> list = null;
    }

    protected void onRestart() {
        super.onRestart();
        this.state = UIState.RESTART;
        //this.logger.debug("UI Instance: {}, UIState: {}", this.mContext.get(), this.state);
    }

    protected void onResume() {
        super.onResume();
        this.visible = 0;
        this.state = UIState.RESUME;
        //this.logger.debug("UI Instance: {}, UIState: {}", this.mContext.get(), this.state);
    }

    protected void onPause() {
        super.onPause();
        this.visible = 4;
        this.state = UIState.PAUSE;
        //this.logger.debug("UI Instance: {}, UIState: {}", this.mContext.get(), this.state);
    }

    protected void onStop() {
        super.onStop();
        this.state = UIState.DESTROY;
        //this.logger.debug("UI Instance: {}, UIState: {}", this.mContext.get(), this.state);
    }

    protected void onDestroy() {
        super.onDestroy();
        ActivityStack.getInstance().finishActivity(this);
        this.state = UIState.DESTROY;
        //this.logger.debug("UI Instance: {}, UIState: {}", this.mContext.get(), this.state);
    }

    protected void setFullWindow() {
        this.getWindow().setFlags(1024, 1024);
    }

    public int getVisible() {
        return this.visible;
    }

    public UIState getState() {
        return this.state;
    }

    public FragmentActivity getInteractionView() {
        return this;
    }

    public void widgetClick(View v) {
    }

    public void onClick(View view) {
        this.widgetClick(view);
    }

    public void initWidget() {
    }

    public void initComponent() {
    }

    public void setRootView() {
    }

    public void registerWidgetEvent() {
    }
}
