package cn.howardliu.tutorials.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.Lists;

/**
 * @author 看山 <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2022-02-15
 */
public class CompletableFutureExample {
    private static final ExecutorService executor = Executors.newCachedThreadPool();

    static void simpleFuture() throws ExecutionException, InterruptedException {
        // 简单使用
        final Future<String> simpleFuture = calculateAsync();
        final String simpleResult = simpleFuture.get();
        System.out.println(simpleResult);

        // 带结果的CompletableFuture
        final Future<String> completeFuture = CompletableFuture.completedFuture("公众号：看山的小屋");
        final String completeResult = completeFuture.get();
        System.out.println(completeResult);
    }

    static void sync() throws ExecutionException, InterruptedException {
        // runAsync
        final CompletableFuture<Void> runAsyncEnd = CompletableFuture.runAsync(() -> {
            randomSleep();
            System.out.println("runAsync end");
        });
        final Void unused = runAsyncEnd.getNow(null);

        // supplyAsync
        final CompletableFuture<String> supplyAsyncEnd = CompletableFuture.supplyAsync(() -> {
            randomSleep();
            System.out.println("supplyAsync end");
            return "complete";
        });
        final String supplyResult = supplyAsyncEnd.get();
        assert supplyResult.equals("complete");
    }

    static void thenAction() throws ExecutionException, InterruptedException {
        // thenApply
        final CompletableFuture<String> thenApplyFuture = CompletableFuture
                .supplyAsync(() -> {
                    randomSleep();
                    return "Hello, ";
                })
                .thenApply(x -> x + "World!");
        final String thenApplyResult = thenApplyFuture.get();
        assert thenApplyResult.equals("Hello, World!");

        // thenAccept
        final CompletableFuture<Void> thenAcceptFuture = CompletableFuture.supplyAsync(() -> "Hello")
                .thenAccept(s -> System.out.println("Computation returned: " + s));
        final Void unused1 = thenAcceptFuture.get();

        // thenRun
        CompletableFuture<Void> supplyAsyncFuture = CompletableFuture.supplyAsync(() -> "Hello")
                .thenRun(() -> System.out.println("Computation finished."));
        final Void unused2 = supplyAsyncFuture.get();
    }

    static void thenCompose() throws ExecutionException, InterruptedException {
        final CompletableFuture<String> thenComposeFuture = CompletableFuture.supplyAsync(() -> "Hello, ")
                .thenCompose(x -> CompletableFuture.supplyAsync(() -> x + " World!"));
        final String thenComposeResult = thenComposeFuture.get();
        assert thenComposeResult.equals("Hello, World!");
    }

    static void thenCompose2() throws ExecutionException, InterruptedException {
        final CompletableFuture<String> thenComposeFuture = CompletableFuture.supplyAsync(() -> "Hello, ")
                .thenCombine(CompletableFuture.supplyAsync(() -> " World!"), (s1, s2) -> s1 + s2);

        final String thenComposeResult = thenComposeFuture.get();
        assert thenComposeResult.equals("Hello, World!");
    }

    static void thenCompose3() throws ExecutionException, InterruptedException {
        final CompletableFuture<Void> thenComposeFuture = CompletableFuture.supplyAsync(() -> "Hello")
                .thenAcceptBoth(CompletableFuture.supplyAsync(() -> " World"),
                        (s1, s2) -> System.out.println(s1 + s2));
        final Void unused = thenComposeFuture.get();
    }

    static void allOf() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1
                = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future2
                = CompletableFuture.supplyAsync(() -> "Beautiful");
        CompletableFuture<String> future3
                = CompletableFuture.supplyAsync(() -> "World");

        CompletableFuture<Void> combinedFuture
                = CompletableFuture.allOf(future1, future2, future3);

        // ...其他业务逻辑

        combinedFuture.get();
    }

    static void combined() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "Beautiful");
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> "World");
        String combined = Stream.of(future1, future2, future3)
                .map(CompletableFuture::join)
                .collect(Collectors.joining(" "));

        assert combined.equals("Hello Beautiful World");
    }

    static void handle() throws ExecutionException, InterruptedException {
        String name = null;

        CompletableFuture<String> completableFuture = CompletableFuture
                .supplyAsync(() -> {
                    if (name == null) {
                        throw new RuntimeException("Computation error!");
                    }
                    return "Hello, " + name;
                })
                .handle((r, t) -> r != null ? r : "Hello, Stranger!");
        final String result = completableFuture.get();

        assert result.equals("Hello, Stranger!");
    }

    static void exceptionally() throws ExecutionException, InterruptedException {
        String name = null;

        CompletableFuture<String> completableFuture = CompletableFuture
                .supplyAsync(() -> {
                    if (name == null) {
                        throw new RuntimeException("Computation error!");
                    }
                    return "Hello, " + name;
                })
                .exceptionally(t -> "Hello, Stranger!");
        final String result = completableFuture.get();

        assert result.equals("Hello, Stranger!");
    }

    static void completeExceptionally() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        // ...
        completableFuture.completeExceptionally(
                new RuntimeException("Calculation failed!"));
        // ...
        completableFuture.get(); // ExecutionException
    }

    static void other() throws ExecutionException, InterruptedException {
        final List<CompletableFuture<?>> futures = new ArrayList<>();

        final CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("1st start");
            randomSleep();
            System.out.println("1st end");
            return "1st";
        }, executor).exceptionally(throwable -> {
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
        }, executor).exceptionally(throwable -> {
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
        }, executor).exceptionally(throwable -> {
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
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("main thread start");
        Runtime.getRuntime().addShutdownHook(new Thread(executor::shutdownNow));

        // 作为普通Future使用
        simpleFuture();

        // 具有封装计算逻辑的 CompletableFuture
        sync();

        // 处理异步计算的结果
        thenAction();

        // 组合多个CompletableFuture
        thenCompose();
        thenCompose2();
        thenCompose3();

        // 并行多个Future
        allOf();
        combined();

        // 异常处理
        handle();
        exceptionally();
        completeExceptionally();

        // 单独的一个复杂示例
        other();
    }

    public static Future<String> calculateAsync() {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        executor.submit(() -> {
            TimeUnit.MILLISECONDS.sleep(500);
            completableFuture.complete("公众号：看山的小屋");
            return null;
        });

        return completableFuture;
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
