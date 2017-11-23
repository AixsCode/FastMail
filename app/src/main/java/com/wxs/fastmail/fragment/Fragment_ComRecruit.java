package com.wxs.fastmail.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.wxs.fastmail.R;
import com.wxs.fastmail.adapter.RecyclerViewRecAdapter;
import com.wxs.fastmail.bean.Recruit;
import com.wxs.fastmail.tool.Tool_ParseJson;
import com.wxs.fastmail.tool.Tool_Url;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by wxs on 2017/7/13.
 */

public class Fragment_ComRecruit extends Fragment implements PullLoadMoreRecyclerView.PullLoadMoreListener {
    @Bind(R.id.pullLoadMoreRecyclerView)
    PullLoadMoreRecyclerView pullLoadMoreRecyclerView;
    private RecyclerView mRecyclerView;
    private RecyclerViewRecAdapter  mRecyclerViewAdapter=new RecyclerViewRecAdapter(getActivity());
    public   static  List<Recruit>  allrecruitlist= new ArrayList<>();
    private   static  final String  TAG="Fragment_ComRecruit";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragmentcom_recruit, null);
        ButterKnife.bind(this, view);
        initializedata();
        return view;
    }
    private void initializedata() {
        mRecyclerView = pullLoadMoreRecyclerView.getRecyclerView();
        pullLoadMoreRecyclerView.setRefreshing(true);
        pullLoadMoreRecyclerView.setFooterViewText("努力加载中...");
        pullLoadMoreRecyclerView.setFooterViewTextColor(R.color.black);
        pullLoadMoreRecyclerView.setOnPullLoadMoreListener(this);
        pullLoadMoreRecyclerView.setAdapter(mRecyclerViewAdapter);
        pullLoadMoreRecyclerView.setLinearLayout();
        pullLoadMoreRecyclerView.setColorSchemeResources(R.color.colorPrimary);
        pullLoadMoreRecyclerView.scrollToTop();
        getData();
    }
    private void getData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        okhttputils_Url_recruit_getAll();
                        pullLoadMoreRecyclerView.setPullLoadMoreCompleted();
                    }
                });
            }
        }, 2100);
    }
    public  void okhttputils_Url_recruit_getAll() {
        OkHttpUtils
                .get()
                .url(Tool_Url.getUrl_recruit_getAll())
                .build()
                .execute(new MyStringCallback());
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
    @Override
    public void onRefresh() {

    }
    @Override
    public void onLoadMore() {

    }
    private void setRefresh() {
        mRecyclerViewAdapter.clearData();
    }
    private class MyStringCallback extends StringCallback {
        @Override
        public void onBefore(Request request, int id) {

            Log.i(TAG, "onBefore");
        }
        @Override
        public void onAfter(int id)
        {
            Log.i(TAG, "onAfter");
        }
        @Override
        public void onError(Call call, Exception e, int id) {
            Log.i(TAG, "onError");
            e.printStackTrace();
        }
        @Override
        public void onResponse(String response, int id)
        {
            Log.i(TAG, "onResponse \t" + response);
            List<Recruit>   recruitList = Tool_ParseJson.getRecruitList("recruitList", response);
            allrecruitlist=recruitList;
            mRecyclerViewAdapter.addAllData(allrecruitlist);
            Log.i(TAG,"recruitList \t"+recruitList);
        }
    }
}
