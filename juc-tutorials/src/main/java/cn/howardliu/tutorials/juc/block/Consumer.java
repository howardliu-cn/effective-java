package cn.howardliu.tutorials.juc.block;

import java.util.concurrent.BlockingQueue;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-12-25
 */
public class Consumer implements Runnable {
    private final BlockingQueue<Integer> queue;
    private final int poisonPill;

    public Consumer(BlockingQueue<Integer> queue, int poisonPill) {
        this.queue = queue;
        this.poisonPill = poisonPill;
    }

    @Override
    public void run() {
        try {
            consumeMessages();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void consumeMessages() throws InterruptedException {
        while (true) {
            Integer message = queue.take();
            if (message.equals(poisonPill)) {
                return;
            }
            System.out.println(Thread.currentThread().getName() + " 消费消息: " + message);
        }
    }
}
