package cn.howardliu.tutorials.juc.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-12-26
 */
public class ReentrantLockExample {
    private static final ReentrantLock lock = new ReentrantLock();
    private static int sharedResource = 0;

    public static void increment() {
        lock.lock();
        try {
            sharedResource++;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                increment();
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("共享资源的值: " + sharedResource);
    }
}
