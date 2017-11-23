package com.wxs.fastmail.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util_Time {

    // 时间戳精确到秒
    public static String timeStamp2Date(long seconds, String format)
    {
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(new Date(seconds * 1000L));
    }
}
