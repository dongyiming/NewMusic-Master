package com.example.nc_basic_ui.fragment.inner;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.example.nc_basic_ui.R;
import com.example.nc_basic_ui.adapter.MenuListAdapter;
import com.example.nc_basic_ui.controller.MenuListController;
import com.example.nc_common_resource.builder.RecyclerViewBuilder;
import com.example.nc_common_resource.view.LoadMoreFooter;
import com.example.nc_super_abs.fragment.BaseInnerFragment;
import com.example.nc_super_abs.interaction.ICommonInvokeResult;
import com.example.uc_common_bean.decoration.DecorationInfo;
import com.example.uc_common_bean.vo.MenuInfo;

import java.util.List;

/**
 * @version : 1.0
 * @Description : 歌单界面
 * @autho : dongyiming
 * @data : 2017/5/24 19:36
 */
public class MenusFragment extends BaseInnerFragment {

    /*变量*/
    private MenuListAdapter menuListAdapter;
    private AnimationDrawable loadingAnimation;

    /*控件*/
    private LoadMoreFooter footer;
    private RecyclerView recyclerView;
    private SwipeToLoadLayout swipeView;
    private MenuListController menuListController;

    /*常量*/
    private boolean isRefresh;
    private int startIndex = 0;
    private final int pageCount = 20;
    private final int type = 0;
    private int page = 0;

    @Override
    public View setRootView(LayoutInflater inflater) {

        View view = inflater.inflate(R.layout.fragment_catchat, null);

        swipeView = (SwipeToLoadLayout) view.findViewById(R.id.swipeToLoad);
        recyclerView = (RecyclerView) view.findViewById(R.id.swipe_target);
        footer = (LoadMoreFooter) view.findViewById(R.id.swipe_load_more_footer);
        swipeView.setLoadMoreFooterView(footer);
        return view;
    }

    @Override
    public void initWidget() {
        int color = this.getResources().getColor(R.color.color_padding_chatlist);
        int diverHeight = (int) this.getResources().getDimension(R.dimen.common_lh_18);
        int diverPadding = (int) this.getResources().getDimension(R.dimen.common_lh_2);

        recyclerView = new RecyclerViewBuilder(getActivity(), recyclerView)
                .setDefultGridLayoutManager(2)
                .setItemDecoration(new DecorationInfo().setColorResource(color).setLeftDividerWidth(diverPadding).setBottomDividerHeight(diverHeight), true)
                .create();
    }

    @Override
    public void initComponent() {

        menuListController = new MenuListController(getActivity());

    }

    @Override
    public void initData() {
        if (page == 0) {
            menuListController.selectByType(type, startIndex, pageCount, commonInvokeResult);
        }

        swipeView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                isRefresh = true;
                menuListController.selectByType(type, startIndex, pageCount, commonInvokeResult);
            }
        });
    }

    private ICommonInvokeResult<List<MenuInfo>, String> commonInvokeResult = new ICommonInvokeResult<List<MenuInfo>, String>() {
        @Override
        public void onResult(List<MenuInfo> menuInfos) {

            if (menuInfos != null && menuInfos.size() != 0) {

                if (page == 0) {
                    closeLoadingView();
                    menuListAdapter = new MenuListAdapter(getActivity(), menuInfos);
                    recyclerView.setAdapter(menuListAdapter);
                } else {
                    menuListAdapter.addData(menuInfos);
                }
                startIndex += pageCount;
                page++;
            } else {
                Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.str_no_more), Toast.LENGTH_SHORT).show();
            }
            if (isRefresh) {
                swipeView.setLoadingMore(false);
            }
        }

        @Override
        public void onFailure(String errorMsg) {
            if (page == 0) {
                recyclerView.setAdapter(null);
            }
            swipeView.setLoadingMore(false);
        }

        @Override
        public void onCompleted() {

        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
