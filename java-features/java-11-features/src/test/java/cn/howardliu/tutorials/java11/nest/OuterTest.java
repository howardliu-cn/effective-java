package cn.howardliu.tutorials.java11.nest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

/**
 * @author 看山 <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2021/12/29 22:48
 */
class OuterTest {
    @Test
    void doesNotGotAnExceptionInJava11() {
        final Outer outer = new Outer();

        assertDoesNotThrow(outer::callInnerReflectionMethod);
        assertDoesNotThrow(outer::callInnerMethod);
    }
}
