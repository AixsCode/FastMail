package com.wxs.fastmail.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.wxs.fastmail.R;
import com.wxs.fastmail.common.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ConversationActivity extends BaseActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void initData() {
    }
    @Override
    protected void initTitle() {


       String title= getIntent().getData().getQueryParameter("title").toString();
      //  String title= getIntent().getData().getQueryParameter("targetId").toString();;
        tvTitle.setText(title);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_conversation;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
