package cn.howardliu.tutorials.juc.semaphore;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.concurrent.TimedSemaphore;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-12-23
 */
public class DelayQueueUsingTimedSemaphore {
    private final TimedSemaphore semaphore;

    DelayQueueUsingTimedSemaphore(long period, int slotLimit) {
        semaphore = new TimedSemaphore(period, TimeUnit.SECONDS, slotLimit);
    }

    public boolean tryAdd() {
        return semaphore.tryAcquire();
    }

    public int availableSlots() {
        return semaphore.getAvailablePermits();
    }

}
