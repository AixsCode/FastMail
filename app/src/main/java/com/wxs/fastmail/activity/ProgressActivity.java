package com.wxs.fastmail.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.Polyline;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.CoordinateConverter;
import com.baidu.mapapi.utils.CoordinateConverter.CoordType;
import com.baoyachi.stepview.HorizontalStepView;
import com.baoyachi.stepview.bean.StepBean;
import com.wxs.fastmail.Events.FlagEvent;
import com.wxs.fastmail.Events.OrderthreeEvent;
import com.wxs.fastmail.R;
import com.wxs.fastmail.common.Const;
import com.wxs.fastmail.utils.Util_GetBitmapFromResource;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.CSCustomServiceInfo;

public class ProgressActivity extends AppCompatActivity
{

    public  Polyline mPolyline;
    public  LatLng target;
    public  BaiduMap mBaiduMap;
    public  MapStatus.Builder builder;
    public  List<LatLng> latLngs = new ArrayList<LatLng>();
    public  BitmapDescriptor startBD = BitmapDescriptorFactory.fromResource(R.drawable.ic_me_history_startpoint);
    public  BitmapDescriptor finishBD = BitmapDescriptorFactory.fromResource(R.drawable.ic_me_history_finishpoint);
    public  BitmapDescriptor  IngBD=BitmapDescriptorFactory.fromResource(R.drawable.map_ing);

    private Marker mMarkerA;

    private Marker mMarkerB;

    private Marker mMarkerC;

