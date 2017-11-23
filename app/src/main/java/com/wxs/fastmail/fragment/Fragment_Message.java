package com.wxs.fastmail.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.wxs.fastmail.R;
import com.wxs.fastmail.activity.MsNotifyActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;

/**
 * Created by wxs on 2017/7/6.
 */

public class Fragment_Message extends Fragment
{
    @Bind(R.id.im_message)
    ImageView imMessage;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.ll_search)
    LinearLayout llSearch;
    @Bind(R.id.person1)
    LinearLayout person1;
    @Bind(R.id.person2)
    LinearLayout person2;
    @Bind(R.id.person3)
    LinearLayout person3;
    @Bind(R.id.person4)
    LinearLayout person4;
    @Bind(R.id.person5)
    LinearLayout person5;
    @Bind(R.id.person6)
    LinearLayout person6;
    @Bind(R.id.person7)
    LinearLayout person7;
    private static String TAG="Fragment_Message";
    //2017
    private  static  final String token3="Tt/qGPZfuBJMSI3DEa9h1sAGqnp2FrMlhV4BFCU1BcUrXsDN8oIMEZaWUIDu4MlVck9D7V8m0AekSQ8ochK8yw==";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = View.inflate(getActivity(), R.layout.fragment_message, null);
        ButterKnife.bind(this, view);
        return view;
    }
    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
    private void RongIM1()
    {
        if (RongIM.getInstance() != null)
        {
            RongIM.getInstance().startPrivateChat(getActivity(), "2017", "大西瓜");
            getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        }
    }
    private void RongIM2()
    {
        if (RongIM.getInstance() != null)
        {
            RongIM.getInstance().startPrivateChat(getActivity(), "2016", "若风");
            getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        }
    }
    private void RongIM3()
    {
        if (RongIM.getInstance() != null)
        {
            RongIM.getInstance().startPrivateChat(getActivity(), "2018", "布兰登英格拉姆");
            getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        }
    }
    private void RongIM4()
    {
        if (RongIM.getInstance() != null)
        {
            RongIM.getInstance().startPrivateChat(getActivity(), "2019", "小可爱");
            getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        }
    }
    private void RongIM5()
    {
        if (RongIM.getInstance() != null)
        {
            RongIM.getInstance().startPrivateChat(getActivity(), "2020", "恋爱小军");
            getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        }
    }
    private void RongIM6()
    {
        if (RongIM.getInstance() != null)
        {
            RongIM.getInstance().startPrivateChat(getActivity(), "2021", "QiangLi哟");
            getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        }
    }
    @OnClick({R.id.im_message, R.id.person1, R.id.person2, R.id.person3, R.id.person4, R.id.person5, R.id.person6, R.id.person7})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.im_message:
                Intent intent=new Intent(getActivity(), MsNotifyActivity.class);
                startActivity(intent);
                break;
            case R.id.person1:
                RongIM1();
                break;
            case R.id.person2:
                RongIM2();
                break;
            case R.id.person3:
                RongIM3();
                break;
            case R.id.person4:
                RongIM4();
                break;
            case R.id.person5:
                RongIM5();
                break;
            case R.id.person6:
                RongIM6();
                break;
            case R.id.person7:
                RongIM6();
                break;
        }
    }
}
