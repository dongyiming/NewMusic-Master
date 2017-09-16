package com.example.nc_basic_ui.fragment.inner;

import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.example.nc_common_resource.utils.TimeUtils;
import com.example.nc_basic_ui.R;
import com.example.nc_basic_ui.activity.NewsInfoActivity;
import com.example.nc_basic_ui.adapter.DailyNewsAdapter;
import com.example.nc_basic_ui.controller.DailyNewsController;
import com.example.nc_common_resource.view.RefreshRecyclerView;
import com.example.nc_super_abs.fragment.BaseInnerFragment;
import com.example.nc_super_abs.interaction.ICommonInvokeResult;
import com.example.nc_super_abs.interaction.IWidgetClickListener;
import com.example.uc_common_bean.vo.news.DailyNews;
import com.example.uc_common_bean.vo.news.LatestNews;

/**
 * @version : 1.0
 * @Description : 排行榜
 * @autho : dongyiming
 * @data : 2017/5/24 19:38
 */
public class DailyNewsFragment extends BaseInnerFragment implements IWidgetClickListener {


    private RefreshRecyclerView recyclerview;
    private DailyNewsController dailyNewsController;
    private IWidgetClickListener iWidgetClickListener;
    private String date;
    private DailyNewsAdapter mAdapter;

    @Override
    public View setRootView(LayoutInflater inflater) {

        View view = inflater.inflate(R.layout.fragment_daily_news, null);
        recyclerview = (RefreshRecyclerView) view.findViewById(R.id.recyclerview);
        recyclerview.setPullRefreshEnabled(false);
        iWidgetClickListener = this;
        return view;
    }

    @Override
    public void initComponent() {

        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        dailyNewsController = new DailyNewsController(getActivity());
        recyclerview.setOnRefreshLoadListener(refreshLoadListener);
    }

    @Override
    public void initData() {
        dailyNewsController.getLatestNews(commonInvokeResult);
    }

    private ICommonInvokeResult<LatestNews, String> commonInvokeResult = new ICommonInvokeResult<LatestNews, String>() {

        @Override
        public void onResult(LatestNews latestNews) {

            date = latestNews.getDate();
            closeLoadingView();
            mAdapter = new DailyNewsAdapter(getActivity(), latestNews.getTop_stories(), latestNews.getStories(), iWidgetClickListener);
            recyclerview.setAdapter(mAdapter);
        }

        @Override
        public void onFailure(String var1) {

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

            Log.e("dongyiming", "date = " + date);
            logger.info("start to load more news in time .{}", date);
            String date = TimeUtils.getDate(DailyNewsFragment.this.date);
            dailyNewsController.getDailyNews(date, invokeResult);
        }
    };

    private ICommonInvokeResult<DailyNews, String> invokeResult = new ICommonInvokeResult<DailyNews, String>() {
        @Override
        public void onResult(DailyNews dailyNews) {

            recyclerview.loadMoreComplete();
            date = dailyNews.getDate();
            if (mAdapter != null) {
                mAdapter.add(dailyNews.getStories());
            }
        }

        @Override
        public void onFailure(String var1) {
        }

        @Override
        public void onCompleted() {

        }
    };

    @Override
    public void click(View view) {

        int id = (int) view.getTag(R.string.app_name);
        NewsInfoActivity.intentActivity(getActivity(), "id", id + "");
    }
}
