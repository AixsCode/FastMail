package com.wxs.fastmail.Events;

/**
 * Created by wxs on 2017/7/29.
 */

public class WayEvent {

    private  String nowaddress;

    private  String aimaddress;

    public WayEvent(String nowaddress, String aimaddress)
    {
        this.nowaddress = nowaddress;
        this.aimaddress = aimaddress;
    }

    public String getNowaddress() {
        return nowaddress;
    }

    public void setNowaddress(String nowaddress)
    {
         this.nowaddress = nowaddress;
    }

    public String getAimaddress() {
        return aimaddress;
    }

    public void setAimaddress(String aimaddress) {
        this.aimaddress = aimaddress;
    }

}
