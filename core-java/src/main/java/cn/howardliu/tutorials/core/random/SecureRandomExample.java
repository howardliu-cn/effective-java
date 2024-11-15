package cn.howardliu.tutorials.core.random;

import java.security.SecureRandom;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-11-14
 */
public class SecureRandomExample {
    public static void main(String[] args) throws Exception {
        SecureRandom random = new SecureRandom(new byte[] {1, 2, 3, 4, 5});
        // 生成7个随机整数
        for (int i = 0; i < 7; i++) {
            System.out.format("%d \t", random.nextInt(100)); // 0到99之间的随机整数
        }
        System.out.println();

        SecureRandom random2 = new SecureRandom(new byte[] {1, 2, 3, 4, 5});
        // 生成7个随机整数
        for (int i = 0; i < 7; i++) {
            System.out.format("%d \t", random2.nextInt(100)); // 0到99之间的随机整数
        }
        System.out.println();

        SecureRandom random3 = new SecureRandom(new byte[] {1, 2, 3, 4, 5});
        // 生成7个随机整数
        for (int i = 0; i < 7; i++) {
            System.out.format("%d \t", random3.nextInt(100)); // 0到99之间的随机整数
        }
    }
}
