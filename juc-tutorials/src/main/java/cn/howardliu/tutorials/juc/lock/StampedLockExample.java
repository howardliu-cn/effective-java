package cn.howardliu.tutorials.juc.lock;

import java.util.concurrent.locks.StampedLock;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-12-26
 */
public class StampedLockExample {
    private static final StampedLock lock = new StampedLock();
    private static int sharedValue = 0;

    public static void optimisticRead() {
        long stamp = lock.tryOptimisticRead();
        int value = sharedValue;
        if (!lock.validate(stamp)) {
            stamp = lock.readLock();
            try {
                value = sharedValue;
            } finally {
                lock.unlockRead(stamp);
            }
        }
        System.out.println(Thread.currentThread().getName() + " 乐观读取值: " + value);
    }

    public static void write(int newValue) {
        long stamp = lock.writeLock();
        try {
            sharedValue = newValue;
            System.out.println(Thread.currentThread().getName() + " 正在写入值: " + newValue);
        } finally {
            lock.unlockWrite(stamp);
        }
    }

    public static void main(String[] args) {
        Thread readThread = new Thread(() -> {
            optimisticRead();
        });

        Thread writeThread = new Thread(() -> {
            write(100);
        });

        readThread.start();
        writeThread.start();

        try {
            readThread.join();
            writeThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
