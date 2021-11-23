package cn.howardliu.tutorials.design.pattern.strategy;

import java.math.BigDecimal;

/**
 * HowardLiu <howardliu1988@163.com>
 * Created on 2021/11/15 23:41
 */
public interface PayStrategy {
    String payType();

    void callPay(BigDecimal amount);
}
