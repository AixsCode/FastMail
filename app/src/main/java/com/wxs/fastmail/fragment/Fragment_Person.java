package com.wxs.fastmail.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wxs.fastmail.R;
import com.wxs.fastmail.activity.ApplyOneActivity;
import com.wxs.fastmail.activity.PersonCoreActivity;
import com.wxs.fastmail.activity.PersonmeyActivity;
import com.wxs.fastmail.activity.PersonmsgActicity;
import com.wxs.fastmail.activity.PersonordActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by wxs on 2017/7/6.
 */

public class Fragment_Person extends Fragment {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.me_icon)
    CircleImageView meIcon;
    @Bind(R.id.me_userName)
    TextView meUserName;
    @Bind(R.id.me_ms)
    LinearLayout meMs;
    @Bind(R.id.me_order)
    LinearLayout meOrder;
    @Bind(R.id.me_core)
    LinearLayout meCore;
    @Bind(R.id.me_money)
    LinearLayout meMoney;
    @Bind(R.id.me_main)
    LinearLayout meMain;
    @Bind(R.id.me_label)
    LinearLayout meLabel;
    @Bind(R.id.me_apply)
    LinearLayout meApply;
    @Bind(R.id.me_set)
    LinearLayout meSet;
    @Bind(R.id.me_feed_back)
    LinearLayout meFeedBack;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = View.inflate(getActivity(), R.layout.fragment_person, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
    @OnClick({R.id.me_ms, R.id.me_order, R.id.me_core, R.id.me_money, R.id.me_apply})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.me_ms:
                Intent intent=new Intent(getContext(), PersonmsgActicity.class);
                startActivity(intent);
                break;
            case R.id.me_order:
                Intent intent3=new Intent(getContext(), PersonordActivity.class);
                startActivity(intent3);
                break;
            case R.id.me_core:
                Intent intent4=new Intent(getContext(), PersonCoreActivity.class);
                startActivity(intent4);
                break;
            case R.id.me_money:
                Intent intent1=new Intent(getContext(), PersonmeyActivity.class);
                startActivity(intent1);
                break;
            case R.id.me_apply:
                Intent intent2=new Intent(getContext(), ApplyOneActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
