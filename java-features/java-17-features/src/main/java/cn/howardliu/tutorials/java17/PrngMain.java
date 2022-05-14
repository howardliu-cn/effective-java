package cn.howardliu.tutorials.java17;

import java.util.Arrays;
import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;
import java.util.stream.IntStream;

/**
 * @author 看山 <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2022/3/30 22:27
 */
public class PrngMain {
    public static void main(String[] args) {
        printAllPrng();

        useRandom();

        got10RandomNums();
    }

    private static void printAllPrng() {
        RandomGeneratorFactory.all().forEach(factory -> {
            System.out.println(factory.group() + ":" + factory.name());
        });
    }

    private static void useRandom() {
        RandomGenerator randomGenerator = RandomGeneratorFactory.of("Random")
                .create(System.currentTimeMillis());
        System.out.println(randomGenerator.getClass());
        System.out.println(randomGenerator.nextInt(10));
    }

    private static void got10RandomNums() {
        final IntStream ints = RandomGeneratorFactory.of("L128X128MixRandom")
                .create()
                .ints(10, 0, 100);
        System.out.println(Arrays.toString(ints.toArray()));
    }
}
