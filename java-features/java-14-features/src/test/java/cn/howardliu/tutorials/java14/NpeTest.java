package cn.howardliu.tutorials.java14;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import lombok.Data;

/**
 * @author 看山 <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2022/2/22 08:01
 */
class NpeTest {
    @Test
    void test1() {
        Student s = null;

        Assertions.assertThrows(NullPointerException.class, () -> s.getName())
                .fillInStackTrace()
                .printStackTrace();
    }

    @Test
    void test2() {
        Student s = new Student();
        s.setName("看山");

        Assertions.assertThrows(NullPointerException.class, () -> s.getClazz().getNo())
                .fillInStackTrace()
                .printStackTrace();
    }

    @Data
    public static class Student {
        private String name;
        private String age;
        private Clazz clazz;
    }

    @Data
    public static class Clazz {
        private String no;
    }
}
