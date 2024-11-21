package cn.howardliu.tutorials.juc;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-11-16
 */
public class HappensBeforeExample {

    private int x = 0;
    private static int y = 1;

    public void calculate() throws InterruptedException {
        final Thread a = new Thread(() -> {
            y = x * y;
        });
        final Thread b = new Thread(() -> {
            try {
                a.join();
                System.out.println(y);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        b.start();
        a.start();
        b.join();
    }

    public static void main(String[] args) throws InterruptedException {
        new HappensBeforeExample().calculate();
    }
}
