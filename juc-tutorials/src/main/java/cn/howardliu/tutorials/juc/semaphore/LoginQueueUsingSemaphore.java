package cn.howardliu.tutorials.juc.semaphore;

import java.util.concurrent.Semaphore;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-12-23
 */
public class LoginQueueUsingSemaphore {

    private final Semaphore semaphore;

    public LoginQueueUsingSemaphore(int slotLimit) {
        semaphore = new Semaphore(slotLimit);
    }

    public boolean tryLogin() {
        return semaphore.tryAcquire();
    }

    public void logout() {
        semaphore.release();
    }

    public int availableSlots() {
        return semaphore.availablePermits();
    }

}
