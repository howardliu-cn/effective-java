package cn.howardliu.tutorials.java11;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author 看山 <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2022/1/8 11:40
 */
class LocalVariableTest {
    @Test
    void testLocalVariable() {
        final List<String> sampleList = Arrays.asList("Hello", "World");
        final String resultString = sampleList.stream()
                .map((@NotNull var x) -> x.toUpperCase())
                .collect(Collectors.joining(", "));
        Assertions.assertEquals("HELLO, WORLD", resultString);
    }
}
