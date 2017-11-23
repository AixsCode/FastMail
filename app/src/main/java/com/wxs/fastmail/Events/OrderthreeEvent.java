package com.wxs.fastmail.Events;

/**
 * Created by wxs on 2017/7/26.
 */

public class OrderthreeEvent {

    private  String Tv_Post_name;

    private  String Tv_Patch_name;

    public OrderthreeEvent() {
    }

    public OrderthreeEvent(String tv_Post_name, String tv_Patch_name) {
        Tv_Post_name = tv_Post_name;
        Tv_Patch_name = tv_Patch_name;
    }

    public String getTv_Post_name() {
        return Tv_Post_name;
    }

    public void setTv_Post_name(String tv_Post_name) {
        Tv_Post_name = tv_Post_name;
    }

    public String getTv_Patch_name() {
        return Tv_Patch_name;
    }

    public void setTv_Patch_name(String tv_Patch_name) {
        Tv_Patch_name = tv_Patch_name;
    }
}
