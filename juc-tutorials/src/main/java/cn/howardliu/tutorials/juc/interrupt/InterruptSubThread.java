package cn.howardliu.tutorials.juc.interrupt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-12-04
 */
public class InterruptSubThread extends Thread {
    private final AtomicBoolean running = new AtomicBoolean(false);
    private final AtomicBoolean stopped = new AtomicBoolean(true);
    private final int interval;
    private final Thread worker;
    private final AtomicInteger count = new AtomicInteger(0);

    public InterruptSubThread(int sleepInterval) {
        interval = sleepInterval;
        worker = new Thread(this);
    }

    @Override
    public void start() {
        worker.start();
    }

    public void shutdown() {
        running.set(false);
    }

    @Override
    public void interrupt() {
        running.set(false);
        worker.interrupt();
    }

    @Override
    public void run() {
        running.set(true);
        stopped.set(false);
        while (running.get()) {
            try {
                System.out.println("线程正在运行(" + System.currentTimeMillis() / 1000 + "): " + count.incrementAndGet());
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("线程被中断，操作未能完成");
            }
            // 在此处执行一些操作
        }
        stopped.set(true);
    }

    public static void main(String[] args) throws InterruptedException {
        final InterruptSubThread thread = new InterruptSubThread(1000);
        thread.start();

        System.out.println("主线程等待");
        TimeUnit.SECONDS.sleep(10);

        thread.interrupt();
        thread.join();
        System.out.println("主线程终止");
    }
}
