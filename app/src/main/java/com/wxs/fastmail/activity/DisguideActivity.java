package com.wxs.fastmail.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wxs.fastmail.Events.FlagEvent;
import com.wxs.fastmail.R;
import com.wxs.fastmail.tool.Tool_Url;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import okhttp3.Call;
import okhttp3.Request;

public class DisguideActivity extends AppCompatActivity
{
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tv_contact1)
    TextView tvContact1;
    @Bind(R.id.tv_contact2)
    TextView tvContact2;
    @Bind(R.id.lay_btn_guide)
    LinearLayout layBtnGuide;
    @Bind(R.id.btn_get_things)
    Button btnGetThings;
    static Activity DisguideActivity;
    private int flag=0;
    private static  final   String TAG=DispatchActivity.class.getSimpleName();
    private static  final   int    sure_get=1234;
    private android.os.Handler mHandler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg)
        {
         switch (msg.what) {
             case  sure_get:
                 btnGetThings   .setText("确认送达");
                 break;
         }
             return false;
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        DisguideActivity=this;
        setContentView(R.layout.activity_disguide);
        ButterKnife.bind(this);
    }
    private void RongIM()
    {
        if (RongIM.getInstance() != null)
        {
            RongIM.getInstance().startPrivateChat(DisguideActivity.this, "2015", "思想");
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        }
    }
    private void RongIM1()
    {
        if (RongIM.getInstance() != null)
        {
            RongIM.getInstance().startPrivateChat(DisguideActivity.this, "2019", "大西瓜");
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
                RongIM1();
                break;
            case R.id.lay_btn_guide:
                Intent intent =new Intent(DisguideActivity.this,BaiduNaviActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_get_things:
                  if(flag==0)
                  {
                      // DisguideActivity.DisguideActivity.finish();
                      Intent intent1 = new Intent(DisguideActivity.this, SurePickActivity.class);
                      startActivity(intent1);
                      Message msg = Message.obtain(mHandler);
                      msg.what = sure_get;
                      mHandler.sendMessageDelayed(msg, 4000);
                      flag++;
                  }
                  else if(flag==1)
                  {
                      FlagEvent flagEvent = new FlagEvent(1);
                      EventBus.getDefault().postSticky(flagEvent);
                      Intent intent2=new Intent(DisguideActivity.this,ScanActivity.class);
                      startActivity(intent2);
                      DisguideActivity.this.finish();
                  }
                  else
                  {
                  }
                  OkHttpUtils
                        .post()
                        .url(Tool_Url.getUrl_post_goodArrive())
                        .addParams("orderId","13")
                        .build()
                        .execute(new MyStringCallback());
                break;
        }
    }
    private class MyStringCallback extends StringCallback {
        @Override
        public void onBefore(Request request, int id) {

            Log.i(TAG, "onBefore");
        }
        @Override
        public void onAfter(int id)
        {
            Log.i(TAG, "onAfter");
        }
        @Override
        public void onError(Call call, Exception e, int id)
        {
            Log.i(TAG, "onError");
            e.printStackTrace();
        }
        @Override
        public void onResponse(String response, int id)
        {
            Log.i(TAG, "onResponse \t" + response);
        }
    }
}
