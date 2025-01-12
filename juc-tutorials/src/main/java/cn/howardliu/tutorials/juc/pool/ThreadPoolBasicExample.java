package cn.howardliu.tutorials.juc.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2025-01-08
 */
public class ThreadPoolBasicExample {
    public static void main(String[] args) {
        // 创建一个固定线程数为 5 的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 10; i++) {
            final int taskId = i;
            executorService.execute(() -> {
                System.out.println("Task " + taskId + " is running on thread " + Thread.currentThread().getName());
            });
        }

        // 关闭线程池，不再接受新任务，但会等待已提交任务执行完成
        executorService.shutdown();
    }
}
