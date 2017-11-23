package com.wxs.fastmail.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Util_GetBitmapFromResource {

    public static Bitmap getBitmapFromResource(Context context, int resID) {
        return BitmapFactory.decodeResource(context.getResources(), resID);
    }

}
