package cn.howardliu.tutorials.core.random;

import java.util.Random;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-11-14
 */
public class RandomWithSeed {
    public static void main(String[] args) {
        Random random = new Random(12345L); // 种子设置为12345
        // 生成7个随机整数
        for (int i = 0; i < 7; i++) {
            System.out.format("%d \t", random.nextInt(100)); // 0到99之间的随机整数
        }
        System.out.println();

        Random random2 = new Random(12345L); // 种子设置为12345
        for (int i = 0; i < 7; i++) {
            System.out.format("%d \t", random2.nextInt(100)); // 0到99之间的随机整数
        }
        System.out.println();

        Random random3 = new Random(12345L); // 种子设置为12345
        for (int i = 0; i < 7; i++) {
            System.out.format("%d \t", random3.nextInt(100)); // 0到99之间的随机整数
        }
    }
}
