package cn.howardliu.tutorials;

import static com.google.common.base.Charsets.UTF_8;

import java.util.Random;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-10-27
 */
@SuppressWarnings("UnstableApiUsage")
public class FuzzyFilter {
    static final int nums = 1_000_000_000;

    // 创建布隆过滤器
    static final BloomFilter<String> bloomFilter = BloomFilter.create(Funnels.stringFunnel(UTF_8), nums, 0.0001);

    public void add(String key) {
        bloomFilter.put(key);
    }

    public boolean contains(String key) {
        return bloomFilter.mightContain(key);
    }

    public static void main(String[] args) {
        final FuzzyFilter fuzzyFilter = new FuzzyFilter();
        final Random random = new Random(Integer.MIN_VALUE);
        for (int i = 0; i < nums; i++) {
            final String key = random.nextInt() + "";
            fuzzyFilter.add(key);
        }

        int count = 0;
        for (int i = 0; i < nums; i++) {
            final String key = random.nextInt() + "";
            if (fuzzyFilter.contains(key)) {
                count++;
            }
        }
        System.out.println("重复key数量为：" + count);
    }
}
