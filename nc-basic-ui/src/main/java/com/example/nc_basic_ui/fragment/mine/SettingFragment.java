package com.example.nc_basic_ui.fragment.mine;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.example.nc_basic_ui.R;
import com.example.nc_super_abs.adapter.CommonAdapter;
import com.example.nc_super_abs.adapter.viewholder.BaseViewHolder;
import com.example.nc_super_abs.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/8/9 20:58
 */
public class SettingFragment extends BaseFragment {

    private RecyclerView recyclerview;

    @Override
    public View setRootView(LayoutInflater inflater) {

        View view = inflater.inflate(R.layout.fragment_music, null);
        recyclerview = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add(i);
        }
        recyclerview.setAdapter(new CommonAdapter<Integer>(getActivity(),list) {

            @Override
            public void convert(BaseViewHolder holder, Integer data, int position) {

                holder.setText(R.id.txt_title,"董一鸣");
            }

            @Override
            public int getLayoutId() {
                return R.layout.adapter_item_eye;
            }
        });

        return view;
    }
}
