package cn.howardliu.tutorials.juc.wait;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-12-07
 */
class WaitingForThreadsToFinishManualTest {

    private final static ExecutorService WORKER_THREAD_POOL = Executors.newFixedThreadPool(10);

    public void awaitTerminationAfterShutdown(ExecutorService threadPool) {
        threadPool.shutdown();
        try {
            if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                threadPool.shutdownNow();
            }
        } catch (InterruptedException ex) {
            threadPool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    @Test
    public void givenMultipleThreads_whenUsingCountDownLatch_thenMainShoudWaitForAllToFinish() {

        ExecutorService WORKER_THREAD_POOL = Executors.newFixedThreadPool(10);

        try {
            long startTime = System.currentTimeMillis();

            // create a CountDownLatch that waits for the 2 threads to finish
            CountDownLatch latch = new CountDownLatch(2);

            for (int i = 0; i < 2; i++) {
                WORKER_THREAD_POOL.submit(() -> {
                    try {
                        Thread.sleep(1000);
                        latch.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                });
            }

            // wait for the latch to be decremented by the two threads
            latch.await();

            long processingTime = System.currentTimeMillis() - startTime;
            assertTrue(processingTime >= 1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        awaitTerminationAfterShutdown(WORKER_THREAD_POOL);
    }

    @Test
    public void givenMultipleThreads_whenInvokeAll_thenMainThreadShouldWaitForAllToFinish() {
        ExecutorService WORKER_THREAD_POOL = Executors.newFixedThreadPool(10);

        List<Callable<String>> callables = Arrays.asList(
                new DelayedCallable("快速线程", 100),
                new DelayedCallable("慢速线程", 3000));

        try {
            long startProcessingTime = System.currentTimeMillis();
            List<Future<String>> futures = WORKER_THREAD_POOL.invokeAll(callables);

            awaitTerminationAfterShutdown(WORKER_THREAD_POOL);

            try {
                WORKER_THREAD_POOL.submit((Callable<String>) () -> {
                    Thread.sleep(1000000);
                    return null;
                });
            } catch (RejectedExecutionException ex) {
                //
            }

            long totalProcessingTime = System.currentTimeMillis() - startProcessingTime;
            assertTrue(totalProcessingTime >= 3000);

            String firstThreadResponse = futures.get(0).get();
            assertEquals("快速线程", firstThreadResponse);

            String secondThreadResponse = futures.get(1).get();
            assertEquals("慢速线程", secondThreadResponse);

        } catch (ExecutionException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void givenMultipleThreads_whenUsingCompletionService_thenMainThreadShouldWaitForAllToFinish() {

        CompletionService<String> service = new ExecutorCompletionService<>(WORKER_THREAD_POOL);

        List<Callable<String>> callables = Arrays.asList(
                new DelayedCallable("快速线程", 100),
                new DelayedCallable("慢速线程", 3000));

        for (Callable<String> callable : callables) {
            service.submit(callable);
        }

        try {

            long startProcessingTime = System.currentTimeMillis();

            Future<String> future = service.take();
            String firstThreadResponse = future.get();
            long totalProcessingTime = System.currentTimeMillis() - startProcessingTime;

            assertEquals("快速线程", firstThreadResponse);
            assertTrue(totalProcessingTime >= 100 && totalProcessingTime < 1000);
            System.out.println("Thread finished after: " + totalProcessingTime + " milliseconds");

            future = service.take();
            String secondThreadResponse = future.get();
            totalProcessingTime = System.currentTimeMillis() - startProcessingTime;

            assertEquals("慢速线程", secondThreadResponse);
            assertTrue(totalProcessingTime >= 3000 && totalProcessingTime < 4000);
            System.out.println("Thread finished after: " + totalProcessingTime + " milliseconds");

        } catch (ExecutionException | InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            awaitTerminationAfterShutdown(WORKER_THREAD_POOL);
        }
    }
}
