package cn.howardliu.tutorials.juc.wait;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-12-07
 */
public class DelayedCallable implements Callable<String> {
    private final String name;
    private final long period;
    private CountDownLatch latch;

    public DelayedCallable(String name, long period, CountDownLatch latch) {
        this(name, period);
        this.latch = latch;
    }

    public DelayedCallable(String name, long period) {
        this.name = name;
        this.period = period;
    }

    public String call() {

        try {
            Thread.sleep(period);

            if (latch != null) {
                latch.countDown();
            }

        } catch (InterruptedException ex) {
            ex.printStackTrace();
            Thread.currentThread().interrupt();
        }

        return name;
    }
}
