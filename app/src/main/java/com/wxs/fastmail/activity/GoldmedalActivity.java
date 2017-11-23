package com.wxs.fastmail.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.wxs.fastmail.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GoldmedalActivity extends AppCompatActivity {

    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.lay_goldcourier1)
    LinearLayout layGoldcourier1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goldmedal);
        ButterKnife.bind(this);
    }
    @OnClick({R.id.back, R.id.lay_goldcourier1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                GoldmedalActivity.this.finish();
                break;
            case R.id.lay_goldcourier1:
                Intent intent =new Intent(GoldmedalActivity.this,GoldcourierActivity.class);
                startActivity(intent);
                break;
        }
    }
}
