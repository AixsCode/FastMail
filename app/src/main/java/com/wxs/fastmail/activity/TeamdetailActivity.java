package com.wxs.fastmail.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.wxs.fastmail.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
public class TeamdetailActivity extends AppCompatActivity {

    @Bind(R.id.btn_detail)
    Button btnDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teamdetails);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.btn_detail)
    public void onViewClicked() {
        Intent intent =new Intent(TeamdetailActivity.this,TeamPayActivity.class);
        startActivity(intent);
    }
}
