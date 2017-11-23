package com.wxs.fastmail.tool;

import com.wxs.fastmail.R;
import com.wxs.fastmail.common.Application;
import com.wxs.fastmail.databean.BusinessData;
import com.wxs.fastmail.databean.OrderData;
import com.wxs.fastmail.databean.PatchData;
import com.wxs.fastmail.utils.Util_GetBitmapFromResource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wxs on 2017/7/11.
 */
public class Tool_GetData
{
    public  static List<OrderData> getOrderDataList()
    {
        List<OrderData> orderDataList=new ArrayList<>();
        orderDataList.add(new OrderData("1分钟前","42","幸福西饼手工红色生日蛋糕","滨湖区五爱人家北门正对面"));
        orderDataList.add(new OrderData("5分钟前","30","紫罗兰寓意美德精美鲜花一束","无锡2号大街308号温馨花店"));
        orderDataList.add(new OrderData("1分钟前","12","黑鱼酸菜鱼，辅料，米饭","无锡惠山区时代广场二楼酸菜鱼"));
        orderDataList.add(new OrderData("10分钟前","42","炸鸡、可乐、薯条","无锡惠山区锦绣商业广场餐饮店"));
        orderDataList.add(new OrderData("3分钟前","52","肯德基全家桶一个","滨湖区五爱人家北门正对面"));
        orderDataList.add(new OrderData("5分钟前","40","可爱多甜筒二十根","润发路外马三小对面美佳便利店"));
        orderDataList.add(new OrderData("6分钟前","53","美的K342暖风干衣机","大学路228号美的专卖店"));
        orderDataList.add(new OrderData("8分钟前","12","一杯美式咖啡一杯拿铁咖啡","无锡惠山区政和大道研磨时光咖啡店"));
        orderDataList.add(new OrderData("9分钟前","41","旺仔特浓牛奶一箱","全第路中段111号爽客便利店"));
        orderDataList.add(new OrderData("10分钟前","23"," 可爱多甜筒二十根","滨湖区五爱人家北门正对面"));
        orderDataList.add(new OrderData("11分钟前","25","一点点四季玛奇朵2杯","和平路28号一点点奶茶店"));
        orderDataList.add(new OrderData("4分钟前","14","《嫌疑人X的献身》","无锡惠山区精品书店"));
        return orderDataList;
    }
    public static List<PatchData> getPatchDataList()
    {
        List<PatchData> patchDataList=new ArrayList<>();
        patchDataList.add(new PatchData("二分钟前","40","思想","12295223519","17:00","幸福西饼手工红色生日蛋糕","0.5km","0.6km"));
        patchDataList.add(new PatchData("一分钟前","14","无奈","14656499882","12:00","一点点四季玛奇朵2杯","0.4km","3.6km"));
        patchDataList.add(new PatchData("三分钟前","12","老哥","15448986655","14:00","紫罗兰寓意美德精美鲜花一束","0.5km","7.6km"));
        patchDataList.add(new PatchData("四分钟前","48","大哥","23132323233","16:00","幸福西饼手工红色生日蛋糕","0.5km","0.6km"));
        patchDataList.add(new PatchData("一分钟前","46","开心","34243434345","17:00","炸鸡、可乐、薯条","0.5km","4.5km"));
        patchDataList.add(new PatchData("半分钟前","13","加油","24344356454","16:00","幸福西饼手工红色生日蛋糕","0.5km","5.1km"));
        patchDataList.add(new PatchData("五分钟前","27","喜欢","87746345432","10:00","幸福西饼手工红色生日蛋糕","3.5km","1.2km"));
        patchDataList.add(new PatchData("一分钟前","35","哈哈","56365455344","14:00","可爱多甜筒二十根","0.5km","0.7km"));
        patchDataList.add(new PatchData("一分钟前","63","西瓜","10325469559","13:00","幸福西饼手工红色生日蛋糕","4.5km","4.2km"));
        patchDataList.add(new PatchData("半分钟前","82","乐观","10223565546","07:00","可爱多甜筒二十根","5.5km","5.3km"));
        patchDataList.add(new PatchData("一分钟前","14","恩恩","14662454565","08:00","一杯美式咖啡一杯拿铁咖啡","0.5km","0.6km"));
        patchDataList.add(new PatchData("二分钟前","45","思思","15825847545","06:00","肯德基全家桶一个","0.4km","0.6km"));
        return patchDataList;
    }
    public  static List<BusinessData> getBusinessDataList()
    {
        List<BusinessData> businessDataList=new ArrayList<>();
        businessDataList.add(new BusinessData(Util_GetBitmapFromResource.getBitmapFromResource(Application.getContextObject(), R.drawable.storefront_business1),"客来聚饭店(无锡滨湖区)","1.8km","35分钟"));
        businessDataList.add(new BusinessData(Util_GetBitmapFromResource.getBitmapFromResource(Application.getContextObject(), R.drawable.storefront_business2),"船菜人家大饭店(价值50元代金券！菜品优质，口感正宗)","2.7km","25分钟"));
        businessDataList.add(new BusinessData(Util_GetBitmapFromResource.getBitmapFromResource(Application.getContextObject(), R.drawable.storefront_business3),"尚品宫自助烤肉(崇安寺店)","5.4km","39分钟"));
        businessDataList.add(new BusinessData(Util_GetBitmapFromResource.getBitmapFromResource(Application.getContextObject(), R.drawable.storefront_business4),"多伦多海鲜自助餐厅（无锡三阳百盛店）","8.7km","33分钟"));
        businessDataList.add(new BusinessData(Util_GetBitmapFromResource.getBitmapFromResource(Application.getContextObject(), R.drawable.storefront_business5),"缇香阁 (无锡市滨湖区太湖街道锡南路)","3.5km","34分钟"));
        businessDataList.add(new BusinessData(Util_GetBitmapFromResource.getBitmapFromResource(Application.getContextObject(), R.drawable.storefront_business6),"火焰极品龙虾(滨湖区建筑路659号)","0.2km","15分钟"));
        businessDataList.add(new BusinessData(Util_GetBitmapFromResource.getBitmapFromResource(Application.getContextObject(), R.drawable.storefront_business7),"尚香蟹黄汤包(惠山古镇店)","10.2km","45分钟"));
        businessDataList.add(new BusinessData(Util_GetBitmapFromResource.getBitmapFromResource(Application.getContextObject(), R.drawable.storefront_business8),"金大福馄饨(荣巷分店)","10.4km","48分钟"));
        businessDataList.add(new BusinessData(Util_GetBitmapFromResource.getBitmapFromResource(Application.getContextObject(), R.drawable.storefront_business9),"福记花甲(硕放店)","8.5km","31分钟"));
        businessDataList.add(new BusinessData(Util_GetBitmapFromResource.getBitmapFromResource(Application.getContextObject(), R.drawable.storefront_business10),"牛太郎时尚自助烧烤城(宝龙广场店)","4.5km","25分钟"));
        return  businessDataList;
    }
}
