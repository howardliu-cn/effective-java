package cn.howardliu.tutorials.juc.callback;

import java.util.concurrent.CompletableFuture;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-12-12
 */
public class CompletableFutureDemo {
    public static void main(String[] args) {
        Task task = new Task();
        Callback callback = new Callback();
        CompletableFuture.runAsync(task)
                .thenAccept(result -> callback.taskDone("完成细节: " + result));
    }
}
