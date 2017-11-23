package com.wxs.fastmail.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.wxs.fastmail.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ApplyFiveActivity extends AppCompatActivity
{
    @Bind(R.id.imageView6)
    ImageView imageView6;
    private  static  final String TAG="ApplyFiveActivity";
    private  int MAIN=122;
    private Handler mHandler=new Handler(new Handler.Callback()
    {
        @Override
        public boolean handleMessage(Message msg)
        {
            Intent intent=new Intent(ApplyFiveActivity.this,DispatchActivity.class);
            startActivity(intent);
            return false;
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_five);
        ButterKnife.bind(this);
        Message msg=Message.obtain(mHandler);
        msg.what=MAIN;
        mHandler.sendMessageDelayed(msg,2500);
    }
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }
}
