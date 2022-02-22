package cn.howardliu.tutorials.java14;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author 看山 <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2022/2/16 21:55
 */
class InstanceofTest {
    @Test
    void test() {
        final Object obj1 = "Hello, World!";
        int result = 0;
        if (obj1 instanceof String) {
            String str = (String) obj1;
            result = str.length();
        } else if (obj1 instanceof Number) {
            Number num = (Number) obj1;
            result = num.intValue();
        }

        Assertions.assertEquals(13, result);
    }

    @Test
    void test1() {
        final Object obj1 = "Hello, World!";
        int result = 0;
        if (obj1 instanceof String str) {
            result = str.length();
        } else if (obj1 instanceof Number num) {
            result = num.intValue();
        }

        Assertions.assertEquals(13, result);
    }

    @Test
    void test2() {
        final Object obj1 = "Hello, World!";
        int result = 0;
        if (obj1 instanceof String str && str.length() > 20) {
            result = str.length();
        } else if (obj1 instanceof Number num) {
            result = num.intValue();
        }

        Assertions.assertEquals(0, result);
    }
}
