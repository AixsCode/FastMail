package com.wxs.fastmail.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wxs.fastmail.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;
import io.rong.imkit.RongIM;

public class GoldcourieroneActivity extends AppCompatActivity {

    @Bind(R.id.bg_person)
    ImageView bgPerson;
    @Bind(R.id.me_icon)
    CircleImageView meIcon;
    @Bind(R.id.tv_me)
    TextView tvMe;
    @Bind(R.id.tv_introduce)
    TextView tvIntroduce;
    @Bind(R.id.iv_goldmedal)
    ImageView ivGoldmedal;
    @Bind(R.id.view_line1)
    View viewLine1;
    @Bind(R.id.linearLayout3)
    LinearLayout linearLayout3;
    @Bind(R.id.view_line2)
    View viewLine2;
    @Bind(R.id.tv_label)
    TextView tvLabel;
    @Bind(R.id.textView3)
    TextView textView3;
    @Bind(R.id.textView8)
    TextView textView8;
    @Bind(R.id.textView14)
    TextView textView14;
    @Bind(R.id.textView15)
    TextView textView15;
    @Bind(R.id.view_line3)
    View viewLine3;
    @Bind(R.id.tv_teambuy)
    TextView tvTeambuy;
    @Bind(R.id.iv_gift)
    ImageView ivGift;
    @Bind(R.id.tv_gift)
    TextView tvGift;
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.btn_sendmessage)
    Button btnSendmessage;
    @Bind(R.id.btn_add_mine)
    Button btnAddMine;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goldcourierone);
        ButterKnife.bind(this);
    }
    private void RongIM()
    {
        if (RongIM.getInstance() != null)
        {
            RongIM.getInstance().startPrivateChat(GoldcourieroneActivity.this, "1997", "大长腿");
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        }
    }
    @OnClick({R.id.btn_sendmessage, R.id.btn_add_mine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_sendmessage:
                RongIM();
                GoldcourieroneActivity.this.finish();
                break;
            case R.id.btn_add_mine:
                Toasty.success(GoldcourieroneActivity.this,"关注成功", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
