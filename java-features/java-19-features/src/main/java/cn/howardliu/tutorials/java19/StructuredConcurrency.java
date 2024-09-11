package cn.howardliu.tutorials.java19;

import java.util.concurrent.StructuredTaskScope;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-08-08
 */
public class StructuredConcurrency {
    public static void main(String[] args) {
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            var task1 = scope.fork(() -> {
                Thread.sleep(1000);
                return "Result from task 1";
            });

            var task2 = scope.fork(() -> {
                Thread.sleep(2000);
                return "Result from task 2";
            });

            scope.join();
            scope.throwIfFailed(RuntimeException::new);

            System.out.println(task1.get());
            System.out.println(task2.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
