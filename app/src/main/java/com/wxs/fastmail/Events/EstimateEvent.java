package com.wxs.fastmail.Events;

/**
 * Created by wxs on 2017/9/1.
 */

public class EstimateEvent {
    private  String flag;

    public EstimateEvent(String flag) {
        this.flag = flag;
    }

    public EstimateEvent() {
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
