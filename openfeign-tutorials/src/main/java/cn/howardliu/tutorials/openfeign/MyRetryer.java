package cn.howardliu.tutorials.openfeign;

import java.util.concurrent.TimeUnit;

import feign.RetryableException;
import feign.Retryer;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-10-08
 */
public class MyRetryer implements Retryer {
    int attempt = 0;

    @Override
    public void continueOrPropagate(RetryableException e) {
        if (attempt++ >= 3) {
            throw e;
        }
        System.out.println("重试第：" + attempt + "次");
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException ex) {
             Thread.currentThread().interrupt();
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Retryer clone() {
        return new MyRetryer();
    }
}
