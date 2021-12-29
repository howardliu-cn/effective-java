package cn.howardliu.tutorials.java10;

import java.util.NoSuchElementException;
import java.util.stream.Stream;

/**
 * @author 看山 <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2021/12/23 08:31
 */
public class OptionalMain {
    public static void main(String[] args) {
        final String someOne = Stream.of("1", "2", "3", "4", "5")
                .map(x -> "id: " + x)
                .findAny()
                .orElseThrow();
        final String someOne2 = Stream.of("1", "2", "3", "4", "5")
                .map(x -> "id: " + x)
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("No value present"));

        System.out.println(someOne);
        System.out.println(someOne2);
    }
}
