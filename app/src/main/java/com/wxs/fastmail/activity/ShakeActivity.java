package com.wxs.fastmail.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wxs.fastmail.R;
import com.wxs.fastmail.Shake.NeedForAnim;
import com.wxs.fastmail.Shake.NeedForSound;
import com.wxs.fastmail.Shake.ShakeListener;
import com.wxs.fastmail.Shake.ShakeListener.OnShakeListenerCallBack;

public class ShakeActivity extends AppCompatActivity
{
    /** 手掌上部分 */
    private RelativeLayout mImgUp;

    /** 手掌下部分 */
    private RelativeLayout mImgDn;

    /** 手掌上部分边线 */
    private LinearLayout mImgUpLine;

    /** 手掌下部分边线 */
    private LinearLayout mImgDnLine;

    /** 摇一摇结果view */
    private RelativeLayout mResultayout;
    private ImageView mResulImg;
    private TextView mResulName;
    private TextView mResulValue;
    private LinearLayout mShakeLoading;

    private ShakeListener mShakeListener = null;

    public final static int RESULT_ANIM_VISIBLE_END = 100;// 结果页显示动画结束
    public final static int RESULT_ANIM_GONE_END = 101;// 结果页隐藏动画结束
    public final static int HAND_ANIM_START = 102;// 手掌动画开始
    public final static int HAND_ANIM_END = 103;// 手掌动画结束

    public final static int SET_IMG_SUC = 104;// 结果页图片设置成功
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shake_root);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mImgUp = (RelativeLayout) findViewById(R.id.shakeImgUp);
        mImgDn = (RelativeLayout) findViewById(R.id.shakeImgDown);
        mImgUpLine = (LinearLayout) findViewById(R.id.shakeImgUp_line);
        mImgDnLine = (LinearLayout) findViewById(R.id.shakeImgDown_line);
        mShakeLoading = (LinearLayout) findViewById(R.id.shake_loading);
        mResultayout = (RelativeLayout) findViewById(R.id.shake_result_layout);
        mResulImg = (ImageView) findViewById(R.id.shake_result_img);
        mResulName = (TextView) findViewById(R.id.shake_result_txt_name);
        mResulValue = (TextView) findViewById(R.id.shake_result_txt_value);
        mResulName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(ShakeActivity.this, GoldcourieroneActivity.class);
                startActivity(intent);
                ShakeActivity.this.finish();
            }
        });
        setHandLineVisible(false);
        setResultVisible(false);
        showShakeLoading(false);
        initShake();
    }
    private void initShake() {
        NeedForSound.getInstance().addSound(this);
        mShakeListener = new ShakeListener(this);
        mShakeListener.setOnShakeListener(shakeListener);
    }
    OnShakeListenerCallBack shakeListener = new OnShakeListenerCallBack() {
        public void onShake() {
            startShakeAnim(); // 开始 摇一摇手掌动画
            mShakeListener.stop();
            NeedForSound.getInstance().playStartSound();
        }
    };
    /** 设置摇一摇结果页面 */
    private void setResultView() {
        if (this == null) {
            return;
        }
        try {
            mResulImg.setImageResource(R.drawable.add_person_10);
            mResulName.setText("我是金牌快递员哦");
            mResulValue.setVisibility(View.GONE);
            showShakeResultView(false);
        }
        catch (NullPointerException e)
        {
        }
     }

    /**
     * 显示摇一摇结果
     *
     * @param isHaveResult
     *            true:摇到了礼品.false:什么都没摇到.
     * */
    private void showShakeResultView(boolean isHaveResult) {
        showShakeLoading(false);
        if (isHaveResult) {
            NeedForSound.getInstance().playEndSound();
        } else {
            NeedForSound.getInstance().playNothingSound();
        }
        startResultVisibleAnim();
    }
    private Handler mhHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case RESULT_ANIM_VISIBLE_END:
                    mShakeListener.start();
                    break;
                case RESULT_ANIM_GONE_END:
                    setResultVisible(false);
                    break;
                case HAND_ANIM_START:
                    setHandLineVisible(true);
                    break;
                case HAND_ANIM_END:
                    setHandLineVisible(false);
                    setResultView();
                    break;
                case SET_IMG_SUC:
                    showShakeResultView(true);
                    break;
                default:
                    break;
            }
        }
    };
    /** 摇一摇手掌开合动画 */
    private void startShakeAnim()
    {
        mImgUp.startAnimation(NeedForAnim.getInstance().getUpAnim(mhHandler));
        mImgDn.startAnimation(NeedForAnim.getInstance().getDownAnim());
        if (mResultayout.getVisibility() == View.VISIBLE) {// 隐藏上次摇到的结果
            startResultGoneAnim();
        }
    }
    /** 摇出的结果显示动画 */
    public void startResultVisibleAnim()
    {
        setResultVisible(true);
        mResultayout.startAnimation(NeedForAnim.getInstance().getResultVisibleAnim(mhHandler));
    }
    /** 摇出的结果隐藏动画 */
    public void startResultGoneAnim()
    {
        mResultayout.startAnimation(NeedForAnim.getInstance().getResultGoneAnim(mhHandler));
    }
    /** 手掌边线显示控制 */
    private void setHandLineVisible(boolean isVisible) {
        try {
            if (this == null) {
                return;
            }
            if (isVisible) {
                mImgUpLine.setVisibility(View.VISIBLE);
                mImgDnLine.setVisibility(View.VISIBLE);
            } else {
                mImgUpLine.setVisibility(View.GONE);
                mImgDnLine.setVisibility(View.GONE);
            }
        } catch (NullPointerException e) {
        }
    }
    /** 摇出的结果显示控制 */
    private void setResultVisible(boolean isVisible)
    {
        if (this == null) {
            return;
        }
        if (isVisible) {
            mResultayout.setVisibility(View.VISIBLE);
        } else {
            mResultayout.setVisibility(View.INVISIBLE);
        }
    }
    /** 正在加载 */
    private void showShakeLoading(boolean isShow) {
        if (this == null) {
            return;
        }

        if (isShow) {
            mShakeLoading.setVisibility(View.VISIBLE);
        } else {
            mShakeLoading.setVisibility(View.GONE);
        }
    }
    @Override
    public void onResume()
    {
        super.onResume();
        mShakeListener.start();
    }
    @Override
    public void onPause()
    {
        mShakeListener.stop();
        super.onPause();
    }
    @Override
    public void onStop()
    {
        mShakeListener.stop();
        super.onStop();
    }
}
