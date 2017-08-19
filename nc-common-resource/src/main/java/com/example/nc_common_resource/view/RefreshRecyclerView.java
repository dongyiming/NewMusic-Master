package com.example.nc_common_resource.view;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import java.util.ArrayList;
import java.util.List;

/**
 * android refresh recyclerview
 */
public class RefreshRecyclerView extends RecyclerView {
    private HeaderView mHeaderView;
    private FooterView mFooterView;

    private boolean isLoadingData = false;
    private boolean isNoMore = false;

    private boolean pullRefreshEnabled = true;
    private boolean loadingMoreEnabled = true;

    private WrapAdapter mWrapAdapter;
    private float mLastY = -1;
    private OnRefreshLoadListener mOnRefreshLoadListener;


    private ArrayList<View> mHeaderViews = new ArrayList<>();
    private static List<Integer> mHeaderTypes = new ArrayList<>();//每个header必须有不同的type,不然滚动的时候顺序会变化
    //下面的ItemViewType是保留值(ReservedItemViewType),如果用户的adapter与它们重复将会强制抛出异常。不过为了简化,我们检测到重复时对用户的提示是ItemViewType必须小于10000
    private static final int TYPE_REFRESH_HEADER = 10000;//设置一个很大的数字,尽可能避免和用户的adapter冲突
    private static final int TYPE_FOOTER = 10001;
    private static final int HEADER_INIT_INDEX = 10002;

    private final AdapterDataObserver mDataObserver = new DataObserver();

    private AppBarState appBarState = AppBarState.EXPANDED;

    public RefreshRecyclerView(Context context) {
        this(context, null);
        init();
    }

