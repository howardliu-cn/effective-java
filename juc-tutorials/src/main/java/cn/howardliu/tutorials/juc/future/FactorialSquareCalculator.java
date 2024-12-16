package cn.howardliu.tutorials.juc.future;

import java.util.concurrent.RecursiveTask;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-12-14
 */
public class FactorialSquareCalculator extends RecursiveTask<Integer> {
    private final Integer n;

    public FactorialSquareCalculator(Integer n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        if (n <= 1) {
            return n;
        }
        System.out.println("计算" + n);
        FactorialSquareCalculator calculator = new FactorialSquareCalculator(n - 1);
        calculator.fork();
        return n * n + calculator.join();
    }
}
