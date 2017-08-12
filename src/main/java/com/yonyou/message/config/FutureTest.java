package com.yonyou.message.config;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Created by Administrator on 2017/8/4.
 */
public class FutureTest {
    public static void main(String[] args) throws InterruptedException {
        FutureTask<String> futureTest = new FutureTask<String>(new RealData("dfsad"));
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(futureTest);
        Thread.sleep(2000);
        System.out.println("main ");
        executorService.shutdown();
        try {
            System.out.println("结果："+futureTest.get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
