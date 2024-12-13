package cn.howardliu.tutorials.juc.callback;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.google.common.collect.Lists;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-12-12
 */
public class AlertingThreadPoolExecutor extends ThreadPoolExecutor {
    CallbackInterface callback;

    public AlertingThreadPoolExecutor(CallbackInterface callback) {
        super(1, 1, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
        this.callback = callback;
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        callback.taskDone("可运行细节在此");
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Task task = new Task();
        Callback callback = new Callback();
        final List<Future<?>> futures = Lists.newArrayList();
        try (AlertingThreadPoolExecutor executor = new AlertingThreadPoolExecutor(callback)) {
            for (int i = 0; i < 5; i++) {
                futures.add(executor.submit(task));
            }
        }
        for (Future<?> future : futures) {
            future.get();
        }
    }
}
