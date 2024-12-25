package cn.howardliu.tutorials.juc.block;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-12-25
 */
public class Producer implements Runnable {
    private final BlockingQueue<Integer> queue;
    private final int poisonPill;
    private final int poisonPillPerProducer;

    public Producer(BlockingQueue<Integer> queue, int poisonPill, int poisonPillPerProducer) {
        this.queue = queue;
        this.poisonPill = poisonPill;
        this.poisonPillPerProducer = poisonPillPerProducer;
    }

    @Override
    public void run() {
        try {
            produceMessages();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void produceMessages() throws InterruptedException {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int message = random.nextInt(100);
            queue.put(message);
        }
        for (int j = 0; j < poisonPillPerProducer; j++) {
            queue.put(poisonPill);
        }
    }
}
