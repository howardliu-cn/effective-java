package cn.howardliu.tutorials.juc.block;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-12-29
 */
public class PriorityBlockingQueueExample {
    public static void main(String[] args) throws InterruptedException {
        PriorityBlockingQueue<Integer> queue = new PriorityBlockingQueue<>();

        // 添加元素
        queue.add(5);
        queue.add(1);
        queue.add(3);

        // 取出元素，将按照优先级顺序取出（从小到大）
        while (!queue.isEmpty()) {
            System.out.println(queue.take());
        }
    }
}
