package com.ysmjjsy.goya.thread;

import cn.hutool.core.lang.Console;
import cn.hutool.core.thread.ThreadUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class MyThread {
    private static Semaphore semaphore = new Semaphore(1);
    private static Semaphore semaphore2 = new Semaphore(0);
    public static void main(String[] args) {
        ExecutorService executorService = ThreadUtil.newExecutor(2);
        AtomicInteger atomicInteger = new AtomicInteger(0);
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            int andIncrement = atomicInteger.getAndIncrement();
            executorService.submit(()->{
                if (finalI %2==0){
                    try {
                        semaphore.acquire();
                        Console.log(Thread.currentThread().getName()+"show info:{}", finalI);
                        semaphore.release();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }else{
                    try {
                        semaphore.acquire();
                        Console.log(Thread.currentThread().getName()+"show info:{}", finalI);
                        semaphore.release();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }


    }
}
