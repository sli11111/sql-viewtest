package com.ysmjjsy.goya.test;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.math.MathUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.RuntimeUtil;

import javax.xml.crypto.Data;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

public class ThreadUtilTest {
    public static void main(String[] args) {
        int startTime = DateUtil.date().millisecond();
        CountDownLatch latch = ThreadUtil.newCountDownLatch(10);
        for (int i = 0; i < 1000; i++) {
            ThreadUtil.execAsync(()->{
               dosomething(startTime,latch);
            });
        }


    }

    private static void dosomething(int startTime,CountDownLatch latch) {
        int endTime = DateUtil.date().millisecond();
        Console.log("show info:{},timeBetween:{}", Thread.currentThread().getName(),endTime-startTime);
    }
}
