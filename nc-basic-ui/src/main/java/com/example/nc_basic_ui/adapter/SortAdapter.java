package com.example.nc_basic_ui.adapter;

import android.content.Context;

import com.example.nc_basic_ui.R;
import com.example.nc_super_abs.adapter.CommonAdapter;
import com.example.nc_super_abs.adapter.viewholder.BaseViewHolder;

import java.util.List;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/6/3 21:05
 */
public class SortAdapter extends CommonAdapter<String> {


    private Context mContext;
    private List<String> nameList;

    public SortAdapter(Context mContext, List<String> dataList) {
        super(mContext, dataList);
        this.mContext = mContext;
    }


    @Override
    public void convert(BaseViewHolder holder, String data, int position) {
        holder.setText(R.id.txt_name, data);
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_sort;
    }

    public String getItemName(int position) {
        return nameList.get(position);
    }

    //List每remove掉一个元素以后，后面的元素都会向前移动，此时如果执行i=i+1，则刚刚移过来的元素没有被读取。
    public void removeItem(int position) {

        for (int i = nameList.size() - 1; i >= 0; i--) {
            if (i == position) {
                nameList.remove(i);
            }
        }
    }

    public void addItemIndex(int index, String name) {

        nameList.add(index, name);
    }
}
