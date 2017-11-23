package com.wxs.fastmail.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.wxs.fastmail.R;
import com.wxs.fastmail.adapter.MyFragmentPagerAdapter;
import com.wxs.fastmail.fragment.Fragment_KnowOrder;
import com.wxs.fastmail.fragment.Fragment_NearOrder;
import com.wxs.fastmail.fragment.Fragment_WayOrder;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DispatchActivity extends AppCompatActivity {

    @Bind(R.id.tablayout)
    TabLayout tablayout;
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    private Fragment fr1, fr2, fr3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch);
        ButterKnife.bind(this);
        initialize();
    }
    private void initialize() {
        fr1 = new Fragment_NearOrder();
        fr2 = new Fragment_KnowOrder();
        fr3 = new Fragment_WayOrder();
        MyFragmentPagerAdapter mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        mAdapter.addFragment(fr1, "就近接单");
        mAdapter.addFragment(fr2, "熟人接单");
        mAdapter.addFragment(fr3, "顺路接单");
        viewpager.setAdapter(mAdapter);
        viewpager.setOffscreenPageLimit(2);
        // initTabLayout
        tablayout.setupWithViewPager(viewpager);
    }
    @OnClick(R.id.back)
    public void onViewClicked()
    {
        DispatchActivity.this.finish();
    }
}
