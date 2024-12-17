package cn.howardliu.tutorials.juc.executor;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-12-16
 */
public class ExecutorServiceMain {
    public static void main(String[] args) {
        Runnable runnableTask = () -> {
            try {
                MILLISECONDS.sleep(300);
                System.out.println("任务完成");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Callable<String> callableTask = () -> {
            MILLISECONDS.sleep(300);
            return "任务执行";
        };

        List<Callable<String>> callableTasks = new ArrayList<>();
        callableTasks.add(callableTask);
        callableTasks.add(callableTask);
        callableTasks.add(callableTask);

        ExecutorService executorService = new ThreadPoolExecutor(1, 1, 0L, MILLISECONDS, new LinkedBlockingQueue<>());

        Future<String> future = executorService.submit(callableTask);
        String result = null;
        try {
            result = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        ScheduledExecutorService executorService2 = Executors.newSingleThreadScheduledExecutor();
        Future<String> resultFuture = executorService2.schedule(callableTask, 1, TimeUnit.SECONDS);

        executorService2.scheduleAtFixedRate(runnableTask, 100, 450, TimeUnit.MILLISECONDS);
        executorService2.scheduleWithFixedDelay(runnableTask, 100, 150, TimeUnit.MILLISECONDS);

    }
}
