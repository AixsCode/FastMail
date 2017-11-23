package com.wxs.fastmail.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.wxs.fastmail.R;
import com.wxs.fastmail.activity.TeamdetailActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wxs on 2017/7/13.
 */

public class Fragment_ComTeamBuy extends Fragment {
    @Bind(R.id.btn_nearby_join)
    Button btnNearbyJoin;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = View.inflate(getActivity(), R.layout.fragmentcom_teambuy, null);
        ButterKnife.bind(this, view);
        return view;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.btn_nearby_join)
    public void onViewClicked() {
        Intent intent=new Intent(getContext(), TeamdetailActivity.class);
        startActivity(intent);
    }
}
