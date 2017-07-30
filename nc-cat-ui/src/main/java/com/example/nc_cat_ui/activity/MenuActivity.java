package com.example.nc_cat_ui.activity;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import com.example.nc_cat_ui.R;
import com.example.nc_super_abs.activity.BaseActivity;

/**
 * @version : 1.0
 * @Description : 歌单详情界面
 * @autho : dongyiming
 * @data : 2017/6/12 15:33
 */
public class MenuActivity extends BaseActivity {


    private Toolbar menuBar;

    @Override
    public void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_menu);
        menuBar = (Toolbar) findViewById(R.id.toolbar_menu);
        setSupportActionBar(menuBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        menuBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void initWidget() {
        super.initWidget();
    }

    @Override
    public void registerWidgetEvent() {
        super.registerWidgetEvent();
        menuBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void initComponent() {
        super.initComponent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_muc, menu);
        return true;
    }
}
