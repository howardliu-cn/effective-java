package cn.howardliu.tutorials.design.pattern.strategy;

import java.math.BigDecimal;

/**
 * HowardLiu <howardliu1988@163.com>
 * Created on 2021/11/15 23:44
 */
public class WxpayPayStrategy implements PayStrategy {

    @Override
    public String payType() {
        return "WXPAY";
    }

    @Override
    public void callPay(BigDecimal amount) {
        // 微信支付接口
        // 这里只是演示，即使都是微信支付，也会分不同的接口
        System.out.println("调用微信支付接口");
    }
}
