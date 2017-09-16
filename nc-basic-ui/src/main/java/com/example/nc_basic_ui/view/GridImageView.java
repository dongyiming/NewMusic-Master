package com.example.nc_basic_ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.nc_basic_ui.R;
import com.example.nc_basic_ui.adapter.GridImageViewAdapter;
import com.example.nc_basic_ui.interaction.IGridImageViewListener;
import com.example.uc_common_bean.vo.tc.ImageBean;


/**
 * @version : 1.0
 * @Description : 自定义图片展示区域
 * @autho : dongyiming
 * @data : 2017/8/28 0:49
 */
public class GridImageView extends ViewGroup {


    //默认的图片高度:330px
    private final int DEFAULT_HEIGHT = 330;
    //图片左右/上下间距:9px
    private final int DEFAULT_DIVER = 9;
    //屏幕宽
    private int widthPixels = 1080;
    private IGridImageViewListener<ImageBean> listener;

    //每个item的长/宽
    private double reallyWidth = 0, reallyHeight = 0;
    //viewgroup的高度
    private double contentHeight = 0;


    public GridImageView(Context mContext) {
        this(mContext, null);
    }

    public GridImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GridImageView(Context mContext, AttributeSet attrs, int defStyleAttr) {
        super(mContext, attrs, defStyleAttr);
    }

    /**
     * 添加item
     *
     * @param listener
     */
    public void addView(IGridImageViewListener<ImageBean> listener) {

        this.listener = listener;
        int count = listener.getCount();
        removeAllViews();
        for (int i = 0; i < count; i++) {
            //Log.e("dongyiming", "addview  count = " + count);
            addView(listener.getItemView(i), generateDefaultLayoutParams());
            if (i == 5) {
                return;
            }
        }
    }

    private GridImageViewAdapter<ImageBean> gridImageViewAdapter;

    public void addView(GridImageViewAdapter<ImageBean> gridImageViewAdapter) {

        this.gridImageViewAdapter = gridImageViewAdapter;
        removeAllViews();
        int count = gridImageViewAdapter.getCount();
        for (int i = 0; i < count; i++) {
            //Log.e("dongyiming", "addview  count ================================= " + count);
            addView(gridImageViewAdapter.getItemView(i), generateDefaultLayoutParams());
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (gridImageViewAdapter == null || gridImageViewAdapter.getCount() == 0) {
            return;
        }
        if (gridImageViewAdapter.getCount() == 1) {//只有一张图片时，按照图片的原始宽高进行等比例缩放
            ImageBean imageBean = gridImageViewAdapter.getPositionItem(0);
            //Log.e("dongyiming", "imageBean.getwidth = " + imageBean.getWidth() + "  height = " + imageBean.getHeight() + "  widthPixels = " + widthPixels);
            reallyWidth = widthPixels;
            reallyHeight = (widthPixels * 1.0 / imageBean.getWidth()) * imageBean.getHeight();
            contentHeight = reallyHeight;
        } else if (gridImageViewAdapter.getCount() == 2) {//两张照片时均分区域
            //Log.e("dongyiming", "widthPixels = " + widthPixels);
            reallyWidth = (widthPixels - DEFAULT_DIVER) / 2;
            reallyHeight = DEFAULT_HEIGHT;
            contentHeight = reallyHeight;
        } else { //每行三张均分，最多两行
            //Log.e("dongyiming", "widthPixels = " + widthPixels);
            reallyWidth = (widthPixels - DEFAULT_DIVER * 2) / 3;
            reallyHeight = DEFAULT_HEIGHT;
            contentHeight = gridImageViewAdapter.getCount() > 3 ? (DEFAULT_HEIGHT * 2 + DEFAULT_DIVER) : DEFAULT_HEIGHT;
        }
        //Log.e("dongyiming", "onMeasure  reallyWidth = " + reallyWidth + "   reallyHeight = " + reallyHeight + "  contentHeight = " + contentHeight);
        //Log.e("dongyiming", "onMeasure  equle = " + ((String) this.getTag(R.string.app_name)).equals(gridImageViewAdapter.getUrl()));
        //确保recyclerView的item的高度不被复用
        if (((String) this.getTag(R.string.app_name)).equals(gridImageViewAdapter.getUrl())) {
            measureChildren(MeasureSpec.makeMeasureSpec((int) reallyWidth, MeasureSpec.EXACTLY)
                    , MeasureSpec.makeMeasureSpec((int) reallyHeight, MeasureSpec.EXACTLY));
            setMeasuredDimension(widthPixels, (int) contentHeight);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        if (gridImageViewAdapter == null || gridImageViewAdapter.getCount() == 0) {
            return;
        }
        double left = 0, top = 0, right = 0, bottom = 0;
        for (int i = 0; i < gridImageViewAdapter.getCount(); i++) {

            if (i > 5) {
                return;
            }
            if (gridImageViewAdapter.getCount() == 1) {//只有一张
                right = widthPixels;
                bottom = reallyHeight;
            } else if (gridImageViewAdapter.getCount() == 2) {

                left = (reallyWidth + DEFAULT_DIVER) * i;
                right = left + reallyWidth;
                bottom = reallyHeight;
            } else {

                if (i >= 3) {
                    top = DEFAULT_HEIGHT + DEFAULT_DIVER;
                    left = (reallyWidth + DEFAULT_DIVER) * (i - 3);
                } else {
                    left = (reallyWidth + DEFAULT_DIVER) * i;
                    top = 0;
                }
                right = left + reallyWidth;
                bottom = top + DEFAULT_HEIGHT;
            }
            //Log.e("dongyiming", " onLayout i = " + i + "  left = " + left + "  top = " + top + "  right = " + right + "  bottom = " + bottom);
            ImageView childView = (ImageView) getChildAt(i);

            if (childView != null) {
                String url = getUrl(gridImageViewAdapter.getPositionItem(i).getUser_id(), gridImageViewAdapter.getPositionItem(i).getImg_id());
                if (gridImageViewAdapter.getCount() == 1) {
                    childView.setScaleType(ImageView.ScaleType.FIT_XY);
                } else {
                    childView.setScaleType(ImageView.ScaleType.CENTER);
                }
                //Log.e("dongyiming", "onlayout , childview equals : " + childView.getTag(R.string.app_name).equals(url));
                //确保每个item里的图片不复用
                if (url != null && childView.getTag(R.string.app_name).equals(url)) {
                    childView.layout((int) left, (int) top, (int) right, (int) bottom);
                }
            } else {

                Log.e("dongyiming", "onlayout , the childView is null :" + i);
            }
        }
    }

    private String getUrl(int urerId, int imageId) {

        String baseUrl = "https://photo.tuchong.com/";
        String url = baseUrl + "/" + urerId + "/f/" + imageId + ".jpg";
        return url;
    }
}
