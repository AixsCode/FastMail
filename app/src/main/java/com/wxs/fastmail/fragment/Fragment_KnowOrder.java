package com.wxs.fastmail.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;
import com.wxs.fastmail.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wxs on 2017/7/28.
 */
public class Fragment_KnowOrder extends Fragment {
    @Bind(R.id.card_view1)
    CardView cardView1;
    @Bind(R.id.order_label)
    ImageView orderLabel;
    @Bind(R.id.order_time)
    TextView orderTime;
    @Bind(R.id.order_money)
    TextView orderMoney;
    @Bind(R.id.order_name)
    TextView orderName;
    @Bind(R.id.order_phone)
    TextView orderPhone;
    @Bind(R.id.order_reqtime)
    TextView orderReqtime;
    @Bind(R.id.order_things)
    TextView orderThings;
    @Bind(R.id.iv_me_position)
    TextView ivMePosition;
    @Bind(R.id.order_dis1)
    TextView orderDis1;
    @Bind(R.id.order_dis2)
    TextView orderDis2;
    @Bind(R.id.btn_getOrder)
    Button btnGetOrder;
    @Bind(R.id.card_view2)
    CardView cardView2;
    @Bind(R.id.progressbar)
    CircleProgressBar progressbar;
    private static  final int  show=666;
    private  Handler  mHandler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
                switch (msg.what) {
                    case show :
                        progressbar.setVisibility(View.INVISIBLE);
                        cardView1.setVisibility(View.VISIBLE);
                        cardView2.setVisibility(View.VISIBLE);
                        break;
                }
            return false;
        }
    });
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = View.inflate(getActivity(), R.layout.fragment_knoworder, null);
        ButterKnife.bind(this, view);
        initialize();
        return view;
    }
    private void initialize()
    {
        Message msg=Message.obtain(mHandler);
        msg.what=show;
        mHandler.sendMessageDelayed(msg,5200);
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
