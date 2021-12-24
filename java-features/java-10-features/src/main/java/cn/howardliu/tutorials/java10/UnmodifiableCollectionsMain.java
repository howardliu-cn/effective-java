package cn.howardliu.tutorials.java10;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 看山 <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2021/12/23 08:31
 */
public class UnmodifiableCollectionsMain {
    public static void main(String[] args) {
        final List<String> unmodifiableList = Stream.of("1", "2", "3", "4", "5")
                .map(x -> "id: " + x)
                .collect(Collectors.toUnmodifiableList());
    }
}
