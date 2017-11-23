package com.wxs.fastmail.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wxs.fastmail.Events.OrderpostEvent;
import com.wxs.fastmail.Events.OrderthreeEvent;
import com.wxs.fastmail.R;
import com.wxs.fastmail.activity.ProgressActivity;
import com.wxs.fastmail.activity.ProgresspostActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

/**
 * Created by wxs on 2017/7/23.
 */

public class Fragment_PersonOrdOne extends Fragment {
    @Bind(R.id.lay_orderpost)
    LinearLayout layOrderpost;
    @Bind(R.id.lay_orderbuy)
    LinearLayout layOrderbuy;
    @Bind(R.id.lay_orderdispatch)
    LinearLayout layOrderdispatch;
    @Bind(R.id.tv_orderthree_state)
    TextView tvOrderthreeState;
    @Bind(R.id.tv_orderpost_state)
    TextView tvOrderpostState;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_personordone, null);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        return view;
    }
    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
    @OnClick({R.id.lay_orderpost, R.id.lay_orderbuy, R.id.lay_orderdispatch})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lay_orderbuy:
                Intent intent = new Intent(getContext(), ProgressActivity.class);
                startActivity(intent);
                Toasty.normal(getContext(),"订单加急配送中", Toast.LENGTH_SHORT).show();
                break;
            case R.id.lay_orderpost:
                Intent intent1 = new Intent(getContext(), ProgresspostActivity.class);
                startActivity(intent1);
                Toasty.normal(getContext(),"订单加急配送中", Toast.LENGTH_SHORT).show();
                break;
            case R.id.lay_orderdispatch:
                break;
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void orderpostEvent(OrderpostEvent event) {
        if (!event.getTv_Dispatch().isEmpty()) {
            tvOrderpostState.setText("快递员已接单");
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void orderthreeEvrnt(OrderthreeEvent event)
    {
        if (!event.getTv_Patch_name().isEmpty()) {
            tvOrderthreeState.setText("发件员已接单");
        }else if(!event.getTv_Post_name().isEmpty()&&!event.getTv_Patch_name().isEmpty()) {
            tvOrderthreeState.setText("快递员已接单");
        }
    }
}
