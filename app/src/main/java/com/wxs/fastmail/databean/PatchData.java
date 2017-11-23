package com.wxs.fastmail.databean;

/**
 * Created by wxs on 2017/7/20.
 */

public class PatchData {

    private String order_time;

    private String order_money;

    private String order_name;

    private String order_phone;

    private String order_reqtime;

    private String order_things;

    private String order_distance1;

    private String order_distance2;


    public PatchData() {
    }

    public PatchData(String order_time, String order_money, String order_name, String order_phone, String order_reqtime, String order_things, String order_distance1, String order_distance2) {
        this.order_time = order_time;
        this.order_money = order_money;
        this.order_name = order_name;
        this.order_phone = order_phone;
        this.order_reqtime = order_reqtime;
        this.order_things = order_things;
        this.order_distance1 = order_distance1;
        this.order_distance2 = order_distance2;
    }

    public String getOrder_time() {
        return order_time;
    }

    public void setOrder_time(String order_time) {
        this.order_time = order_time;
    }

    public String getOrder_money() {
        return order_money;
    }

    public void setOrder_money(String order_money) {
        this.order_money = order_money;
    }

    public String getOrder_name() {
        return order_name;
    }

    public void setOrder_name(String order_name) {
        this.order_name = order_name;
    }

    public String getOrder_phone() {
        return order_phone;
    }

    public void setOrder_phone(String order_phone) {
        this.order_phone = order_phone;
    }

    public String getOrder_reqtime() {
        return order_reqtime;
    }

    public void setOrder_reqtime(String order_reqtime) {
        this.order_reqtime = order_reqtime;
    }

    public String getOrder_things() {
        return order_things;
    }

    public void setOrder_things(String order_things) {
        this.order_things = order_things;
    }

    public String getOrder_distance1() {
        return order_distance1;
    }

    public void setOrder_distance1(String order_distance1) {
        this.order_distance1 = order_distance1;
    }

    public String getOrder_distance2() {
        return order_distance2;
    }

    public void setOrder_distance2(String order_distance2) {
        this.order_distance2 = order_distance2;
    }
}
