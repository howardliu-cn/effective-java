package cn.howardliu.tutorials.design.pattern.strategy;

import java.math.BigDecimal;

/**
 * HowardLiu <howardliu1988@163.com>
 * Created on 2021/11/15 23:50
 */
public class Main {
    public static void main(String[] args) {
        final StrategyContext strategyContext = new StrategyContext();
        strategyContext.pay("ALIPAY", BigDecimal.TEN);
        strategyContext.pay("WXPAY", BigDecimal.ONE);
    }
}
