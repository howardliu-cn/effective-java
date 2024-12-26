package cn.howardliu.tutorials.juc.lock;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-12-26
 */
public class BoundedBufferExample {
    public static void main(String[] args) {
        BoundedBuffer buffer = new BoundedBuffer(5);

        // 创建生产者线程
        Thread producer1 = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    buffer.put(i);
                    System.out.println("生产者1放入: " + i);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        Thread producer2 = new Thread(() -> {
            for (int i = 11; i <= 20; i++) {
                try {
                    buffer.put(i);
                    System.out.println("生产者2放入: " + i);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // 创建消费者线程
        Thread consumer1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    int value = buffer.take();
                    System.out.println("消费者1取出: " + value);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        Thread consumer2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    int value = buffer.take();
                    System.out.println("消费者2取出: " + value);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // 启动线程
        producer1.start();
        producer2.start();
        consumer1.start();
        consumer2.start();

        try {
            // 等待所有线程完成
            producer1.join();
            producer2.join();
            consumer1.join();
            consumer2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
