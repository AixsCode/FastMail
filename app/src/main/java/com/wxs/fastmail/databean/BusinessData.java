package com.wxs.fastmail.databean;

import android.graphics.Bitmap;

/**
 * Created by wxs on 2017/7/25.
 */

public class BusinessData {


    private Bitmap bitmap;
    private String store_name;

    private String store_distance;

    private String store_time;

    public BusinessData(Bitmap bitmap, String store_name, String store_distance, String store_time) {
        this.bitmap = bitmap;
        this.store_name = store_name;
        this.store_distance = store_distance;
        this.store_time = store_time;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public BusinessData(String store_name, String store_distance, String store_time) {
        this.store_name = store_name;
        this.store_distance = store_distance;
        this.store_time = store_time;
    }

    public BusinessData() {
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getStore_distance() {
        return store_distance;
    }

    public void setStore_distance(String store_distance) {
        this.store_distance = store_distance;
    }

    public String getStore_time() {
        return store_time;
    }

    public void setStore_time(String store_time) {
        this.store_time = store_time;
    }
}
