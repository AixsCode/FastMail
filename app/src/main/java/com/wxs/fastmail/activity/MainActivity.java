package com.wxs.fastmail.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wxs.fastmail.R;
import com.wxs.fastmail.common.BaseActivity;
import com.wxs.fastmail.fragment.Fragment_Community;
import com.wxs.fastmail.fragment.Fragment_MainPage;
import com.wxs.fastmail.fragment.Fragment_Message;
import com.wxs.fastmail.fragment.Fragment_Person;

import butterknife.Bind;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public class MainActivity extends BaseActivity
{
    @Bind(R.id.main_layout)
    LinearLayout mainLayout;
    @Bind(R.id.main_fragment)
    FrameLayout mainFragment;
    @Bind(R.id.main_img1)
    ImageButton mainImg1;
    @Bind(R.id.main_tv1)
    TextView mainTv1;
    @Bind(R.id.main_main)
    LinearLayout mainMain;
    @Bind(R.id.main_img3)
    ImageButton mainImg3;
    @Bind(R.id.main_tv3)
    TextView mainTv3;
    @Bind(R.id.main_message)
    LinearLayout mainMessage;
    @Bind(R.id.main_img2)
    ImageButton mainImg2;
    @Bind(R.id.main_tv2)
    TextView mainTv2;
    @Bind(R.id.main_find)
    LinearLayout mainFind;
    @Bind(R.id.main_img4)
    ImageButton mainImg4;
    @Bind(R.id.main_tv4)
    TextView mainTv4;
    @Bind(R.id.main_me)
    LinearLayout mainMe;
    private Fragment fr_mainpage, fr_message, fr_me, fr_btn2;
    private static final String TAG = "MainActivity";
    @Override
    protected void initData()
    {
    }
    @Override
    protected void initTitle()
    {
        mainMain.setOnClickListener(new IconOnClickListener());
        mainFind.setOnClickListener(new IconOnClickListener());
        mainMessage.setOnClickListener(new IconOnClickListener());
        mainMe.setOnClickListener(new IconOnClickListener());
        // initFragment
        setFragmentSelect(0);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (fr_mainpage != null) {
            transaction.hide(fr_mainpage);
        }
        if (fr_message != null) {
            transaction.hide(fr_message);
        }
        if (fr_me != null) {
            transaction.hide(fr_me);
        }
        if (fr_btn2 != null) {
            transaction.hide(fr_btn2);
        }
    }
    private void setFragmentSelect(int i) {
        FragmentManager mFragmentManager = getSupportFragmentManager();
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        hideFragment(mFragmentTransaction);

        switch (i) {
            case 0:
                if (fr_mainpage == null) {
                    fr_mainpage = new Fragment_MainPage();
                    mFragmentTransaction.add(R.id.main_fragment, fr_mainpage);
                } else {
                    mFragmentTransaction.show(fr_mainpage);
                }
                break;
            case 1:
                if (fr_btn2 == null) {
                    fr_btn2 = new Fragment_Community();
                    mFragmentTransaction.add(R.id.main_fragment, fr_btn2);
                } else {
                    mFragmentTransaction.show(fr_btn2);
                }
                break;
            case 2:
                if (fr_message == null) {
                    fr_message = new Fragment_Message();
                    mFragmentTransaction.add(R.id.main_fragment, fr_message);
                } else {
                    mFragmentTransaction.show(fr_message);
                }
                break;
            case 3:
                if (fr_me == null) {
                    fr_me = new Fragment_Person();
                    mFragmentTransaction.add(R.id.main_fragment, fr_me);

                } else {
                    mFragmentTransaction.show(fr_me);
                }
                break;
        }
        mFragmentTransaction.commit();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
    private class IconOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            resetOtherTabs();
            switch (v.getId()) {
                case R.id.main_main:
                    mainTv1.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.colorAccent));
                    mainImg1.setBackgroundResource(R.drawable.table_home_click);
                    setFragmentSelect(0);
                    break;
                case R.id.main_find:
                    mainTv2.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.colorAccent));
                    mainImg2.setBackgroundResource(R.drawable.table_community_click);
                    setFragmentSelect(1);
                    break;
                case R.id.main_message:
                    mainTv3.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.colorAccent));
                    mainImg3.setBackgroundResource(R.drawable.table_message_click);
                    setFragmentSelect(2);
                    break;
                case R.id.main_me:
                    mainTv4.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.colorAccent));
                    mainImg4.setBackgroundResource(R.drawable.table_person_click);
                    setFragmentSelect(3);
                    break;
                default:
                    break;
            }
        }
        private void resetOtherTabs() {
            mainTv1.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.text1));
            mainImg1.setBackgroundResource(R.drawable.table_home_unclick);

            mainTv2.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.text1));
            mainImg2.setBackgroundResource(R.drawable.table_community_unclick);

            mainTv3.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.text1));
            mainImg3.setBackgroundResource(R.drawable.table_message_unclick);

            mainTv4.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.text1));
            mainImg4.setBackgroundResource(R.drawable.table_person_unclick);
        }
    }
    //重写onKeyUp()，实现连续两次点击方可退出当前应用
    private boolean flag = true;
    private static final int WHAT_RESET_BACK = 1;
    private android.os.Handler handler = new Handler(){
        public void handleMessage(Message msg){
            Log.e("TAG", "handleMessage");
            switch (msg.what) {
                case WHAT_RESET_BACK :
                    flag = true;//复原
                    break;
            }
        }
    };
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && flag){

            Toasty.normal(MainActivity.this,"再点击一次，退出当前应用",Toast.LENGTH_SHORT).show();
            flag = false;
            //发送延迟消息
            handler.sendEmptyMessageDelayed(WHAT_RESET_BACK,2000);
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

}

