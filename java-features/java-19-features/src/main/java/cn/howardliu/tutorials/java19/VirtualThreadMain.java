package cn.howardliu.tutorials.java19;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.IntStream;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-08-08
 */
public class VirtualThreadMain {
    public static void main(String[] args) {
try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
    IntStream.range(0, 10_000).forEach(i -> {
        executor.submit(() -> {
            Thread.sleep(Duration.ofSeconds(1));
            System.out.println(Thread.currentThread().getName() + ": " + i);
            return i;
        });
    });
}

Thread.startVirtualThread(() -> {
    System.out.println("Hello from a virtual thread[Thread.startVirtualThread]");
});

final ThreadFactory factory = Thread.ofVirtual().factory();
factory.newThread(() -> {
            System.out.println("Hello from a virtual thread[ThreadFactory.newThread]");
        })
        .start();
    }
}
