package com.example.nc_basic_ui.fragment.inner;

import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.example.nc_basic_ui.R;
import com.example.nc_basic_ui.adapter.RecAdapter;
import com.example.nc_basic_ui.controller.RecommendController;
import com.example.nc_basic_ui.view.RecommendItemDrcoration;
import com.example.nc_super_abs.fragment.BaseInnerFragment;
import com.example.nc_super_abs.interaction.ICommonInvokeResult;
import com.example.uc_common_bean.vo.MenuInfo;

import java.util.List;

/**
 * @version : 1.0
 * @Description :个性推荐界面 : 用了NestScrollerView贼他妈卡，弄了几天，改了好多界面设计方法
 * @autho : dongyiming
 * @data : 2017/5/24 19:35
 */
public class RecommendFragment extends BaseInnerFragment {

    /* widget */
    private RecommendController recommendController;

    private Handler handler = new Handler();
    private RecyclerView recycler_recomment;

    @Override
    public View setRootView(LayoutInflater inflater) {

        View view = inflater.inflate(R.layout.fragment_recommend, null);
        recycler_recomment = (RecyclerView) view.findViewById(R.id.recycler_recomment);
        recycler_recomment.setNestedScrollingEnabled(false);
        return view;
    }

    @Override
    public void registerWidgetEvent() {
        super.registerWidgetEvent();
    }

    @Override
    public void initComponent() {
        recommendController = new RecommendController(getActivity());
    }

    @Override
    public void initData() {

        Log.e("dongyiming", "RecommendFragment initData");
        //联网请求服务器数据的方法，自己调试时使用，演示时从本地获取数据
        /*recommendController.getRecommend(new ICommonInvokeResult<List<MenuInfo>, String>() {
            @Override
            public void onResult(List<MenuInfo> var1) {
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
       handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                showContentView();
                initView();
            }
        }, 1000);
    }

    public void initView() {

        recycler_recomment.setLayoutManager(new GridLayoutManager(getActivity(), 6));
        recycler_recomment.addItemDecoration(new RecommendItemDrcoration(getActivity()));
        List<MenuInfo> list = recommendController.getList();
        RecAdapter recAdapter = new RecAdapter(getActivity(), list);
        recycler_recomment.setAdapter(recAdapter);
    }

    @Override
    public void widgetClick(View v) {

        /*int weightId = v.getId();
        if (weightId == R.id.rlayout_itemcontroller) {//调整栏目顺序
            Intent intent = new Intent();
            intent.setClass(getActivity(), RecommendSortActivity.class);
            startActivity(intent);
        }*/
    }

    @Override
    public void onDestroyView() {

        super.onDestroyView();
    }

}
