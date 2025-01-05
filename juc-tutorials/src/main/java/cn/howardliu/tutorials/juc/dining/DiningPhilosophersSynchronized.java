package cn.howardliu.tutorials.juc.dining;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2025-01-03
 */
public class DiningPhilosophersSynchronized {
    public static void main(String[] args) {
        Object[] chopsticks = new Object[5];
        for (int i = 0; i < 5; i++) {
            chopsticks[i] = new Object();
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
        private final Object leftChopstick;
        private final Object rightChopstick;

        public Philosopher(Object leftChopstick, Object rightChopstick) {
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

                    synchronized (leftChopstick) {
                        System.out.println(Thread.currentThread().getName() + " picked up left chopstick");
                        synchronized (rightChopstick) {
                            System.out.println(Thread.currentThread().getName() + " picked up right chopstick");
                            System.out.println(Thread.currentThread().getName() + " is eating");
                            Thread.sleep((long) (Math.random() * 1000));
                            System.out.println(Thread.currentThread().getName() + " put down right chopstick");
                        }
                        System.out.println(Thread.currentThread().getName() + " put down left chopstick");
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
