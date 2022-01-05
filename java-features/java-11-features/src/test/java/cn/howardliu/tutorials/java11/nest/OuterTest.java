package cn.howardliu.tutorials.java11.nest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import cn.howardliu.tutorials.java11.nest.Outer.Inner;

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

    @Test
    void checkNestHostName() {
        final String outerNestHostName = Outer.class.getNestHost().getName();
        assertEquals("cn.howardliu.tutorials.java11.nest.Outer", outerNestHostName);

        final String innerNestHostName = Inner.class.getNestHost().getName();
        assertEquals("cn.howardliu.tutorials.java11.nest.Outer", innerNestHostName);

        assertEquals(outerNestHostName, innerNestHostName);

        final String notNestClass = NotNestClass.class.getNestHost().getName();
        assertEquals("cn.howardliu.tutorials.java11.nest.NotNestClass", notNestClass);
    }

    @Test
    void checkIsNestmateOf() {
        assertTrue(Inner.class.isNestmateOf(Outer.class));
        assertTrue(Outer.class.isNestmateOf(Inner.class));
    }

    @Test
    void getNestMembers() {
        final List<String> outerNestMembers = Arrays.stream(Outer.class.getNestMembers())
                .map(Class::getName)
                .collect(Collectors.toList());

        assertEquals(2, outerNestMembers.size());
        assertTrue(outerNestMembers.contains("cn.howardliu.tutorials.java11.nest.Outer"));
        assertTrue(outerNestMembers.contains("cn.howardliu.tutorials.java11.nest.Outer$Inner"));

        final List<String> innerNestMembers = Arrays.stream(Inner.class.getNestMembers())
                .map(Class::getName)
                .collect(Collectors.toList());

        assertEquals(2, innerNestMembers.size());
        assertTrue(innerNestMembers.contains("cn.howardliu.tutorials.java11.nest.Outer"));
        assertTrue(innerNestMembers.contains("cn.howardliu.tutorials.java11.nest.Outer$Inner"));
    }
}
