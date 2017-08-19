package com.example.nc_basic_ui.controller;

import android.util.Log;

import com.example.nc_basic_ui.BuildConfig;
import com.example.nc_basic_ui.application.BasicUiApplication;
import com.example.nc_super_abs.interaction.ICommonInvokeResult;
import com.example.uc_common_bean.vo.MenuInfo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

/**
 * @version : 1.0
 * @Description : 推荐界面单元测试
 * @autho : dongyiming
 * @data : 2017/7/14 21:17
 */
//@RunWith(RobolectricTestRunner.class)
//@Config(constants = BuildConfig.class, sdk = 23, application = BasicUiApplication.class)
public class RecommendControllerTest {

    private RecommendController recommendController;

    @Before
    public void setUp() {
        //ShadowLog.stream = System.out;
        recommendController = new RecommendController(BasicUiApplication.getContext());
    }

    @Test
    public void testGet() throws Exception {
        try {
            /*recommendController = new RecommendController(BasicUiApplication.getContext());
            recommendController.getRecommend(new ICommonInvokeResult<List<MenuInfo>, String>() {
                @Override
                public void onResult(List<MenuInfo> menuInfos) {

                    for (MenuInfo menuInfo : menuInfos) {
                        Assert.assertNotNull("dongyiming", menuInfo.getMenuName());
                        Log.e("123456", menuInfo.getMenuName());
                    }
                }

                @Override
                public void onFailure(String exception) {
                    Assert.assertNotNull("dongyiming", exception);
                }

                @Override
                public void onCompleted() {
                    Assert.assertNotNull("dongyiming", "onCompleted");
                }
            });*/
        } catch (Exception e) {
            Assert.assertNotNull("dongyiming", e.toString());
        }

    }
}
