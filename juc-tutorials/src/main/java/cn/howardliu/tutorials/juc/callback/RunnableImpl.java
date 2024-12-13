package cn.howardliu.tutorials.juc.callback;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-12-12
 */
public class RunnableImpl implements Runnable {
    private final Runnable task;
    private final CallbackInterface callback;
    private final String taskDoneMessage;

    public RunnableImpl(Runnable task, CallbackInterface callback, String taskDoneMessage) {
        this.task = task;
        this.callback = callback;
        this.taskDoneMessage = taskDoneMessage;
    }

    @Override
    public void run() {
        task.run();
        callback.taskDone(taskDoneMessage);
    }

    public static void main(String[] args) {
        Task task = new Task();
        Callback callback = new Callback();
        RunnableImpl runnableImpl = new RunnableImpl(task, callback, "准备下一个任务");
        runnableImpl.run();
    }
}
