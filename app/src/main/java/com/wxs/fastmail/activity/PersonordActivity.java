package com.wxs.fastmail.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;
import com.wxs.fastmail.R;
import com.wxs.fastmail.adapter.MyFragmentPagerAdapter;
import com.wxs.fastmail.fragment.Fragment_PersonOrdOne;
import com.wxs.fastmail.fragment.Fragment_PersonOrdThree;
import com.wxs.fastmail.fragment.Fragment_PersonOrdTwo;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PersonordActivity extends AppCompatActivity {

//    @Bind(R.id.toolbar)
//    Toolbar toolbar;
    @Bind(R.id.tablayout)
    TabLayout tablayout;
    @Bind(R.id.personord_appbarlayout)
    AppBarLayout personordAppbarlayout;
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    @Bind(R.id.personord_progressbar)
    CircleProgressBar personordProgressbar;
    private Fragment fr1, fr2, fr3;
    private static final int INIT = 123;
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what)
            {
                case INIT:
                    personordProgressbar.setVisibility(View.GONE);
                    initViewPager();
                    break;
            }
            return false;
        }
    });

    private void initViewPager() {
        fr1=new Fragment_PersonOrdOne();
        fr2=new Fragment_PersonOrdTwo();
        fr3=new Fragment_PersonOrdThree();
        MyFragmentPagerAdapter mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        mAdapter.addFragment(fr1, "未完成");
        mAdapter.addFragment(fr2, "已完成");
        mAdapter.addFragment(fr3, "待评价");
        viewpager.setAdapter(mAdapter);
        viewpager.setOffscreenPageLimit(2);
        tablayout.setupWithViewPager(viewpager);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personord);
        ButterKnife.bind(this);
        initialize();
    }
    private void initialize() {
        Message message = Message.obtain(mHandler);
        message.what = INIT;
        mHandler.sendMessageDelayed(message, 4200);
    }
}
