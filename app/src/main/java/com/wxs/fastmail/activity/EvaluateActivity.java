package com.wxs.fastmail.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wxs.fastmail.R;
import com.wxs.fastmail.utils.Util_HiddenAnim;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

public class EvaluateActivity extends AppCompatActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.textView4)
    TextView textView4;
    @Bind(R.id.iv_toggle1)
    ImageView ivToggle1;
    @Bind(R.id.id_flowlayout)
    TagFlowLayout idFlowlayout;
    @Bind(R.id.tv_send)
    TextView tvSend;
    @Bind(R.id.lay_hide1)
    LinearLayout layHide1;
    @Bind(R.id.iv_toggle2)
    ImageView ivToggle2;
    @Bind(R.id.lay_hide2)
    LinearLayout layHide2;
    @Bind(R.id.btn_evaluate)
    Button btnEvaluate;

    private String[] mVals = new String[]{"超快", "技术大神", "社交小哥", "NIKE达人", "爱狗人士", "很好吃", "超贴心", "饮食人才"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluate);
        ButterKnife.bind(this);
        initialize();
    }
    private void initialize()
    {
        idFlowlayout.setAdapter(new TagAdapter<String>(mVals)
        {
            @Override
            public View getView(FlowLayout parent, int position, String s)
            {
                final LayoutInflater mInflater = LayoutInflater.from(getApplicationContext());
                TextView tv = (TextView) mInflater.inflate(R.layout.tag_item_tv, idFlowlayout, false);
                tv.setText(s);
                return tv;
            }
        });
    }
    @OnClick({R.id.iv_toggle1, R.id.iv_toggle2})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.iv_toggle1:
                Util_HiddenAnim.newInstance(getApplicationContext(), layHide1, ivToggle1, 200).toggle();
                break;
            case R.id.iv_toggle2:
                Util_HiddenAnim.newInstance(getApplicationContext(), layHide2, ivToggle2, 240).toggle();
                break;
        }
    }
    @OnClick(R.id.btn_evaluate)
    public void onViewClicked()
    {
         Toasty.success(EvaluateActivity.this,"评价成功", Toast.LENGTH_SHORT).show();
         Intent intent =new Intent(EvaluateActivity.this,MainActivity.class);
         startActivity(intent);
    }
}
