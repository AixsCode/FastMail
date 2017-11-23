package com.wxs.fastmail.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.wxs.fastmail.R;

/**
 * Created by wxs on 2017/7/29.
 */

public class InsuranceDialog extends Dialog{
    Context mContext;

    public InsuranceDialog (Context context)
    {
        super(context);
        mContext = context;
    }
    public InsuranceDialog(Context context, int theme) {
        super(context, theme);
        mContext = context;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.insurance_dialog, null);
        this.setContentView(layout);
    }
}
