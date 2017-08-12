package com.yonyou.message.config;

import java.util.concurrent.Callable;

/**
 * Created by Administrator on 2017/8/4.
 */
public class RealData implements Callable<String> {
    protected String realdata;

    public RealData(String realdata) {
        this.realdata = realdata;
    }
    public String call() throws Exception {
        Thread.sleep(3000);
        System.out.println("deal data done");
        return realdata;
    }
}
