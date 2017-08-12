package com.yonyou.message.config;

import com.alibaba.fastjson.JSONObject;

import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Administrator on 2017/8/3.
 */
public class test {
    public static void main(String[] args) {
        ConcurrentLinkedQueue<String> a = new ConcurrentLinkedQueue<String>();
        a.add("asdf");
        a.add("wef");
        a.add("we");
        String[] b =  a.toArray(new String[a.size()]);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("dd", b);
        System.out.println(jsonObject.get("dd").toString());
//        System.out.println(a.contains("w"));
//        System.out.println(a.toArray()[0].toString());
//        String c = "2kmohx0qeejyhijxgfvk02rt.udn.yonyou";
//        System.out.println(c.substring(0,c.indexOf(".")));
    }
}
