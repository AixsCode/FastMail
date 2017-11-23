package com.wxs.fastmail.Events;

/**
 * Created by wxs on 2017/7/27.
 */

public class FlagEvent {
    private   int  Flag_tag;

    public FlagEvent(int flag_tag)
    {
        Flag_tag = flag_tag;
    }

    public int getFlag_tag() {
        return Flag_tag;
    }

    public void setFlag_tag(int flag_tag) {
        Flag_tag = flag_tag;
    }
}
