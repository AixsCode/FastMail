package com.wxs.fastmail.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wxs.fastmail.Events.EstimateEvent;
import com.wxs.fastmail.R;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class PriceestimateActivity extends AppCompatActivity {
    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.ll_search)
    LinearLayout llSearch;
    @Bind(R.id.lay_tab)
    LinearLayout layTab;
    @Bind(R.id.tv_write1)
    TextView tvWrite1;
    @Bind(R.id.ll_list1)
    LinearLayout llList1;
    @Bind(R.id.tv_write2)
    TextView tvWrite2;
    @Bind(R.id.ll_list2)
    LinearLayout llList2;
    @Bind(R.id.tv_write3)
    TextView tvWrite3;
    @Bind(R.id.ll_list3)
    LinearLayout llList3;
    @Bind(R.id.tv_write4)
    TextView tvWrite4;
    @Bind(R.id.ll_list4)
    LinearLayout llList4;
    @Bind(R.id.ll_price)
    LinearLayout llPrice;
    @Bind(R.id.btn_alter)
    TextView btnAlter;
    @Bind(R.id.btn_sure)
    TextView btnSure;
    @Bind(R.id.ll_btn)
    LinearLayout llBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_priceestimate);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.back)
    public void onViewClicked() {
        PriceestimateActivity.this.finish();
    }

    @OnClick({R.id.tv_write1, R.id.tv_write2, R.id.tv_write3, R.id.tv_write4, R.id.btn_alter, R.id.btn_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_write1:
                EstimateEvent estimateEvent1 = new EstimateEvent("24");
                EventBus.getDefault().postSticky(estimateEvent1);
                PriceestimateActivity.this.finish();
                break;
            case R.id.tv_write2:
                EstimateEvent estimateEvent2 = new EstimateEvent("21.8");
                EventBus.getDefault().postSticky(estimateEvent2);
                PriceestimateActivity.this.finish();
                break;
            case R.id.tv_write3:
                EstimateEvent estimateEvent3 = new EstimateEvent("22");
                EventBus.getDefault().postSticky(estimateEvent3);
                PriceestimateActivity.this.finish();
                break;
            case R.id.tv_write4:
                EstimateEvent estimateEvent4= new EstimateEvent("22.4");
                EventBus.getDefault().postSticky(estimateEvent4);
                PriceestimateActivity.this.finish();
                break;
            case R.id.btn_alter:
                PriceestimateActivity.this.finish();
                break;
            case R.id.btn_sure:
                EstimateEvent estimateEvent = new EstimateEvent("20");
                EventBus.getDefault().postSticky(estimateEvent);
                PriceestimateActivity.this.finish();
                break;
        }
    }
}
