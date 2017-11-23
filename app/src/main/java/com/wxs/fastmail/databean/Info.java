package com.wxs.fastmail.databean;

import com.wxs.fastmail.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by wxs on 2017/7/14.
 */

public class Info implements Serializable {
    private static final long serialVersionUID = -758459502806858414L;
    /**
     * 精度
     */
    private double latitude;
    /**
     * 纬度
     */
    private double longitude;
    /**
     * 图片ID，真实项目中可能是图片路径
     */
    private int imgId;
    /**
     * 名称
     */
    private String name;
    /**
     * 距离
     */
    private String distance;
    /**
     * 爱好
     */
    private String hobby;
    /**
     * 信用分数
     */
    private int core;

    public Info(double latitude, double longitude, int imgId, String name, String distance, String hobby, int core) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.imgId = imgId;
        this.name = name;
        this.distance = distance;
        this.hobby = hobby;
        this.core = core;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public int getCore() {
        return core;
    }

    public void setCore(int core) {
        this.core = core;
    }

    public Info() {
    }

    public static List<Info> infos=new ArrayList<Info>();

    static
    {
        infos.add(new Info(30.319237, 120.360858, R.drawable.mail_buy_person1,"Tyler","距离50米"," 打篮球、唱歌",500));
        infos.add(new Info(30.319238, 120.362857, R.drawable.mail_buy_person1,"Tyler","距离50米"," 打篮球、唱歌",500));
        infos.add(new Info(30.319239, 120.343856, R.drawable.mail_buy_person1,"Tyler","距离50米"," 打篮球、唱歌",500));
        infos.add(new Info(30.319240, 120.354855, R.drawable.mail_buy_person1,"Tyler","距离50米"," 打篮球、唱歌",500));
    }





}
