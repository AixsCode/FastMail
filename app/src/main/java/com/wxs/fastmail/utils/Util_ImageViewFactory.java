package com.wxs.fastmail.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.widget.ImageView;

import com.wxs.fastmail.R;


public class Util_ImageViewFactory {

    public static ImageView getImageView(Context context, String url, Bitmap bitmap) {
        ImageView imageView = (ImageView) LayoutInflater.from(context).inflate(R.layout.item_imageview, null);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        }
        return imageView;
    }
}
