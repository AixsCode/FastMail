package com.wxs.fastmail.Events;

/**
 * Created by wxs on 2017/7/26.
 */

public class OrderpostEvent {
    public  String Tv_Dispatch;

    public OrderpostEvent() {

    }
    public OrderpostEvent(String tv_Dispatch) {
        Tv_Dispatch = tv_Dispatch;
    }

    public String getTv_Dispatch()
    {
        return Tv_Dispatch;
    }

    public void setTv_Dispatch(String tv_Dispatch)
    {
        Tv_Dispatch = tv_Dispatch;
    }
}
