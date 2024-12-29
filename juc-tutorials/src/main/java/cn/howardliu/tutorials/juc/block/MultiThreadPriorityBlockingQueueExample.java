package cn.howardliu.tutorials.juc.block;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-12-29
 */
public class MultiThreadPriorityBlockingQueueExample {
    public static void main(String[] args) {
        // 创建优先级阻塞队列
        PriorityBlockingQueue<Task> taskQueue = new PriorityBlockingQueue<>();

        // 创建生产者线程
        Thread producer = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                int priority = (int) (Math.random() * 5) + 1;
                Task task = new Task(priority, "任务" + i);
                taskQueue.put(task);
                System.out.println("生产者添加任务: " + task);
            }
        });

        // 创建消费者线程
        Thread consumer = new Thread(() -> {
            while (true) {
                try {
                    Task task = taskQueue.take();
                    System.out.println("消费者处理任务: " + task);
                    if ("结束标志".equals(task.getDescription())) {
                        return;
                    }
                    TimeUnit.SECONDS.sleep((int) (Math.random() * 5) + 1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // 启动生产者和消费者线程
        producer.start();
        consumer.start();

        try {
            // 等待生产者线程完成
            producer.join();
            while (true) {
                if (taskQueue.isEmpty()) {
                    break;
                }
                TimeUnit.SECONDS.sleep(1);
            }
            // 向队列中添加一个结束标志任务
            taskQueue.put(new Task(0, "结束标志"));
            // 等待消费者线程处理完所有任务
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
