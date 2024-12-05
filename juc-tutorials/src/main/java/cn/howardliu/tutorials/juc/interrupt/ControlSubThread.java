package cn.howardliu.tutorials.juc.interrupt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-12-04
 */
public class ControlSubThread extends Thread {
    private final AtomicBoolean running = new AtomicBoolean(false);
    private final AtomicBoolean stopped = new AtomicBoolean(true);
    private final int interval;
    private final AtomicInteger count = new AtomicInteger(0);

    public ControlSubThread(int sleepInterval) {
        interval = sleepInterval;
    }

    @Override
    public void start() {
        Thread worker = new Thread(this);
        worker.start();
    }

    public void shutdown() {
        running.set(false);
        System.out.println("线程正在关闭，当前count为：" + count.get());
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
        final ControlSubThread thread = new ControlSubThread(1000);
        thread.start();

        System.out.println("主线程等待");
        TimeUnit.SECONDS.sleep(10);

        thread.shutdown();
        thread.join();
        System.out.println("主线程终止");
    }
}
