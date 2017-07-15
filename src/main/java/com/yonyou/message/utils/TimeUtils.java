package com.yonyou.message.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/6/22.
 */
public class TimeUtils {
    /**
     * 得到系统当前时间，返回yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getCurrentTime() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(d);
    }

    /**
     * 比较两个时间差，返回long分钟数
     * @param beginTime
     * @return
     * @throws Exception
     */

    public static long betweenTime(String beginTime) throws Exception{
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String endTime = sdf.format(d);
        long nd = 1000*24*60*60;//毫秒数
        long nh = 1000*60*60;//毫秒数
        long nm = 1000*60;//钟毫秒数
        //获两间毫秒间差异
        long time1 = sdf.parse(endTime).getTime();
        long time2 = sdf.parse(beginTime).getTime();
        long diff;
        diff = time1 - time2;
        long min = diff%nd%nh/nm;//计算差少钟
        return min;
    }

    public static void main(String[] args) {
        String a = "e:/jfwo/jfiowejf/jfiwoe/iwebap/jfiow/jifowej/joa.pdn";
        System.out.println(a.substring(a.indexOf("/iwebap"),a.length()));
    }
}
