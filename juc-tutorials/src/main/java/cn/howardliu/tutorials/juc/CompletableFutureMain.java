package cn.howardliu.tutorials.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.alibaba.ttl.threadpool.TtlExecutors;
import com.google.common.collect.Lists;

/**
 * @author 看山 <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2022-02-15
 */
public class CompletableFutureMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(16);
        taskExecutor.setKeepAliveSeconds(200);
        taskExecutor.setMaxPoolSize(48);
        taskExecutor.setQueueCapacity(100);
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        taskExecutor.initialize();
        final Executor ttlExecutor = TtlExecutors.getTtlExecutor(taskExecutor);

        System.out.println("main thread start");

        final List<CompletableFuture<?>> futures = new ArrayList<>();

        final CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("1st start");
            randomSleep();
            System.out.println("1st end");
            return "1st";
        }, ttlExecutor).exceptionally(throwable -> {
            System.out.println("1st got exception");
            if (throwable != null) {
                throwable.printStackTrace();
            }
            return "1st-has-exception";
        });
        futures.add(future1);
        final CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("2nd start");
            randomSleep();
            System.out.println("2nd end");
            return "2nd";
        }, ttlExecutor).exceptionally(throwable -> {
            System.out.println("2nd got exception");
            if (throwable != null) {
                throwable.printStackTrace();
            }
            return "2nd-got-exception";
        });
        futures.add(future2);
        final CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("3rd start");
            randomSleep();
            System.out.println("3rd end");
            return "3rd";
        }, ttlExecutor).exceptionally(throwable -> {
            System.out.println("3rd got exception");
            if (throwable != null) {
                throwable.printStackTrace();
            }
            return "3rd-got-exception";
        });
        futures.add(future3);

        final CompletableFuture<ArrayList<String>> future4 =
                future3.thenApply(str -> {
                    System.out.println("future3 complete");
                    return "future3 result: " + str;
                }).thenCombine(future2, (f1, f2) -> {
                    System.out.println("thenCombine: " + f1);
                    System.out.println("thenCombine: " + f2);

                    return Lists.newArrayList(f1, "future2 result: " + f2);
                }).whenCompleteAsync((list, t) -> {
                    System.out.println("future4 complete action start");
                    randomSleep();

                    if (t != null) {
                        t.printStackTrace();
                    }
                    System.out.println("future4 complete action end");
                });
        futures.add(future4);

        System.out.println("all futures created");

        final List<String> result = future4.get();
        System.out.println(result);

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        System.out.println("main thread end");
        taskExecutor.shutdown();
    }

    private static final Random RANDOM = new Random();

    private static void randomSleep() {
        try {
            TimeUnit.SECONDS.sleep(RANDOM.nextInt(10));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }
}
