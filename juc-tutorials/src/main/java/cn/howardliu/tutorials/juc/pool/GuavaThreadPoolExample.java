package cn.howardliu.tutorials.juc.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2025-01-02
 */
public class GuavaThreadPoolExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(executorService);

        ListenableFuture<Integer> future = listeningExecutorService.submit(() -> {
            // 模拟任务执行
            Thread.sleep(2000);
            return 42;
        });

        final ExecutorService callbackExecutor = Executors.newFixedThreadPool(3);
        Futures.addCallback(future, new FutureCallback<Integer>() {
            @Override
            public void onSuccess(Integer result) {
                System.out.println("任务成功执行，结果: " + result);
                callbackExecutor.shutdown();
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("任务执行失败: " + t.getMessage());
                callbackExecutor.shutdown();
            }
        }, callbackExecutor);

        // 关闭线程池
        executorService.shutdown();
    }
}
