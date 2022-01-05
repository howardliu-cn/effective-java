package cn.howardliu.tutorials.java8.nest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * @author 看山 <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2021/12/29 22:38
 */
class OuterTest {
    @Test
    void gotAnExceptionInJava8() {
        final Outer outer = new Outer();

        final Exception e = assertThrows(IllegalAccessException.class, outer::callInnerReflectionMethod);
        e.printStackTrace();

        assertDoesNotThrow(outer::callInnerMethod);
    }
}
