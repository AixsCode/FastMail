package com.wxs.fastmail.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.wxs.fastmail.R;

/**
 * Created by wxs on 2017/7/24.
 */

public class DispatchDialog extends Dialog {
    Context mContext;

    public DispatchDialog (Context context){
        super(context);
        mContext = context;
    }
    public DispatchDialog(Context context, int theme) {
        super(context, theme);
        mContext = context;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.dispatch_dialog, null);
        this.setContentView(layout);
    }
}
