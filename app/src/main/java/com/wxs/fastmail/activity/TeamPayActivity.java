package com.wxs.fastmail.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wxs.fastmail.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

public class TeamPayActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tvGoodsPrice)
    TextView tvGoodsPrice;
    @Bind(R.id.ivGoodsAdd)
    ImageView ivGoodsAdd;
    @Bind(R.id.tvGoodsSelectNum)
    TextView tvGoodsSelectNum;
    @Bind(R.id.ivGoodsMinus)
    ImageView ivGoodsMinus;
    @Bind(R.id.tv_finalmoney)
    TextView tvFinalmoney;
    @Bind(R.id.btn_pay)
    Button btnPay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_pay);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ivGoodsAdd, R.id.ivGoodsMinus})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivGoodsAdd:
                tvGoodsSelectNum.setText("2");
                tvFinalmoney.setText("¥28.00");
                break;
            case R.id.ivGoodsMinus:
                tvGoodsSelectNum.setText("1");
                tvFinalmoney.setText("¥18.00");
                break;
        }
    }

    @OnClick(R.id.btn_pay)
    public void onViewClicked()
    {
        Toasty.success(getApplicationContext(),"支付成功", Toast.LENGTH_SHORT).show();
          this.finish();
    }
}
