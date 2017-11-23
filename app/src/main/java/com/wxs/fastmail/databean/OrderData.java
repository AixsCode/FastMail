package com.wxs.fastmail.databean;

/**
 * Created by wxs on 2017/7/11.
 */

public class OrderData
{
    private  String ordertime;
    private  String ordermoney;
    private  String ordersendaddress;
    private  String ordergetaddress;
    public OrderData(String ordertime, String ordermoney, String ordersendaddress, String ordergetaddress)
    {
        this.ordertime = ordertime;
        this.ordermoney = ordermoney;
        this.ordersendaddress = ordersendaddress;
        this.ordergetaddress = ordergetaddress;
    }

    public String getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }

    public String getOrdermoney() {
        return ordermoney;
    }

    public void setOrdermoney(String ordermoney) {
        this.ordermoney = ordermoney;
    }

    public String getOrdersendaddress() {
        return ordersendaddress;
    }

    public void setOrdersendaddress(String ordersendaddress) {
        this.ordersendaddress = ordersendaddress;
    }

    public String getOrdergetaddress() {
        return ordergetaddress;
    }

    public void setOrdergetaddress(String ordergetaddress)
    {
        this.ordergetaddress = ordergetaddress;
    }

}
