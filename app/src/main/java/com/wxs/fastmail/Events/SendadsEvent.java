package com.wxs.fastmail.Events;

/**
 * Created by wxs on 2017/7/17.
 */

public class SendadsEvent
{
    private  String postaddress;
    private  String sendname;
    private  String sendphone;

    public SendadsEvent(String postaddress, String sendname, String sendphone) {
        this.postaddress = postaddress;
        this.sendname = sendname;
        this.sendphone = sendphone;
    }

    public String getSendname() {
        return sendname;
    }

    public void setSendname(String sendname) {
        this.sendname = sendname;
    }

    public String getSendphone() {
        return sendphone;
    }

    public void setSendphone(String sendphone) {
        this.sendphone = sendphone;
    }

    public String getPostaddress() {
        return postaddress;
    }
    public void setPostaddress(String postaddress) {
        this.postaddress = postaddress;
    }
}
