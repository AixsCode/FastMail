package com.wxs.fastmail.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.wxs.fastmail.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

public class ApplyOneActivity extends AppCompatActivity
{

    @Bind(R.id.btn_sure_apply)
    Button btnSureApply;

    //Core_tag 是用户信用积分

    public  static   final  int Core_tag=500;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_one);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.btn_sure_apply)
    public void onViewClicked()
    {

        if(Core_tag>=500)
        {
            Intent intent =new Intent(ApplyOneActivity.this,ApplyTwoActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        }
        else
        {
            Toasty.error(getApplication(),"您的幸运积分还未达500", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }
}
