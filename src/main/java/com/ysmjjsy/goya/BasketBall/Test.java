package com.ysmjjsy.goya.BasketBall;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.RandomUtil;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Test {
    public static void main(String[] args) {
        int i = RandomUtil.randomInt(1, 3);
        User user = new User();
        CompareChoose choose = new CompareChoose();
        //跳球
        switch (i) {
            case 1:
                user.setQiuQuan("红方");
                user.setZhenYing("红方");
                Console.log("show info:{}得到球权,控球后卫开始持球组织进攻。",user.getQiuQuan());
                break;
            case 2:
                user.setQiuQuan("蓝方");
                user.setZhenYing("蓝方");
                Console.log("show info:{}得到球权,控球后卫开始持球组织进攻。",user.getQiuQuan());
                break;
        }
        //进攻
        CountDownLatch buleLatch = new CountDownLatch(5);
        CountDownLatch redLatch = new CountDownLatch(5);
        //球
        Semaphore semaphore = new Semaphore(1, true);
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i1 = 0; i1 < 4;i1++) {
            int finalI = i1;
            new Thread(()->{
                try {
                    semaphore.acquire();
                    int i2 = RandomUtil.randomInt(0, 4);
                    User user1 = CommonUtil.acqiureQiuQuan(user, i2);
                    CompareChoose compareCho = CommonUtil.getCompareCho(choose,user1);
                    Console.log("show info:{}{}请求球权", user1.getZhenYing(), user1.getName());
                    Console.log("show info:{}得到球权，他{}", user1.getName(), compareCho.getChoose());
                    semaphore.release();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }




    }
}
