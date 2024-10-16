package cn.howardliu.tutorials;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.config.Config;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-10-12
 */
public class RedissonBloomFilterTest {

@Test
public void testBloomFilter() {
    // 使用Docker本地启动一个Redis服务用来测试：
    //  docker run -d -p 6379:6379 --name redis-redisbloom redislabs/rebloom:latest

    Config config = new Config();
    config.useSingleServer().setAddress("redis://127.0.0.1:6379");

    // 生成key是 myBloomFilter 的存储
    // 会生成两个key，"myBloomFilter"、"{myBloomFilter}:config"
    // "myBloomFilter"是string类型，布隆过滤器的主存
    // "{myBloomFilter}:config"是hash结构，存储元信息，比如大小size、期望容量expectedInsertions、误报率falseProbability、使用的哈希函数数量hashIterations等。
    RBloomFilter<String> bloomFilter = Redisson.create(config)
            .getBloomFilter("myBloomFilter");

    // 初始化布隆过滤器，定义期望容量和误报率
    bloomFilter.tryInit(1000000, 0.01);

    // 准备一些测试数据
    final List<String> itemsToInsert = Arrays.asList("apple", "banana", "cherry", "elderberry");

    // 向布隆过滤器中插入数据
    for (String item : itemsToInsert) {
        bloomFilter.add(item);
    }

    // 测试已插入的数据
    for (String item : itemsToInsert) {
        Assertions.assertTrue(bloomFilter.contains(item), "Item should be in the Bloom Filter: " + item);
    }

    // 测试未插入的数据
    final List<String> itemsNotInserted = Arrays.asList("grape", "orange", "peach", "quince", "raspberry");
    for (String item : itemsNotInserted) {
        Assertions.assertFalse(bloomFilter.contains(item), "Item should not be in the Bloom Filter: " + item);
    }
}
}
