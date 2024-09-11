package cn.howardliu.tutorials.java19;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-08-08
 */
public class VirtualThreadMain2 {
    public static void main(String[] args) throws InterruptedException {
        final int count = 10_000;
        long start = System.currentTimeMillis();
        runThreadPool(count);
        System.out.println("线程池：" + (System.currentTimeMillis() - start));
        start = System.currentTimeMillis();
        runVirtualThread(count);
        System.out.println("虚拟线程：" + (System.currentTimeMillis() - start));
        start = System.currentTimeMillis();
        runThread(count);
        System.out.println("线程：" + (System.currentTimeMillis() - start));
    }

    private static void runVirtualThread(int count) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(count);
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, count).forEach(i -> {
                executor.submit(() -> {
                    TimeUnit.SECONDS.sleep(1L);
                    latch.countDown();
                    return i;
                });
            });
        }
        latch.await();
    }

    private static void runThreadPool(int count) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(count);
        try (var executor = Executors.newFixedThreadPool(100)) {
            IntStream.range(0, count).forEach(i -> {
                executor.submit(() -> {
                    TimeUnit.SECONDS.sleep(1L);
                    latch.countDown();
                    return i;
                });
            });
        }
        latch.await();
    }

    private static void runThread(int count) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(count);
        IntStream.range(0, count).forEach(i -> {
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1L);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                latch.countDown();
            }).start();
        });
        latch.await();
    }
}
