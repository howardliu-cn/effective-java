package cn.howardliu.tutorials;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-10-12
 */
public class GuavaBloomFilterTest {
    @SuppressWarnings("UnstableApiUsage")
@Test
public void testBloomFilter() {
    final List<String> itemsToInsert = Arrays.asList("apple", "banana", "cherry", "elderberry");

    // 创建布隆过滤器
    BloomFilter<String> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), 100, 0.01);
    // 当前元素数量为0
    Assertions.assertEquals(0, bloomFilter.approximateElementCount());

    // 向布隆过滤器中插入数据
    for (String item : itemsToInsert) {
        bloomFilter.put(item);
    }
    // 当前元素数量为4
    Assertions.assertEquals(4, bloomFilter.approximateElementCount());

    // 测试已插入的数据
    for (String item : itemsToInsert) {
        Assertions.assertTrue(bloomFilter.mightContain(item), "Item should be in the Bloom Filter: " + item);
    }

    // 测试未插入的数据
    final List<String> itemsNotInserted = Arrays.asList("grape", "orange", "peach", "quince", "raspberry");
    for (String item : itemsNotInserted) {
        Assertions.assertFalse(bloomFilter.mightContain(item), "Item should not be in the Bloom Filter: " + item);
    }
}
}
