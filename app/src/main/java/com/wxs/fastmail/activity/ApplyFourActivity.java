package com.wxs.fastmail.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.wxs.fastmail.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ApplyFourActivity extends AppCompatActivity {

    @Bind(R.id.btn_sure_next)
    Button btnSureNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_four);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.btn_sure_next)
    public void onViewClicked() {
        Intent intent =new Intent(ApplyFourActivity.this,ApplyFiveActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
