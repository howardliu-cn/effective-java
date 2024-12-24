package cn.howardliu.tutorials.juc.delay;

import java.util.UUID;
import java.util.concurrent.BlockingQueue;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-12-24
 */
public class DelayQueueProducer implements Runnable {

    private final BlockingQueue<DelayObject> queue;
    private final Integer numberOfElementsToProduce;
    private final Integer delayOfEachProducedMessageMilliseconds;


    public DelayQueueProducer(BlockingQueue<DelayObject> queue, Integer numberOfElementsToProduce,
            Integer delayOfEachProducedMessageMilliseconds) {
        this.queue = queue;
        this.numberOfElementsToProduce = numberOfElementsToProduce;
        this.delayOfEachProducedMessageMilliseconds = delayOfEachProducedMessageMilliseconds;
    }

    @Override
    public void run() {
        for (int i = 0; i < numberOfElementsToProduce; i++) {
            DelayObject object = new DelayObject(
                    UUID.randomUUID().toString(), delayOfEachProducedMessageMilliseconds);
            System.out.println("Put object: " + object);
            try {
                queue.put(object);
                Thread.sleep(500);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }
}