    public RefreshRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        init();
    }

    public RefreshRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        if (pullRefreshEnabled) {
            mHeaderView = new HeaderView(getContext());
        }
        mFooterView = new FooterView(getContext());
        mFooterView.setVisibility(GONE);
        setItemAnimator(new DefaultItemAnimator());//设置默认ItemAnimator
    }

    public void addHeaderView(View view) {
        mHeaderTypes.add(HEADER_INIT_INDEX + mHeaderViews.size());
        mHeaderViews.add(view);
    }

    public int getHeaderViewSize() {
        return mHeaderViews.size();
    }

    /**
     * 根据header的ViewType判断是哪个header
     */
    private View getHeaderViewByType(int itemType) {
        if (!isHeaderType(itemType)) {
            return null;
        }
        return mHeaderViews.get(itemType - HEADER_INIT_INDEX);
    }

    /**
     * 判断一个type是否为HeaderType
     */
    private boolean isHeaderType(int itemViewType) {
        return mHeaderViews.size() > 0 && mHeaderTypes.contains(itemViewType);
    }

    /**
     * 判断是否是RefreshRecyclerView保留的itemViewType
     */
    private boolean isReservedItemViewType(int itemViewType) {
        return itemViewType == TYPE_REFRESH_HEADER || itemViewType == TYPE_FOOTER || mHeaderTypes.contains(itemViewType);
    }

    public void loadMoreComplete() {
        isLoadingData = false;
        mFooterView.setCompleteState();
    }

    public void setNoMore(boolean noMore) {
        isLoadingData = false;
        isNoMore = noMore;
        if (isNoMore) {
            mFooterView.setNoMoreState();
        } else {
            mFooterView.setCompleteState();
        }
    }

    public void reset() {
        setNoMore(false);
        loadMoreComplete();
        refreshComplete();
    }

    public void refreshComplete() {
        mHeaderView.refreshComplete();
        setNoMore(false);
    }

    public void setPullRefreshEnabled(boolean enabled) {
        pullRefreshEnabled = enabled;
    }

    public void setLoadingMoreEnabled(boolean enabled) {
        loadingMoreEnabled = enabled;
        if (!enabled) {
            mFooterView.setCompleteState();
        }
    }

    @Override
    public void setAdapter(Adapter adapter) {
        mWrapAdapter = new WrapAdapter(adapter);
        super.setAdapter(mWrapAdapter);
        adapter.registerAdapterDataObserver(mDataObserver);
        mDataObserver.onChanged();
    }

    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);
        if (state == RecyclerView.SCROLL_STATE_IDLE && mOnRefreshLoadListener != null && !isLoadingData && loadingMoreEnabled) {
            LayoutManager layoutManager = getLayoutManager();
            int lastVisibleItemPosition;
            if (layoutManager instanceof GridLayoutManager) {
                lastVisibleItemPosition = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                int[] into = new int[((StaggeredGridLayoutManager) layoutManager).getSpanCount()];
                ((StaggeredGridLayoutManager) layoutManager).findLastVisibleItemPositions(into);
                lastVisibleItemPosition = findMax(into);
            } else {
                lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
            }
            if (layoutManager.getChildCount() > 0
                    && lastVisibleItemPosition >= layoutManager.getItemCount() - 1 && layoutManager.getItemCount() > layoutManager.getChildCount() && !isNoMore && !mHeaderView.isRefreshing()) {
                isLoadingData = true;
                mFooterView.setLoadingState();
                mOnRefreshLoadListener.onLoadMore();
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mLastY == -1) {
            mLastY = ev.getRawY();
        }
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastY = ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                final float deltaY = ev.getRawY() - mLastY;
                mLastY = ev.getRawY();
                if (isOnTop() && pullRefreshEnabled && appBarState == AppBarState.EXPANDED) {
                    final float DRAG_RATE = 2;//阻尼系数，值越大越难下拉
                    mHeaderView.onMove(deltaY / DRAG_RATE);
                    if (mHeaderView.getVisibleHeight() > 0 && !mHeaderView.isRefreshing()) {
                        return false;
                    }
                }
                break;
            default:
                mLastY = -1; // reset
                if (isOnTop() && pullRefreshEnabled && appBarState == AppBarState.EXPANDED) {
                    if (mHeaderView.releaseAction()) {
                        if (mOnRefreshLoadListener != null) {
                            mOnRefreshLoadListener.onRefresh();
                        }
                    }
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    private int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    private boolean isOnTop() {
        return mHeaderView.getParent() != null;
    }

    private class DataObserver extends AdapterDataObserver {
        @Override
        public void onChanged() {
            if (mWrapAdapter != null) {
                mWrapAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            mWrapAdapter.notifyItemRangeInserted(positionStart, itemCount);
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            mWrapAdapter.notifyItemRangeChanged(positionStart, itemCount);
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
            mWrapAdapter.notifyItemRangeChanged(positionStart, itemCount, payload);
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            mWrapAdapter.notifyItemRangeRemoved(positionStart, itemCount);
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            mWrapAdapter.notifyItemMoved(fromPosition, toPosition);
        }
    }

    public class WrapAdapter extends RecyclerView.Adapter<ViewHolder> {
        private RecyclerView.Adapter adapter;

        public WrapAdapter(RecyclerView.Adapter adapter) {
            this.adapter = adapter;
        }

        public boolean isHeader(int position) {
            return position >= 1 && position < mHeaderViews.size() + 1;
        }

        public boolean isFooter(int position) {
            if (loadingMoreEnabled) {
                return position == getItemCount() - 1;
            } else {
                return false;
            }
        }

        public boolean isRefreshHeader(int position) {
            return position == 0;
        }

        public int getHeadersCount() {
            return mHeaderViews.size();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == TYPE_REFRESH_HEADER) {
                return new SimpleViewHolder(mHeaderView);
            } else if (isHeaderType(viewType)) {
                return new SimpleViewHolder(getHeaderViewByType(viewType));
            } else if (viewType == TYPE_FOOTER) {
                return new SimpleViewHolder(mFooterView);
            }
            return adapter.onCreateViewHolder(parent, viewType);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            if (isHeader(position) || isRefreshHeader(position)) {
                return;
            }
            int adjPosition = position - (getHeadersCount() + 1);
            int adapterCount;
            if (adapter != null) {
                adapterCount = adapter.getItemCount();
                if (adjPosition < adapterCount) {
                    adapter.onBindViewHolder(holder, adjPosition);
                    return;
                }
            }
        }

        @Override
        public int getItemCount() {
            if (loadingMoreEnabled) {
                if (adapter != null) {
                    return getHeadersCount() + adapter.getItemCount() + 2;
                } else {
                    return getHeadersCount() + 2;
                }
            } else {
                if (adapter != null) {
                    return getHeadersCount() + adapter.getItemCount() + 1;
                } else {
                    return getHeadersCount() + 1;
                }
            }
        }

        @Override
        public int getItemViewType(int position) {
            int adjPosition = position - (getHeadersCount() + 1);
            if (isReservedItemViewType(adapter.getItemViewType(adjPosition))) {
                throw new IllegalStateException("RefreshRecyclerView require itemViewType in adapter should be less than 10000 ");
            }
            if (isRefreshHeader(position)) {
                return TYPE_REFRESH_HEADER;
            }
            if (isHeader(position)) {
                position = position - 1;
                return mHeaderTypes.get(position);
            }
            if (isFooter(position)) {
                return TYPE_FOOTER;
            }

            int adapterCount;
            if (adapter != null) {
                adapterCount = adapter.getItemCount();
                if (adjPosition < adapterCount) {
                    return adapter.getItemViewType(adjPosition);
                }
            }
            return 0;
        }

        @Override
        public long getItemId(int position) {
            if (adapter != null && position >= getHeadersCount() + 1) {
                int adjPosition = position - (getHeadersCount() + 1);
                if (adjPosition < adapter.getItemCount()) {
                    return adapter.getItemId(adjPosition);
                }
            }
            return -1;
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
            LayoutManager manager = recyclerView.getLayoutManager();
            if (manager instanceof GridLayoutManager) {
                final GridLayoutManager gridManager = ((GridLayoutManager) manager);
                gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        return (isHeader(position) || isFooter(position) || isRefreshHeader(position))
                                ? gridManager.getSpanCount() : 1;
                    }
                });
            }
            adapter.onAttachedToRecyclerView(recyclerView);
        }

        @Override
        public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
            adapter.onDetachedFromRecyclerView(recyclerView);
        }

        @Override
        public void onViewAttachedToWindow(ViewHolder holder) {
            super.onViewAttachedToWindow(holder);
            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
            if (lp != null
                    && lp instanceof StaggeredGridLayoutManager.LayoutParams
                    && (isHeader(holder.getLayoutPosition()) || isRefreshHeader(holder.getLayoutPosition()) || isFooter(holder.getLayoutPosition()))) {
                StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
                p.setFullSpan(true);
            }
            adapter.onViewAttachedToWindow(holder);
        }

        @Override
        public void onViewDetachedFromWindow(ViewHolder holder) {
            adapter.onViewDetachedFromWindow(holder);
        }

        @Override
        public void onViewRecycled(ViewHolder holder) {
            adapter.onViewRecycled(holder);
        }

        @Override
        public boolean onFailedToRecycleView(ViewHolder holder) {
            return adapter.onFailedToRecycleView(holder);
        }

        @Override
        public void unregisterAdapterDataObserver(AdapterDataObserver observer) {
            adapter.unregisterAdapterDataObserver(observer);
        }

        @Override
        public void registerAdapterDataObserver(AdapterDataObserver observer) {
            adapter.registerAdapterDataObserver(observer);
        }

        private class SimpleViewHolder extends ViewHolder {
            public SimpleViewHolder(View itemView) {
                super(itemView);
            }
        }
    }

    public void autoRefresh() {
        if (pullRefreshEnabled && mOnRefreshLoadListener != null) {
            mHeaderView.setRefreshingState();
            mHeaderView.onMove(mHeaderView.getMeasuredHeight());
            mOnRefreshLoadListener.onRefresh();
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        //解决和CollapsingToolbarLayout冲突的问题
        AppBarLayout appBarLayout = null;
        ViewParent p = getParent();
        while (p != null) {
            if (p instanceof CoordinatorLayout) {
                break;
            }
            p = p.getParent();
        }
        if (p instanceof CoordinatorLayout) {
            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) p;
            final int childCount = coordinatorLayout.getChildCount();
            for (int i = childCount - 1; i >= 0; i--) {
                final View child = coordinatorLayout.getChildAt(i);
                if (child instanceof AppBarLayout) {
                    appBarLayout = (AppBarLayout) child;
                    break;
                }
            }
            if (appBarLayout != null) {
                appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {

                    @Override
                    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                        if (verticalOffset == 0) {
                            appBarState = AppBarState.EXPANDED;
                        } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                            appBarState = AppBarState.COLLAPSED;
                        } else {
                            appBarState = AppBarState.IDLE;
                        }
                    }
                });
            }
        }
    }

    public void setOnRefreshLoadListener(OnRefreshLoadListener listener) {
        mOnRefreshLoadListener = listener;
    }

    public interface OnRefreshLoadListener {

        void onRefresh();

        void onLoadMore();
    }

    public enum AppBarState {
        EXPANDED,
        COLLAPSED,
        IDLE
    }

    /*public class FooterView extends FrameLayout {
        private TextView mTextView;
        private ImageView loadingView;
        private AnimationDrawable animationDrawable;

        public FooterView(Context context) {
            super(context);
            init();
        }

        public void init() {

            View view = LayoutInflater.from(getContext()).inflate(R.layout.recyclerview_footer_layout, this);
            mTextView = (TextView) view.findViewById(R.id.txt_content);
            loadingView = (ImageView) view.findViewById(R.id.image_loading);
            setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        }

        public void setCompleteState() {
            mTextView.setText(R.string.loading);
            setVisibility(GONE);
        }

        public void setLoadingState() {
            loadingView.setVisibility(View.VISIBLE);
            if (animationDrawable == null && loadingView != null) {
                animationDrawable = (AnimationDrawable) loadingView.getDrawable();
            }
            if (!animationDrawable.isRunning()) {
                animationDrawable.start();
            }
            mTextView.setText(R.string.loading);
            setVisibility(VISIBLE);
        }

        public void setNoMoreState() {
            mTextView.setText(R.string.nomore_loading);
            if (animationDrawable != null && animationDrawable.isRunning()) {
                animationDrawable.stop();
            }
            loadingView.setVisibility(View.GONE);
            setVisibility(VISIBLE);
        }
    }*/

    /*private class HeaderView extends LinearLayout {
        private static final int STATE_NORMAL = 0;
        private static final int STATE_RELEASE_TO_REFRESH = 1;
        private static final int STATE_REFRESHING = 2;
        private static final int STATE_DONE = 3;
        private int mState = STATE_NORMAL;
        private LinearLayout mContainer;
        private ImageView mArrowImageView;
        private ProgressBar mProgressBar;
        private TextView mStatusTextView;
        private int mMeasuredHeight;

        public HeaderView(Context context) {
            super(context);
            init();
        }

        private void init() {
            mContainer = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.recyclerview_header_layout, null);
            LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            lp.setMargins(0, 0, 0, 0);
            this.setLayoutParams(lp);
            this.setPadding(0, 0, 0, 0);
            // 初始情况，设置下拉刷新view高度为0
            addView(mContainer, new LayoutParams(LayoutParams.MATCH_PARENT, 0));
            setGravity(Gravity.BOTTOM);

            mArrowImageView = (ImageView) findViewById(R.id.header_view_arrow);
            mStatusTextView = (TextView) findViewById(R.id.header_view_status);
            mProgressBar = (ProgressBar) findViewById(R.id.header_view_progressbar);

            measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            mMeasuredHeight = getMeasuredHeight();
        }

        private void setState(int state) {
            if (state == mState) return;
            if (state == STATE_REFRESHING) {    // 显示进度
                mArrowImageView.clearAnimation();
                mArrowImageView.setVisibility(INVISIBLE);
                mProgressBar.setVisibility(VISIBLE);
            } else if (state == STATE_DONE) {
                mArrowImageView.setVisibility(INVISIBLE);
                mProgressBar.setVisibility(INVISIBLE);
            } else {    // 显示箭头图片
                mArrowImageView.setVisibility(VISIBLE);
                mProgressBar.setVisibility(INVISIBLE);
            }
            switch (state) {
                case STATE_NORMAL:
                    if (mState == STATE_RELEASE_TO_REFRESH) {
                        Animation rotateDownAnim = new RotateAnimation(-180.0f, 0.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        rotateDownAnim.setDuration(180);
                        rotateDownAnim.setFillAfter(true);
                        mArrowImageView.startAnimation(rotateDownAnim);
                    }
                    if (mState == STATE_REFRESHING) {
                        mArrowImageView.clearAnimation();
                    }
                    mStatusTextView.setText(R.string.listview_header_hint_normal);
                    break;
                case STATE_RELEASE_TO_REFRESH:
                    if (mState != STATE_RELEASE_TO_REFRESH) {
                        Animation rotateUpAnim = new RotateAnimation(0.0f, -180.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        rotateUpAnim.setDuration(180);
                        rotateUpAnim.setFillAfter(true);
                        mArrowImageView.clearAnimation();
                        mArrowImageView.startAnimation(rotateUpAnim);
                        mStatusTextView.setText(R.string.listview_header_hint_release);
                    }
                    break;
                case STATE_REFRESHING:
                    mStatusTextView.setText(R.string.refreshing);
                    break;
                case STATE_DONE:
                    mStatusTextView.setText(R.string.refresh_done);
                    break;
                default:
            }
            mState = state;
        }

        public void refreshComplete() {
            setState(STATE_DONE);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    reset();
                }
            }, 200);
        }

        public void setRefreshingState() {
            setState(STATE_REFRESHING);
        }

        public boolean isRefreshing() {
            return mState == STATE_REFRESHING;
        }

        public void setVisibleHeight(int height) {
            if (height < 0)
                height = 0;
            LayoutParams lp = (LayoutParams) mContainer.getLayoutParams();
            lp.height = height;
            mContainer.setLayoutParams(lp);
        }

        public int getVisibleHeight() {
            LayoutParams lp = (LayoutParams) mContainer.getLayoutParams();
            return lp.height;
        }

        public void onMove(float delta) {
            if (getVisibleHeight() > 0 || delta > 0) {
                setVisibleHeight((int) delta + getVisibleHeight());
                if (mState <= STATE_RELEASE_TO_REFRESH) { // 未处于刷新状态，更新箭头
                    if (getVisibleHeight() > mMeasuredHeight) {
                        setState(STATE_RELEASE_TO_REFRESH);
                    } else {
                        setState(STATE_NORMAL);
                    }
                }
            }
        }

        public boolean releaseAction() {
            boolean isOnRefresh = false;
            int height = getVisibleHeight();
            if (height == 0) // not visible.
                isOnRefresh = false;

            if (getVisibleHeight() > mMeasuredHeight && mState < STATE_REFRESHING) {
                setState(STATE_REFRESHING);
                isOnRefresh = true;
            }
            // refreshing and header isn't shown fully. do nothing.
            if (mState == STATE_REFRESHING && height <= mMeasuredHeight) {
                //return;
            }
            int destHeight = 0; // default: scroll back to dismiss header.
            // is refreshing, just scroll back to show all the header.
            if (mState == STATE_REFRESHING) {
                destHeight = mMeasuredHeight;
            }
            smoothScrollTo(destHeight);

            return isOnRefresh;
        }

        public void reset() {
            smoothScrollTo(0);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    setState(STATE_NORMAL);
                }
            }, 500);
        }

        private void smoothScrollTo(int destHeight) {
            ValueAnimator va = ValueAnimator.ofInt(getVisibleHeight(), destHeight);
            va.setDuration(300).start();
            va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    setVisibleHeight((int) animation.getAnimatedValue());
                }
            });
            va.start();
        }
    }*/
}