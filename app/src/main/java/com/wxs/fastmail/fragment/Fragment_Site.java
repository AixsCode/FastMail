package com.wxs.fastmail.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.mapapi.search.core.PoiInfo;
import com.wxs.fastmail.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wxs on 2017/7/16.
 */

public class Fragment_Site extends Fragment {
    @Bind(R.id.site_rv)
    RecyclerView siteRv;
    private View view;
    private List<PoiInfo> poiInfoList;
    private MyAdapter myAdapter;
    @SuppressLint({"NewApi", "ValidFragment"})
    public Fragment_Site(List<PoiInfo> poiInfoList) {
        this.poiInfoList = poiInfoList;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_site, container, false);
        ButterKnife.bind(this, view);
        initialize();
        return view;
    }
    private void initialize() {
        if (poiInfoList != null) {
            myAdapter = new MyAdapter();
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            siteRv.setLayoutManager(layoutManager);
            siteRv.setAdapter(myAdapter);
        }
    }
    public void notifyData(List<PoiInfo> poiInfoList) {
        this.poiInfoList = poiInfoList;

        myAdapter.notifyDataSetChanged();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        private OnItemClickListener onItemClickListener;

        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = getActivity().getLayoutInflater().inflate(R.layout.item_site, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            final PoiInfo poiInfo = poiInfoList.get(position);

            holder.siteName.setText(poiInfo.name);
            holder.siteAdress.setText(poiInfo.address);
            if (onItemClickListener != null)
            {
                holder.siteLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onItemClickListener.onItemClick(poiInfo, position);
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return poiInfoList.size();
        }
    }
    class MyViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.site_name)
        TextView siteName;
        @Bind(R.id.site_adress)
        TextView siteAdress;
        @Bind(R.id.site_imageview)
        ImageView siteImageview;
        @Bind(R.id.site_layout)
        RelativeLayout siteLayout;
        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
    public interface OnItemClickListener
    {
        void onItemClick(PoiInfo poiInfo, int position);
    }
}
