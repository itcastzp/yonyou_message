package com.yonyou.message.config;

import com.alibaba.fastjson.JSONObject;

import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Administrator on 2017/8/3.
 */
public class test {
    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("aa", "a");
        jsonObject.put("aa", "b");
        System.out.println(jsonObject.getString("aa"));
    }
}
