package com.wxs.fastmail.tool;

/**
 * Created by wxs on 2017/7/20.
 */

public class Tool_Url {

    //寄快递
    private static final String Url_post_deliverymail="http://106.14.143.63:8080/SimpleDelivery/orderOnlySend-publish";

    private static final String Url_post_deliveryAcceptOrder="http://106.14.143.63:8080/SimpleDelivery/orderOnlySend-deliveryAcceptOrder";

    //物品送达
    private static  final String  Url_post_goodArrive="http://106.14.143.63:8080/SimpleDelivery/orderOnlySend-goodArrive";

    //确认取货
    private static  final  String Url_post_deliveryGetGood="http://106.14.143.63:8080/SimpleDelivery/orderOnlySend-deliveryGetGood";

    //收件人下单
    private  static final  String Url_orderUserThree_publish="http://106.14.143.63:8080/SimpleDelivery/orderUserThree-publish";

    //发件人接单
    private  static final  String Url_orderUserThree_sendAcceptOrder="http://106.14.143.63:8080/SimpleDelivery/orderUserThree-sendAcceptOrder";

    private  static  final String Url_recruit_getAll="http://106.14.143.63:8080/SimpleDelivery/recruit-getAll";

    //发件人接单

    private static  final String Url_orderUserThree_getAllPublishedByGet="http://106.14.143.63:8080/SimpleDelivery/orderUserThree-getAllPublishedByGet";

    public static String getUrl_orderUserThree_getAllPublishedByGet() {
        return Url_orderUserThree_getAllPublishedByGet;
    }

    public static String getUrl_recruit_getAll()
    {
        return Url_recruit_getAll;

    }
    public static String getUrl_orderUserThree_sendAcceptOrder()
    {
        return Url_orderUserThree_sendAcceptOrder;
    }
    public static String getUrl_orderUserThree_publish()
    {
        return Url_orderUserThree_publish;
    }
    public static String getUrl_post_goodArrive()
    {
        return Url_post_goodArrive;

    }
    public static String getUrl_post_deliveryGetGood() {
        return Url_post_deliveryGetGood;
    }

    public static String getUrl_post_deliveryAcceptOrder()
    {
        return Url_post_deliveryAcceptOrder;
    }
    public static String getUrl_post_deliverymail()
    {
        return Url_post_deliverymail;
    }
}
