package com.wxs.fastmail.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.wxs.fastmail.R;
import com.wxs.fastmail.databean.Info;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MaplookActivity extends AppCompatActivity {


    private  static  String TAG=MaplookActivity.class.getSimpleName();

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.mMapView)
    MapView mMapView;
    BaiduMap mBaiduMap;

    public LocationClient mLocationClient=null;

    public boolean isFirstLoc=true;

    private int mCurrentDirection = 0;

    private double mCurrentLat = 0.0;

    private double mCurrentLon = 0.0;

    private float mCurrentAccracy;

    private MyLocationData locData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_maplook);
        ButterKnife.bind(this);
        mBaiduMap=mMapView.getMap();
        //获取地图实例
        mBaiduMap.setMyLocationEnabled(true);
        initView();
        mLocationClient.start();
        addInfosOverlay(Info.infos);
    }
    private void initView()
    {
        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker)
            {
                return true;
            }
        });
        BitmapDescriptor  mCurrentMarker = BitmapDescriptorFactory.fromResource(R.drawable.map_locate);
        mBaiduMap.setMyLocationConfiguration(new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL,
                true, mCurrentMarker));
        mLocationClient=new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new MyLocationListenner());

        LocationClientOption mOption=new LocationClientOption();

        //设置高精度
        mOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);

        //是否打开GPS
        mOption.setOpenGps(true);

        mOption.setScanSpan(1000);
        mOption.setCoorType("bd0911");
        mOption.setIsNeedAddress(true);

        mOption.setIsNeedLocationDescribe(true);

        mOption.setIsNeedLocationPoiList(true);
        mOption.setLocationNotify(true);

        mOption.setIgnoreKillProcess(true);
        mOption.SetIgnoreCacheException(false);
        mOption.setIsNeedAltitude(false);
        mLocationClient.setLocOption(mOption);

    }
    @Override
    protected void onStop() {
        super.onStop();
        mLocationClient.stop();
    }
    public class MyLocationListenner implements BDLocationListener
    {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            if (bdLocation == null || mMapView == null) {
                return;
            }
            mCurrentLat = bdLocation.getLatitude();
            mCurrentLon = bdLocation.getLongitude();
            mCurrentAccracy = bdLocation.getRadius();
            locData = new MyLocationData.Builder().accuracy(bdLocation.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(mCurrentDirection).latitude(bdLocation.getLatitude()).longitude(bdLocation.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);
            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(18.0f);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            }
        }
        @Override
        public void onConnectHotSpotMessage(String s, int i) {

        }
     }
    public void addInfosOverlay(List<Info> infos)
    {
        LatLng latLng = null;
        OverlayOptions overlayOptions = null;
        Marker marker = null;
        for (Info info : infos)
        {
            // 位置
            latLng = new LatLng(info.getLatitude(), info.getLongitude());
            // 图标
            BitmapDescriptor miconmaker1=BitmapDescriptorFactory.fromResource(R.drawable.miconmarker1);
            overlayOptions = new MarkerOptions().position(latLng).icon(miconmaker1).zIndex(5);
            marker = (Marker) (mBaiduMap.addOverlay(overlayOptions));
            Bundle bundle = new Bundle();
            bundle.putSerializable("info", info);
            marker.setExtraInfo(bundle);
        }
    }

}
