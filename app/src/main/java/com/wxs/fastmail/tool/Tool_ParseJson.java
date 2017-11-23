package com.wxs.fastmail.tool;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wxs.fastmail.bean.OrderUserThree;
import com.wxs.fastmail.bean.Recruit;

import java.util.List;

/**
 * Created by wxs on 2017/7/24.
 */

public class Tool_ParseJson<E> {
    public static List<Recruit> getRecruitList(String key, String jsonString)

    {
        JSONObject jsonObject = JSON.parseObject(jsonString);

        JSONArray jsonArray = jsonObject.getJSONArray(key);

        List<Recruit> recruitList = JSONArray.parseArray(jsonArray.toJSONString(), Recruit.class);

        return recruitList;
    }

    public static  List<OrderUserThree> getOrderUserList(String key, String jsonString)
    {
        JSONObject jsonObject = JSON.parseObject(jsonString);
        JSONArray jsonArray = jsonObject.getJSONArray(key);
        List<OrderUserThree> orderUserThreeList=JSONArray.parseArray(jsonArray.toJSONString(),OrderUserThree.class);
        return  orderUserThreeList;
    }

}
