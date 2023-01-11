package JUCtest;

import cn.hutool.core.lang.Console;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownlatchTest {
    @Test
    public void test1() throws InterruptedException {
        CountDownLatch beginLatch = new CountDownLatch(5);
        CountDownLatch endLatch = new CountDownLatch(5);

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            int finalTest = i+1;
            executorService.submit(()->{
                Console.log("顾客{}准备过山车完备",finalTest);
                beginLatch.countDown();
            });
        }

        Thread thread = new Thread(() -> {
            try {
                beginLatch.await();
                Console.log("所有顾客都已准备完成，过山车发车！");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread.sleep(100);
        thread.start();


    }

}
