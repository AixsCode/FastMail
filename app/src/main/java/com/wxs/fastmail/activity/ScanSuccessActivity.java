package com.wxs.fastmail.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.wxs.fastmail.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

public class ScanSuccessActivity extends AppCompatActivity {
    static  ScanSuccessActivity ScanSuccessActivity;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.textView10)
    TextView textView10;
    @Bind(R.id.tv_interest1)
    TextView tvInterest1;
    @Bind(R.id.tv_interest2)
    TextView tvInterest2;
    @Bind(R.id.btn_comment)
    Button btnComment;
    @Bind(R.id.btn_feedback)
    Button btnFeedback;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        ScanSuccessActivity=this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_success);
        ButterKnife.bind(this);
    }
    @OnClick({R.id.tv_interest1, R.id.tv_interest2, R.id.btn_comment, R.id.btn_feedback})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.tv_interest1:
                Toasty.success(ScanSuccessActivity.this,"已添加关注", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_interest2:
                Toasty.success(ScanSuccessActivity.this,"已添加关注", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_comment:
                Intent intent =new Intent(ScanSuccessActivity.this,EvaluateActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_feedback:
                Toasty.normal(ScanSuccessActivity.this,"确认反馈",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
