package cn.howardliu.tutorials.juc.safe;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2025-01-01
 */
public class ObjectLockCounter {

    private int counter = 0;
    private final Object lock = new Object();

    public void incrementCounter() {
        synchronized (lock) {
            counter += 1;
        }
    }

    public int getCounter() {
        return counter;
    }
}
