package com.wxs.fastmail.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.wxs.fastmail.Events.Aimaddress;
import com.wxs.fastmail.Events.Currentaddress;
import com.wxs.fastmail.Events.WayEvent;
import com.wxs.fastmail.R;
import com.wxs.fastmail.activity.MapCurrentActivity;
import com.wxs.fastmail.activity.MapaimActivity;
import com.wxs.fastmail.adapter.RecyclerViewpachwayAdapter;
import com.wxs.fastmail.tool.Tool_GetData;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wxs on 2017/7/28.
 */

public class Fragment_WayOrder extends Fragment implements PullLoadMoreRecyclerView.PullLoadMoreListener {
    @Bind(R.id.pullLoadMoreRecyclerView)
    PullLoadMoreRecyclerView pullLoadMoreRecyclerView;
    @Bind(R.id.lay_current)
    LinearLayout layCurrent;
    @Bind(R.id.lay_aim)
    LinearLayout layAim;
    @Bind(R.id.tv_currentaddress)
    TextView tvCurrentaddress;
    @Bind(R.id.tv_aimaddress)
    TextView tvAimaddress;
    private RecyclerView mRecyclerView;
    private RecyclerViewpachwayAdapter mRecyclerViewAdapter;
    private static final String TAG = "Fragment_WayOrder";
    private String current="";
    private String aim="  ";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_wayorder, null);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        initialize();
        return view;
    }
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void currentaddress(Currentaddress event) {
          current=event.getCurrentaddress();
    }
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void aimaddress(Aimaddress event) {
          aim=event.getAimaddress();
    }
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void Wayevent(WayEvent event) {
         current=event.getNowaddress();
         aim=event.getAimaddress();
    }
    @Override
    public void onStart() {
        super.onStart();
        tvCurrentaddress.setText(current);
        tvAimaddress.setText(aim);
    }
    private void initialize()
    {
        mRecyclerView = pullLoadMoreRecyclerView.getRecyclerView();

        pullLoadMoreRecyclerView.setRefreshing(true);
        //设置上拉刷新
        pullLoadMoreRecyclerView.setFooterViewText("努力加载中.. ");

        //设置上拉刷新文字颜色
        pullLoadMoreRecyclerView.setFooterViewTextColor(R.color.black);

        //设置线性布局
        pullLoadMoreRecyclerView.setLinearLayout();

        pullLoadMoreRecyclerView.setOnPullLoadMoreListener(this);

        mRecyclerViewAdapter = new RecyclerViewpachwayAdapter(getContext());

        pullLoadMoreRecyclerView.setAdapter(mRecyclerViewAdapter);

        pullLoadMoreRecyclerView.setColorSchemeResources(R.color.colorPrimary);

        pullLoadMoreRecyclerView.scrollToTop();
        getData();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
    private void getData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mRecyclerViewAdapter.addAllData(Tool_GetData.getPatchDataList());
                pullLoadMoreRecyclerView.setPullLoadMoreCompleted();
            }
        }, 2200);
    }
    @Override
    public void onRefresh()
    {
         setRefresh();
         getData();
    }
    @Override
    public void onLoadMore()
    {
    }
    private void setRefresh()
    {
         mRecyclerViewAdapter.clearData();
    }
    @OnClick({R.id.lay_current, R.id.lay_aim})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.lay_current:
                Intent intent1 = new Intent(getActivity(), MapCurrentActivity.class);
                startActivity(intent1);
                setRefresh();
                break;
            case R.id.lay_aim:
                Intent intent =  new  Intent(getActivity(), MapaimActivity.class);
                startActivity(intent);
                setRefresh();
                break;
        }
    }
}
