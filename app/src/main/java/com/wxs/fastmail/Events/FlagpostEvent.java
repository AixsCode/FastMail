package com.wxs.fastmail.Events;

/**
 * Created by wxs on 2017/7/27.
 */

public class FlagpostEvent {
    private  int flag;

    public FlagpostEvent(int flag) {
        this.flag = flag;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
