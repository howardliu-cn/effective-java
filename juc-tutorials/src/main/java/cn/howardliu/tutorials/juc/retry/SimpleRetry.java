package cn.howardliu.tutorials.juc.retry;

import java.util.Random;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-12-05
 */
public class SimpleRetry {
    public static void main(String[] args) {
        final Random random = new Random();

        final int maxRetryCount = 10;
        int times = 0;
        while (true) {
            times++;
            if (times > maxRetryCount) {
                break;
            }
            try {
                // 业务逻辑
                System.out.println("最大重试" + maxRetryCount + "次，当前是第" + times + "次");
                if (random.nextInt(10) > 5) {
                    throw new RuntimeException("随机数失败");
                }

                if (random.nextInt(10) / 2 == 0) {
                    System.out.println("逻辑执行成功");
                    break;
                }
                Thread.sleep(1000);
                // 业务逻辑
            } catch (Exception e) {
                System.out.println("进入异常捕获");
            }
        }
        System.out.println("业务逻辑执行完毕");
    }
}
