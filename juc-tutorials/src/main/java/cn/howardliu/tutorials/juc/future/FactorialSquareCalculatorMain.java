package cn.howardliu.tutorials.juc.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-12-14
 */
public class FactorialSquareCalculatorMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            FactorialSquareCalculator calculator = new FactorialSquareCalculator(10);
            forkJoinPool.execute(calculator);
            System.out.println("计算结果：" + calculator.join());
        }
    }
}
