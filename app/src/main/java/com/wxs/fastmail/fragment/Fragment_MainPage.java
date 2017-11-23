package com.wxs.fastmail.fragment;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;
import com.wxs.fastmail.R;
import com.wxs.fastmail.adapter.MyFragmentPagerAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wxs on 2017/7/6.
 */

public class Fragment_MainPage extends Fragment {

    @Bind(R.id.tablayout)
    TabLayout tablayout;
    @Bind(R.id.progressbar)
    CircleProgressBar progressbar;
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    private  Fragment fr1,fr2,fr3;
    private  android.os.Handler mHandler =new android.os.Handler(new android.os.Handler.Callback() {
    @Override
    public boolean handleMessage(Message msg)
    {
        switch (msg.what) {
            case INIT:
                progressbar.setVisibility(View.GONE);
                initViewPager();
                break;
        }
        return false;
    }
});
    private static final int INIT = 122;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = View.inflate(getActivity(), R.layout.fragment_mainpage, null);

        ButterKnife.bind(this, view);
        initialize();
        initViewPager();
        return view;
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
    private void initialize()
    {
        Message message = Message.obtain(mHandler);
        message.what = INIT;
        mHandler.sendMessageDelayed(message, 2200);
    }
    private  void initViewPager()
    {
        fr1=new Fragmentmain_send();
        fr2=new Fragmentmain_post();
        fr3=new Fragmentmain_buy();
        MyFragmentPagerAdapter mAdapter = new MyFragmentPagerAdapter(getActivity().getSupportFragmentManager());
        mAdapter.addFragment(fr1, "帮我买");
        mAdapter.addFragment(fr2, "帮我送");
        mAdapter.addFragment(fr3, "我来买");
        viewpager.setAdapter(mAdapter);
        viewpager.setOffscreenPageLimit(2);
        // initTabLayout
        tablayout.setupWithViewPager(viewpager);
    }
}
