package cn.howardliu.tutorials.juc.callback;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-12-12
 */
public class Task implements Runnable {
    @Override
    public void run() {
        System.out.println("任务正在进行");
        // 业务逻辑在此处
    }
}
