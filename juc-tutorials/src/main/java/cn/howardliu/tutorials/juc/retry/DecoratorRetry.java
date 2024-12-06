package cn.howardliu.tutorials.juc.retry;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-12-05
 */
public class DecoratorRetry {
    static <T> Supplier<T> retryFunction(Supplier<T> supplier, int maxRetries) {
        return () -> {
            int retries = 0;
            while (retries < maxRetries) {
                try {
                    return supplier.get();
                } catch (Exception e) {
                    retries++;
                }
            }
            throw new IllegalStateException(String.format("任务在 %s 次尝试后失败", maxRetries));
        };
    }

    static <T> CompletableFuture<T> retryTask(Supplier<T> supplier, int maxRetries) {
        Supplier<T> retryableSupplier = retryFunction(supplier, maxRetries);
        return CompletableFuture.supplyAsync(retryableSupplier);
    }

    static <T> CompletableFuture<T> retryTwice(Supplier<T> supplier) {
        return CompletableFuture.supplyAsync(supplier)
                .exceptionally(__ -> supplier.get())
                .exceptionally(__ -> supplier.get());
    }

    static <T> CompletableFuture<T> retryUnsafe(Supplier<T> supplier, int maxRetries) throws InterruptedException {
        CompletableFuture<T> cf = CompletableFuture.supplyAsync(supplier);
        Thread.sleep(1000);
        for (int i = 0; i < maxRetries; i++) {
            cf = cf.exceptionally(__ -> supplier.get());
        }
        return cf;
    }

    static <T> CompletableFuture<T> retryExceptionallyAsync(Supplier<T> supplier, int maxRetries) {
        CompletableFuture<T> cf = CompletableFuture.supplyAsync(supplier);
        for (int i = 0; i < maxRetries; i++) {
            cf = cf.exceptionallyAsync(__ -> supplier.get());
        }
        return cf;
    }

    static <T> CompletableFuture<T> retryNesting(Supplier<T> supplier, int maxRetries)
            throws InterruptedException {
        CompletableFuture<T> cf = CompletableFuture.supplyAsync(supplier);
        Thread.sleep(1000);
        for (int i = 0; i < maxRetries; i++) {
            cf = cf.thenApply(CompletableFuture::completedFuture)
                    .exceptionally(__ -> CompletableFuture.supplyAsync(supplier))
                    .thenCompose(Function.identity());
        }
        return cf;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final AtomicInteger retriesCounter = new AtomicInteger(0);

        Supplier<Integer> codeToRun = () -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            int retryNr = retriesCounter.get();
            System.out.println("Retrying: " + retryNr);
            if (retryNr < 4) {
                retriesCounter.incrementAndGet();
                throw new RuntimeException();
            }
            return 100;
        };

        CompletableFuture<Integer> result = retryTask(codeToRun, 10);
        System.out.println(result.get());
        System.out.println(retriesCounter.get());

        retriesCounter.set(0);
        try {
            result = retryTask(codeToRun, 3);
            System.out.println(result.get());
        } catch (Exception e) {
            System.out.println("超过最大重试次数");
        }

        retriesCounter.set(0);
        codeToRun = () -> {
            int retryNr = retriesCounter.get();
            System.out.println("Retrying: " + retryNr + "; thread:" + Thread.currentThread().getName());
            if (retryNr < 4) {
                retriesCounter.incrementAndGet();
                throw new RuntimeException();
            }
            return 100;
        };
        retryUnsafe(codeToRun, 3);

        retriesCounter.set(0);
        retryExceptionallyAsync(codeToRun, 3);

        retriesCounter.set(0);
        retryNesting(codeToRun, 3);
    }
}
