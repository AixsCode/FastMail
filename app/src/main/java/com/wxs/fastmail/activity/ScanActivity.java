package com.wxs.fastmail.activity;

import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.wxs.fastmail.R;

import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;
import es.dmoral.toasty.Toasty;

public class ScanActivity extends AppCompatActivity  implements  QRCodeView.Delegate{

    private  static  final  String TAG=ScanActivity.class.getSimpleName();
    private static final int REQUEST_CODE_CHOOSE_QRCODE_FROM_GALLERY = 666;
    private QRCodeView mQRCodeView;
    private   static  final  int ScanSuccess=555;
    private Handler mHandler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg)
        {
            switch (msg.what)
            {
                case  ScanSuccess:
                     vibrate();
                      Toasty.success(getApplicationContext(),"订单20086111655632签收成功",Toast.LENGTH_LONG).show();
                      ScanActivity.this.finish();
                      break;
                default:
                    break;
            }
            return false;
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        mQRCodeView = (ZXingView) findViewById(R.id.zxingview);
        mQRCodeView.setDelegate(this);
        mQRCodeView.startSpot();
        Message msg=Message.obtain(mHandler);
        msg.what=ScanSuccess;
        mHandler.sendMessageDelayed(msg,7000);

    }
    @Override
    protected void onStart()
    {
        super.onStart();
        mQRCodeView.startCamera();
        mQRCodeView.startCamera(Camera.CameraInfo.CAMERA_FACING_FRONT);
        mQRCodeView.showScanRect();
        mQRCodeView.startSpot();
    }
    @Override
    protected void onStop()
    {
        mQRCodeView.stopCamera();
        super.onStop();
    }
    @Override
    protected void onRestart() {
        Message msg=Message.obtain(mHandler);
        msg.what=ScanSuccess;
        mHandler.sendMessageDelayed(msg,7000);
        mQRCodeView.startSpot();
        super.onRestart();
    }
    @Override
    protected void onDestroy() {
        mQRCodeView.onDestroy();
        super.onDestroy();
    }

    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }
    @Override
    public void onScanQRCodeSuccess(String result)
    {
        Log.i(TAG, "result:" + result);
        Toasty.success(ScanActivity.this,"签收成功",Toast.LENGTH_SHORT).show();
        Intent intent =new Intent(ScanActivity.this,ScanSuccessActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
        vibrate();
        mQRCodeView.startSpot();
    }
    @Override
    public void onScanQRCodeOpenCameraError()
    {
        Log.e(TAG, "打开相机出错");
    }
}
