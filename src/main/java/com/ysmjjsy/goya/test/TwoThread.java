package com.ysmjjsy.goya.test;

import cn.hutool.core.lang.Console;
import cn.hutool.core.thread.ThreadUtil;

/**
 * 两线程交替打印0-100
 */
public class TwoThread {
    private static int count = 0;
    //定义唯一的一把锁
    private static final Object lock = new Object();
    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            ThreadUtil.execAsync(()->{
                while (count<=100){
                    synchronized (lock){
                        Console.log("show info:{}", count++);
                        lock.notify();
                        try {
                            if (count<=100) {
                                lock.wait();
                            }
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            });
        }
    }

    private static void printInfo() {

    }
}
