package com.example.nc_basic_ui.fragment.inner;

import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.example.nc_common_resource.utils.IntentUtils;
import com.example.nc_common_resource.utils.MapUtils;
import com.example.nc_basic_ui.R;
import com.example.nc_basic_ui.activity.VideoPlayerActivity;
import com.example.nc_basic_ui.activity.EyepertorzerRollViewActivity;
import com.example.nc_basic_ui.adapter.EyepetorzerAdapter;
import com.example.nc_basic_ui.controller.EyepetorzerController;
import com.example.nc_common_resource.view.RefreshRecyclerView;
import com.example.nc_super_abs.fragment.BaseInnerFragment;
import com.example.nc_super_abs.interaction.ICommonInvokeResult;
import com.example.nc_super_abs.interaction.IWidgetClickListener;
import com.example.uc_common_bean.vo.HotItemInfo;

import java.util.HashMap;
import java.util.List;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/5/24 19:37
 */
public class EyepetorzerFragment extends BaseInnerFragment implements IWidgetClickListener {


    private EyepetorzerController eyepetorzerController;
    private RefreshRecyclerView recyclerView;
    private EyepetorzerAdapter mAdapter;
    private int START_INDEX = 0;
    private final int PAGE_COUNT = 20;

    @Override
    public View setRootView(LayoutInflater inflater) {

        View view = inflater.inflate(R.layout.fragment_eyepetorzer, null);
        recyclerView = (RefreshRecyclerView) view.findViewById(R.id.recycler_eye);
        return view;
    }

    @Override
    public void initComponent() {

        eyepetorzerController = new EyepetorzerController(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setPullRefreshEnabled(false);
        //下拉刷新
        recyclerView.setOnRefreshLoadListener(refreshLoadListener);
    }

    @Override
    public void initData() {

        httpInvoke();
    }

    private ICommonInvokeResult<HashMap<String, List<HotItemInfo>>, String> iCommonInvokeResult = new ICommonInvokeResult<HashMap<String, List<HotItemInfo>>, String>() {
        @Override
        public void onResult(HashMap<String, List<HotItemInfo>> hotItemInfos) {

            logger.info("successed to load infos with startIndex is .{} and count is .{}", START_INDEX, PAGE_COUNT);
            recyclerView.loadMoreComplete();
            //配置轮播图
            initFinish(hotItemInfos);
            START_INDEX += PAGE_COUNT;
        }

        @Override
        public void onFailure(String errorMsg) {

            logger.error("failed to load hotItemInfo whth .{}", errorMsg);
        }

        @Override
        public void onCompleted() {

        }
    };

    private RefreshRecyclerView.OnRefreshLoadListener refreshLoadListener = new RefreshRecyclerView.OnRefreshLoadListener() {
        @Override
        public void onRefresh() {

        }

        @Override
        public void onLoadMore() {
            httpInvoke();
        }
    };

    @Override
    public void click(View view) {

        String dateId = (String) view.getTag(R.string.video_str);
        String cardUrl = (String) view.getTag(R.string.card_str);
        if (cardUrl != null) {
            IntentUtils.startActivity(getActivity(), EyepertorzerRollViewActivity.class, "url", cardUrl);
        } else if (dateId != null) {
            VideoPlayerActivity.startIntent(getActivity(), view, "dongyiming",dateId);
        }

    }

    /**
     * http
     */
    private void httpInvoke() {

        eyepetorzerController.discoveryHot(START_INDEX, PAGE_COUNT, iCommonInvokeResult);
    }

    /**
     * data
     */
    public void initFinish(HashMap<String, List<HotItemInfo>> hotItemInfos) {

        List<HotItemInfo> videoBeanForClients = MapUtils.getInfos(hotItemInfos, getActivity().getResources().getString(R.string.video_str));
        if (START_INDEX == 0) {
            closeLoadingView();
            List<HotItemInfo> horizontalScrollCards = MapUtils.getInfos(hotItemInfos, getActivity().getResources().getString(R.string.card_str));
            mAdapter = new EyepetorzerAdapter(getActivity(), horizontalScrollCards, videoBeanForClients, this);
            recyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.add(videoBeanForClients);
        }
    }
}
