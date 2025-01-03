package cn.howardliu.tutorials.juc.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2025-01-02
 */
public class ThreadPoolExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        List<Future<Integer>> futures = new ArrayList<>();

        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        for (int number : numbers) {
            Future<Integer> future = executorService.submit(() -> number * number);
            futures.add(future);
        }

        executorService.shutdown();

        for (Future<Integer> future : futures) {
            try {
                System.out.println("平方结果: " + future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
