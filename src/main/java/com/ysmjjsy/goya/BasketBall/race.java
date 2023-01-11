package com.ysmjjsy.goya.BasketBall;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.RandomUtil;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class race {
    public static void main(String[] args) throws InterruptedException {
        //模拟跑步比赛，裁判员枪响，运动员跑步开始，并跑步完成后记录跑步完成时间
        CountDownLatch begin = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(10);
        CountDownLatch end1 = new CountDownLatch(10);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            int finalI1 = i;
            executorService.submit(()->{
                Console.log("show info:运动员{}已就位", finalI);
                end.countDown();
                try {
                    begin.await();
                    long l = System.currentTimeMillis();
                    Thread.sleep(RandomUtil.randomInt(0,1000));

                    long l1 = System.currentTimeMillis();
                    Console.log("show info:运动员{}到达终点,用时{}ms", finalI1,(l1-l));
                    end1.countDown();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            });

        }
        executorService.shutdown();
        end.await();
        Console.log("show info:所有运动员全部到达，等待裁判员枪响");
        new Thread(()->{
            begin.countDown();
            Console.log("show info:裁判员枪响，比赛开始");
        }).start();
        end1.await();
        Console.log("show info:所有运动员到达终点，比赛结束");


    }
}
