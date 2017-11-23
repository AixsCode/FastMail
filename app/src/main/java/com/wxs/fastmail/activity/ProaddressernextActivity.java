package com.wxs.fastmail.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.Polyline;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.model.LatLng;
import com.wxs.fastmail.R;
import com.wxs.fastmail.utils.Util_GetBitmapFromResource;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;

public class ProaddressernextActivity extends AppCompatActivity {
    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tv_contact1)
    TextView tvContact1;
    @Bind(R.id.lay_getperson)
    LinearLayout layGetperson;
    @Bind(R.id.tv_contact2)
    TextView tvContact2;
    @Bind(R.id.mMapView)
    MapView mMapView;
    @Bind(R.id.lay_showtime)
    LinearLayout layShowtime;
    @Bind(R.id.btnSend)
    Button btnSend;
    public BaiduMap mBaiduMap;
    public Polyline mPolyline;
    private static final int change = 123;
    @Bind(R.id.iv_send)
    ImageView ivSend;
    @Bind(R.id.tv_send)
    TextView tvSend;
    @Bind(R.id.iv_get)
    ImageView ivGet;
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case change:
                    tvContact2.setText("和她联系");
                    tvContact2.setTextColor(Color.RED);
                    ivSend.setImageBitmap(Util_GetBitmapFromResource.getBitmapFromResource(getApplicationContext(), R.drawable.add_person_5));
                    tvSend.setText("小可爱");
                    break;
            }
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proaddressernext);
        ButterKnife.bind(this);
        initialize();
        initgetOrder();
    }
    private void initgetOrder() {
        Message msg = Message.obtain(mHandler);
        msg.what = change;
        mHandler.sendMessageDelayed(msg, 3200);
    }
    private void initialize() {
        //30.320316    120.360918
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMyLocationEnabled(true);
        MapStatus.Builder builder = new MapStatus.Builder();
        builder.target(new LatLng(31.564323232, 120.275434332)).zoom(18.0f);
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.icon_gcoding);
        MarkerOptions markerOptions = new MarkerOptions().icon(bitmap).position(new LatLng(31.564323232, 120.275434332));
        mBaiduMap.addOverlay(markerOptions);
        BitmapDescriptor bitmap1 = BitmapDescriptorFactory.fromResource(R.drawable.map_ing);
        MarkerOptions markerOption1 = new MarkerOptions().icon(bitmap1).position(new LatLng(31.568832323, 120.275022332));
        mBaiduMap.addOverlay(markerOption1);
        List<LatLng> latLngs = new ArrayList<LatLng>();
        latLngs.add(new LatLng(31.564323232, 120.275434332));
        latLngs.add(new LatLng(31.568832323, 120.275022332));
        OverlayOptions ooPolyline = new PolylineOptions().width(10).color(0xAAFF0000).points(latLngs);
        mPolyline = (Polyline) mBaiduMap.addOverlay(ooPolyline);
    }

    private void RongIM() {
        if (RongIM.getInstance() != null) {
            RongIM.getInstance().startPrivateChat(ProaddressernextActivity.this, "2015", "思想");
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        }
    }
    private void RongIM1()
    {
        if (RongIM.getInstance() != null)
        {
            RongIM.getInstance().startPrivateChat(ProaddressernextActivity.this, "2018", "小可爱");
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        }
    }
    @OnClick({R.id.tv_contact1, R.id.tv_contact2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_contact1:
                RongIM();
                break;
            case R.id.tv_contact2:
                RongIM1();
                break;
        }
    }
    @OnClick(R.id.btnSend)
    public void onViewClicked()
    {
        Intent intent=new Intent(ProaddressernextActivity.this, OrderfinishActivity.class);
        startActivity(intent);
    }
}
