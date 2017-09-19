package com.example.nc_basic_ui.fragment.home;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.nc_basic_ui.R;
import com.example.nc_basic_ui.adapter.TuChongAdapter;
import com.example.nc_basic_ui.controller.TcImageController;
import com.example.nc_basic_ui.interaction.ITcImageView;
import com.example.nc_basic_ui.view.TcItemDecoration;
import com.example.nc_common_resource.view.RefreshRecyclerView;
import com.example.nc_super_abs.fragment.BaseInnerFragment;
import com.example.nc_super_abs.itemdecoration.CommonDecoration;
import com.example.uc_common_bean.decoration.DecorationInfo;
import com.example.uc_common_bean.vo.tc.TuChong;

import java.util.List;

/**
 * @version : 1.0
 * @Description : 发现
 * @autho : dongyiming
 * @data : 2017/5/23 18:00
 */
public class FoundFragment extends BaseInnerFragment implements ITcImageView {


    private RefreshRecyclerView recyclerview;
    private SwipeRefreshLayout swipeRefreshView;
    private TcImageController tcImageController;
    //首页序列号
    private int page = 1;
    private TuChongAdapter mAdapter;

    @Override
    public View setRootView(LayoutInflater inflater) {

        View view = inflater.inflate(R.layout.fragment_found, null);
        recyclerview = (RefreshRecyclerView) view.findViewById(R.id.recyclerview);
        swipeRefreshView = (SwipeRefreshLayout) view.findViewById(R.id.swipere_fresh_view);
        return view;
    }

    @Override
    public void registerWidgetEvent() {

        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerview.addItemDecoration(new TcItemDecoration(getActivity()));
        recyclerview.setPullRefreshEnabled(false);
        recyclerview.setOnRefreshLoadListener(refreshLoadListener);
        swipeRefreshView.setRefreshing(false);
        swipeRefreshView.setOnRefreshListener(refreshListener);
        swipeRefreshView.setColorSchemeResources(R.color.color_orange, R.color.color_orange);
    }

    @Override
    public void initComponent() {
        tcImageController = new TcImageController(getActivity(), this);
    }

    @Override
    public void initData() {
        tcImageController.onInitFinished();
    }

    @Override
    public void onRefresh(List<TuChong.FeedListBean> feedListBeen) {

        closeLoadingView();
        if (mAdapter != null) {
            mAdapter = null;
        }
        swipeRefreshView.setRefreshing(false);
        mAdapter = new TuChongAdapter(getActivity(), feedListBeen);
        recyclerview.setAdapter(mAdapter);
    }

    @Override
    public void onLoadMore(List<TuChong.FeedListBean> feedListBeen) {

        recyclerview.loadMoreComplete();
        if (mAdapter != null) {
            mAdapter.addAll(feedListBeen);
        }
    }

    @Override
    public void onLoadFailed(String errorMsg) {
        page--;
    }

    private RefreshRecyclerView.OnRefreshLoadListener refreshLoadListener = new RefreshRecyclerView.OnRefreshLoadListener() {
        @Override
        public void onRefresh() {
        }

        @Override
        public void onLoadMore() {
            page++;
            tcImageController.getMoreRecommendList(page);
        }
    };

    private SwipeRefreshLayout.OnRefreshListener refreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            tcImageController.onInitFinished();
        }
    };
}
