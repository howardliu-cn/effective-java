package cn.howardliu.tutorials.java11;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author 看山 <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2022/1/8 11:10
 */
class PredicateTest {
    @Test
    void testNot() {
        final List<String> list = Arrays.asList("1", "a");
        final List<String> nums = list.stream()
                .filter(NumberUtils::isDigits)
                .collect(Collectors.toList());
        Assertions.assertEquals(1, nums.size());
        Assertions.assertTrue(nums.contains("1"));

        final List<String> notNums = list.stream()
                .filter(x -> !NumberUtils.isDigits(x))
                .collect(Collectors.toList());
        Assertions.assertEquals(1, notNums.size());
        Assertions.assertTrue(notNums.contains("a"));

        final List<String> notNums2 = list.stream()
                .filter(Predicate.not(NumberUtils::isDigits))
                .collect(Collectors.toList());
        Assertions.assertEquals(1, notNums2.size());
        Assertions.assertTrue(notNums2.contains("a"));
    }
}
