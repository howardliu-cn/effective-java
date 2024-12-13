package cn.howardliu.tutorials.juc.callback;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-12-12
 */
public class Callback implements CallbackInterface {

    @Override
    public void taskDone(String details) {
        System.out.println("任务完成: " + details);
        // 提醒/通知在此处
    }
}
