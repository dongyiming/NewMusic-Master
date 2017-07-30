package com.example.nc_basic_ui.fragment.inner;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.nc_basic_ui.R;
import com.example.nc_basic_ui.activity.RecommendSortActivity;
import com.example.nc_basic_ui.adapter.MyRollViewPager;
import com.example.nc_basic_ui.controller.ContentViewEngine;
import com.example.nc_basic_ui.controller.RecommendController;
import com.example.nc_basic_ui.view.RecommendHintView;
import com.example.nc_super_abs.fragment.BaseInnerFragment;
import com.jude.rollviewpager.RollPagerView;

/**
 * @version : 1.0
 * @Description :个性推荐界面 : 用了NestScrollerView贼他妈卡，弄了几天，改了好多界面设计方法
 * @autho : dongyiming
 * @data : 2017/5/24 19:35
 */
public class RecommendFragment extends BaseInnerFragment {

    /* widget */
    private LinearLayout hotRecLayout;
    private LinearLayout sortRecLayout;
    private LinearLayout dailyRecLayout;
    private LinearLayout privateFmLayout;
    private RelativeLayout itemCtrLayout;
    private RollPagerView rollpagerRecommend;
    private RecommendController recommendController;
    private ContentViewEngine contentViewEngine;
    private boolean isFirst;//第一次加载

    private boolean isPrepared;
    private Handler handler = new Handler();
    private RecyclerView recycler_recomment;

    @Override
    public View setRootView(LayoutInflater inflater) {

        View view = inflater.inflate(R.layout.fragment_recommend, null);
        rollpagerRecommend = (RollPagerView) view.findViewById(R.id.rollpager_recommend);
        recycler_recomment = (RecyclerView) view.findViewById(R.id.recycler_recomment);
        privateFmLayout = (LinearLayout) view.findViewById(R.id.rlayout_private_recommend);
        dailyRecLayout = (LinearLayout) view.findViewById(R.id.rlayout_daily_recommend);
        hotRecLayout = (LinearLayout) view.findViewById(R.id.rlayout_hotmusic_recommend);
        itemCtrLayout = (RelativeLayout) view.findViewById(R.id.rlayout_itemcontroller);
        return view;
    }

    @Override
    public void registerWidgetEvent() {
        super.registerWidgetEvent();
        itemCtrLayout.setOnClickListener(this);
    }

    @Override
    public void initComponent() {
        recommendController = new RecommendController(getActivity());
    }

    @Override
    public void initData() {


        //联网请求服务器数据的方法，自己调试时使用，演示时从本地获取数据
        /*recommendController.getRecommend(new ICommonInvokeResult<List<MenuInfo>, String>() {
            @Override
            public void OnResult(List<MenuInfo> var1) {
                //第一次使用
            }

            @Override
            public void onFailure(String errorInfo) {
                logger.error("RecommendController failed .{}", errorInfo);
            }

            @Override
            public void onCompleted() {

            }
        });*/
        if (!isFirst) {

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    showContentView();
                    isFirst = true;
                    initView();
                }
            }, 1000);
            return;
        }
        initView();
    }

    public void initView() {

        //配置轮播图
        rollpagerRecommend.setAdapter(new MyRollViewPager(getActivity(), rollpagerRecommend));
        rollpagerRecommend.setHintView(new RecommendHintView(getActivity()
                , getActivity().getResources().getColor(R.color.color_rollpager_hint_bg)
                , getActivity().getResources().getColor(R.color.color_bg_top_white)
                , 4, 5));

        contentViewEngine = new ContentViewEngine(getActivity());
        contentView.addView(contentViewEngine.buildRecMenu());
        contentView.addView(contentViewEngine.buildUnp());
        contentView.addView(contentViewEngine.buildLat());
        contentView.addView(contentViewEngine.buildRecMv());
        contentView.addView(contentViewEngine.buildPerf());
        contentView.addView(contentViewEngine.buildRad());
    }

    @Override
    public void widgetClick(View v) {

        int weightId = v.getId();
        if (weightId == R.id.rlayout_itemcontroller) {//调整栏目顺序
            Intent intent = new Intent();
            intent.setClass(getActivity(), RecommendSortActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onDestroyView() {

        super.onDestroyView();
    }

}
