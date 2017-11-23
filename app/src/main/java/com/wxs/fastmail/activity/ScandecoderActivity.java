package com.wxs.fastmail.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.Toast;

import com.wxs.fastmail.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bingoogolapple.qrcode.core.BGAQRCodeUtil;
import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;

public class ScandecoderActivity extends AppCompatActivity
{
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.iv_english_logo)
    ImageView ivEnglishLogo;

    private  static  final int  Order_end=134;
    private Handler mHandler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case  Order_end:

                    Intent intent =new Intent(ScandecoderActivity.this,ScanSuccessActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
                    break;
            }
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scandecoder);
        ButterKnife.bind(this);
        createQRCode();
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        Message msg=Message.obtain(mHandler);
        msg.what=Order_end;
        mHandler.sendMessageDelayed(msg,5000);
    }
    private void createQRCode()
    {
        new AsyncTask<Void, Void, Bitmap>()
        {
            @Override
            protected Bitmap doInBackground(Void... params) {
                Bitmap logoBitmap = BitmapFactory.decodeResource(ScandecoderActivity.this.getResources(), R.mipmap.logo_icon);
                return QRCodeEncoder.syncEncodeQRCode("20086111655632", BGAQRCodeUtil.dp2px(ScandecoderActivity.this, 200), Color.BLACK, Color.WHITE, logoBitmap);
            }
            @Override
            protected void onPostExecute(Bitmap bitmap) {
                if (bitmap != null) {
                    ivEnglishLogo.setImageBitmap(bitmap);
                } else {
                    Toast.makeText(ScandecoderActivity.this, "生成带logo的英文二维码失败", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }
}
