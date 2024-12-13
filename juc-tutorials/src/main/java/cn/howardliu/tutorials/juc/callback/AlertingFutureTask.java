package cn.howardliu.tutorials.juc.callback;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-12-12
 */
public class AlertingFutureTask extends FutureTask<String> {
    CallbackInterface callback;

    public AlertingFutureTask(Runnable runnable, Callback callback) {
        super(runnable, null);
        this.callback = callback;
    }

    @Override
    protected void done() {
        callback.taskDone("警报警报");
    }

    public static void main(String[] args) {
        Task task = new Task();
        Callback callback = new Callback();
        FutureTask<String> future = new AlertingFutureTask(task, callback);
        try (ExecutorService executor = Executors.newSingleThreadExecutor()) {
            executor.submit(future);
        }
    }
}
