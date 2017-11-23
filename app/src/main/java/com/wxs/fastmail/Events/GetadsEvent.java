package com.wxs.fastmail.Events;

/**
 * Created by wxs on 2017/7/17.
 */

public class GetadsEvent
{

    private  String getaddress;
    private  String getname;
    private  String gephone;

    public GetadsEvent(String getaddress, String getname, String gephone) {
        this.getaddress = getaddress;
        this.getname = getname;
        this.gephone = gephone;
    }

    public String getGetname() {
        return getname;
    }

    public void setGetname(String getname) {
        this.getname = getname;
    }

    public String getGephone() {
        return gephone;
    }

    public void setGephone(String gephone) {
        this.gephone = gephone;
    }

    public String getGetaddress() {
        return getaddress;
    }
    public void setGetaddress(String getaddress) {
        this.getaddress = getaddress;
    }
}
