package com.wxs.fastmail.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wxs.fastmail.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InsuranceActivity extends AppCompatActivity
{
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.textView11)
    TextView textView11;
    @Bind(R.id.btn_insurance)
    Button btnInsurance;
    @Bind(R.id.lay_call)
    LinearLayout layCall;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insurance);
        ButterKnife.bind(this);
    }
    @OnClick({R.id.lay_call, R.id.btn_insurance})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.lay_call:
                Intent dialIntent =  new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "15858196281"));//跳转到拨号界面，同时传递电话号码
                startActivity(dialIntent);
                break;
            case R.id.btn_insurance:
                 Intent intent =new Intent(getApplicationContext(),InsurancefinishActivity.class);
                startActivity(intent);
                InsuranceActivity.this.finish();
                break;
        }
    }
}