    private Marker mMarkerD;
    private  int Flag_tag=0;
    public StepBean stepBean0 = new StepBean("支付", 1);
    public StepBean stepBean1 = new StepBean("接单", 0);
    public StepBean stepBean2 = new StepBean("发货", -1);
    public StepBean stepBean3 = new StepBean("交接", -1);
    public StepBean stepBean4 = new StepBean("配送", -1);
    public StepBean stepBean5 = new StepBean("完成", -1);
    //发件人
    public String Tv_Post_name = "";
    //快递员
    public String Tv_Patch_name = "";
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.pro_map)
    MapView proMap;
    @Bind(R.id.Fram_map)
    FrameLayout FramMap;
    @Bind(R.id.step_view)
    HorizontalStepView stepView;
    @Bind(R.id.iv_post)
    ImageView ivPost;
    @Bind(R.id.tv_post)
    TextView tvPost;
    @Bind(R.id.tv_state1)
    TextView tvState1;
    @Bind(R.id.tv_dispatch)
    TextView tvDispatch;
    @Bind(R.id.linearLayout2)
    LinearLayout linearLayout2;
    @Bind(R.id.lay_order)
    LinearLayout layOrder;
    @Bind(R.id.btn_getOrder)
    Button btnGetOrder;
    @Bind(R.id.btn_feedback)
    Button btnFeedback;
    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.iv_dispatch)
    ImageView ivDispatch;
    @Bind(R.id.tv_state2)
    TextView tvState2;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        showSetpView();
    }
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void OrderthreeEvent(OrderthreeEvent event) {
        Tv_Post_name = event.getTv_Post_name();
        Tv_Patch_name = event.getTv_Patch_name();
    }
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void FlagEvent(FlagEvent event)
    {
         Flag_tag=event.getFlag_tag();
    }
    @Override
    protected void onStart()
    {
        if (!Tv_Post_name.isEmpty() && Tv_Patch_name.isEmpty())
        {
            initialize();
            tvPost.setText(Tv_Post_name);
            tvState1.setText("已接单");
            tvState1.setTextColor(Color.RED);
            ivPost.setImageBitmap(Util_GetBitmapFromResource.getBitmapFromResource(getApplicationContext(), R.drawable.mail_buy_person4));
            stepBean1.setState(1);
            stepBean2.setState(1);
        }
        if (!Tv_Post_name.isEmpty()&&!Tv_Patch_name.isEmpty())
        {
            tvPost.setText(Tv_Post_name);
            tvState1.setText("已接单");
            tvState1.setTextColor(Color.RED);
            tvDispatch.setText(Tv_Patch_name);
            ivPost.setImageBitmap(Util_GetBitmapFromResource.getBitmapFromResource(getApplicationContext(), R.drawable.mail_buy_person4));
            tvState2.setText("已接单");
            tvState2.setTextColor(Color.RED);
            ivDispatch.setImageBitmap(Util_GetBitmapFromResource.getBitmapFromResource(getApplicationContext(), R.drawable.add_person_5));
            btnGetOrder.setText("确认送达");
            stepBean1.setState(1);
            stepBean2.setState(1);
            stepBean3.setState(1);
            initialize();
            MarkerOptions oIng=new MarkerOptions().position(latLngs.get(latLngs.size()-60)).icon(IngBD).zIndex(3);
            mMarkerC=(Marker)(mBaiduMap.addOverlay(oIng));
        }
        if(Flag_tag==1)
        {
            stepBean4.setState(1);
            stepBean5.setState(1);
            mMarkerC.setVisible(false);
            MarkerOptions oIng=new MarkerOptions().position(latLngs.get(latLngs.size()-10)).icon(IngBD).zIndex(3);
            mMarkerD=(Marker)(mBaiduMap.addOverlay(oIng));
        }
        super.onStart();
    }
    private void initialize()
    {
        mBaiduMap=proMap.getMap();
        mBaiduMap.setMyLocationEnabled(true);
        coordinateConvert();
        builder = new MapStatus.Builder();
        builder.target(target).zoom(18f);
        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
        MarkerOptions oStart = new MarkerOptions();//地图标记覆盖物参数配置类
        oStart.position(latLngs.get(0));//覆盖物位置点，第一个点为起点
        oStart.icon(startBD);//设置覆盖物图片
        oStart.zIndex(1);//设置覆盖物Index
        mMarkerA = (Marker) (mBaiduMap.addOverlay(oStart)); //在地图上添加此图层
        //添加终点图层
        MarkerOptions oFinish = new MarkerOptions().position(latLngs.get(latLngs.size()-1)).icon(finishBD).zIndex(2);
        mMarkerB = (Marker) (mBaiduMap.addOverlay(oFinish));
        //添加快递员图层
        OverlayOptions ooPolyline = new PolylineOptions().width(10).color(0xAAFF0000).points(latLngs);
        mBaiduMap.setOnPolylineClickListener(new BaiduMap.OnPolylineClickListener() {
            @Override
            public boolean onPolylineClick(Polyline polyline)
            {
                if (polyline.getZIndex() == mPolyline.getZIndex())
                {
                    Toast.makeText(getApplicationContext(),"点数：" + polyline.getPoints().size() + ",width:" + polyline.getWidth(), Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
        mPolyline = (Polyline) mBaiduMap.addOverlay(ooPolyline);
        mPolyline.setZIndex(3);
    }
    @Override
    protected void onResume() {
        super.onResume();
        // 为系统的方向传感器注册监听器
    }
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }
    private void showSetpView() {
        List<StepBean> stepsBeanList = new ArrayList<>();
        stepsBeanList.add(stepBean0);
        stepsBeanList.add(stepBean1);
        stepsBeanList.add(stepBean2);
        stepsBeanList.add(stepBean3);
        stepsBeanList.add(stepBean4);
        stepsBeanList.add(stepBean5);
        stepView.setStepViewTexts(stepsBeanList)
                .setTextSize(15)//set textSize
                .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(getApplication(), R.color.material_red))//设置StepsViewIndicator完成线的颜色
                .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(getApplication(), R.color.material_red))//设置StepsViewIndicator未完成线的颜色
                .setStepViewComplectedTextColor(ContextCompat.getColor(getApplication(), R.color.material_red))//设置StepsView text完成线的颜色
                .setStepViewUnComplectedTextColor(ContextCompat.getColor(getApplication(), R.color.material_red))//设置StepsView text未完成线的颜色
                .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(getApplication(), R.drawable.progress_complete))//设置StepsViewIndicator CompleteIcon
                .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(getApplication(), R.drawable.default_icon))//设置StepsViewIndicator DefaultIcon
                .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(getApplication(), R.drawable.progress_attention));//设置StepsViewIndicator AttentionIcon
    }
    @OnClick({R.id.btn_getOrder, R.id.btn_feedback})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_getOrder:

                Intent intent = new Intent(ProgressActivity.this, ScandecoderActivity.class);
                startActivity(intent);
                Toasty.success(ProgressActivity.this, "确认送达", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_feedback:

                RongIM();
                break;
        }
    }
    private void RongIM()
    {
        if (RongIM.getInstance() != null)
        {

            CSCustomServiceInfo.Builder csBuilder = new CSCustomServiceInfo.Builder();
            CSCustomServiceInfo csInfo = csBuilder.nickName("hiho").build();
            RongIM.getInstance().startCustomerServiceChat(ProgressActivity.this, "KEFU150141994749394", "乐货派智能客服",csInfo);
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        }
    }
    @OnClick(R.id.back)
    public void onViewClicked()
    {
        ProgressActivity.this.finish();
    }
    private void  coordinateConvert() {
        CoordinateConverter converter = new CoordinateConverter();
        converter.from(CoordType.COMMON);
        double lanSum = 0;
        double lonSum = 0;
        for (int i = 0; i < Const.googleWGS84.length; i++) {
            String[] ll = Const.googleWGS84[i].split(",");
            LatLng sourceLatLng = new LatLng(Double.valueOf(ll[0]), Double.valueOf(ll[1]));
            converter.coord(sourceLatLng);
            LatLng desLatLng = converter.convert();
            latLngs.add(desLatLng);
            lanSum += desLatLng.latitude;
            lonSum += desLatLng.longitude;
        }
        target = new LatLng(lanSum / latLngs.size(), lonSum / latLngs.size());
    }
}
