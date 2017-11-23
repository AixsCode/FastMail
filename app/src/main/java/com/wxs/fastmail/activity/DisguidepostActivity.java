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
import android.widget.Toast;

import com.wxs.fastmail.Events.FlagEvent;
import com.wxs.fastmail.R;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import io.rong.imkit.RongIM;

public class DisguidepostActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tv_contact1)
    TextView tvContact1;
    @Bind(R.id.tv_contact2)
    TextView tvContact2;
    @Bind(R.id.lay_person)
    LinearLayout layPerson;
    @Bind(R.id.lay_address)
    LinearLayout layAddress;
    @Bind(R.id.lay_time)
    LinearLayout layTime;
    @Bind(R.id.lay_money)
    LinearLayout layMoney;
    @Bind(R.id.lay_btn_guide)
    LinearLayout layBtnGuide;
    @Bind(R.id.btn_get_things)
    Button btnGetThings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disguidepost);
        ButterKnife.bind(this);
    }
    private void RongIM()
    {
        if (RongIM.getInstance() != null)
        {
            RongIM.getInstance().startPrivateChat(DisguidepostActivity.this, "2015", "随手快递");
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        }
    }
    @OnClick({R.id.tv_contact1, R.id.tv_contact2, R.id.lay_btn_guide, R.id.btn_get_things})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.tv_contact1:
                RongIM();
                break;
            case R.id.tv_contact2:
                Intent dialIntent =  new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "15858196281"));//跳转到拨号界面，同时传递电话号码
                startActivity(dialIntent);
                break;
            case R.id.lay_btn_guide:
                Toasty.normal(DisguidepostActivity.this,"导航", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_get_things:
                FlagEvent  flagEvent=new FlagEvent(1);
                EventBus.getDefault().postSticky(flagEvent);
                Toasty.success(DisguidepostActivity.this,"提货成功",Toast.LENGTH_LONG).show();
                DisguidepostActivity.this.finish();
                break;
        }
    }
}
