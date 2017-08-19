package com.example.nc_basic_ui.activity;

import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.nc_basic_ui.R;
import com.example.nc_basic_ui.adapter.HomePageAdapter;
import com.example.nc_basic_ui.view.MyViewPager;
import com.example.nc_common_resource.view.CommonToolBar;
import com.example.nc_super_abs.activity.BaseActivity;


/**
 * @version : 1.0
 * @Description : 登入成功后的首页activity
 * @autho : dongyiming
 * @data : 2017/5/23 12:35
 */
public class HomePageActivity extends BaseActivity {

    RadioButton rdobtnUnname1;
    RadioButton rdobtnUnname2;
    RadioButton rdobtnUnname3;
    RadioButton rdobtnUnname4;
    RadioGroup rdogroupHomepage;
    MyViewPager viewpagerHomepage;
    //CommonToolBar commonbar;

    @Override
    public void setRootView() {

        setContentView(R.layout.activity_homepage);
    }

    @Override
    public void initWidget() {
        viewpagerHomepage = (MyViewPager) findViewById(R.id.viewpager_homepage);
        rdobtnUnname1 = (RadioButton) findViewById(R.id.rdobtn_unname1);
        rdobtnUnname2 = (RadioButton) findViewById(R.id.rdobtn_unname2);
        rdobtnUnname3 = (RadioButton) findViewById(R.id.rdobtn_unname3);
        rdobtnUnname4 = (RadioButton) findViewById(R.id.rdobtn_unname4);
        rdogroupHomepage = (RadioGroup) findViewById(R.id.rdogroup_homepage);
        //rdogroupHomepage.getBackground().setAlpha(255);
        viewpagerHomepage.setOffscreenPageLimit(3);
    }

    @Override
    public void registerWidgetEvent() {
        super.registerWidgetEvent();
        rdogroupHomepage.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.rdobtn_unname1) {

                    viewpagerHomepage.setCurrentItem(0, false);
                } else if (checkedId == R.id.rdobtn_unname2) {
                    viewpagerHomepage.setCurrentItem(1, false);
                } else if (checkedId == R.id.rdobtn_unname3) {
                    viewpagerHomepage.setCurrentItem(2, false);
                } else if (checkedId == R.id.rdobtn_unname4) {
                    viewpagerHomepage.setCurrentItem(3, false);
                }
                switchColor(checkedId);

            }
        });

        rdogroupHomepage.check(R.id.rdobtn_unname1);

        viewpagerHomepage.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {

                switch (position) {
                    case 0:
                        rdogroupHomepage.check(R.id.rdobtn_unname1);
                        break;
                    case 1:
                        rdogroupHomepage.check(R.id.rdobtn_unname2);
                        break;
                    case 2:
                        rdogroupHomepage.check(R.id.rdobtn_unname3);
                        break;
                    case 3:
                        rdogroupHomepage.check(R.id.rdobtn_unname4);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void initComponent() {
        super.initComponent();
        HomePageAdapter adapter = new HomePageAdapter(getSupportFragmentManager());
        adapter.setPagerCount(4);
        viewpagerHomepage.setAdapter(adapter);
    }

    public void switchColor(int id) {
        if (id == R.id.rdobtn_unname1) {
            rdobtnUnname1.setTextColor(HomePageActivity.this.getResources().getColor(R.color.color_btn_homepage_pressed));
            rdobtnUnname2.setTextColor(HomePageActivity.this.getResources().getColor(R.color.color_btn_homepage_normal));
            rdobtnUnname3.setTextColor(HomePageActivity.this.getResources().getColor(R.color.color_btn_homepage_normal));
            rdobtnUnname4.setTextColor(HomePageActivity.this.getResources().getColor(R.color.color_btn_homepage_normal));
        } else if (id == R.id.rdobtn_unname2) {
            rdobtnUnname1.setTextColor(HomePageActivity.this.getResources().getColor(R.color.color_btn_homepage_normal));
            rdobtnUnname2.setTextColor(HomePageActivity.this.getResources().getColor(R.color.color_btn_homepage_pressed));
            rdobtnUnname3.setTextColor(HomePageActivity.this.getResources().getColor(R.color.color_btn_homepage_normal));
            rdobtnUnname4.setTextColor(HomePageActivity.this.getResources().getColor(R.color.color_btn_homepage_normal));
        } else if (id == R.id.rdobtn_unname3) {
            rdobtnUnname1.setTextColor(HomePageActivity.this.getResources().getColor(R.color.color_btn_homepage_normal));
            rdobtnUnname2.setTextColor(HomePageActivity.this.getResources().getColor(R.color.color_btn_homepage_normal));
            rdobtnUnname3.setTextColor(HomePageActivity.this.getResources().getColor(R.color.color_btn_homepage_pressed));
            rdobtnUnname4.setTextColor(HomePageActivity.this.getResources().getColor(R.color.color_btn_homepage_normal));
        } else if (id == R.id.rdobtn_unname4) {
            rdobtnUnname1.setTextColor(HomePageActivity.this.getResources().getColor(R.color.color_btn_homepage_normal));
            rdobtnUnname2.setTextColor(HomePageActivity.this.getResources().getColor(R.color.color_btn_homepage_normal));
            rdobtnUnname3.setTextColor(HomePageActivity.this.getResources().getColor(R.color.color_btn_homepage_normal));
            rdobtnUnname4.setTextColor(HomePageActivity.this.getResources().getColor(R.color.color_btn_homepage_pressed));
        }
    }
}
