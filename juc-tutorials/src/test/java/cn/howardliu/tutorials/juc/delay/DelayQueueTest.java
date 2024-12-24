package cn.howardliu.tutorials.juc.delay;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-12-24
 */
class DelayQueueTest {

    @Test
    public void test1() throws InterruptedException {
        try (ExecutorService executor = Executors.newFixedThreadPool(2);) {
            BlockingQueue<DelayObject> queue = new DelayQueue<>();
            int numberOfElementsToProduce = 2;
            int delayOfEachProducedMessageMilliseconds = 500;
            DelayQueueConsumer consumer = new DelayQueueConsumer(queue, numberOfElementsToProduce);
            DelayQueueProducer producer = new DelayQueueProducer(
                    queue, numberOfElementsToProduce, delayOfEachProducedMessageMilliseconds);

            executor.submit(producer);
            executor.submit(consumer);

            executor.awaitTermination(5, TimeUnit.SECONDS);
            executor.shutdown();

            assertEquals(consumer.numberOfConsumedElements.get(), numberOfElementsToProduce);
        }
    }

    @Test
    public void test2() throws InterruptedException {
        try (ExecutorService executor = Executors.newFixedThreadPool(2);) {
            BlockingQueue<DelayObject> queue = new DelayQueue<>();

            int numberOfElementsToProduce = 1;
            int delayOfEachProducedMessageMilliseconds = 10_000;
            DelayQueueConsumer consumer = new DelayQueueConsumer(
                    queue, numberOfElementsToProduce);
            DelayQueueProducer producer = new DelayQueueProducer(
                    queue, numberOfElementsToProduce, delayOfEachProducedMessageMilliseconds);

            executor.submit(producer);
            executor.submit(consumer);

            executor.awaitTermination(5, TimeUnit.SECONDS);
            executor.shutdown();
            assertEquals(consumer.numberOfConsumedElements.get(), 0);
        }
    }

    @Test
    public void test3() throws InterruptedException {
        try (ExecutorService executor = Executors.newFixedThreadPool(2);) {
            BlockingQueue<DelayObject> queue = new DelayQueue<>();
            int numberOfElementsToProduce = 1;
            int delayOfEachProducedMessageMilliseconds = -10_000;
            DelayQueueConsumer consumer = new DelayQueueConsumer(queue, numberOfElementsToProduce);
            DelayQueueProducer producer = new DelayQueueProducer(
                    queue, numberOfElementsToProduce, delayOfEachProducedMessageMilliseconds);

            executor.submit(producer);
            executor.submit(consumer);

            executor.awaitTermination(1, TimeUnit.SECONDS);
            executor.shutdown();
            assertEquals(consumer.numberOfConsumedElements.get(), 1);
        }
    }
}
