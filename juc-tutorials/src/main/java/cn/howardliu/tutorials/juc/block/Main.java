package cn.howardliu.tutorials.juc.block;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-12-25
 */
public class Main {
    public static void main(String[] args) {
        final int BOUND = 10;
        final int N_PRODUCERS = 3;
        final int N_CONSUMERS = 2;
        final int poisonPill = Integer.MAX_VALUE;
        final int poisonPillPerProducer = N_CONSUMERS / N_PRODUCERS;
        final int mod = N_CONSUMERS % N_PRODUCERS;

        final BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(BOUND);

        for (int i = 0; i < N_PRODUCERS; i++) {
            new Thread(new Producer(queue, poisonPill, poisonPillPerProducer)).start();
        }

        for (int j = 0; j < N_CONSUMERS; j++) {
            new Thread(new Consumer(queue, poisonPill)).start();
        }

        new Thread(new Producer(queue, poisonPill, poisonPillPerProducer + mod)).start();
    }
}
