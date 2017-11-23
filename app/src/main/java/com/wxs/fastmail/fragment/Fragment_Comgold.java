package com.wxs.fastmail.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wxs.fastmail.R;

/**
 * Created by wxs on 2017/7/13.
 */

public class Fragment_Comgold extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view=View.inflate(getActivity(), R.layout.fragmentcom_gold,null);
        return   view;
    }
}
