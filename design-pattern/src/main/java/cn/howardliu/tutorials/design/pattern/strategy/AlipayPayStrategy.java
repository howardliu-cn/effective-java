package cn.howardliu.tutorials.design.pattern.strategy;

import java.math.BigDecimal;

/**
 * HowardLiu <howardliu1988@163.com>
 * Created on 2021/11/15 23:43
 */
public class AlipayPayStrategy implements PayStrategy {

    @Override
    public String payType() {
        return "ALIPAY";
    }

    @Override
    public void callPay(BigDecimal amount) {
        // 调用支付宝支付接口
        // 这里只是演示，即使都是支付宝支付，也会分不同的接口
        System.out.println("调用支付宝支付接口");
    }
}
