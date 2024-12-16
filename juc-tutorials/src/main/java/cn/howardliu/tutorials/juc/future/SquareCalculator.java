package cn.howardliu.tutorials.juc.future;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-12-14
 */
public class SquareCalculator {
    private final ExecutorService executor = Executors.newFixedThreadPool(2);// Executors.newSingleThreadExecutor();

    public Future<Integer> calculate(Integer input) {
        return executor.submit(() -> {
            System.out.printf("计算%d的平方%n", input);
            Thread.sleep(1000);
            return input * input;
        });
    }

    public void shutdown() {
        executor.shutdownNow();
    }
}
