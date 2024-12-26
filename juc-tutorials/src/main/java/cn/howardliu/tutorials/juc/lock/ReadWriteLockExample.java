package cn.howardliu.tutorials.juc.lock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-12-26
 */
public class ReadWriteLockExample {
    private static final ReadWriteLock lock = new ReentrantReadWriteLock();
    private static int sharedData = 0;

    public static void read() {
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 正在读取数据: " + sharedData);
        } finally {
            lock.readLock().unlock();
        }
    }

    public static void write(int value) {
        lock.writeLock().lock();
        try {
            sharedData = value;
            System.out.println(Thread.currentThread().getName() + " 正在写入数据: " + sharedData);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public static void main(String[] args) {
        Thread readThread1 = new Thread(() -> {
            read();
        });

        Thread readThread2 = new Thread(() -> {
            read();
        });

        Thread writeThread = new Thread(() -> {
            write(42);
        });

        readThread1.start();
        readThread2.start();
        writeThread.start();

        try {
            readThread1.join();
            readThread2.join();
            writeThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
