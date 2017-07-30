package com.example.nc_basic_ui.activity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.nc_basic_ui.R;
import com.example.nc_basic_ui.adapter.SortAdapter;
import com.example.nc_common_resource.builder.RecyclerViewBuilder;
import com.example.nc_common_resource.view.DragSortRecycler;
import com.example.nc_super_abs.activity.BaseActivity;
import com.example.uc_common_bean.decoration.DecorationInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @version : 1.0
 * @Description : 个性推荐-调整栏目顺序
 * @autho : dongyiming
 * @data : 2017/6/3 10:06
 */
public class RecommendSortActivity extends BaseActivity {

    private Context mContext;
    private LinearLayout toolbar;

    private RelativeLayout rlayout_back;
    private RecyclerView recycler_sort;
    private SortAdapter sortAdapter;

    private int diverHeight;
    private int diverLeft;
    private int color;

    @Override
    public void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_recsort);
        mContext = RecommendSortActivity.this;
    }

    @Override
    public void initWidget() {
        toolbar = (LinearLayout) findViewById(R.id.toolbar);
        rlayout_back = (RelativeLayout) findViewById(R.id.rlayout_back);
        recycler_sort = (RecyclerView) findViewById(R.id.recycler_sort);
        diverHeight = (int) mContext.getResources().getDimension(R.dimen.common_lh_0);
        diverLeft = (int) mContext.getResources().getDimension(R.dimen.dimens_recommend_sort_item_maginleft);
        color = mContext.getResources().getColor(R.color.color_gray_text);
    }

    @Override
    public void registerWidgetEvent() {
        rlayout_back.setOnClickListener(this);
    }

    @Override
    public void initComponent() {
        super.initComponent();
        recycler_sort = new RecyclerViewBuilder(mContext, recycler_sort).setDefaultLinearLayoutManager()
                .setItemDecoration(new DecorationInfo().setVerticalDividerHeight(diverHeight)
                        .setVerticalDividerMaginLeft(diverLeft).setColorResource(color), false).create();

        sortAdapter = new SortAdapter(mContext, getList());
        recycler_sort.setAdapter(sortAdapter);

        DragSortRecycler dragSortRecycler = new DragSortRecycler();
        dragSortRecycler.setViewHandleId(R.id.rlayout_drag);
        dragSortRecycler.setOnItemMovedListener(new DragSortRecycler.OnItemMovedListener() {
            @Override
            public void onItemMoved(int from, int to) {

                final String str = sortAdapter.getItemName(from);
                sortAdapter.removeItem(from);
                sortAdapter.addItemIndex(to, str);
                sortAdapter.notifyDataSetChanged();
            }
        });
        recycler_sort.addItemDecoration(dragSortRecycler);
        recycler_sort.addOnItemTouchListener(dragSortRecycler);
        recycler_sort.addOnScrollListener(dragSortRecycler.getScrollListener());
        recycler_sort.setHasFixedSize(true);
    }

    public List<String> getList() {

        List<String> nameList = new ArrayList<>();
        nameList.add(mContext.getResources().getString(R.string.recommend_itemcontroller_item1_str));
        nameList.add(mContext.getResources().getString(R.string.recommend_itemcontroller_item2_str));
        nameList.add(mContext.getResources().getString(R.string.recommend_itemcontroller_item3_str));
        nameList.add(mContext.getResources().getString(R.string.recommend_itemcontroller_item4_str));
        nameList.add(mContext.getResources().getString(R.string.recommend_itemcontroller_item5_str));
        nameList.add(mContext.getResources().getString(R.string.recommend_itemcontroller_item6_str));
        return nameList;
    }
}
