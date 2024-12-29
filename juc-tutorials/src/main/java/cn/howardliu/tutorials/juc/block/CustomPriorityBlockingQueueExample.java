package cn.howardliu.tutorials.juc.block;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-12-29
 */
public class CustomPriorityBlockingQueueExample {
    public static void main(String[] args) throws InterruptedException {
        PriorityBlockingQueue<Task> taskQueue = new PriorityBlockingQueue<>();

        // 添加任务
        taskQueue.add(new Task(3, "任务3"));
        taskQueue.add(new Task(1, "任务1"));
        taskQueue.add(new Task(2, "任务2"));

        // 处理任务，按照优先级顺序（从高到低）
        while (!taskQueue.isEmpty()) {
            Task task = taskQueue.take();
            System.out.println("处理任务: " + task);
        }
    }
}
