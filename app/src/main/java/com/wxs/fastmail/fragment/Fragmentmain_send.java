package com.wxs.fastmail.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.wxs.fastmail.R;
import com.wxs.fastmail.activity.BusListActivity;
import com.wxs.fastmail.activity.MadeOrderActivity;
import com.wxs.fastmail.activity.MaplookActivity;
import com.wxs.fastmail.activity.VoiceActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wxs on 2017/7/15.
 */

public class Fragmentmain_send extends Fragment {
    @Bind(R.id.send_dec)
    ImageView sendDec;
    @Bind(R.id.send_eat)
    ImageView sendEat;
    @Bind(R.id.send_market)
    ImageView sendMarket;
    @Bind(R.id.send_flower)
    ImageView sendFlower;
    @Bind(R.id.send_drink)
    ImageView sendDrink;
    @Bind(R.id.send_baidumap)
    LinearLayout sendBaidumap;
    @Bind(R.id.mail_editText)
    EditText mailEditText;
    @Bind(R.id.mail_oder)
    Button mailOder;
    @Bind(R.id.mail_ad)
    ImageView mailAd;
    @Bind(R.id.send_voice)
    ImageView sendVoice;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = View.inflate(getActivity(), R.layout.fragmentmain_send, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.send_dec, R.id.send_eat, R.id.send_market, R.id.send_flower, R.id.send_drink, R.id.send_baidumap, R.id.mail_editText, R.id.mail_oder, R.id.mail_ad})
    public void onViewClicked(View view)
    {
        switch (view.getId()) {
            case R.id.send_dec:
                Intent intent2 = new Intent(getContext(), BusListActivity.class);
                startActivity(intent2);
                break;
            case R.id.send_eat:
                break;
            case R.id.send_market:
                break;
            case R.id.send_flower:
                break;
            case R.id.send_drink:
                break;
            case R.id.send_baidumap:
                Intent intent3 = new Intent(getContext(), MaplookActivity.class);
                startActivity(intent3);
                break;
            case R.id.mail_editText:
                break;
            case R.id.mail_oder:
                Intent intent1 = new Intent(getContext(), MadeOrderActivity.class);
                startActivity(intent1);
                break;
            case R.id.mail_ad:
                break;
            default:
                break;
        }
    }
    @OnClick(R.id.send_voice)
    public void onViewClicked()
    {
        Intent intent=new Intent(getActivity(), VoiceActivity.class);
        startActivity(intent);
    }
}
