package com.wxs.fastmail.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.wxs.fastmail.R;
import com.wxs.fastmail.activity.GoldmedalActivity;
import com.wxs.fastmail.activity.PublishActivity;
import com.wxs.fastmail.activity.RecruitActivity;
import com.wxs.fastmail.activity.ShakeActivity;
import com.wxs.fastmail.activity.TeamBuyActivity;
import com.wxs.fastmail.adapter.MyFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.bgabanner.BGABanner;
import cn.bingoogolapple.bgabanner.BGABannerUtil;
import lrq.com.addpopmenu.PopMenu;
import lrq.com.addpopmenu.PopMenuItem;
import lrq.com.addpopmenu.PopMenuItemListener;

/**
 * Created by wxs on 2017/7/6.
 */

public class Fragment_Community extends Fragment {
    @Bind(R.id.banner)
    BGABanner banner;
    @Bind(R.id.tv_search)
    TextView tvSearch;
    @Bind(R.id.ll_search)
    LinearLayout llSearch;
    @Bind(R.id.eat)
    LinearLayout eat;
    @Bind(R.id.recruit)
    LinearLayout recruit;
    @Bind(R.id.goldmedal)
    LinearLayout goldmedal;
    @Bind(R.id.flowers)
    LinearLayout flowers;
    @Bind(R.id.imageView5)
    ImageView imageView5;
    @Bind(R.id.find)
    LinearLayout find;
    @Bind(R.id.imageView4)
    ImageView imageView4;
    @Bind(R.id.diamond)
    LinearLayout diamond;
    @Bind(R.id.address)
    LinearLayout address;
    @Bind(R.id.teambuy)
    LinearLayout teambuy;
    @Bind(R.id.imageView3)
    ImageView imageView3;
    @Bind(R.id.more)
    LinearLayout more;
    @Bind(R.id.community_vf)
    ViewFlipper communityVf;
    @Bind(R.id.image_horn)
    ImageView imageHorn;
    @Bind(R.id.community_tablayout)
    TabLayout communityTablayout;
    @Bind(R.id.community_viewpager)
    ViewPager communityViewpager;
    @Bind(R.id.trend)
    LinearLayout trend;

    private Fragment frc1, frc2, frc3, frc4;
    private PopMenu mPopMenu;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = View.inflate(getActivity(), R.layout.fragment_community, null);
        ButterKnife.bind(this, view);
        initialize();
        initViewPager();
        initBanner();
        return view;
    }
    private void initViewPager() {
        frc1 = new Fragment_ComTrend();
        frc2 = new Fragment_ComRecruit();
        frc3 = new Fragment_ComTeamBuy();
        frc4 = new Fragment_Comgold();
        MyFragmentPagerAdapter mcAdapter = new MyFragmentPagerAdapter(getActivity().getSupportFragmentManager());
        mcAdapter.addFragment(frc1, "动态");
        mcAdapter.addFragment(frc2, "招募");
        mcAdapter.addFragment(frc3, "团购");
        mcAdapter.addFragment(frc4, "金牌");
        communityViewpager.setAdapter(mcAdapter);
        communityViewpager.setOffscreenPageLimit(2);
        communityTablayout.setupWithViewPager(communityViewpager);
    }
    private void initialize() {
        communityVf.addView(View.inflate(getContext(), R.layout.view_advertisement01, null));
        communityVf.addView(View.inflate(getContext(), R.layout.view_advertisement02, null));
        communityVf.addView(View.inflate(getContext(), R.layout.view_advertisement03, null));
        communityVf.addView(View.inflate(getContext(), R.layout.view_advertisement04, null));

        mPopMenu = new PopMenu.Builder().attachToActivity(getActivity())
                .addMenuItem(new PopMenuItem("记录", getResources().getDrawable(R.drawable.tabbar_compose_idea)))
                .addMenuItem(new PopMenuItem("动态", getResources().getDrawable(R.drawable.tabbar_compose_photo)))
                .addMenuItem(new PopMenuItem("小视频", getResources().getDrawable(R.drawable.tabbar_compose_headlines)))
                .setOnItemClickListener(new PopMenuItemListener() {
                    @Override
                    public void onItemClick(PopMenu popMenu, int position) {
                        if (position == 1) {
                            Intent intent = new Intent(getActivity(), PublishActivity.class);
                            startActivity(intent);
                            getActivity().overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                        } else {
                            Toast.makeText(getActivity(), "你点击了第" + position + "个位置", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .build();
    }
    private void initBanner()
    {
        List<View> views = new ArrayList<>();
        views.add(BGABannerUtil.getItemImageView(getContext(), R.drawable.community_pic1));
        views.add(BGABannerUtil.getItemImageView(getContext(), R.drawable.community_pic2));
        views.add(BGABannerUtil.getItemImageView(getContext(), R.drawable.community_pic3));
        views.add(BGABannerUtil.getItemImageView(getContext(), R.drawable.community_pic4));
        views.add(BGABannerUtil.getItemImageView(getContext(), R.drawable.community_pic5));
        banner.setData(views);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
    @OnClick({R.id.find, R.id.recruit, R.id.trend, R.id.goldmedal, R.id.teambuy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.find:
                Intent intent3=new Intent(getActivity(), ShakeActivity.class);
                startActivity(intent3);
                break;
            case R.id.recruit:
                Intent intent = new Intent(getActivity(), RecruitActivity.class);
                startActivity(intent);
                break;
            case R.id.trend:
                if (mPopMenu != null)
                    mPopMenu.show();
                break;
            case R.id.goldmedal:
                Intent intent1 = new Intent(getActivity(), GoldmedalActivity.class);
                startActivity(intent1);
                break;
            case R.id.teambuy:
                Intent intent2 = new Intent(getActivity(), TeamBuyActivity.class);
                startActivity(intent2);
                break;
        }
    }

}
