package cn.howardliu.tutorials.juc.safe;

import java.util.Arrays;
import java.util.List;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2025-01-01
 */
public class ThreadA extends Thread {

    private final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

    @Override
    public void run() {
        numbers.forEach(System.out::println);
    }
}
