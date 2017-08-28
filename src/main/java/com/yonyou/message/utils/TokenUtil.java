package com.yonyou.message.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.xml.internal.ws.api.ha.StickyFeature;
import com.yonyou.message.config.Config;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Administrator on 2017/8/24.
 */
@Service
public class TokenUtil {

    public static void main(String[] args) {

//        System.out.println(System.currentTimeMillis() / 1000);
//        System.out.println(Date2TimeStamp(TimeUtils.getCurrentTime(),"yyyy-MM-dd HH:mm:ss"));
//        System.out.println(TimeStamp2Date( String.valueOf(1503803697635L /1000) , "yyyy-MM-dd HH:mm:ss"));
//        System.out.println(stampToDate("1503803697635"));
//        System.out.println(stampToDate("82959"));
        String a = "ffjwioef.fjwoiejf.fwoefj";
        System.out.println(a.substring(0,a.indexOf(".")));
    }

    /**
     * 指定格式日期转换成Unix时间戳
     * @param timestampString
     * @param formats
     * @return
     */
    public static String TimeStamp2Date(String timestampString, String formats) {
        if (TextUtils.isEmpty(formats))
            formats = "yyyy-MM-dd HH:mm:ss";
        Long timestamp = Long.parseLong(timestampString) * 1000;
        String date = new SimpleDateFormat(formats, Locale.CHINA).format(new Date(timestamp));
        return date;
    }

    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    /**
     * 日期格式字符串转换成时间戳
     *
     * @param dateStr 字符串日期
     * @param format   如：yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String Date2TimeStamp(String dateStr, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(dateStr).getTime() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
