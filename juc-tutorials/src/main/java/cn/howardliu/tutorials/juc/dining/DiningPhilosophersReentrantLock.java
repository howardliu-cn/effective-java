package cn.howardliu.tutorials.juc.dining;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2025-01-03
 */
public class DiningPhilosophersReentrantLock {
    public static void main(String[] args) {
        ReentrantLock[] chopsticks = new ReentrantLock[5];
        for (int i = 0; i < 5; i++) {
            chopsticks[i] = new ReentrantLock();
        }

        Thread[] philosophers = new Thread[5];
        for (int i = 0; i < 5; i++) {
            int leftChopstick = i;
            int rightChopstick = (i + 1) % 5;
            philosophers[i] = new Thread(new Philosopher(chopsticks[leftChopstick], chopsticks[rightChopstick]));
            philosophers[i].setName("Philosopher " + (i + 1));
            philosophers[i].start();
        }
    }

    static class Philosopher implements Runnable {
        private final ReentrantLock leftChopstick;
        private final ReentrantLock rightChopstick;

        public Philosopher(ReentrantLock leftChopstick, ReentrantLock rightChopstick) {
            this.leftChopstick = leftChopstick;
            this.rightChopstick = rightChopstick;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + " is thinking");
                    Thread.sleep((long) (Math.random() * 1000));

                    System.out.println(Thread.currentThread().getName() + " is hungry");

                    boolean leftAcquired = false;
                    boolean rightAcquired = false;
                    try {
                        leftAcquired = leftChopstick.tryLock();
                        if (leftAcquired) {
                            System.out.println(Thread.currentThread().getName() + " picked up left chopstick");
                            rightAcquired = rightChopstick.tryLock();
                            if (rightAcquired) {
                                System.out.println(Thread.currentThread().getName() + " picked up right chopstick");
                                System.out.println(Thread.currentThread().getName() + " is eating");
                                Thread.sleep((long) (Math.random() * 1000));
                            }
                        }
                    } finally {
                        if (rightAcquired) {
                            rightChopstick.unlock();
                            System.out.println(Thread.currentThread().getName() + " put down right chopstick");
                        }
                        if (leftAcquired) {
                            leftChopstick.unlock();
                            System.out.println(Thread.currentThread().getName() + " put down left chopstick");
                        }
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
