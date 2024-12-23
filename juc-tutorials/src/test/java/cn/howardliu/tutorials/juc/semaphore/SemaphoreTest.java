package cn.howardliu.tutorials.juc.semaphore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-12-23
 */
class SemaphoreTest {

    @Test
    public void givenLoginQueueWhenReachLimitThenBlocked() {
        int slots = 10;
        try (ExecutorService executorService = Executors.newFixedThreadPool(slots);) {
            LoginQueueUsingSemaphore loginQueue = new LoginQueueUsingSemaphore(slots);
            IntStream.range(0, slots)
                    .forEach(user -> executorService.execute(loginQueue::tryLogin));
            executorService.shutdown();

            assertEquals(0, loginQueue.availableSlots());
            assertFalse(loginQueue.tryLogin());
        }
    }

    @Test
    public void givenLoginQueueWhenLogoutThenSlotsAvailable() {
        int slots = 10;
        try (ExecutorService executorService = Executors.newFixedThreadPool(slots);) {
            LoginQueueUsingSemaphore loginQueue = new LoginQueueUsingSemaphore(slots);
            IntStream.range(0, slots)
                    .forEach(user -> executorService.execute(loginQueue::tryLogin));
            executorService.shutdown();
            assertEquals(0, loginQueue.availableSlots());
            loginQueue.logout();

            assertTrue(loginQueue.availableSlots() > 0);
            assertTrue(loginQueue.tryLogin());
        }
    }

    @Test
    public void givenDelayQueueWhenReachLimitThenBlocked() {
        int slots = 50;
        try (ExecutorService executorService = Executors.newFixedThreadPool(slots)) {
            DelayQueueUsingTimedSemaphore delayQueue = new DelayQueueUsingTimedSemaphore(1, slots);

            IntStream.range(0, slots)
                    .forEach(user -> executorService.execute(delayQueue::tryAdd));
            executorService.shutdown();

            assertEquals(0, delayQueue.availableSlots());
            assertFalse(delayQueue.tryAdd());
        }
    }

    @Test
    public void givenDelayQueueWhenTimePassThenSlotsAvailable() throws InterruptedException {
        int slots = 50;
        try (ExecutorService executorService = Executors.newFixedThreadPool(slots)) {
            DelayQueueUsingTimedSemaphore delayQueue = new DelayQueueUsingTimedSemaphore(1, slots);
            IntStream.range(0, slots)
                    .forEach(user -> executorService.execute(delayQueue::tryAdd));
            executorService.shutdown();

            assertEquals(0, delayQueue.availableSlots());
            Thread.sleep(1000);
            assertTrue(delayQueue.availableSlots() > 0);
            assertTrue(delayQueue.tryAdd());
        }
    }

    @Test
    public void whenMutexAndMultipleThreads_thenBlocked() {
        int count = 5;
        try (ExecutorService executorService = Executors.newFixedThreadPool(count);) {
            CounterUsingMutex counter = new CounterUsingMutex();
            IntStream.range(0, count)
                    .forEach(user -> executorService.execute(() -> {
                        try {
                            counter.increase();
                            System.out.println("count: " + counter.getCount());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }));
            executorService.shutdown();
            assertTrue(counter.hasQueuedThreads());
        }
    }

    @Test
    public void givenMutexAndMultipleThreads_ThenDelay_thenCorrectCount() throws InterruptedException {
        int count = 5;
        try (ExecutorService executorService = Executors.newFixedThreadPool(count);) {
            CounterUsingMutex counter = new CounterUsingMutex();
            IntStream.range(0, count)
                    .forEach(user -> executorService.execute(() -> {
                        try {
                            counter.increase();
                            System.out.println("count: " + counter.getCount());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }));
            executorService.shutdown();
            assertTrue(counter.hasQueuedThreads());
            Thread.sleep(5000);
            assertFalse(counter.hasQueuedThreads());
            assertEquals(count, counter.getCount());
        }
    }
}
