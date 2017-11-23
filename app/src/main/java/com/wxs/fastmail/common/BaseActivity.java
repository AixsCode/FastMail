package com.wxs.fastmail.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import butterknife.ButterKnife;

/**
 * Created by wxs on 2017/7/6.
 */

public abstract class BaseActivity extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initTitle();
        initData();
        ActivityManager.getInstance().add(this);
    }
    protected abstract void initData();

    protected abstract void initTitle();

    public abstract int getLayoutId();

    //销毁当前的Activity
    public void removeCurrentActivity() {
        ActivityManager.getInstance().removeCurrent();
    }
    //销毁所有的activity
    public void removeAll()
    {
        ActivityManager.getInstance().removeAll();
    }

}
