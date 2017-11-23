package com.wxs.fastmail.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;
import com.wxs.fastmail.Events.Purchaseaddress;
import com.wxs.fastmail.R;
import com.wxs.fastmail.common.Application;
import com.wxs.fastmail.fragment.Fragment_Site;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MapPurchaseActivity extends AppCompatActivity
{
    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.btn_sure)
    TextView btnSure;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.mapview_mapview)
    MapView mapviewMapview;
    @Bind(R.id.mapview_tablayout)
    TabLayout mapviewTablayout;
    @Bind(R.id.mapview_viewpager)
    ViewPager mapviewViewpager;
    @Bind(R.id.mapview_progressbar)
    CircleProgressBar mapviewProgressbar;
    // POI信息列表
    private List<PoiInfo> poiInfoList, list1, list2, list3, list4;
    // 当前物理坐标
    private Point mCenterPoint;
    // 当前选中地理坐标
    private LatLng mCurrentSelected;
    // 地理编码
    private GeoCoder mGeoCoder;
    private BaiduMap mBaiduMap;
    private ImageView mImageViewPointer;
    private BitmapDescriptor mPointer;
    private MyAdapter myAdapter;
    private Fragment_Site tab1, tab2, tab3, tab4;
    private static final String TAG = "MapReceiveActivity";
    //全局Application
    private Application mApplication;
    private  static final int   buyaddress=124;
    // 当前地理坐标 120.376754,31.501899
    private static final LatLng mLoactionLatLng = new LatLng(31.501899, 120.366754);

    private String pur_address="";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_purchase);
        ButterKnife.bind(this);
        initialize();
    }
    @OnClick({R.id.back, R.id.btn_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                MapPurchaseActivity.this.finish();
                break;
            case R.id.btn_sure:
                Purchaseaddress purchaseaddress=new Purchaseaddress(pur_address);
                EventBus.getDefault().postSticky(purchaseaddress);
                MapPurchaseActivity.this.finish();
                break;
        }
    }
    private  void initialize()
    {
        // 初始化POI信息列表
        poiInfoList = new ArrayList<>();
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        list3 = new ArrayList<>();
        list4 = new ArrayList<>();
        // 初始化地图
        mapviewMapview.showZoomControls(false);
        mBaiduMap=mapviewMapview.getMap();
        // 地理编码
        mGeoCoder = GeoCoder.newInstance();
        mGeoCoder.setOnGetGeoCodeResultListener(new MyGetGeoCoderResultListener());

        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLng(mLoactionLatLng);
        mBaiduMap.setMapStatus(mapStatusUpdate);
        mBaiduMap.animateMapStatus(mapStatusUpdate);
        mGeoCoder.reverseGeoCode((new ReverseGeoCodeOption()).location(mLoactionLatLng));

        //初始化缩放比例
        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(17.0f);
        mBaiduMap.setMapStatus(msu);

        mBaiduMap.setOnMapTouchListener(new MyMapTouchListener());

        // 初始化当前MapView中心屏幕坐标(物理坐标)
        mapviewMapview.post(new Runnable() {
            @Override
            public void run() {
                mCenterPoint = mBaiduMap.getMapStatus().targetScreen;
                int x = mCenterPoint.x;
                int y = mCenterPoint.y;

                mImageViewPointer = new ImageView(getApplicationContext());
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.map_locate);
                mImageViewPointer.setImageBitmap(bitmap);
                mImageViewPointer.setX(x - bitmap.getWidth() / 2);
                mImageViewPointer.setY(y - bitmap.getHeight());
                ViewGroup parent = (ViewGroup) mapviewMapview.getParent();
                parent.addView(mImageViewPointer, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT));
            }
        });
        // init ViewPager
        tab1 = new Fragment_Site(list1);
        tab2 = new Fragment_Site(list2);
        tab3 = new Fragment_Site(list3);
        tab4 = new Fragment_Site(list4);

        myAdapter = new MyAdapter((getSupportFragmentManager()));

        myAdapter.addFragment(tab1, "全部");
        myAdapter.addFragment(tab2, "交通");
        myAdapter.addFragment(tab3, "美食");
        myAdapter.addFragment(tab4, "购物");

        mapviewViewpager.setAdapter(myAdapter);
        mapviewViewpager.setOffscreenPageLimit(3);
        // initTabLayout
        mapviewTablayout.setupWithViewPager(mapviewViewpager);
    }
    private class MyMapTouchListener implements BaiduMap.OnMapTouchListener {
        @Override
        public void onTouch(MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (mCenterPoint == null) {
                    return;
                }
                // 获取当前MapView中心屏幕坐标对应的地理坐标
                LatLng currentLatLng = mBaiduMap.getProjection().fromScreenLocation(mCenterPoint);
                //发起反地理编码检索
                 mGeoCoder.reverseGeoCode((new ReverseGeoCodeOption()).location(currentLatLng));
                mapviewProgressbar.setVisibility(View.VISIBLE);
            }
        }
    }
    // 地理编码监听器
    private class MyGetGeoCoderResultListener implements OnGetGeoCoderResultListener
    {
        @Override
        public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
            if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                // 没有找到检索结果
            }
            // 获取反向地理编码结果
            else
                {
                Log.i(TAG, "获取到检索结果");
                // 当前位置信息
                PoiInfo mCurentInfo = new PoiInfo();
                mCurentInfo.address = result.getAddress();
                pur_address=result.getAddress();
                mCurentInfo.location = result.getLocation();
                mCurentInfo.name = "[当前选中]";
                poiInfoList.clear();
                poiInfoList.add(mCurentInfo);
                // 将周边信息加入表
                if (result.getPoiList() != null) {
                    poiInfoList.addAll(result.getPoiList());
                }
                // 数据分类
                classify(poiInfoList);
                // 通知适配器数据已改变
                tab1.notifyData(list1);
                tab2.notifyData(list2);
                tab3.notifyData(list3);
                tab4.notifyData(list4);
                mBaiduMap.clear();
                mapviewProgressbar.setVisibility(View.GONE);
                runShakeAnimation(mImageViewPointer);
                //初始选中信息
                mCurrentSelected = result.getLocation();
                // mAddress = result.getAddress();
            }
        }
        @Override
        public void onGetGeoCodeResult(GeoCodeResult result)
        {
            if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                // 没有检索到结果
            }
            // 获取地理编码结果
        }
    }
    public void runShakeAnimation(View view)
    {
        TranslateAnimation anim = new TranslateAnimation(0, 0, 0, 10);
        anim.setDuration(500);
        anim.setInterpolator(new CycleInterpolator(1));
        view.startAnimation(anim);
    }
    private void classify(List<PoiInfo> poiInfoList) {
        Log.i(TAG, "classify!");

        if (poiInfoList != null && poiInfoList.size() != 0) {
            Log.i(TAG, "!= null!");

            // 全部
            // list1.clear();
            list1 = poiInfoList;

            // 先clear一遍
            list2.clear();
            list3.clear();
            list4.clear();

            for (int i = 0; i < poiInfoList.size(); i++) {
                PoiInfo poiInfo = poiInfoList.get(i);

                // 交通
                if (poiInfo.type != null &&
                        (poiInfo.type.getInt() == 1 || poiInfo.type.getInt() == 2
                                || poiInfo.type.getInt() == 3 || poiInfo.type.getInt() == 4)) {
                    list2.add(poiInfo);
                } else if (poiInfo.name.contains("客运站") || poiInfo.name.lastIndexOf("火车") != -1
                        || poiInfo.name.lastIndexOf("地铁") != -1 || poiInfo.name.lastIndexOf("机场") != -1
                        || poiInfo.address.contains("客运站") || poiInfo.address.lastIndexOf("火车") != -1
                        || poiInfo.address.lastIndexOf("地铁") != -1 || poiInfo.address.lastIndexOf("机场") != -1
                        || poiInfo.name.lastIndexOf("交通") != -1) {
                    list2.add(poiInfo);
                }
                // 美食
                if (poiInfo.hasCaterDetails) {
                    list3.add(poiInfo);
                } else if (poiInfo.name.contains("小吃") || poiInfo.name.contains("食")
                        || poiInfo.name.contains("餐厅") || poiInfo.name.contains("火锅")
                        || poiInfo.name.contains("饭") || poiInfo.name.contains("锅")
                        || poiInfo.name.contains("鱼") || poiInfo.name.contains("煲")
                        || poiInfo.address.contains("小吃") || poiInfo.address.contains("食")
                        || poiInfo.address.contains("餐厅") || poiInfo.address.contains("火锅")
                        || poiInfo.address.contains("饭") || poiInfo.address.contains("锅")
                        || poiInfo.address.contains("鱼") || poiInfo.address.contains("煲")
                        || poiInfo.name.contains("生活")) {
                    list3.add(poiInfo);
                }

                // 住房
                if (poiInfo.name.contains("小区") || poiInfo.name.contains("房")
                        || poiInfo.name.contains("屋") || poiInfo.name.contains("家")
                        || poiInfo.name.contains("酒店") || poiInfo.name.contains("旅")
                        || poiInfo.name.contains("庭") || poiInfo.name.contains("宾馆")
                        || poiInfo.address.contains("小区") || poiInfo.address.contains("房")
                        || poiInfo.address.contains("屋") || poiInfo.address.contains("家")
                        || poiInfo.address.contains("酒店") || poiInfo.address.contains("旅")
                        || poiInfo.address.contains("庭") || poiInfo.address.contains("宾馆")
                        || poiInfo.name.contains("生活")) {
                    list4.add(poiInfo);
                }
            }
        } else {
            list1 = new ArrayList<>();
            list2 = new ArrayList<>();
            list3 = new ArrayList<>();
            list4 = new ArrayList<>();
        }
    }

    private class MyAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}
