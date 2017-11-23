package com.wxs.fastmail.tool;

import com.wxs.fastmail.R;

import java.util.Random;


/**
 * Created by wxs on 2017/7/11.
 */

public class Tool_Random
{
   public static final  Random  RANDOM=new Random();

   public static String getRandomTime()
   {
       switch (RANDOM.nextInt(6))
       {
           case 1:
               return "一分钟前";
           case 2:
               return "二分钟前";
           case 3:
               return "三分钟前";
           case 4:
               return "半分钟前";
           case 5:
               return  "四分钟前";
           case 6:
               return  "五分钟前";
           default:
               return "一分钟前";
       }
   }

  public  static  int getRandomLabel()
   {
       switch (RANDOM.nextInt(10))
       {
           case 1:
               return R.drawable.order_send_label;
           case 2:
               return R.drawable.order_post_label;
           case 3:
               return R.drawable.order_send_label;
           case 4:
               return R.drawable.order_store_label;
           case 5:
               return R.drawable.order_teambuy_label;
           case 6:
               return R.drawable.order_send_label;
           case 7:
               return R.drawable.order_send_label;
           case 8:
               return R.drawable.order_send_label;
           case 9:
               return R.drawable.order_post_label;
           case 10:
               return R.drawable.order_post_label;
           case 11:
               return  R.drawable.order_post_label;
           default:
               return R.drawable.order_send_label;
       }
   }
   public static  int getRandomIcon()
   {
        switch (RANDOM.nextInt(10))
        {
            case 1:
                return R.drawable.add_person_1;
            case 2:
                return R.drawable.add_person_2;
            case 3:
                return R.drawable.add_person_3;
            case 4:
                return R.drawable.add_person_4;
            case 5:
                return R.drawable.add_person_5;
            case 6:
                return R.drawable.add_person_6;
            case 7:
                return R.drawable.add_person_7;
            case 8:
                return R.drawable.add_person_8;
            case 9:
                return R.drawable.add_person_9;
            case 10:
                return  R.drawable.add_person_10;
            case 11:
                return  R.drawable.add_person_11;
            case 12:
                return  R.drawable.add_person_12;
            case 13:
                return  R.drawable.mail_buy_person1;
            case 14:
                return  R.drawable.mail_buy_person2;
            case 15:
                return  R.drawable.mail_buy_person3;
            default:
                return  R.drawable.mail_buy_person4;
        }
   }
   public static  String  getRandomName()
   {

       switch (RANDOM.nextInt(11)) {

           case 1:
                return "思想聚焦";
           case 2:
                return "小白兔";
           case 3:
                return "我是女神";
           case 4:
                return "最暖先生";
           case 5:
                return "郑喝*惠子";
           case 6:
                return "小花同学阿";
           case 7:
                return "帅未来";
           case 8:
                return "鹿青青";
           case 9:
               return  "彭于晏";
           case 10:
               return  "Swag-run";
           case 11:
               return  "一支白菜";
           default:
               return "橙熟097793";
       }
   }
}
