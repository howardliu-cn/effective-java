package cn.howardliu.tutorials.java11;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author 看山 <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2022/1/8 10:26
 */
class CollectionTest {
    @Test
    void testArray() {
        final List<String> vars = Arrays.asList("1", "2", "3");
        final Object[] objArray = vars.toArray();
        final String[] strArray = vars.toArray(new String[0]);
        Assertions.assertTrue(Arrays.asList(strArray).contains("1"));
        Assertions.assertTrue(Arrays.asList(strArray).contains("2"));
        Assertions.assertTrue(Arrays.asList(strArray).contains("3"));

        final String[] strArray2 = vars.toArray(String[]::new);
        Assertions.assertTrue(Arrays.asList(strArray2).contains("1"));
        Assertions.assertTrue(Arrays.asList(strArray2).contains("2"));
        Assertions.assertTrue(Arrays.asList(strArray2).contains("3"));
    }
}
