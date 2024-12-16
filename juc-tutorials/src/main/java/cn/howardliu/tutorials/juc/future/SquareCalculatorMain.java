package cn.howardliu.tutorials.juc.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-12-14
 */
public class SquareCalculatorMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
SquareCalculator squareCalculator = new SquareCalculator();

Future<Integer> future1 = squareCalculator.calculate(10);
Future<Integer> future2 = squareCalculator.calculate(100);

while (!(future1.isDone() && future2.isDone())) {
    System.out.printf(
            "future1 is %s and future2 is %s%n",
            future1.isDone() ? "done" : "not done",
            future2.isDone() ? "done" : "not done"
    );
    Thread.sleep(300);
}

Integer result1 = future1.get();
Integer result2 = future2.get();

System.out.println(result1 + " and " + result2);

squareCalculator.shutdown();
    }
}
